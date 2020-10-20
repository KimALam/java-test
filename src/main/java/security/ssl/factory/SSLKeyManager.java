package security.ssl.factory;

import javax.net.ssl.X509KeyManager;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Vector;

class SSLKeyManager implements X509KeyManager {
    protected String alias;
    protected KeyStore ks;
    protected char[] pw;

    private String keyType;
    private String issuer;

    SSLKeyManager(KeyStore ks, String alias, char[] pw) {
        this.ks = ks;
        this.alias = alias;
        this.pw = pw;

        try {
            Certificate c = ks.getCertificate(alias);
            this.keyType = c.getPublicKey().getAlgorithm();
            this.issuer = ((X509Certificate) c).getIssuerDN().getName();
        } catch (Exception e) {
            throw new IllegalArgumentException(alias + " has a bad key");
        }
    }

    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket sock) {
        boolean contain = Arrays.stream(keyTypes).anyMatch(keyType::equals);
        if (!contain) return null;
        if (issuers == null) return alias;

        for (int i = 0; i < issuers.length; i++) {
            if (issuer.equals(issuers[i].getName()))
                return alias;
        }
        return null;
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket sock) {
        return chooseClientAlias(new String[]{keyType}, issuers, sock);
    }

    // Get the certificates -- make sure each is an X509Certificate before copying it into the array.
    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        try {
            Certificate[] c1 = ks.getCertificateChain(alias);
            Vector<Certificate> c2 = new Vector<>(c1.length);
            for (Certificate c : c1) {
                c2.add(c);
            }
            return c2.toArray(new X509Certificate[0]);
        } catch (KeyStoreException e) {
            return null;
        }
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        String[] s;
        String alias = chooseClientAlias(new String[]{keyType}, issuers, null);
        if (alias == null) {
            s = new String[0];
        } else {
            s = new String[1];
            s[0] = alias;
        }
        return s;
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return getClientAliases(keyType, issuers);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        try {
            return (PrivateKey) ks.getKey(alias, pw);
        } catch (Exception e) {
            return null;
        }
    }
}

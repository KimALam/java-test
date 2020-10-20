package security.ssl.factory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public class SSLKeyManagerFactory extends KeyManagerFactorySpi {
    char[] pw;
    KeyStore ks;
    String alias;

    public SSLKeyManagerFactory() {
        this.alias = System.getProperty("xyz.aliasName");
        if (alias == null)
            throw new IllegalArgumentException("Must specify alias property");
    }

    @Override
    protected KeyManager[] engineGetKeyManagers() {
        SSLKeyManager[] km = new SSLKeyManager[1];
        km[0] = new SSLKeyManager(ks, alias, pw);
        return km;
    }

    @Override
    protected void engineInit(KeyStore ks, char[] pw) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        this.ks = ks;
        this.pw = new char[pw.length];
        System.arraycopy(pw, 0, this.pw, 0, pw.length);
    }

    @Override
    protected void engineInit(ManagerFactoryParameters mfp) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("Not supported");
    }
}

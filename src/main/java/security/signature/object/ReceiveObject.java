package security.signature.object;

import security.keystore.KeyStoreHandler;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

class ReceiveObject {
    private static void verifySigner(Certificate c, String name) throws CertificateException {
        Certificate issuerCert = null;
        X509Certificate sCert = null;
        KeyStore ks = null;

        try {
            KeyStoreHandler ksh = new KeyStoreHandler(null);
            ks = ksh.getKeyStore();
        } catch (Exception e) {
            throw new CertificateException("Invalid keystore");
        }

        try {
            String signer = ks.getCertificateAlias(c);
            if (signer != null) {
                System.out.println("We know the signer as " + signer);
                return;
            }

            for (Enumeration alias = ks.aliases(); alias.hasMoreElements();) {
                String s = (String) alias.nextElement();
                try {
                    sCert = (X509Certificate) ks.getCertificate(s);
                } catch (Exception e) {
                    continue;
                }
                if (name.equals(sCert.getSubjectDN().getName())) {
                    issuerCert = sCert;
                    break;
                }
            }
        } catch (KeyStoreException e) {
            throw new CertificateException("Invalid keystore");
        }

        if (issuerCert == null) {
            throw new CertificateException("No such certificate");
        }

        try {
            c.verify(issuerCert.getPublicKey());
        } catch (Exception e) {
            throw new CertificateException(e.toString());
        }
    }

    private static void processCertificate(X509Certificate x509) throws CertificateParsingException {
        Principal p = x509.getSubjectDN();
        System.out.println("This certificate was provided by " + p.getName());

        p = x509.getIssuerDN();
        System.out.println("This certificate was provided by " + p.getName());

        try {
            verifySigner(x509, p.getName());
        } catch (CertificateException e) {
            System.out.println("We don't know the certificate signer");
        }

        try {
            x509.checkValidity();
        } catch (CertificateExpiredException e) {
            System.out.println("That certificate is no longer valid");
        } catch (CertificateNotYetValidException e) {
            System.out.println("That certificate is not yet valid");
        }
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(SendObject.FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object o = ois.readObject();
            if (o instanceof Message) {
                Message m = (Message) o;
                System.out.println("Received message");
                processCertificate((X509Certificate) m.certificate);
                PublicKey pk = m.certificate.getPublicKey();
                if (m.object.verify(pk, Signature.getInstance("MD5withRSA"))) {
                    System.out.println("Message is valid");
                    System.out.println(m.object.getObject());
                } else {
                    System.out.println("Signature is invalid");
                }
            } else {
                System.out.println("Message is corrupted");
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

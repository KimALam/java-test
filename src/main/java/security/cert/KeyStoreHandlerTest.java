package security.cert;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

// .keystore 파일 생성
// $> keytool -genkey -alias sdo -keyalg RSA -keystore ./.keystore

@Slf4j
class KeyStoreHandlerTest {

    public static void main(String[] args) {
        try {
            final String alias = "sdo";//args[0];
            final String pw = "1234qwer";//args[1];

            KeyStore ks = new KeyStoreHandler(null).getKeyStore();

            if (ks.isKeyEntry(alias)) {
                log.info("{} is a key entry in the keystore", alias);

                char[] c = new char[pw.length()];
                pw.getChars(0, c.length, c, 0);

                log.info("The private key for {} is {}", alias, ks.getKey(alias, c));

                Certificate[] certs = ks.getCertificateChain(alias);
                if (certs[0] instanceof X509Certificate) {
                    X509Certificate x509 = (X509Certificate) certs[0];
                    log.info("{} is really {}", alias, x509.getSubjectDN());
                }

                if (certs[certs.length - 1] instanceof X509Certificate) {
                    X509Certificate x509 = (X509Certificate) certs[certs.length - 1];
                    log.info("{} was verified by {}", alias, x509.getIssuerDN());
                }
            } else if (ks.isCertificateEntry(alias)) {
                log.info("{} is a certificate entry in the keystore", alias);

                Certificate c = ks.getCertificate(alias);
                if (c instanceof X509Certificate) {
                    X509Certificate x509 = (X509Certificate) c;
                    log.info("{} is really {}", alias, x509.getSubjectDN());
                    log.info("{} was verified by {}", alias, x509.getIssuerDN());
                }
            } else {
                log.info("{} is unknown to this keystore", alias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

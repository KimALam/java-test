package security.signature;

import lombok.extern.slf4j.Slf4j;
import security.cert.KeyStoreHandler;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;

@Slf4j
class Receive {
    private static final String ALIAS = "sdo";
    private static final String PW = "1234qwer";
    private static final String FILE_NAME = "sig_test";

    public static void main(String[] args) {
        try {
            String data = null;
            byte[] signature = null;

            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // read plain data
            Object o = ois.readObject();
            try {
                data = (String) o;
            } catch (ClassCastException cce) {
                log.error("Unexpected data in file");
                System.exit(-1);
            }

            // read signature
            o = ois.readObject();
            try {
                signature = (byte[]) o;
            } catch (ClassCastException cce) {
                log.error("Unexpected data in file");
                System.exit(-1);
            }

            log.info("Received message :{}", data);

            KeyStoreHandler ksh = new KeyStoreHandler(PW.toCharArray());
            KeyStore ks = ksh.getKeyStore();

            // certificate has public key + signature + info(ex. DN)
            Certificate cert = ks.getCertificate(ALIAS);
            PublicKey pk = cert.getPublicKey();
            Signature s = Signature.getInstance("MD5withRSA");
            s.initVerify(pk);
            s.update(data.getBytes());
            if (s.verify(signature)) {
                log.info("Message is valid");
            } else {
                log.info("Message was corrupted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

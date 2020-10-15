package security.signature.object;

import security.keystore.KeyStoreHandler;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignedObject;
import java.security.cert.Certificate;

class SendObject {
    static final String ALIAS = "sdo";
    static final String PW = "1234qwer";
    static final String FILE_NAME = "test.obj";
    static final String data = "This have I thought good to deliver thee, " +
            "that thou mightst not lose the dues of rejoicing " +
            "by being ignorant of what greatness is promised thee.";

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            KeyStoreHandler ksh = new KeyStoreHandler(null);
            KeyStore ks = ksh.getKeyStore();

            Certificate[] certs = ks.getCertificateChain(ALIAS);
            PrivateKey pk = (PrivateKey) ks.getKey(ALIAS, PW.toCharArray());

            Message m = new Message();
            m.object = new SignedObject(data, pk, Signature.getInstance("MD5withRSA"));
            m.certificate = certs[0];

            oos.writeObject(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

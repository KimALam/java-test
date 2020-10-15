package security.signature;

import security.keystore.KeyStoreHandler;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;

class Send {
    private static final String ALIAS = "sdo";
    private static final String PW = "1234qwer";
    private static final String FILE_NAME = "sig_test";
    private static final String data = "This have I thought good to deliver thee, " +
            "that thou mightst not lose the dues of rejoicing " +
            "by being ignorant of what greatness is promised thee.";

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            KeyStoreHandler ksh = new KeyStoreHandler(null);
            KeyStore ks = ksh.getKeyStore();
            PrivateKey pk = (PrivateKey) ks.getKey(ALIAS, PW.toCharArray());

            Signature s = Signature.getInstance("MD5withRSA");
            s.initSign(pk);

            byte[] buf = data.getBytes();
            s.update(buf);

            oos.writeObject(data);
            oos.writeObject(s.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

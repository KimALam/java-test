package security.signature.basic;

import security.keystore.KeyStoreHandler;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;

import static security.signature.basic.Send.ALIAS;
import static security.signature.basic.Send.FILE_NAME;
import static security.signature.basic.Send.PW;

class Receive {
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
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }

            // read signature
            o = ois.readObject();
            try {
                signature = (byte[]) o;
            } catch (ClassCastException cce) {
                System.out.println("Unexpected data in file");
                System.exit(-1);
            }

            System.out.println("Received message : " + data);

            KeyStoreHandler ksh = new KeyStoreHandler(PW.toCharArray());
            KeyStore ks = ksh.getKeyStore();

            // certificate has public key + signature + info(ex. DN)
            Certificate cert = ks.getCertificate(ALIAS);
            PublicKey pk = cert.getPublicKey();
            Signature s = Signature.getInstance("MD5withRSA");
            s.initVerify(pk);
            s.update(data.getBytes());
            if (s.verify(signature)) {
                System.out.println("Message is valid");
            } else {
                System.out.println("Message was corrupted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

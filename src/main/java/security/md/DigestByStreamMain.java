package security.md;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class DigestByStreamMain {
    private static final String SHA = "SHA";
    private static final String FILE_NAME = "md_test";

    public static void main(String[] args) {
        DigestByStreamMain test = new DigestByStreamMain();

        test.digestTo();
        test.digestFrom();
    }

    private void digestTo() {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA);

            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            DigestOutputStream dos = new DigestOutputStream(fos, md);
            ObjectOutputStream oos = new ObjectOutputStream(dos);

            String data = "I am a super man and you are super woman.";

            oos.writeObject(data);
            dos.on(false);
            oos.writeObject(md.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    private void digestFrom() {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA);

            FileInputStream fis = new FileInputStream(FILE_NAME);
            DigestInputStream dis = new DigestInputStream(fis, md);
            ObjectInputStream ois = new ObjectInputStream(dis);

            Object o = ois.readObject();
            if (!(o instanceof String)) {
                System.out.println("Unexpected data in file #1");
                System.exit(-1);
            }

            String data = (String) o;
            System.out.println("Got message: " + data);

            dis.on(false);

            o = ois.readObject();
            if (!(o instanceof byte[])) {
                System.out.println("Unexpected data in file #2");
                System.exit(-1);
            }

            byte[] origDigest = (byte[]) o;
            if (MessageDigest.isEqual(md.digest(), origDigest)) {
                System.out.println("Message is valid");
            } else {
                System.out.println("Message was corrupted");
            }

        } catch (NoSuchAlgorithmException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

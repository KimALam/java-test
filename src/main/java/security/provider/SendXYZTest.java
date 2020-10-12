package security.provider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

class SendXYZTest {
    private static final String FILE_NAME = "test.xyz";

    public static void main(String[] args) {
        try {
            Security.addProvider(new XYZProvider());
            MessageDigest md = MessageDigest.getInstance("XYZ");

            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            String data = "I am hero and will save us.";

            byte[] buf = data.getBytes();
            md.update(buf);
            oos.writeObject(data);
            oos.writeObject(md.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}

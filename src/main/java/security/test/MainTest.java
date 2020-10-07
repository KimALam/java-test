package security.test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class MainTest {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, InvalidAlgorithmParameterException, SignatureException {
        String encryptedString = Base64.getEncoder().encodeToString(
                RSAUtil.encrypt("Dhiraj is the author", RSAUtil.publicKey));
        System.out.println("en: " + encryptedString);

        String decryptedString = RSAUtil.decrypt(encryptedString, RSAUtil.privateKey);
        System.out.println("dc" + decryptedString);
    }
}

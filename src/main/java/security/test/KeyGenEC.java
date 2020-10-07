package security.test;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class KeyGenEC {
    public void generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        System.out.println(null instanceof Key);
//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//        keyGen.initialize(2048);
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256);
//        keyGen.initialize(new ECGenParameterSpec("secp521r1"));

        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("public: " + publicKey);
        System.out.println("public#: " + byteArrayToHex(publicKey.getEncoded()));
        System.out.println("public##: " + publicKey.getEncoded().length);
        System.out.println("public###: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        System.out.println("private: " + privateKey);
        System.out.println("private#: " + byteArrayToHex(privateKey.getEncoded()));
        System.out.println("private##: " + privateKey.getEncoded().length);
        System.out.println("private###: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        System.out.println("public key algorithm: " + publicKey.getAlgorithm());
        System.out.println("private key algorithm: " + privateKey.getAlgorithm());

        System.out.println("public key format: " + publicKey.getFormat());
        System.out.println("private key formate: " + privateKey.getFormat());

        byte[] challenge = new byte[10000];
        ThreadLocalRandom.current().nextBytes(challenge);

        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initSign(privateKey);
        sig.update(challenge);
        byte[] signature = sig.sign();
        System.out.println("signed: " + Base64.getEncoder().encodeToString(signature));

        // verify with public key
        sig.initVerify(publicKey);
        sig.update(challenge);
        boolean keyPairMatches = sig.verify(signature);
        System.out.println("matched? " + keyPairMatches);
    }

    public String stringToHex(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(String.format("%02X", (int) s.charAt(i)));
        }
        return sb.toString();
    }

    public String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for (final byte b : a) {
            sb.append(String.format("%02X", b & 0xff));
        }
        return sb.toString();
    }
}

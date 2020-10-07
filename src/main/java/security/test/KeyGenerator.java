package security.test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class KeyGenerator {
    public static void exampleKeyPair() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("public64: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("private64: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        byte[] challenge = new byte[10000];
        ThreadLocalRandom.current().nextBytes(challenge);

        // sign with private key
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(challenge);
        byte[] signature = sig.sign();
        System.out.println("signed: " + Base64.getEncoder().encodeToString(signature));

        // verify with public key
        sig.initVerify(publicKey);
        sig.update(challenge);
        boolean keyPairMatches = sig.verify(signature);
        System.out.println("matched? " + keyPairMatches);

        /*
        // byte [] encodedKey contains the RSA private key retrieved from the DB
        KeyFactory rsaKeyFac = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(encodedKey);
        RSAPrivateKey privKey = (RSAPrivateKey)rsaKeyFac.generatePrivate(encodedKeySpec);

        // for public key
        KeyFactory rsaKeyFac = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encKey);
        RSAPublicKey pubKey = (RSAPublicKey )rsaKeyFac .generatePublic(keySpec);
         */

    }
}

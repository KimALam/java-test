package security.test;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

class ECKeyPairTest {
    public void generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp256r1"));

        KeyPair keyPair = keyGen.generateKeyPair();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        ECPoint w = publicKey.getW();
        String x_str = w.getAffineX().toString(16);
        byte[] x_byte = w.getAffineX().toByteArray();

        String y_str = w.getAffineY().toString(16);
        byte[] y_byte = w.getAffineY().toByteArray();

        System.out.println("X str: " + x_str);
        System.out.println("X byt: " + byteArrayToHex(x_byte));
        System.out.println("Y str: " + y_str);
        System.out.println("Y byt: " + byteArrayToHex(y_byte));
    }

    public String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for (final byte b : a) {
            sb.append(String.format("%02X", b & 0xff));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        ECKeyPairTest ect = new ECKeyPairTest();
        ect.generateKeyPair();
    }
}

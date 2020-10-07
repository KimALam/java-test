package security.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Sha256Test {
    private final static String txt = "YtnAfwLM+x6p4GyJzLz6wLlGzpN4WJ3oMOStHLBf8tw=15224746010001901165801000012812";

    public static void testShar256() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(txt.getBytes("UTF-8"));

        System.out.println("byte: " + byteArrayToHex(hash));
        System.out.println("b64 : " + Base64.getEncoder().encodeToString(hash));
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for (final byte b : a) {
            sb.append(String.format("%02X", b & 0xff));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        testShar256();
    }
}

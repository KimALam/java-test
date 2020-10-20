package security.provider;

import java.security.Provider;

// ch. 8
public class XYZProvider extends Provider {
    public XYZProvider() {
        super("XYZ", 1.0, "XYZ Security Provider v1.0");
        // These are examples we'll demonstrate throughout the next chapters.
        put("KeyGenerator.XOR", "security.provider.alg.keygen.XORKeyGenerator");
        put("KeyPairGenerator.XYZ", "security.provider.alg.keypairgen.XYZKeyPairGenerator");
        put("MessageDigest.XYZ", "security.provider.alg.md.XYZMessageDigest");
        put("KeyManagerFactory.XYZ", "security.ssl.factory.SSLKeyManagerFactory");
        // TODO
        put("KeyFactory.XYZ", "security.provider.alg.keyfactory.XYZKeyFactory");
        put("Signature.XYZwithSHA", "security.provider.alg.sig.XYZSignature");
        put("Cipher.XOR", "security.provider.alg.ciph.XORCipher");


        // Now include any aliases
        put("Alg.Alias.MessageDigest.SHA-1", "SHA");
    }

    public static final synchronized void verifyForJCE() {
        // See Appendix E for more details
        throw new SecurityException("Can't verify for JCE");
    }
}

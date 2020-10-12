package security.provider.alg.keygen;

import security.provider.XYZProvider;

import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

// ch. 9
public class XORKeyGenerator extends KeyGeneratorSpi {
    private SecureRandom sr;

    public XORKeyGenerator() {
        XYZProvider.verifyForJCE();
    }

    @Override
    public void engineInit(SecureRandom sr) {
        this.sr = sr;
    }

    @Override
    public void engineInit(int len, SecureRandom sr) {
        if (len != 32) {
            throw new IllegalArgumentException("Only support 32 bit keys");
        }
        this.sr = sr;
    }

    @Override
    public void engineInit(AlgorithmParameterSpec aps, SecureRandom sr) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("Not supported");
    }

    @Override
    public SecretKey engineGenerateKey() {
        if (sr == null) {
            sr = new SecureRandom();
        }
        byte[] b = new byte[1];
        sr.nextBytes(b);
        return new XORKey(b[0]);
    }
}

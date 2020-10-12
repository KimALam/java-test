package security.provider.alg.keypairgen;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class XYZKeyPairGenerator extends KeyPairGenerator {
    private SecureRandom random;

    public XYZKeyPairGenerator() {
        super("XYZ");
    }

    @Override
    public void initialize(int strength, SecureRandom sr) {
        random = sr;
    }

    @Override
    public KeyPair generateKeyPair() {
        int rotValue = random.nextInt() % 25;
        XYZKey pub = new XYZKey();
        XYZKey priv = new XYZKey();

        pub.rotValue = rotValue;
        priv.rotValue = -rotValue;

        KeyPair kp = new KeyPair(pub, priv);
        return kp;
    }
}

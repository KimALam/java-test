package security.provider.alg.keypairgen;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public class XYZKey implements Key, PublicKey, PrivateKey {
    int rotValue;

    @Override
    public String getAlgorithm() {
        return "XYZ";
    }

    @Override
    public String getFormat() {
        return "XYZ Special Format";
    }

    @Override
    public byte[] getEncoded() {
        byte[] b = new byte[4];
        b[3] = (byte) ((rotValue >> 24) & 0xff);
        b[2] = (byte) ((rotValue >> 16) & 0xff);
        b[1] = (byte) ((rotValue >> 8) & 0xff);
        b[0] = (byte) ((rotValue >> 0) & 0xff);
        return b;
    }
}

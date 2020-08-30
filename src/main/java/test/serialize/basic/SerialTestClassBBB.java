package test.serialize.basic;

import java.io.Serializable;

public class SerialTestClassBBB implements Serializable {
    private static final long serialVersionUID = 1L;
//    int error = 111; //
    int field1;

    public SerialTestClassBBB(int field1) {
        this.field1 = field1;
    }

    @Override
    public String toString() {
        return "SerialTestClassBBB{" +
//                "error=" + error +
                "field1=" + field1 +
                '}';
    }
}

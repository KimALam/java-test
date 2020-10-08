package test.serialize.basic;

import java.io.Serializable;

class SerialTestClassCCC implements Serializable {
    int field1;
    SerialTestClassBBB field2;
    static int field3;
    transient int field4;

    public SerialTestClassCCC(int field1, SerialTestClassBBB field2, int field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field4 = field4;
    }

    @Override
    public String toString() {
        return "SerialTestClassCCC{" +
                "field1=" + field1 +
                ", field2=" + field2 +
                ", field3=" + field3 +
                ", field4=" + field4 +
                '}';
    }
}

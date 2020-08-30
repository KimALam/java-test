package test.serialize.basic;

import java.io.Serializable;

public class SerialTestClassAAA implements Serializable {
    public int field1;
    protected int field2;
    int field3;
    private int field4;
    public static int field5; // exclude static
    transient int field6;     // exclude transient

    public SerialTestClassAAA(int field1, int field2, int field3, int field4, int field6) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field6 = field6;
    }

    @Override
    public String toString() {
        return "SerialTestClassAAA{" +
                "field1=" + field1 +
                ", field2=" + field2 +
                ", field3=" + field3 +
                ", field4=" + field4 +
                ", field5=" + field5 +
                ", field6=" + field6 +
                '}';
    }
}

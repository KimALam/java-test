package test.serialize.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SerialWriteTestMain {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./serial_test1.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // TEST1
//        SerialTestClassAAA aaa = new SerialTestClassAAA(1, 2, 3, 4, 6);
//        SerialTestClassAAA.field5 = 5;
//        System.out.println("TEST1 writeObject: " + aaa);

        // TEST2
        SerialTestClassCCC ccc = new SerialTestClassCCC(1, new SerialTestClassBBB(100), 4);
        SerialTestClassCCC.field3 = 3;
        System.out.println("TEST2 writeObject" + ccc);


        objectOutputStream.writeObject(ccc);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
}

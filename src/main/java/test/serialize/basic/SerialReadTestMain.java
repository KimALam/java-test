package test.serialize.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerialReadTestMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./serial_test1.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // TEST1
//        SerialTestClassAAA aaa = (SerialTestClassAAA) objectInputStream.readObject();
//        System.out.println("TEST1 readObject: " + aaa);

        // TEST2
        SerialTestClassCCC ccc = (SerialTestClassCCC) objectInputStream.readObject();
        System.out.println("TEST2 readObject: " + ccc);

        objectInputStream.close();
        fileInputStream.close();
    }
}

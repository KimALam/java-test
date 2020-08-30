package test.serialize.inheritance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class InherentTestMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("./serial_inherent.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        Child child = new Child();
        child.field1 = "티스토리";
        child.field2 = "블로거";

        objectOutputStream.writeObject(child);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("./serial_inherent.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Child c = (Child) objectInputStream.readObject();
        System.out.println(c);
        objectInputStream.close();
        fileInputStream.close();
    }
}

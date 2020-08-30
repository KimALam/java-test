package test.serialize.readresolve;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ReadResolveTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("./serial_readresolve.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        SingletonClass singletonClass = SingletonClass.getInstance();
        System.out.println(singletonClass);

        objectOutputStream.writeObject(singletonClass);
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("./serial_readresolve.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        SingletonClass sc = (SingletonClass) objectInputStream.readObject();
        System.out.println(sc);

        objectInputStream.close();
        fileInputStream.close();
    }
}

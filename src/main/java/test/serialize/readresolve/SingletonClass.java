package test.serialize.readresolve;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

class SingletonClass implements Serializable {
    private static final SingletonClass INSTANCE = new SingletonClass();

    private SingletonClass() {}

    public static SingletonClass getInstance() {
        return INSTANCE;
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("readObject...");
    }

    private Object readResolve() throws ObjectStreamException {
        System.out.println("readResolve...");
        // Replace an object by readResolve() after readObject().
        return INSTANCE;
    }
}

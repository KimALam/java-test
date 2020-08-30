package test.serialize.inheritance;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 부모에 serializable 빠져 있으면 부모 제외된다.
 * 그러나, 부모가 serializable 이면 자식은 serializable 없어도 자동으로 serializable 이다.
 */
class Child extends Parent implements Serializable {
    String field2;

    /**
     * 부모에 serializable 이 없으때 강제적으로 하는 방법.
     *
     * Method signature
     * 1) private void writeObject(ObjectOutputStream)
     * 2) private void readObject(ObjectInputStream)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(field1);
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        field1 = in.readUTF();
        in.defaultReadObject();
    }

    @Override
    public String toString() {
        return "Child{" +
                "field2='" + field2 + '\'' +
                ", field1='" + field1 + '\'' +
                '}';
    }
}

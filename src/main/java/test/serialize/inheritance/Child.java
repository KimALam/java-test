package test.serialize.inheritance;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * case 1) parent - serializable / child - no serializable
 *         child는 부모 따라서 serialize 가능 하다.
 * case 2) parent - no serializable / child - serializable
 *        child를 serialize 시에 부모의 필드는 제외 된다.
 *        또한 부모는 반드시 default(no-arg) constructor를 가져야 한다.
 *
 *  결론: 상속 관계에 있을 때 implements serializable 가 명시된 클래스와 그 subclass 들만 serialize 된다. 부모는 안됨.
 *
 *  ref: https://www.geeksforgeeks.org/object-serialization-inheritance-java/
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

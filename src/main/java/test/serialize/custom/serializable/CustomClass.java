package test.serialize.custom.serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Slf4j
@Getter
@Setter
public class CustomClass implements Serializable {
    private static final long serialVersionUID = 483328785962780170L;

    private static String address = "Seoul";
    private transient String firstName;
    private String lastName;
    private int age;

    @Builder
    public CustomClass(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // customizing serialized data
    private void writeObject(ObjectOutputStream oos) throws IOException {
        log.info("@@@ writeObject");

        // default serializable action
        oos.defaultWriteObject();

        // customizing for transient
        oos.writeUTF("abcde");
    }

    // customizing deserialized data
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        log.info("### readObject");

        // default deserializable action
        ois.defaultReadObject();

        // customizing for transient
        String fn = ois.readUTF();
        log.info("deserialized firstName : {}", fn);
        firstName = fn;
    }

    @Override
    public String toString() {
        return "CustomClass{" +
                "address='" + address + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

package test.serialize.custom.externalizable;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class ExternalizableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee ee = new Employee("kim", "alam", 24, "Admin");

        log.info("origin : {}", ee);

        try (FileOutputStream fos = new FileOutputStream("data.obj");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(ee);
        }

        try (FileInputStream fis = new FileInputStream("data.obj");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Employee d_ee = (Employee) ois.readObject();

            log.info("deserialized : {}", d_ee);
        }
    }
}

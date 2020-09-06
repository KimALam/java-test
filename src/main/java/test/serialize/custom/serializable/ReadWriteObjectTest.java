package test.serialize.custom.serializable;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class ReadWriteObjectTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CustomClass cc = new CustomClass("kim", "alam", 21);
        log.info("original : {}", cc);

        // serialize
        try (FileOutputStream fos = new FileOutputStream("data.obj");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cc);
        }

        try (FileInputStream fis = new FileInputStream("data.obj");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            CustomClass d_cc = (CustomClass) ois.readObject();
            log.info("deserialized : {}", d_cc);
        }
    }
}

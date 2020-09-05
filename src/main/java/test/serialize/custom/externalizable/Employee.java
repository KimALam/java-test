package test.serialize.custom.externalizable;

import lombok.extern.slf4j.Slf4j;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Slf4j
public class Employee implements Externalizable {
    private static final long serialVersionUID = 5256159422055963844L;

    private String firstName;
    private transient String lastName;
    private int age;
    private static String department;

    public Employee() {
        log.info("default constructor");
    }

    public Employee(String firstName, String lastName, int age, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        Employee.department = department;

        log.info("constructor : firstName={}, lastName={}, age={}, department={}", firstName, lastName, age, department);
    }

    // writeExternal / readExternal이 있으면 default serializable 메카니즘은 무시 된다.
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        log.info("@@@ writeExternal()");

        out.writeUTF(firstName);
        out.writeUTF(lastName);
        out.writeInt(age);
        out.writeUTF(department);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        log.info("@@@ readExternal()");

        String fn, ln, dpt;
        int a;

        // writeExternal에서 쓴 순서대로
        fn = in.readUTF();
        ln = in.readUTF();
        a = in.readInt();
        dpt = in.readUTF();

        log.info("firstName={}, lastName={}, age={}, department={}", fn, ln, a, dpt);

        firstName = fn;
        lastName = ln;
        age = a;
        Employee.department = dpt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", department=" + Employee.department +
                '}';
    }
}

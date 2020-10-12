package sample.deepcopy.by_serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

class Employee implements Serializable {
    private static final long serialVersionUID = -8704694695887038734L;

    private String name;
    private LocalDate doj;
    private List<String> skills;

    public Employee(String name, LocalDate doj, List<String> skills) {
        this.name = name;
        this.doj = doj;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public List<String> getSkills() {
        return skills;
    }

    // Method to deep clone a object using in memory serialization
    public Employee deepClone() throws IOException, ClassNotFoundException {
        // First serializing the object and its state to memory using ByteArrayOutputStream instead of FileOutputStream.
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);

        // And then deserializing it from memory using ByteArrayOutputStream instead of FileInputStream.
        // Deserialization process will create a new object with the same state as in the serialized object,
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        return (Employee) in.readObject();


/* Reference to Java.security.SignedObject constructor

public SignedObject(Serializable object, PrivateKey signingKey, Signature signingEngine) throws IOException, InvalidKeyException, SignatureException {
    // creating a stream pipe-line, from a to b
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    ObjectOutput a = new ObjectOutputStream(b);

    // write and flush the object content to byte array
    a.writeObject(object);
    a.flush();
    a.close();
    this.content = b.toByteArray();
    b.close();

    // now sign the encapsulated object
    this.sign(signingKey, signingEngine);
}
*/
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", doj=" + doj +
                ", skills=" + skills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(doj, employee.doj) &&
                Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, doj, skills);
    }
}

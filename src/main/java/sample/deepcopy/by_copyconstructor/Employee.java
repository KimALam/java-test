package sample.deepcopy.by_copyconstructor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
class Employee {
    private int id;
    private String name;
    private String depart;

    public Employee() {}

    public Employee(int id, String name, String depart) {
        this.id = id;
        this.name = name;
        this.depart = depart;
    }

    /** copy constructor : 자신의 type 을 변수로 받는 constructor */
    public Employee(Employee origin) {
        this.id = origin.id;
        this.name = origin.name;
        this.depart = origin.depart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name) &&
                Objects.equals(depart, employee.depart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, depart);
    }
}

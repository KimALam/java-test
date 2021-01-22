package test.stream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

class ToMapTest {
    public static void main(String[] args) {
        List<Employee> dupList = new ArrayList() {{
            add(new Employee("aaa", 20, "hr"));
            add(new Employee("bbb", 24, "dev"));
            add(new Employee("aaa", 20, "hr"));
        }};

        Map<String, Employee> map = dupList.stream()
                .collect(toMap(Employee::getDepart,
                        Function.identity(),
                        (o, n) -> {
                            System.out.println("old : " + o);
                            System.out.println("new : " + n);
                            return n;
                        }));

        System.out.println("map : " + map);
    }

    @Getter
    @Setter
    @ToString
    private static class Employee {
        String name;
        int age;
        String depart;

        public Employee(String name, int age, String depart) {
            this.name = name;
            this.age = age;
            this.depart = depart;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return age == employee.age && name.equals(employee.name) && depart.equals(employee.depart);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, depart);
        }
    }
}

package test.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
class CopyTest {
    public static void main(String[] args) {
//        List<Employee> origin = Arrays.asList(
//                new Employee(1, "john", "hr"),
//                new Employee(2, "jane", "dev"),
//                new Employee(3, "peter", "design"));
        List<Integer> origin = Arrays.asList(1, 2, 3, 4);

//        List<Employee> unmod = Collections.unmodifiableList(origin);
//        List<Employee> unmod = List.copyOf(origin);
        List<Integer> unmod = List.copyOf(origin);
        log.info("origin : {}", origin);
        log.info("unmod : {}", unmod);
        log.info("---------------------------------------------");

//        origin.get(0).setNum(10);
        origin.set(0, 10);
        log.info("origin : {}", origin);
        log.info("unmod : {}", unmod);
        log.info("---------------------------------------------");

//        unmod.get(1).setNum(20);
        unmod.set(1, 20);
        log.info("origin : {}", origin);
        log.info("unmod : {}", unmod);
        log.info("---------------------------------------------");

//        unmod.add(new Employee(4, "yarn", "md"));
//        log.info("origin : {}", origin);
//        log.info("unmod : {}", unmod);
    }

    @Getter
    @Setter
    @ToString
    private static class Employee {
        private int num;
        private String name;
        private String depart;

        public Employee(int num, String name, String depart) {
            this.num = num;
            this.name = name;
            this.depart = depart;
        }
    }
}

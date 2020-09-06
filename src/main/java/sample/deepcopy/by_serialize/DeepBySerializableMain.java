package sample.deepcopy.by_serialize;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * deep copy 전통적인 방법 3가지
 *   1. cloneable
 *   2. copy constructor
 *   3. serializable
 */
@Slf4j
/** 3번 serializable을 이용한 deep clone */
class DeepBySerializableMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee emp = new Employee("chulsu kim", LocalDate.now(), Arrays.asList("Java", "Kotlin", "Spring"));
        log.info("Employee : {}", emp);

        Employee cloneEmp = emp.deepClone();
        log.info("Cloned employee : {}", cloneEmp);

        log.info("emp == cloneEmp : {}", (emp == cloneEmp));
        log.info("emp.getDoj() == cloneEmp.getDoj() : {}", (emp.getDoj() == cloneEmp.getDoj()));
        log.info("emp.getSkills() == cloneEmp.getSkills() : {}", (emp.getSkills() == cloneEmp.getSkills()));

        log.info("Objects.equals(emp, cloneEmp()) : {}", Objects.equals(emp, cloneEmp));
        log.info("Objects.equals(emp.getDoj(), cloneEmp.getDoj()) : {}", Objects.equals(emp.getDoj(), cloneEmp.getDoj()));
        log.info("Objects.equals(emp.getSkills(), cloneEmp.getSkills()) : {}", Objects.equals(emp.getSkills(), cloneEmp.getSkills()));
    }
}

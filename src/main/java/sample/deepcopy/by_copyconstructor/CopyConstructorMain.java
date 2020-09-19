package sample.deepcopy.by_copyconstructor;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * deep copy 전통적인 방법 3가지
 *   1. cloneable
 *   2. copy constructor
 *   3. serializable
 */
@Slf4j
/** 3번 serializable을 이용한 deep clone */
class CopyConstructorMain {
    public static void main(String[] args) {
        Employee emp = new Employee(1, "chulsu", "HR");
        log.info("Employee : {}", emp);

        Employee cloned = new Employee(emp);
        log.info("Cloned employee : {}", cloned);

        log.info("emp == cloned : {}", emp == cloned);
        log.info("Objects.equals(emp, cloned) : {}", Objects.equals(emp, cloned));

        emp.setId(2);
        emp.setName("younghee");
        emp.setDepart("DEV");

        log.info("Employee : {}", emp);
        log.info("Cloned employee : {}", cloned);
    }
}

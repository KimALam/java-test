package sample.deepcopy.by_copyconstructor;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
class ProblemCopyConstructorMain {
    public static void main(String[] args) {
        Mammal mammal = new Mammal("Human");
        Human human = new Human("Human", "Naresh");

        // 1) 자신의 type 은 문제 없음.
        Mammal clonedMammal = new Mammal(mammal);
        Human clonedHuman = new Human(human);

        log.info("mammal == clonedMammal : {}", mammal == clonedMammal); // false
        log.info("human == clonedHuman :{}", human == clonedHuman); // false
        log.info("Objects.equals(mammal, clonedMammal) : {}", Objects.equals(mammal, clonedMammal)); // true
        log.info("Objects.equals(human, clonedHuman) : {}", Objects.equals(human, clonedHuman)); // true

        // 2) 상위 type 으로 캐스팅 되면 copy constructor 되지 않는다.
        // Mammal mammalHuman = new Human("Human", "Mahesh");
        // Mammal clonedMammalHuman = new Human(mammalHuman); // compilation error

        // 3) 상속 문제로 copy constructor 사용이 어려울 때는 copy method 를 만들어 줘서 해결 할 수 있다
        // Mammal.cloneObject() / Human.cloneObject() 참고
    }
}

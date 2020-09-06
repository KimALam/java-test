package sample.deepcopy.by_copyconstructor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
class Human extends Mammal {
    protected String name;

    public Human(String type, String name) {
        super(type);
        this.name = name;
    }

    /** copy constructor : 자신의 type 을 변수로 받는 constructor */
    public Human(Human origin) {
        super(origin.type);
        this.name = origin.name;
    }

    /**
     * 상속 문제로 copy constructor 사용이 어려울 때는 copy method 를 만들어 줘서 해결 할 수 있다
     * copy constructor 를 private 으로 둬도 좋을 듯.
     */
    @Override
    public Human cloneObject() {
        return new Human(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}

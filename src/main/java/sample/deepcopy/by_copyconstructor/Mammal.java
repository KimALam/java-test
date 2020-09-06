package sample.deepcopy.by_copyconstructor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
class Mammal {
    protected String type;

    public Mammal(String type) {
        this.type = type;
    }

    /**
     * 상속 문제로 copy constructor 사용이 어려울 때는 copy method 를 만들어 줘서 해결 할 수 있다
     * copy constructor 를 private 으로 둬도 좋을 듯.
     */
    public Mammal(Mammal origin) {
        this.type = origin.type;
    }

    public Mammal cloneObject() {
        return new Mammal(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mammal mammal = (Mammal) o;
        return Objects.equals(type, mammal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

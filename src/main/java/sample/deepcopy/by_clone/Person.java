package sample.deepcopy.by_clone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
class Person implements Cloneable {
    // Primitives are cloned
    private int income;

    /** Object should be cloned explicitly */
    private int[] nums;
    // But, immutable should be also cloned explicitly?
    private String name;
    private City city;
    // Java Collection 은 shallow copy 만 제공하고 deep copy 는 제공하지 않는다.
    private List<Integer> scores;

    public Person(int income, int[] nums, String name, City city, List<Integer> scores) {
        this.income = income;
        this.nums = nums;
        this.name = name;
        this.city = city;
        this.scores = scores;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.nums = nums.clone();
        // immutable 의 경우 == 은 true로 나오겠지만, 값이 변경되면 다른 object 로 대체되니 사용상 reference copy 되어도 상관 없다.
        // 여기서는 명시적인 deep copy 를 보여주기 위해 사용.
        cloned.name = new String(name);
        cloned.city = city.clone();

        // Collection 은 deep copy 만들어 줘야 한다.
        List<Integer> clonedScores = new ArrayList<>();
        for (Integer i : scores) {
            // primitive 가 아닌 경우 해당 object.clone()을 호출해 준다.
            clonedScores.add(i);
        }
        cloned.scores = clonedScores;

        return cloned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return income == person.income &&
                Arrays.equals(nums, person.nums) &&
                Objects.equals(name, person.name) &&
                Objects.equals(city, person.city) &&
                Objects.equals(scores, person.scores);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(income, name, city, scores);
        result = 31 * result + Arrays.hashCode(nums);
        return result;
    }
}

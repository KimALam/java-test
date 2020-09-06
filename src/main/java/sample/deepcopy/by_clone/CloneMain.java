package sample.deepcopy.by_clone;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
class CloneMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        int[] nums = {10, 20, 30};
        List<Integer> score = Arrays.asList(1, 2, 3);
        City city = new City(1, "Seoul");
        Person person = new Person(10, nums, "chulsu", city, score);

        log.info("person :{}", person);

        Person cloned = person.clone();

        log.info("person == cloned : {}", person == cloned);
        log.info("person.getNums() == cloned.getNums() : {}", person.getNums() == cloned.getNums());
        log.info("person.getName() == cloned.getName() : {}", person.getName() == cloned.getName());
        log.info("person.getCity() == cloned.getCity() : {}", person.getCity() == cloned.getCity());
        log.info("person.getScore() == cloned.getScore() : {}", person.getScores() == cloned.getScores());

        log.info("Objects.equals(person, cloned) : {}", Objects.equals(person, cloned));
        // cf) array comparison : https://stackoverflow.com/a/8777279/1411827
        log.info("Objects.equals(person.getNums(), cloned.getNums()) : {}", Arrays.equals(person.getNums(), cloned.getNums()));
        log.info("Objects.equals(person.getName(), cloned.getName()) : {}", Objects.equals(person.getName(), cloned.getName()));
        log.info("Objects.equals(person.getCity(), cloned.getCity()) : {}", Objects.equals(person.getCity(), cloned.getCity()));
        log.info("Objects.equals(person.getScore(), cloned.getScore()) : {}", Objects.equals(person.getScores(), cloned.getScores()));

        person.setIncome(20);
        person.getNums()[0] = 2;
        person.setName("younghee");
        person.getCity().setId(2);
        person.getCity().setName("Busan");
        person.getScores().set(0, 2);

        log.info("person : {}", person);
        log.info("cloned : {}", cloned);
    }
}

package test.generic;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PECSTest {
    public static void main(String[] args) {
        List<Super> superList = new ArrayList<>();
        superList.add(new Super());
        superList.add(new Sub1());
        addOne(superList);
        superList.forEach(item -> item.printName());
        addTwo(superList);
        superList.forEach(item -> item.printName());

        List<Sub1> sub1List = new ArrayList<>();
        sub1List.add(new Sub1());
        sub1List.add(new Sub2());
        addOne(sub1List);
        sub1List.forEach(item -> item.printName());
    }

    private static void addOne(List<? super Sub1> list) {
        list.add(new Sub2());
    }

    private static void addTwo(List<Super> list) {
        list.add(new Sub2());
    }

    private static List<Super> genOne() {
        List<Super> superList = new ArrayList<>();
        superList.add(new Super());
        superList.add(new Sub1());
        return superList;
    }

    private static List<? extends Super> genTwo() {
        List<Sub1> sub1List = new ArrayList<>();
        sub1List.add(new Sub1());
        sub1List.add(new Sub2());
        return sub1List;
    }

    private static List<Super> genThree() {
        List<Sub1> sub1List = new ArrayList<>();
        sub1List.add(new Sub1());
        sub1List.add(new Sub2());
        return sub1List.stream().map(Super.class::cast).collect(toList());
    }

    private static class Super {
        void printName() {
            System.out.println("Super");
        }
    }

    private static class Sub1 extends Super {
        @Override
        void printName() {
            System.out.println("Sub1");
        }
    }

    private static class Sub2 extends Sub1 {
        @Override
        void printName() {
            System.out.println("Sub2");
        }
    }
}

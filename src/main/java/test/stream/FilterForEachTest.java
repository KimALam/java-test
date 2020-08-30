package test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FilterForEachTest {
    public static void main(String[] args) {
        List<MyClass> list = new ArrayList<MyClass>() {{
            add(new MyClass(10, "aaa"));
            add(new MyClass(20, "bbb"));
            add(new MyClass(30, "ccc"));
            add(new MyClass(40, "ddd"));
        }};

//        List<MyClass> filtered =
                list.stream()
                .filter(m -> m.getAge() > 20)
                .forEach(m -> m.setName("zzz"));
//                .map(m -> {
//                    m.setName("zzz");
//                    return m;
//                })
//                .collect(toList());

        System.out.println("list : " + list);
//        System.out.println("filtered : " + filtered);
    }

    private static class MyClass {
        private int age;
        private String name;

        public MyClass(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyClass{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

package test.string;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

class StringJoinTest {
    public static void main(String[] args) {
        List<String> coms = new ArrayList() {{
            add("Hyundae");
            add("Samsung");
            add("SK");
        }};

        StringJoiner joiner = new StringJoiner(", ", "< ", " >");
        for (String com : coms) {
            joiner.add(com);
        }

        System.out.println("result = " + joiner.toString());
    }
}

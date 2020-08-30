package test.collection;

import java.util.Arrays;
import java.util.List;

public class SubListTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        List<String> subList = list.subList(2, 4);   // ok
        List<String> subList1 = list.subList(3, 2);  // x
        List<String> subList2 = list.subList(3, 3);  // ok = empty list
        List<String> subList3 = list.subList(-1, 2); // x
        System.out.println("subList : " + subList);
    }
}

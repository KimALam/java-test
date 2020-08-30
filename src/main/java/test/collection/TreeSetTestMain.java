package test.collection;

import java.util.Arrays;
import java.util.TreeSet;

class TreeSetTestMain {
    public static void main(String[] args) {
        TreeSet<String> tree1 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        tree1.addAll(Arrays.asList("a", "b", "c"));
        TreeSet<String> tree2 = new TreeSet<>(tree1);

        tree1.removeAll(Arrays.asList("C", "D"));
        System.out.println(tree1);
        tree2.retainAll(Arrays.asList("C", "D"));
        System.out.println(tree2);



//        Set<String> tree1 = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
//        tree1.addAll(Arrays.asList("a", "b", "c"));
//        Set<String> tree2 = new TreeSet<>(tree1);
//        tree1.removeAll(Arrays.asList("C", "D", "E", "F"));
//        System.out.println(tree1);
//        tree2.retainAll(Arrays.asList("C", "D", "E", "F"));
//        System.out.println(tree2);
    }
}

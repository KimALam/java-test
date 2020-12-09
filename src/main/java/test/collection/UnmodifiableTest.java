package test.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableTest {
    public static void main(String[] args) {
        // list of primitive or immutable
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> unmod = Collections.unmodifiableList(list);
        try {
            unmod.add(0, "d");
            System.out.println("Modified...");
        } catch (Exception e) {
            System.out.println("Can not modify...");
        }

        List<String> copycons = new ArrayList<>(list);

        list.add("d");
        System.out.println("unmod list : " + unmod);
        System.out.println("copycons : " + copycons);

        System.out.println();

        // for modifiable class
        List<ModifiableClass> modList = new ArrayList<>();
        modList.add(new ModifiableClass("a", "b"));
        System.out.println("modList : " + modList);
        List<ModifiableClass> unmodModList = Collections.unmodifiableList(modList);
        unmodModList.get(0).a = "aaa";
        System.out.println("unmodModList : " + unmodModList);
        try {
            unmodModList.add(new ModifiableClass("ccc", "ccc"));
            System.out.println("Modified...");
        } catch (Exception e) {
            System.out.println("Can not modify...");
        }
    }

    private static class ModifiableClass {
        String a;
        String b;

        public ModifiableClass(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "ModifiableClass{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    '}';
        }
    }
}

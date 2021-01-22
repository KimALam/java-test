package test.reference;

import java.util.Map;
import java.util.WeakHashMap;

class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new WeakHashMap<>();

        // -128 ~ 127 Integer cached. So, key1 = null not effect
        Integer key1 = 127;
        Integer key2 = 128;

        map.put(key1, "1");
        map.put(key2, "2");

//        key1 = null;
        key2 = null;
        System.gc();
        Thread.sleep(1000);

        map.entrySet().stream().forEach(et -> System.out.println("entry : " + et));
    }
}

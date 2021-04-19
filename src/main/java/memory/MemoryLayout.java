package memory;

public class MemoryLayout {
    public static void main(String[] args) {
        SimpleInt obj = new SimpleInt();
        System.out.println("obj : " + obj);
        System.out.println("identityHashCode : " + System.identityHashCode(obj));
        System.out.println("hashCode : " + obj.hashCode());
    }
}

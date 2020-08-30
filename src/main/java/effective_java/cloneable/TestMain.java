package effective_java.cloneable;

public class TestMain {
    public static void main(String[] args) {
        Item[] origin = new Item[5];
        for (int i = 0; i < 5; i++) {
            origin[i] = new Item(i);
        }
        Item[] clone = origin.clone();

        System.out.println("origin[0]: " + origin[0]);
        System.out.println("clone[0]: " + clone[0]);

        origin[0].num = 100;

        System.out.println("origin[0]: " + origin[0]);
        System.out.println("clone[0]: " + clone[0]);

        int[] ints = new int[5];
        for (int i = 0; i < 5; i++) {
            ints[i] = i;
        }
        int[] iclone = ints.clone();

        System.out.println("ints[0]: " + ints[0]);
        System.out.println("iclone[0]: " + iclone[0]);

        ints[0] = 100;

        System.out.println("ints[0]: " + ints[0]);
        System.out.println("iclone[0]: " + iclone[0]);
    }

    private static class Item {
        int num;

        public Item(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return String.valueOf(num);
        }
    }
}

package test.iter;

class CustomListTestMain {
    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}

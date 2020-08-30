package test.hodgepodge;

class BooleanTest {
    public static void main(String[] args) {
        if (over5(3)) {
            System.out.println("over 5");
        } else {
            System.out.println("under 5");
        }

        if (over5(9)) {
            System.out.println("over 5");
        } else {
            System.out.println("under 5");
        }
    }

    public static boolean over5(int val) {
        return val > 5;
    }
}

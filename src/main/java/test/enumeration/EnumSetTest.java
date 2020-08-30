package test.enumeration;

import java.util.EnumSet;

public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet<Color> colorSet = EnumSet.allOf(Color.class);
        System.out.println("contains ? " + colorSet.contains(Color.RED));
    }

    private enum Color {
        RED, YELLOW, GREEN, BLUE, BLACK, WHITE;
    }
}

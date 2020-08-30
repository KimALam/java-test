package effective_java.enums;

import java.util.EnumSet;
import java.util.Set;

// Use EnumSet instead of bit fields.
class EnumSetTest {
    static class Text {
        enum Style {
            BOLD,
            ITALIC,
            UNDERLINE,
            STRIKETHROUGH;
        }

        public void applyStyles(Set<Style> styles) {
            // Do something.
        }
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.UNDERLINE));
    }
}

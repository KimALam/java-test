package rxjava.common;

public class Shape {
    public static final String HEXAGON = "HEXAGON";
    public static final String OCTAGON = "OCTAGON";
    public static final String RECTANGLE = "RECTANGLE";
    public static final String TRIANGLE = "TRIANGLE";
    public static final String DIAMOND = "DIAMOND";
    public static final String PENTAGON = "PENTAGON";
    public static final String BALL = "BALL";
    public static final String STAR = "STAR";

    public static String getColor(String shape) {
        if (shape.endsWith("♢")) {
            return shape.replace("♢", "").trim();
        }

        int hyphen = shape.indexOf("-");
        if (hyphen > 0) {
            return shape.substring(0, hyphen);
        }
        return shape;
    }

    public static String getSuffix(String shape) {
        if (HEXAGON.equals(shape)) return "-H";
        if (OCTAGON.equals(shape)) return "-O";
        if (RECTANGLE.equals(shape)) return "-R";
        if (TRIANGLE.equals(shape)) return "-T";
        if (DIAMOND.equals(shape)) return "♢";
        if (PENTAGON.equals(shape)) return "-P";
        if (STAR.equals(shape)) return "-S";
        return "";
    }

    public static String getShape(String shape) {
        int hyphen = shape.indexOf("-");
        if (hyphen < 0) {
            return BALL;
        }

        switch (shape.substring(hyphen)) {
            case "-H": return HEXAGON;
            case "-O": return OCTAGON;
            case "-R": return RECTANGLE;
            case "-T": return TRIANGLE;
            case "♢": return DIAMOND;
            case "-P": return PENTAGON;
            case "-S": return STAR;
            default: return BALL;
        }
    }

    public static String flip(String shape) {
        return getShape(shape);
    }
}

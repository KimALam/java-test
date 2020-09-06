package pattern.bridge;

public class BridgeSample {
    public static void main(String[] args) {
        Shape square = new Square(new Red());
        Shape square2 = new Square(new Blue());

        System.out.println(square.draw());
        System.out.println(square2.draw());
    }

    private interface Color {
        String fill();
    }

    private static class Blue implements Color {
        @Override
        public String fill() {
            return "Blue";
        }
    }

    private static class Red implements Color {
        @Override
        public String fill() {
            return "Red";
        }
    }

    // birdge
    private static abstract class Shape {
        protected Color color;

        public Shape(Color color) {
            this.color = color;
        }

        public abstract String draw();
    }

    private static class Square extends Shape {
        public Square(Color color) {
            super(color);
        }

        @Override
        public String draw() {
            return "Square drawn. " + color.fill();
        }
    }
}

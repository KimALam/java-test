package test.verifier;

public class Card {
    private int number;
    private String name;

    public void setNumber(String number) {
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c))
                throw new IllegalArgumentException();
        }
        this.number = Integer.parseInt(number);
    }
}

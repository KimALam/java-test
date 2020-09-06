package test.verifier;

public class AccessVerifierTest {
    public static void main(String[] args) {
        Card card = new Card();
        try {
            card.setNumber("1234");
        } catch (IllegalArgumentException exception) {
//            System.out.println("Can't process for account " + card.name);
        }
    }
}

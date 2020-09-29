package test.string;

class StringConcatTest {
    public static void main(String[] args) {
        String result = "";
        for (int i = 0; i < 1e6; i++)
        {
            result += "some data";
        }
        System.out.println(result);
    }
}

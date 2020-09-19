package test.annotation.custom;

public class TestMain {
    public static void main(String[] args) {
        Person person = new Person("sofiane", "cheouati", "34", "address");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);
    }
}

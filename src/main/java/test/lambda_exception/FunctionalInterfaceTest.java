package test.lambda_exception;

@FunctionalInterface
interface FunctionsOnly {
    double clone();
}

class FunctionalInterfaceTest {
    public static void main(String[] args) {
        FunctionsOnly lambda = () -> 1;
        FunctionsOnly methodRef = Math::random;

        System.out.println(lambda.clone());
        System.out.println(methodRef.clone());

//        FunctionsOnly annonymousClass = new FunctionsOnly() {
//            @Override
//            public double clone() {
//                return 1L;
//            }
//        };
    }
}

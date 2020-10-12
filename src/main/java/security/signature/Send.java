package security.signature;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class Send {
    private static final String FILE_NAME = "sig_test";

    public static void main(String[] args) {
        String data = "This have I thought good to deliver thee, " +
                      "that thou mightst not lose the dues of rejoicing " +
                      "by being ignorant of what greatness is promised thee.";

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // TODO
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

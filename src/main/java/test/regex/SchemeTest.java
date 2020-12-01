package test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchemeTest {
    private static final Pattern protoPtrn = Pattern.compile("^(http(s?):)?\\/\\/");

    public static void main(String[] args) {
        String text1 = "https://m.11st.co.kr/MW/1234";

        Matcher matcher = protoPtrn.matcher(text1);
        if (matcher.find()) {
            System.out.println("matched : " + text1);
            System.out.println("group :" + matcher.group());
            System.out.println("group count :" + matcher.groupCount());
            System.out.println("group(1) : " + matcher.group(1));
            System.out.println("group(2) : " + matcher.group(2));
            System.out.println("start :" + matcher.start());
            System.out.println("end :" + matcher.end());
        } else {
            System.out.println("not matched : " + text1);
        }
    }
}

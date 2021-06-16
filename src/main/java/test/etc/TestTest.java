package test.etc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

public class TestTest {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.setProperty("name1", "value1");
        prop.setProperty("name2", "value2");
        String encodeString = encodeString(prop);

        URL url = new URL("http://www.javacan.com/index.html?" + encodeString);
        URLConnection conn = url.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);

        if ("POST".equals(args[0])) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream out = null;
            try {
                out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(encodeString);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) out.close();
            }
        }

        // GET or POST
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        char[] buff = new char[512];
        int len = -1;

        while ((len = br.read(buff)) != -1) {
            System.out.println(new String(buff, 0, len));
        }

        br.close();
    }

    public static String encodeString(Properties params) {
        StringBuffer sb = new StringBuffer(256);
        Enumeration names = params.propertyNames();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = params.getProperty(name);
            sb.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value));

            if (names.hasMoreElements()) sb.append("&");
        }

        return sb.toString();
    }
}

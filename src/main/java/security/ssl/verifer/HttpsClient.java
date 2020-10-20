package security.ssl.verifer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class HttpsClient {
    static class HttpClientVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String url, SSLSession ss) {
            try {
                // We know the strings don't match, but it's conceivable that their IP addresses do.
                // We could also ask the user here.
                InetAddress iaU = InetAddress.getByName(url);
                InetAddress iaC = InetAddress.getByName(ss.getPeerHost());
                return iaU.equals(iaC);
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        URL u = new URL(args[0]);
        // Alternately, we could set a global verifier like this
//        HttpsURLConnection.setDefaultHostnameVerifier(new HttpClientVerifier());

        HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
        // Instead, we set the verifier only for this instance
        huc.setHostnameVerifier(new HttpClientVerifier());

        BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
        String s = br.readLine();
        while (s != null) {
            System.out.println(s);
            s = br.readLine();
        }
    }
}

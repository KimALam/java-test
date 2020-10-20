package security.ssl;

import sun.security.x509.X500Name;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class SSLClientVerifier {
    public static void main(String[] args) throws IOException {
        SocketFactory sf = SSLSocketFactory.getDefault();
        SSLSocket s = (SSLSocket) sf.createSocket(args[0], Integer.parseInt(args[1]));

        SSLSession sess = s.getSession();
        String host = sess.getPeerHost();
        X509Certificate[] certs = sess.getPeerCertificateChain();
        String dn = certs[0].getSubjectDN().getName();
        X500Name name = new X500Name(dn);
        if (!host.equals(name.getCommonName())) {
            System.err.println("Warning: Expected " + host + " and got " + name.getCommonName());
        }
        System.out.println("Host: " + host + ", DN: " + dn + ", CN: " + name.getCommonName());
        System.out.println("X500Name: " + name);

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        System.out.println("Who is Sylvia?");
        pw.println("Who is Sylvia?");
        pw.flush();
        System.out.println(br.readLine());
        s.close();
    }
}

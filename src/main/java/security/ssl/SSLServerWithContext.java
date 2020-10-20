package security.ssl;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.security.KeyStore;

class SSLServerWithContext {
    public static void main(String[] args) throws Exception {
        SSLContext sc = SSLContext.getInstance("TLS");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        KeyStore ks = KeyStore.getInstance("jceks");

        char[] password = args[1].toCharArray();
        ks.load(new FileInputStream(args[0]), null);
        kmf.init(ks, password);
        sc.init(kmf.getKeyManagers(), null, null);

        ServerSocketFactory ssf = sc.getServerSocketFactory();
        ServerSocket ss = ssf.createServerSocket(9096);

        while (true) {
            new SSLSimpleServer(ss.accept()).start();
        }
    }
}

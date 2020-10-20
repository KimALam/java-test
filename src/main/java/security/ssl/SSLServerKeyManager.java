package security.ssl;

import security.provider.XYZProvider;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.security.Security;

// java -Dxyz.aliasName=sdo security.ssl.SSLServerKeyManager ./keystore/server.jceks 1234qwer
class SSLServerKeyManager {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new XYZProvider());

        SSLContext sc = SSLContext.getInstance("TLS");
        KeyStore ks = KeyStore.getInstance("jceks");
        char[] password = args[1].toCharArray();

        ks.load(new FileInputStream(args[0]), null);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("XYZ");
        kmf.init(ks, password);
        sc.init(kmf.getKeyManagers(), null, null);

        ServerSocketFactory ssf = sc.getServerSocketFactory();
        ServerSocket ss = ssf.createServerSocket(9096);

        while (true) {
            new SSLSimpleServer(ss.accept()).start();
        }
    }
}

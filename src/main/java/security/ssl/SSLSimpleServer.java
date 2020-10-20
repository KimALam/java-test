package security.ssl;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Create keystore
//  $> cd src/main/resources/keystore
//  $> keytool -genkey -keystore server.jks -keyalg RSA
//  $> keytool -export -keystore server.jks -file server.cer
//  $> keytool -import -keystore ./truststore.jks -file server.cer

// Server start:
// java -Djavax.net.ssl.keyStore=./keystore/server.jks -Djavax.net.ssl.keyStorePassword=1234qwer security.ssl.SSLSimpleServer
class SSLSimpleServer extends Thread {
    public static void main(String[] args) throws IOException {
        ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(9096);

        while (true) {
            new SSLSimpleServer(ss.accept()).start();
        }
    }

    private Socket sock;

    public SSLSimpleServer(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter pw = new PrintWriter(sock.getOutputStream());

            String data = br.readLine();
            pw.println("What is she?");
            pw.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

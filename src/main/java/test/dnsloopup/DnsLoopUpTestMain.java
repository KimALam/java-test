package test.dnsloopup;

import java.net.InetAddress;
import java.net.UnknownHostException;

class DnsLoopUpTestMain {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getByName("shop.11st.co.kr");
        System.out.println("address: " + host.getHostAddress());
    }
}

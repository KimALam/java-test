package test.bytebuffer;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        byte[] data = new byte[]{0x11, 0x12, 0x13, 0x14};
        printBuf(data);
    }

    private static void printBuf(byte[] data) {
        ByteBuffer buf = ByteBuffer.allocate(10);
//        ByteBuffer buf = ByteBuffer.allocateDirect(10);

        // write
        if (data.length <= buf.remaining()) {
            buf.put(data);
        }

        printBuf(buf);
        buf.flip();
        printBuf(buf);

//        for (int i = buf.position(); i < buf.limit(); i++) {
        for (int i = buf.position(); i < 3; i++) {
            // change position
            System.out.println(String.format("0x%02x", buf.get()));
            // no change position
//            System.out.println(String.format("0x%02x", buf.get(i)));
        }

        printBuf(buf);
//        buf.flip();
        buf.compact();
        printBuf(buf);
    }

    private static void printBuf(ByteBuffer buf) {
        System.out.println("buffer info : " + buf);
        System.out.println("isDirect : " + buf.isDirect());
        System.out.println("isReadOnly :" + buf.isReadOnly());
    }
}

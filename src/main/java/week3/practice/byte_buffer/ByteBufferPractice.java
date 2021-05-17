package week3.practice.byte_buffer;

import java.nio.ByteBuffer;

public class ByteBufferPractice {
    public static void main(String[] args) {
        String msg = "練習 ByteBuffer";
        byte[] bytes = msg.getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(bytes);

        // change to read mode
        byteBuffer.flip();

        byte[] tmpBytes = new byte[bytes.length];
        int i = 0;

        while (byteBuffer.hasRemaining()) {
            tmpBytes[i] = byteBuffer.get();
            i++;
        }

        System.out.println(new String(tmpBytes));
    }
}

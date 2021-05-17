package week3.practice.byte_buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelPractice {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = null;
        FileChannel fileInputStreamChannel = null;

        FileOutputStream fileOutputStream = null;
        FileChannel fileOutputStreamChannel = null;

        try {
            File file = new File("test_byte_buffer_1.txt");
            fileInputStream = new FileInputStream(file);
            fileInputStreamChannel = fileInputStream.getChannel();

            fileOutputStream = new FileOutputStream("test_byte_buffer_2.txt");
            fileOutputStreamChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

            fileInputStreamChannel.read(byteBuffer);
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
            fileInputStreamChannel.close();

            fileOutputStream.close();
            fileOutputStreamChannel.close();
        }
    }
}

package others.kryo_practice;

import java.io.*;
import java.nio.file.Files;

public class Run {
    public static void main(String[] args) throws IOException {
        final TestObject testObject = new TestObject();
        testObject.setName("Kai");
        testObject.setField(123);


        // kryo write
        byte[] serialize = KryoUtil.getInstance().serialize(testObject, TestObject.class);
        try {
            Files.write(new File("./TestObject").toPath(), serialize);
        } catch (Exception e2) {
            e2.printStackTrace();
        }


        // native write
        try (FileOutputStream fileOutputStream = new FileOutputStream("./TestObjectNative");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(testObject);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // kryo read
        try {
            byte[] read = Files.readAllBytes(new File("./TestObject").toPath());
            TestObject deserialize = KryoUtil.getInstance().deserialize(read, TestObject.class);
            System.out.println(deserialize);
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        // native read
        try (FileInputStream fileInputStream = new FileInputStream("./TestObjectNative");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            final Object obj = objectInputStream.readObject();
            System.out.println((TestObject)obj);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

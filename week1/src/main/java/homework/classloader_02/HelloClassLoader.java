package homework.classloader_02;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object obj = new HelloClassLoader().loadClass("Hello").newInstance();
            Method method = obj.getClass().getMethod("hello");
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public Class findClass(String name) throws ClassNotFoundException {
        System.out.println("=== findClass ===");
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws ClassNotFoundException {
        System.out.println("=== loadClassData ===");
        try (InputStream inputStream = Files.newInputStream(Paths.get("Hello.xlass"))) {
            StringBuilder hex = new StringBuilder();

            int value;
            while ((value = inputStream.read()) != -1) {
                hex.append(String.format("%02x", 255 - value));
            }

            return hex2Byte(hex.toString());
        } catch (Exception e) {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    public byte[] hex2Byte(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}

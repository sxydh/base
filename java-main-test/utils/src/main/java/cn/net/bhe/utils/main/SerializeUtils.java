package cn.net.bhe.utils.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum SerializeUtils {
    ;
    static Logger LOGGER = LoggerFactory.getLogger(SerializeUtils.class);

    public static byte[] serialize(Object obj) throws Exception {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArray);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArray.toByteArray();
    }

    public static Object deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objInput = new ObjectInputStream(inputStream);
        return objInput.readObject();
    }

    public static String toStr(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    @SuppressWarnings("unchecked")
    public static <X> X toObj(Class<X> clazz, String str) throws IOException, ClassNotFoundException {
        byte[] bytes = Base64.getDecoder().decode(str);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object o = ois.readObject();
        ois.close();
        return (X) o;
    }
}

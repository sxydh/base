package cn.net.bhe.basics.io;

import org.junit.jupiter.api.Test;
import static cn.net.bhe.utils.main.PrintUtils.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeTest implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Test
    public void serialize() {
        try {
            SerializeTest serializeTest = new SerializeTest();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
            objectOutputStream.writeObject(serializeTest);
            byte[] bytes = bos.toByteArray();
            pln(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void deserialize() {
        try {
            SerializeTest serializeTest = new SerializeTest();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
            objectOutputStream.writeObject(serializeTest);
            byte[] bytes = bos.toByteArray();

            // 反序列化
            InputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object deserializeObj = objectInputStream.readObject();
            pln(deserializeObj);
            pln(deserializeObj == serializeTest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

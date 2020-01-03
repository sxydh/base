package fun.ehe.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class MySerializeUtils {
    private MySerializeUtils() {
    }

    /**
     * @Date 2018-08-22 15:50
     * @Description
     * @Version
     * @param obj
     * @return
     */
    public static byte[] toByte(Object obj) {
        byte[] bts = null;
        try (ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                ObjectOutputStream outputStream = new ObjectOutputStream(byteArray)) {
            outputStream.writeObject(obj);
            outputStream.flush();
            bts = byteArray.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bts;
    }

    /**
     * @Date 2018-08-22 15:51
     * @Description
     * @Version
     * @param bytes
     * @return
     */
    public static Object toObj(byte[] bytes) {
        Object result = null;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objInput = new ObjectInputStream(inputStream)) {
            result = objInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

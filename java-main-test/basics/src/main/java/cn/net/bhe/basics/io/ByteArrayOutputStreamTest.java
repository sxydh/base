package cn.net.bhe.basics.io;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteArrayOutputStreamTest {
    
    static Logger log = LoggerFactory.getLogger(ByteArrayOutputStreamTest.class);

    /**
     * 适用于特定场景下替代FileOutputStream，然后从中获取字节数组
     */
    @Test
    public void toByteArray() {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            workbook.close();
            byte[] bytes = bos.toByteArray();
            bos.close();
            log.info(Arrays.toString(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
}

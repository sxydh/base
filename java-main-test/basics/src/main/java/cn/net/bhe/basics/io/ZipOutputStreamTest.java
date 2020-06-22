package cn.net.bhe.basics.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipOutputStreamTest {

    static Logger log = LoggerFactory.getLogger(ZipOutputStreamTest.class);

    /**
     * 压缩多个文件简单例子，注意JDK自带的zip不支持设置字符集，压缩后中文文件名会乱码
     */
    @Test
    public void sample() {
        try {
            List<String> srcFiles = Arrays.asList("C:\\Users\\Administrator\\Desktop\\test1.csv", "C:\\Users\\Administrator\\Desktop\\test2.txt");
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.zip");
            
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            
            for (String srcFile : srcFiles) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                
                fis.close();
            }
            zipOut.close(); // 必须close否则压缩包损坏
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 可以用apache zip解决中文文件名乱码
     */
    @Test
    public void sampleApacheZip() {
        try {
            List<String> srcFiles = Arrays.asList("C:\\Users\\Administrator\\Desktop\\压缩文件1.csv", "C:\\Users\\Administrator\\Desktop\\压缩文件2.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\压缩文件.zip");
            
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
            org.apache.tools.zip.ZipOutputStream zipOutputStream = new org.apache.tools.zip.ZipOutputStream(checkedOutputStream);
            zipOutputStream.setEncoding(System.getProperty("sun.jnu.encoding")); // 设置文件名编码方式
            
            for(String srcFile : srcFiles) {
                File fileToZip = new File(srcFile);
                FileInputStream fileInputStream = new FileInputStream(fileToZip);
                
                org.apache.tools.zip.ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry(fileToZip.getName());
                zipOutputStream.putNextEntry(zipEntry);
                int length = 1024;
                byte[] bytes = new byte[length];
                while((length = fileInputStream.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes);
                }
                fileInputStream.close();
            }
            zipOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

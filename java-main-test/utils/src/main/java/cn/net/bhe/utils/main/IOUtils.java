package cn.net.bhe.utils.main;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {
    static Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);
    
    @Test
    public void test() {
    }
    
    public static void copy(File src, File dest) {
        try (InputStream is = new FileInputStream(src); 
             OutputStream os = new FileOutputStream(dest)) 
        {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException io) {
            LOGGER.error("", io);
        }
    }

    public static int bytes2int(byte... bytes) {
        if (bytes.length < 4) {
            byte[] dest = new byte[4];
            System.arraycopy(bytes, 0, dest, dest.length - bytes.length, bytes.length);
            bytes = dest;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int num = byteBuffer.getInt();
        return num;
    }
    
    public static String getResourcePath(Class<?> clazz, String fileName) {
        URL url = clazz.getClassLoader().getResource(fileName);
        return url.toString();
    }
    
    public static <T extends Closeable> void close(T closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                pln(e.getLocalizedMessage());
            }
        }
    }
    
    public static String streamToStr(InputStream is) {
        StringBuilder result = new StringBuilder();
        InputStream inputStream = is;
        Reader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            int data;
            while ((data = inputStreamReader.read()) != -1) {
                result.append((char) data);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(inputStreamReader);
            close(inputStream);
        }
    }

    public static String readConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getClassPath(Class<?> clazz, int cutTheChild) {
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
        path = path.replace("\\", "/");
        path += clazz.getPackage().getName().replace(".", "/");
        path = path.substring(path.indexOf("/") + 1);
        for (int i = 0; i < cutTheChild; i++) {
            int first = path.indexOf("/");
            int last = path.lastIndexOf("/");
            if (last > first) {
                path = path.substring(0, last);
            } else {
                break;
            }
        }
        return path;
    }

    public static String readerRead(String path) {
        StringBuilder result = new StringBuilder();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            int i;
            while ((i = fr.read()) != -1) {
                result.append((char) i);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(fr);
        }
    }

    public static String streamRead(String path) throws Exception {
        StringBuilder result = new StringBuilder();
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[fis.available()];
        while (fis.read(buffer) > 0) {
            result.append(new String(buffer));
        }
        fis.close();
        return result.toString();
    }

    public static List<String> iterFolder(String path) {
        List<String> list = new ArrayList<>();
        recursion(new File(path).listFiles(), list);
        return list;
    }

    private static void recursion(File[] files, List<String> list) {
        for (File file : files) {
            list.add(file.getName());
            if (file.isDirectory()) {
                recursion(file.listFiles(), list);
            }
        }
    }

    public static void writerWrite(String path, String content) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.append(content);
        writer.close();
    }

    public static void printWrite(String path, String content) throws Exception {
        FileWriter fileWriter = new FileWriter(path, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();
    }

    public static void streamWrite(String path, String content) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(path, true);
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    public static Map<String, String> propToMap(String pathName) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(pathName));
        @SuppressWarnings({ "unchecked", "rawtypes" })
        Map<String, String> map = new HashMap(prop);
        return map;
    }

    public static void writeExcel(String pathName, String fileType, String sheetName, List<List<String>> data) throws Exception {
        Workbook wb;
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook();
        } else if (fileType.equals("xlsx")) {
            wb = new XSSFWorkbook();
        } else {
            throw new Exception("wrong file format");
        }
        Sheet sheet = wb.createSheet(sheetName);
        int rownum = 0;
        for (List<String> data_row : data) {
            Row row = sheet.createRow(rownum);
            int colnum = 0;
            for (String data_cell : data_row) {
                row.createCell(colnum).setCellValue(data_cell);
                colnum++;
            }
            rownum++;
        }
        try (OutputStream fileOut = new FileOutputStream(pathName)) {
            wb.write(fileOut);
        }
        wb.close();
    }

    public static List<List<String>> readExcel(String pathName, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException {
        Workbook wb = WorkbookFactory.create(new File(pathName));
        Sheet sheet = wb.getSheet(sheetName);
        List<List<String>> data = new ArrayList<List<String>>();
        for (Row row : sheet) {
            List<String> data_row = new ArrayList<String>();
            for (Cell data_cell : row) {
                switch (data_cell.getCellTypeEnum()) {
                case STRING:
                    data_row.add(data_cell.getRichStringCellValue().getString());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(data_cell)) {
                        data_row.add(data_cell.getDateCellValue().toString());
                    } else {
                        data_row.add(String.valueOf(data_cell.getNumericCellValue()));
                    }
                    break;
                case BOOLEAN:
                    data_row.add(String.valueOf(data_cell.getBooleanCellValue()));
                    break;
                case FORMULA:
                    data_row.add(data_cell.getCellFormula());
                    break;
                case BLANK:
                    data_row.add("");
                    break;
                default:
                    data_row.add("");
                }
            }
            data.add(data_row);
        }
        return data;
    }

}

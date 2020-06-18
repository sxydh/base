package cn.net.bhe.utils.main;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * 对Microsoft Office格式文件读和写的功能。
 */
public class POIUtils {
    static Logger log = LoggerFactory.getLogger(POIUtils.class);
    
    @Test
    public void test() throws Exception {
        @SuppressWarnings("unused")
        class Bean {
            String aaaaaaaaaaaaaaaaaaaa = "1";
            String bbb = "2";
        }
        List<Bean> list = new ArrayList<Bean>();
        list.add(new Bean());
        list.add(new Bean());
        HSSFWorkbook workbook = new HSSFWorkbook();
        listToSheetWithVMerge(workbook, "", null, list);
        
        log.info(workbookToHtml(workbook));
//        writeWorkbookToFile("C:\\Users\\Administrator\\Desktop\\test.xls", workbook);
    }
    
    public static String workbookToHtml(HSSFWorkbook workbook) throws Exception {
        ExcelToHtmlConverter converter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
       
        converter.setOutputColumnHeaders(false); // 去掉Excel头行
        converter.setOutputRowNumbers(false); // 去掉Excel行号
        
        converter.processWorkbook(workbook);
        
        Document htmlDoc = converter.getDocument();
        DOMSource domSource = new DOMSource(htmlDoc);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StreamResult streamResult = new StreamResult(out);
        
        TransformerFactory transfFactory = TransformerFactory.newInstance();
        Transformer serializer = transfFactory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        
        out.close();
        
        return new String(out.toByteArray());
    }
    
    /**
     * 替换内容
     * 
     * @param workbook
     * @param sheetIndex
     * @param orig
     * @param dest
     */
    public static void replaceCellContent(HSSFWorkbook workbook, int sheetIndex, List<String> orig, List<String> dest) {
        Iterator<Row> rows = workbook.getSheetAt(sheetIndex).iterator();
        while(rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if (CellType.STRING == cell.getCellTypeEnum()) {
                    int i = orig.indexOf(cell.getStringCellValue());
                    if (i >= 0) {
                        cell.setCellValue(dest.get(i));
                    }
                }
            }
        }
    }
    
    public static void copyProperties(Object dest, Object orig) throws Exception {
        for(Field origField : orig.getClass().getDeclaredFields()) {
            for(Field destField : dest.getClass().getDeclaredFields()) {
                if (destField.getName().equals(origField.getName())) {
                    destField.setAccessible(true);
                    origField.setAccessible(true);
                    destField.set(dest, origField.get(orig));
                }
            }
        }
    }
    
    /**
     * bean字段值保存到string数组
     * 
     * @param bean
     * @return
     */
    public static List<String[]> beanToStringArray(Object bean) {
        List<String[]> ret = new ArrayList<String[]>();
        try {
            Field[] fields = bean.getClass().getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                String[] fv = new String[2];
                fv[0] = field.getName();
                fv[1] = field.get(bean) + "";
                ret.add(fv);
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return ret;
    }
    
    /**
     * 数组存excel并纵向合并有相同key的单元格，key=前面所有单元格内容+当前单元格内容
     * 
     * @param workbook
     * @param sheetName
     * @param colHeads
     * @param list
     * @return
     */
    public static void listToSheetWithVMerge(HSSFWorkbook workbook, String sheetName, String[] colHeads, List<?> list) {
        try {
            if (workbook == null) return;
            HSSFSheet sheet = workbook.createSheet();
            if (sheetName != null && !sheetName.isEmpty()) {
                workbook.setSheetName(workbook.getSheetIndex(sheet), sheetName);
            }
            if (list == null || list.size() == 0) {
                return;
            }
            List<List<String[]>> data = new ArrayList<List<String[]>>();
            // 预生成数据
            for (Object bean : list) {
                List<String[]> fvs = beanToStringArray(bean);
                StringBuilder key = new StringBuilder();
                for (String[] fv : fvs) {
                    fv[0] = key.append('-').append(fv[1]).toString();
                }
                data.add(fvs);
            }

            // 预生成列标题
            if (colHeads == null) {
                Field[] fields = list.get(0).getClass().getDeclaredFields();
                colHeads = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    colHeads[i] = fields[i].getName();
                }
            }
            
            // 创建标题行
            
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setWrapText(true);
            
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            HSSFFont font = workbook.createFont();
            font.setFontName("黑体");
            font.setBold(true);
            font.setFontHeightInPoints((short) 16);
            cellStyle.setFont(font);
            
            cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
            
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            
            HSSFRow headRow = sheet.createRow(0);
            for (int i = 0; i < colHeads.length; i++) {
                HSSFCell cell = headRow.createCell(i);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(colHeads[i]);
                
                cell.setCellStyle(cellStyle); 
            }
            
            // 预生成所有行列
            List<HSSFRow> dataRows = new ArrayList<HSSFRow>();
            for (int i = 1; i <= list.size(); i++) {
                HSSFRow dataRow = sheet.createRow(i);
                for(int j = 0; j < colHeads.length; j++) {
                    HSSFCell cell = dataRow.createCell(j);
                    cell.setCellType(CellType.STRING);
                }
                dataRows.add(dataRow);
            }

            // 纵向填充，合并单元格
            
            cellStyle = workbook.createCellStyle();
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            
            for (int vi = 0; vi < colHeads.length; vi++) {
                int mergeVStart = 1; // 纵向合并起始
                int mergeVEnd = mergeVStart; // 纵向合并结束
                String preKey = ""; // 判断单元格内容是否相同的依据key
                CellRangeAddress region = null;
                for (int hi = 0; hi < dataRows.size(); hi++) {
                    String[] fv = data.get(hi).get(vi);
                    HSSFRow dataRow = dataRows.get(hi);
                    HSSFCell cell = dataRow.getCell(vi);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(fv[1]);
                    
                    if (StringUtils.isEmpty(preKey)) {
                        preKey = fv[0];
                    } else if (preKey.equals(fv[0])) {
                        mergeVEnd++;
                    } 
                    if (!preKey.equals(fv[0]) || hi == dataRows.size() - 1) {
                        if (mergeVStart != mergeVEnd) {
                            region = new CellRangeAddress(mergeVStart, mergeVEnd, vi, vi);
                            sheet.addMergedRegion(region);
                        }
                        preKey = fv[0];
                        mergeVStart = mergeVEnd + 1;
                        mergeVEnd = mergeVStart;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void writeWorkbookToFile(String file, HSSFWorkbook workbook) {
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数组到sheet
     * 
     * @param workbook
     * @param sheetName
     * @param list
     */
    public static void listToSheet(HSSFWorkbook workbook, String sheetName, List<?> list) {
        try {
            if (workbook == null) return;
            HSSFSheet sheet = workbook.createSheet();
            if (sheetName != null && !sheetName.isEmpty()) {
                workbook.setSheetName(workbook.getSheetIndex(sheet), sheetName);
            }
            
            if (list == null || list.size() == 0) return;
            
            // 写标题行
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow(0);

            // 通过反射获取bean字段作为标题行
            Field[] fields = list.get(0).getClass().getDeclaredFields();
            int i = 0;
            for (Field f : fields) {
                // 在索引i的位置创建单元格（左上端）
                HSSFCell cell = row.createCell(i);
                // 定义单元格为字符串类型
                cell.setCellType(CellType.STRING);
                // 在单元格中输入一些内容
                cell.setCellValue(f.getName());
                i++;
            }

            // 获取其他行数据
            for (int j = 0; j < list.size(); j++) {
                HSSFRow dataRow = sheet.createRow(j + 1);
                Object bean = list.get(j);
                for (int k = 0; k < fields.length; k++) {
                    // 在索引k的位置创建单元格（左上端）
                    HSSFCell cell = dataRow.createCell(k);
                    // 定义单元格为字符串类型
                    cell.setCellType(CellType.STRING);
                    fields[k].setAccessible(true);
                    cell.setCellValue(fields[k].get(bean) + "");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setColWidth(HSSFWorkbook workbook, int sheetIndex, int[] colIndexs, int[] widths) {
        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        for(int i = 0; i < colIndexs.length; i++) {
            sheet.setColumnWidth(colIndexs[i], widths[i]);
        }
    }

}

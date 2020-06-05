package cn.net.bhe.utils.main;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对Microsoft Office格式文件读和写的功能。
 */
public class POIUtils {
    static Logger log = LoggerFactory.getLogger(POIUtils.class);
    
    /**
     * 替换内容，只替换第一个表格
     * 
     * @param workbook
     * @param orig
     * @param dest
     */
    public static void replaceCellContent(HSSFWorkbook workbook, List<String> orig, List<String> dest) {
        Iterator<Row> rows = workbook.getSheetAt(0).iterator();
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
     * @param colHeads
     * @param list
     * @param hiddenCols
     * @return
     */
    public static HSSFWorkbook listToWorkbookWithVMerge(String[] colHeads, List<?> list, int[] hiddenCols) {
        if (list == null || list.size() == 0) return null;
        List<List<String[]>> data = new ArrayList<List<String[]>>();
        try {
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
            
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            CellStyle vAlignCenter = workbook.createCellStyle();
            vAlignCenter.setVerticalAlignment(VerticalAlignment.CENTER);

            // 创建标题行
            HSSFRow headRow = sheet.createRow(0);
            for (int i = 0; i < colHeads.length; i++) {
                HSSFCell cell = headRow.createCell(i);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(colHeads[i]);
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
            for (int vi = 0; vi < colHeads.length; vi++) {
                int mergeVStart = 1; // 纵向合并起始
                int mergeVEnd = mergeVStart; // 纵向合并结束
                String preKey = ""; // 判断单元格内容是否相同的依据key
                CellRangeAddress region = null;
                for (int hi = 0; hi < dataRows.size(); hi++) {
                    String[] fv = data.get(hi).get(vi);
                    HSSFRow dataRow = dataRows.get(hi);
                    HSSFCell cell = dataRow.getCell(vi);
                    cell.setCellValue(fv[1]);
                    cell.setCellStyle(vAlignCenter);
                    
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
            
            if (hiddenCols != null) {
                for (int i : hiddenCols) {
                    sheet.setColumnHidden(i, true);
                }
            }
            return workbook;
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
     * 数组存excel
     * 
     * @param file
     * @param list
     */
    public static void listToExcel(String file, List<?> list) {
        if (list == null || list.size() == 0) return;
        try {
            // 创建新的Excel工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            HSSFSheet sheet = workbook.createSheet();
            
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

            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(file);
            // 把相应的Excel工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            // 关闭workbook
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

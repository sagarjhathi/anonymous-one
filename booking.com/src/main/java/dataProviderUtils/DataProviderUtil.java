package dataProviderUtils;
import excelUtils.ExcelUtil;

import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DataProviderUtil {

    // ========================
    // ARRAY BASED DATA
    // ========================
    public static Object[][] getDataAsArray(String file, String sheetName) {

        ExcelUtil excel = new ExcelUtil(file);
        excel.setSheet(sheetName);

        Sheet sheet = excel.getSheet(); // we expose this

        DataFormatter formatter = new DataFormatter();

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            for (int j = 0; j < colCount; j++) {

                Cell cell = row.getCell(j);
                data[i - 1][j] = formatter.formatCellValue(cell);
            }
        }

        excel.close();
        return data;
    }

    // ========================
    // MAP BASED DATA
    // ========================
    public static Object[][] getDataAsMap(String file, String sheetName) {

        ExcelUtil excel = new ExcelUtil(file);
        excel.setSheet(sheetName);

        Sheet sheet = excel.getSheet();

        DataFormatter formatter = new DataFormatter();

        Row header = sheet.getRow(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = header.getPhysicalNumberOfCells();

        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 1; i <= rowCount; i++) {

            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> map = new HashMap<>();

            for (int j = 0; j < colCount; j++) {

                String key = formatter.formatCellValue(header.getCell(j));
                String value = formatter.formatCellValue(row.getCell(j));

                // store only non-empty values
                if (value != null && !value.trim().isEmpty()) {
                    map.put(key, value);
                }
            }

            list.add(map);
        }

        excel.close();

        // convert to Object[][]
        Object[][] arr = new Object[list.size()][1];

        for (int i = 0; i < list.size(); i++) {
            arr[i][0] = list.get(i);
        }

        return arr;
    }
}
package excelUtils;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	
	
	Workbook workbook ;
    Sheet sheet;
	String filePath;
	String outputPath;
	
	
	
	public ExcelUtil(String filePath){
		this.filePath=filePath;
		
		try {
			
			InputStream is=getClass().getClassLoader().getResourceAsStream(filePath);
			if(is==null) {
				throw new RuntimeException("File not found: " + filePath);
			}
			
			 workbook = WorkbookFactory.create(is);
			
		}catch(Exception  e) {
			e.printStackTrace();
		}
	}
	
	
	
		public void setSheet(String sheetName) {
			sheet=workbook.getSheet(sheetName);
			if(sheet==null) {
				throw new RuntimeException("Sheet not found: " + sheetName);
			}
		}
		
		public String getCellData(int rowNum , int colNum) {
			try {
				
				Row row=sheet.getRow(rowNum);
				Cell cell=row.getCell(colNum);
				
				DataFormatter formatter= new DataFormatter();
				return formatter.formatCellValue(cell);
				
			}catch(Exception e) {
				return "";
			}
		}
		
		
		public String getCellData(int rowNum, String columnName) {
			int colIndex=getColumnIndex(columnName);
			return getCellData(rowNum,colIndex);
		}
		
		public int getColumnIndex(String columnName) {
			Row header= sheet.getRow(0);
			
			for(int i=0;i<header.getLastCellNum();i++) {
				String value= header.getCell(i).toString();
				
				if(value.equals(columnName)) {
					return i;
				}
			}
			
			 throw new RuntimeException("Column not found: " + columnName);
		}
		
		public int getPhysicalRowCount() {
			return sheet.getPhysicalNumberOfRows();
				
		}
		
		
		public int getLastRowNumCount() {
	        return sheet.getLastRowNum();
	    }
		
		
		public int getLastCellColCount() {
			
			return sheet.getRow(0).getLastCellNum();
		}
		
		
		public void setCellData(int rowNum , int colNum , String value) {
			try {
				Row row=sheet.getRow(rowNum);
				 if (row == null) row = sheet.createRow(rowNum);
				 
				     Cell cell = row.getCell(colNum);
			        if (cell == null) cell = row.createCell(colNum);
			        
			        cell.setCellValue(value);
			}catch(Exception e) {
				e.printStackTrace();
			}
		        
		}
		
		public void save(String outputPath) {
			if (outputPath == null || outputPath.isEmpty()) {
		        throw new RuntimeException("Output path not set. Call setOutputPath() before save.");
		    }
			 this.outputPath = outputPath;
			 
			 try {
				 FileOutputStream fos= new FileOutputStream(outputPath);
				 workbook.write(fos);
			 }catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		
		
		public void close() {
	        try {
	            workbook.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
	
	
			 
		}


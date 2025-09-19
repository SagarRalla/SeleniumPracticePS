package com.optum.rxcomet.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {


    public static HSSFSheet getSheet(String filepath, String sheetName){
        HSSFSheet sheet = null;
        try {
            FileInputStream file = new FileInputStream(filepath);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        }catch (Throwable t){

        }
        return sheet;
    }
    public static int getRow(String filepath, String cellContent){
        try {
            FileInputStream file = new FileInputStream(filepath);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        if (cell.getRichStringCellValue().getString().trim().equals(cellContent) ) {
                            return row.getRowNum();
                        }
                    }
                }
            }


        }catch (Throwable t){
            t.printStackTrace();
        }
        return -1;
    }
    
    public static int getRowNumberFromExcel(String filePath, String excelValue) throws IOException 
    {
    	FileInputStream fis = new FileInputStream(filePath);
    	HSSFWorkbook workBook = new HSSFWorkbook(fis);
    	Sheet sheet = workBook.getSheet("data");
    	int rowNumber = 0;
    	for(Row row : sheet)
    	{
    		Cell cell = row.getCell(0);
    		if(cell!=null)
    		{
    			String value = cell.getStringCellValue();
    			if(value.equals(excelValue))
    			{
    				rowNumber = row.getRowNum();
    			}
    		}
    	}
		return rowNumber;
    }

    public static int getRow(String filepath, String cellContent, short cellColor){
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
//                        if (cell.getRichStringCellValue().getString().trim().equals(cellContent)
//                                && cell.getCellStyle().getFillForegroundColor() == cellColor) {
                        if (cell.getRichStringCellValue().getString().trim().equals(cellContent)
                                && cell.getCellStyle().getFillForegroundColor() == cellColor) {
                            return row.getRowNum();
                        }
                    }
                }
            }


        }catch (Throwable t){
            t.printStackTrace();
        }
        return -1;
    }

    public static int getRow(String filepath, String cellContent, int nthNumberMatch){
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            int match = 0;
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                            match++;
                            if(match==nthNumberMatch)
                                return row.getRowNum();
                        }
                    }
                }
            }


        }catch (Throwable t){
            t.printStackTrace();
        }
        return -1;
    }

    public static String getAnswer(String fileName, String cellContent){
        try {
            String filePath = System.getProperty("user.dir") +
                    "/src/test/resources/data/" + fileName + ".xls";
            FileInputStream file = new FileInputStream(filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            Row row = sheet.getRow(getRow(filePath, cellContent));
            return row.getCell(1).getStringCellValue();
        }catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllHeaders(String fileName){
        List<String> list = new ArrayList<>();
        try {
            String filePath = System.getProperty("user.dir") +
                    "/src/test/resources/data/" + fileName + ".xls";
            FileInputStream file = new FileInputStream(filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING && cell.getCellStyle().getFillForegroundColor()==56) {
                        list.add(cell.getStringCellValue());
                    }
                }
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        return list;
    }
    public static String getAnswer(String fileName, String cellContent, int nthMatch){
        try {
            String filePath = System.getProperty("user.dir") +
                    "/src/test/resources/data/" + fileName + ".xls";
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            int match = 0;
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                            match++;
                            if(match==nthMatch)
                                return row.getCell(1).getRichStringCellValue().getString();
                        }
                    }
                }
            }

        }catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }
    
    public static String getAnswer(String fileName, int nthMatch, String cellContent)
    {
        try 
        {
            String filePath = System.getProperty("user.dir") +
                    "/src/test/resources/data/" + fileName + ".xls";
            FileInputStream file = new FileInputStream(new File(filePath));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet("data");
            int match = 0;
            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING) {
                        if (cell.getRichStringCellValue().getString().equals(cellContent)) {
                            if(row.getRowNum()==nthMatch) {
                            	if(cellContent.equals("Benefits Plan Name")||cellContent.equals("Clinical Plan Name"))
                            		 return row.getCell(1).getRichStringCellValue().getString()+"v2";
                                return row.getCell(1).getRichStringCellValue().getString();
                            }
                        }
                    }
                }
            }

        }catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }
}

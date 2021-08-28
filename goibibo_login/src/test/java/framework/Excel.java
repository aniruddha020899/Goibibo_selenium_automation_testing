package framework;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	XSSFWorkbook wb;
	XSSFSheet sh;

	public Excel(String pathWithFileName) throws Exception {

		wb = new XSSFWorkbook(new FileInputStream(pathWithFileName));
		sh = wb.getSheet("Sheet1");
	}

	public String readData(String sheetName, int row, int col) {
		String data = sh.getRow(row).getCell(col).toString();
		return data;
	}

	public void writeData(String sheetName, int row, int col, String value) throws Exception {
		sh.getRow(row).createCell(col).setCellValue(value);
		wb.write(new FileOutputStream("C:\\Users\\aniru\\OneDrive\\Desktop\\sample1.xlsx"));
	}
	
	public void writeData1(String sheetName, int row, int col, String value) throws Exception {
		sh.getRow(row).createCell(col).setCellValue(value);
		wb.write(new FileOutputStream("C:\\Users\\aniru\\OneDrive\\Desktop\\login.xlsx"));
	}

}
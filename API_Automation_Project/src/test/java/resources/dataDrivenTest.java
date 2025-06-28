package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.common.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test; 
public class dataDrivenTest {
	
	
	//identify testcase column by scanning row
	//once column is identifed then idenitfy entire 
	@Test
	public ArrayList<String> excelDriverTestCase(String testcasename) throws IOException {
		
		ArrayList<String> a =new ArrayList<String>();
		
		FileInputStream fin = new FileInputStream("C:\\Users\\omgoyal\\API_Automation\\API_Automation_Project\\src\\test\\resources\\API_Testing_Data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		int sheets =workbook.getNumberOfSheets();
		
	
		
		for(int i=0;i<sheets;i++) {
			
			if(workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
			XSSFSheet sheet=workbook.getSheetAt(i);
			Iterator<Row> rows = sheet.iterator();//sheet is collection of rows
			
			Row firstrow =rows.next();
			Iterator<Cell> cell=firstrow.cellIterator();//row is collection of cells
			int k=0;
			int column=0;
			//hasNext is used for nextcell value is present or not check via while loop
			while(cell.hasNext()) {
				
				
				
				//for movement to next cell if value is present
				Cell value=cell.next();
				
			
					
				
				if(value.getStringCellValue().equalsIgnoreCase("Testcases")){
					
					column=k;
					
				}
				k++;
			}
			
			System.out.println(column);	
			
			
			
			while(rows.hasNext()) {
				
				Row row=rows.next();
				
				if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
					
					Iterator<Cell> cv=row.cellIterator();
					
					
					
					while(cv.hasNext()) {
						
						Cell c =cv.next();
						
						if(c.getCellType()==CellType.STRING) {
						
						a.add(c.getStringCellValue());
					} else if(c.getCellType()==CellType.NUMERIC) {
						
						a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						
					
					}
						
					}
				}
					
				
			}
			
			}
			
			
			
			
		}
		
		return a;
		
	}

}

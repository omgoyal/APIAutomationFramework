import java.io.IOException;
import java.util.ArrayList;
import org.testng.annotations.Test; 

import API_Automation.API_Automation_Project.dataDrivenTest;

public class excelTestExample {
	
	@Test
	public void testCaseExcel() throws IOException {
		
		
		dataDrivenTest data = new dataDrivenTest();
		
		ArrayList<String> arr=data.excelDriverTestCase("Purchase");
		
		System.out.println(arr.get(0));
		System.out.println(arr.get(1));
	}
	
	

}

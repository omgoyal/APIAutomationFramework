package API_Automation.API_Automation_Project;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.*;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class excelDriven extends Utils {
	
	
	@Test
	public void excelDrivenTest() throws IOException {
		
		dataDrivenTest d = new dataDrivenTest();
		ArrayList<String> arr=d.excelDriverTestCase("RestAddBook");
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		
		hm.put("name", arr.get(1));
		hm.put("isbn", arr.get(2));
		hm.put("aisle", arr.get(3));
		hm.put("author", arr.get(3));
		
		/*HashMap<String,Object> hmnested = new HashMap<String,Object>();
		hm.put("lat", "24.3");
		hm.put("lng", "25.5");
		hm.put("location", hmnested);*/

		
		RestAssured.baseURI="http://216.10.245.166";
		
		Response res=given().header("Content-Type","application/json").body(hm)
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response();
		
		String idvalue=Utils.getJsonPath(res, "ID");
		System.out.println(idvalue);
		
	}

}

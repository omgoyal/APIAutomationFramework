package API_Automation.API_Automation_Project.files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="BooksData")
	public void AddBook(String isbn,String aisle) {
		
RestAssured.baseURI="http://216.10.245.166";
String response=given().log().all().header("Content-Type","application/json")
		.body(Payload.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		  System.out.println("Response: " + response);
		    
		  
		JsonPath js=ReusableMethods.rawToJson(response);
		
		String id=js.getString("ID");
		System.out.println(id);
		 
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getdata() {
		return new Object [][] {{"alz","689"},{"adf","456"},{"ahj","567"}};
	}

}

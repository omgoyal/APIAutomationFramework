package API_Automation.API_Automation_Project;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

@Test
public class serializedTest {
	
	
	public void serializationTest() {
		
		addPlace p= new addPlace();
		
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setName("Frontline house");
		p.setWebsite("http://google.com");
		List<String> list=new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		p.setTypes(list);
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		p.setLocation(location);
		
		
	
	
	RestAssured.baseURI   ="https://rahulshettyacademy.com";
	
	Response response=given().log().all().queryParam("key","qaclick123")
	.body(p)
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).extract().response();
	
	System.out.println(response.asString());
	
	}
	
}

	

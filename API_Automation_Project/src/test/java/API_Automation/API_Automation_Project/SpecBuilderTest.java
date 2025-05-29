package API_Automation.API_Automation_Project;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

@Test
public class SpecBuilderTest {
	
	
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
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON).build();
		
		
	  ResponseSpecification response  = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	RequestSpecification res=given().spec(req)
	.body(p);
	
	
	Response responsevalue=res.when().post("/maps/api/place/add/json")
	.then().spec(response).extract().response();
	
	System.out.println(responsevalue.asString());
	
	}
	
}

	

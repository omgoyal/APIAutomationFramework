package API_Automation.API_Automation_Project;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.Assert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import API_Automation.API_Automation_Project.files.Payload;
import API_Automation.API_Automation_Project.files.ReusableMethods;

public class Basic {

	
	public static void main(String[] args) throws IOException {
		

		//Validate if Add place API is working expecting
		
		//given --all input details 
		//when --submit specific api --resource http method 
		//then --validate the response
		//content of the file to String->content of file can convert into Byte>Byte data to String
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Files.readAllBytes(Paths.get("C:\\Users\\omgoyal\\API_Automation\\API_Automation_Project\\src\\test\\resources\\addplace.json")))
		.when().post("/maps/api/place/add/json")
		.then()
		.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js =new JsonPath(response);//for parsing json
		
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
	
		
		//update place
		
		String newaddress="70 Summer Walk ,Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get place 
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address",equalTo(newaddress)).extract().response().asString();
			
		
		JsonPath jsonpath=ReusableMethods.rawToJson(getPlaceResponse);
		//JsonPath jsonpath=new JsonPath(getPlaceResponse);
		
		String actualnewAddress=jsonpath.getString("address");
		
		System.out.println(actualnewAddress);
		
		Assert.assertEquals(actualnewAddress,newaddress);
	}

}

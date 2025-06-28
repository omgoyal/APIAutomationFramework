package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;
	import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


public class stepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification response;
	Response responsevalue;
	//Define static that variable will be share across all test cases(Scenarios) for that particular run for ex- Scenario-1 will add place and get placeid value then Scenario-2 refer delete the same place using 1st scenatio place_id
	static String place_id_value;
	 TestDataBuild  testdatabuild = new TestDataBuild();
	
	
	 @Given("Add Place Payload with {string} {string} {string}")
	    public void addPlacePayloadwith(String name,String launguage, String address) throws IOException {
		 
		 //Test Data 
		  	
		 res=given().spec(requestSpecification())
		.body(testdatabuild.addPlacePayload(name, launguage, address));
		 
	    }

	    @When("user calls {string} with {string} HTTP request")
	    public void user_calls_with_http_request(String apiresource,String apimethod) {
	    	
	    
	    	
	    	 APIResources resourceAPI = APIResources.valueOf(apiresource); 
	    System.out.println(resourceAPI.getResource());
	    
	    
	    if(apimethod.equalsIgnoreCase("POST")) 
	    	 responsevalue=res.when().post(resourceAPI.getResource());
	    else if(apimethod.equalsIgnoreCase("GET"))
	    	responsevalue=res.when().get(resourceAPI.getResource());
	    else if(apimethod.equalsIgnoreCase("DELETE"))
	    	responsevalue=res.when().delete(resourceAPI.getResource());
	    
	    
	    }

	    @Then("the API call success status code {int}")
	    public void the_api_call_success_status_code(Integer statuscode) {
	    	
	    	assertEquals(responsevalue.getStatusCode(),statuscode.intValue());
	        
	    }

	    @Then("{string} in response body is {string}")
	    public void status_in_response_body_should_be_ok(String key, String expectedvalue) {
	      
	    	
	    	//String actualvalue=js.get(key).toString();
	    	
	    	assertEquals(getJsonPath(responsevalue,key),expectedvalue);
	    	
	    }
	    
	    

	    @Then("verify place_id created maps to {string} using {string} with {string} HTTP request")
	    public void verify_place_id_created_maps_to_using_with_http_request(String expectednamevalue, String apiresource, String apimethod) throws IOException {
	      
	    	//requestspec
	    	
	    	place_id_value=getJsonPath(responsevalue,"place_id");
	    	res=given().spec(requestSpecification()).queryParam("place_id", place_id_value);
	    	user_calls_with_http_request(apiresource,apimethod);
	    	String nameactualvalue=getJsonPath(responsevalue,"name");
	         System.out.println(nameactualvalue);
	    	assertEquals(nameactualvalue,expectednamevalue);
	    }
	    
	   

	    @Given("Delete Place Payload")
	    public void delete_place_payload() throws IOException {
	        
	    	res=given().spec(requestSpecification()).body(testdatabuild.deletePlacePayload(place_id_value));
	    }




	}



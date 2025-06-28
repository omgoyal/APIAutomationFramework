package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	
	 public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException  {

			
			if(req==null) {
		
		File logFile = new File( "C:\\Users\\omgoyal\\API_Automation\\API_Automation_Project\\logging.txt");
		
		System.out.println("Log file path: " + logFile.getAbsolutePath());
		

		
		PrintStream log = new PrintStream(new FileOutputStream(logFile, true));

	        // Set the base URI for REST Assured
	       

	        // Build the request specification with logging filters
	        req = new RequestSpecBuilder()
	                .setBaseUri(getGlobalValue("baseURL"))
	                .addQueryParam("key", "qaclick123")
	                .addFilter(new RequestLoggingFilter(log)) // Log requests to the file
	                .addFilter(new ResponseLoggingFilter(log)) // Log responses to the file
	                .setContentType("application/json")
	                .build();
	        
		
		
	     
		return req;	     
		} {
			return req;
		}
		
		
}
	
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\omgoyal\\API_Automation\\API_Automation_Project\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	  
		
	}
	
	
	
	public static String getJsonPath(Response response, String key) {
		String resp =response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key);
		
	}
}

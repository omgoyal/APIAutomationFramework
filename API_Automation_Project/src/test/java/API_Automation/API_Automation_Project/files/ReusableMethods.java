package API_Automation.API_Automation_Project.files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String response) {
		
		JsonPath js1= new JsonPath(response);
		
		return js1;
		
	}

}

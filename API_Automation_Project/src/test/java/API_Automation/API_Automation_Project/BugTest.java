package API_Automation.API_Automation_Project;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://osgoyal.atlassian.net/";
		String response=given().header("Content-Type","application/json")
		.header("Authorization","Basic b3Nnb3lhbEBnbWFpbC5jb206QVRBVFQzeEZmR0YweTkxUEk1VnVUSDdJc21POTZBRVluRnZMbFZ4a0hKbHRiZWY4RDVVbjY0U0RMWDlFcTdoZ0JSRHdZTDVtT3hScFIxb2VYYk1pNlFFZXd3ckhQQ3JCSXJWM0ltU19fbUIzZE5xMURCZkhPQ29ETTJRQURRbkVRcUFiZjB2aUVXd1RSVFlpMWw2SnAzU09rLXRrX2tPSkczR3FSX29aRmZ2YklNbkh5NEdEbkZVPThENEE1QzQ5")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Drop-down are not opening...\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "").log().all()
		.post("/rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js= new JsonPath(response);
		String issueid=js.getString("id");
		System.out.println("jira issue id:"+issueid);
		
		given()
		.pathParam("key",issueid )
		.header("Authorization","Basic b3Nnb3lhbEBnbWFpbC5jb206QVRBVFQzeEZmR0YweTkxUEk1VnVUSDdJc21POTZBRVluRnZMbFZ4a0hKbHRiZWY4RDVVbjY0U0RMWDlFcTdoZ0JSRHdZTDVtT3hScFIxb2VYYk1pNlFFZXd3ckhQQ3JCSXJWM0ltU19fbUIzZE5xMURCZkhPQ29ETTJRQURRbkVRcUFiZjB2aUVXd1RSVFlpMWw2SnAzU09rLXRrX2tPSkczR3FSX29aRmZ2YklNbkh5NEdEbkZVPThENEE1QzQ5")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("C:\\Users\\omgoyal\\API_Automation\\API_Automation_Project\\src\\test\\resources\\File_Type.png")).log().all()
		.post("/rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);

	}

}

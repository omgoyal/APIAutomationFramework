package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		//write a code that will give you place_id
		//execute this code only when place_id is null
		
		stepDefination  stepdef= new stepDefination();
		
		//Static variable access via classname as variable is tied with class instead of object
		if(stepDefination.place_id_value==null) {
		stepdef.addPlacePayloadwith("Om","English","India");
		stepdef.user_calls_with_http_request("addPlaceAPI","POST");
		stepdef.verify_place_id_created_maps_to_using_with_http_request("Om","getPlaceAPI","Get");
		}
		
	}

}

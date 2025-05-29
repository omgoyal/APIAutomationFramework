package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {
	
	public addPlace addPlacePayload(String name, String launguage, String address) {
		
		 addPlace p= new addPlace();
			
			p.setAccuracy(50);
			p.setAddress(address);
			p.setLanguage(launguage);
			p.setPhone_number("(+91) 983 893 3937");
			p.setName(name);
			p.setWebsite("http://google.com");
			List<String> list=new ArrayList<String>();
			list.add("shoe park");
			list.add("shop");
			p.setTypes(list);
			
			Location location = new Location();
			location.setLat(-38.383494);
			location.setLng(33.427362);
			
			p.setLocation(location);
			
			return p;
	}
	
	public String deletePlacePayload(String placeid) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}\r\n"
				+ "";
	}

}
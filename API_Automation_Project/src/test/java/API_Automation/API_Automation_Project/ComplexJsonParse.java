package API_Automation.API_Automation_Project;

import org.testng.Assert;

import API_Automation.API_Automation_Project.files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//Print no of courses returned by api
		int count=js.getInt("courses.size()");
		System.out.println("no of courses returned by api:" +count);
		
		//Print purchase amount 
		
		int totalamount=js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount: "+totalamount);
		
		//Print title of first course 
		String firsttitle=js.getString("courses[0].title");
		System.out.println(firsttitle);
		
		//Print all course title with respective prices 
		for(int i=0;i<count;i++) {
		System.out.println(js.getString("courses["+i+"].title"));
		System.out.println(js.getInt("courses["+i+"].price"));
		}
		
		
		//Print no of copies sold by RPA 
		
		for(int i=0;i<count;i++) {
			
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")){
				
				System.out.println("RPA  course copies:"+js.getInt("courses["+i+"].copies"));
				break;
			}
			
		}
		
		//Verify if all of course price and copies with purchase amount 
		
		int actualPurchaseAmount=0;
		for(int i=0;i<count;i++) {
		
			int itemprice=js.getInt("courses["+i+"].price");
			int itemcopies=js.getInt("courses["+i+"].copies");
			
			actualPurchaseAmount=actualPurchaseAmount+(itemprice*itemcopies);
		}
		
		System.out.println("Actual Purchase amount:"+actualPurchaseAmount);
		
		Assert.assertEquals(actualPurchaseAmount, totalamount);
		
	}

}

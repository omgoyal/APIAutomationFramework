package API_Automation.API_Automation_Project;



import API_Automation.API_Automation_Project_Pojo.LoginRequest;
import API_Automation.API_Automation_Project_Pojo.LoginResponse;
import API_Automation.API_Automation_Project_Pojo.OrderDetails;
import API_Automation.API_Automation_Project_Pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ECommerceAPITest {
	
	
	@Test
	public void ECommerceAppAPITest() {
	
		//SSL Certification to bypass -given().relaxedHTTPSValidation()
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
	
	ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	LoginRequest loginreq= new LoginRequest();
	
	loginreq.setUserEmail("osgoyal@gmail.com");
	loginreq.setUserPassword("Rahulshetty@1992");
	
	RequestSpecification reqLogin = given().log().all().spec(req).body(loginreq);
	
   LoginResponse loginresponse=reqLogin.when().post("/api/ecom/auth/login").then().log().all().spec(response).extract().response().as(LoginResponse.class);
   
   
   String token=loginresponse.getToken();
   System.out.println(loginresponse.getToken());
   String userid=loginresponse.getUserId();
   
   System.out.println(loginresponse.getUserId());
   
   
   //Add product 
   
   RequestSpecification addProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
   
   ResponseSpecification resProduct= new ResponseSpecBuilder().expectStatusCode(201).build();
   
      
   
   RequestSpecification reqAddProduct=given().log().all().spec(addProductBaseReq).formParam("productName", "Laptop")
   .formParam("productAddedBy", userid)
   .formParam("productCategory", "Electronic")
   .formParam("productSubCategory", "Machine")
   .formParam("productPrice", "45000")
   .formParam("productDescription", "Lenevo")
   .formParam("productFor", "Technology")
   .multiPart("productImage",new File("C:\\Users\\omgoyal\\API_Automation\\API_Automation_Project\\src\\test\\resources\\File_Type.png"));
   
    String addProductResponse= reqAddProduct.when().post("api/ecom/product/add-product").then().log().all().spec(resProduct).extract().response().asString();
    
    JsonPath js = new JsonPath(addProductResponse);
    
    String productIdvalue=js.get("productId");
     String messagevalue= js.get("message");
    
     System.out.println(productIdvalue);
     System.out.println(messagevalue);
     
     
     //Create Order 
     
     RequestSpecification createProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization", token).build();
     
     OrderDetails orderdetail = new OrderDetails();
     Orders order= new Orders();
     
     orderdetail.setCountry("India");
     orderdetail.setProductOrderedId(productIdvalue);
     
     List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
     orderDetailList.add(orderdetail);
     
     order.setOrders(orderDetailList);
     
     RequestSpecification createOrderReq=given().log().all().spec(createProductBaseReq).body(order);
     
    String responseAddOrder= createOrderReq.when().post("api/ecom/order/create-order").then().log().all().spec(resProduct).extract().response().asString();
		System.out.println(responseAddOrder);
    
		
		
	//Delete Product 
		
	RequestSpecification deleteProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization", token).build();
		
	RequestSpecification deleteproductReq=given().log().all().spec(deleteProductBaseReq).pathParam("productId", productIdvalue);
	
	
	 ResponseSpecification resProductDelete= new ResponseSpecBuilder().expectStatusCode(200).build();
	
	String responsedeleteproduct=deleteproductReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().spec(resProductDelete).extract().response().asString();
		
	JsonPath jsondeleteresponseproduct = new JsonPath(responsedeleteproduct);
	
	String messageval=jsondeleteresponseproduct.get("message");
	
	System.out.println(messageval);
	
	Assert.assertEquals("Product Deleted Successfully", messageval);
	
	
	
	}

}

package API_Automation.API_Automation_Project;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Collections;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import resources.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OAuth2_0Test  extends Utils  {
	
	Utils util;
	@Test
	public void OAuth2_0TestMethod() throws IOException, InterruptedException{
		
		//It will auotmatically download chormedriver exe from CFT dashboard URL
		
		/*WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=C:\\Users\\omgoyal\\AppData\\Local\\Google\\Chrome\\User Data");
		options.addArguments("profile-directory=Profile 1");
		options.setBinary("C:\\Users\\omgoyal\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
		ChromeDriver driver = new ChromeDriver(options);*/
	       
		/*WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		util =new Utils();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(util.getGlobalValue("email"));
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(util.getGlobalValue("password"));
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//div[@id='countryList']")).click();
		driver.findElement(By.xpath("//ul[@aria-label='Select a country']/li[@data-value='in']")).click();
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(util.getGlobalValue("mobileno"));
		driver.findElement(By.xpath("//span[text()='Send']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		//String url=driver.getCurrentUrl();*/
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AUJR-x5NH3KWBtyXopkUC-Wn7AjktwYtryyGeJIC1HHHpQNG5Ph7iay2Za9qgJVJVe3iSQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		String partialcode =url.split("code=")[1];
		String code= partialcode.split("&scope")[0];
		System.out.println(code);
		
		Response accessTokenResponse=given().urlEncodingEnabled(false)
				.log().all().queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token");
		
		String accesstokenvalue=getJsonPath(accessTokenResponse,"access_token");
		
		String response =given().queryParam("access_token", accesstokenvalue)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
	
		
		System.out.println(response);
		System.out.println("This is testing1");
		System.out.println("This is testing2");
		System.out.println("This is testing3");
		System.out.println("This is testing4");
		
		System.out.println("This is testing5");
		System.out.println("This is testing6");
		System.out.println("This is testing7");
	}

}

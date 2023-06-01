package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.OrderPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahulshettyacademy.PageObject.checkoutPage;
import rahulshettyacademy.PageObject.confirmatioPage;
import rahulshettyacademy.data.DataReader;
import rahulshettyacademy.testcomponent.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider ="getData", groups= {"purchase"})
	
	public void orderSubmitTest(HashMap<String,String> input) throws InterruptedException, IOException {
				
		ProductCatalogue productcatalogueobjectc = landingpageobject.loginApplication(input.get("email"), input.get("pwd"));
				List<WebElement> products = productcatalogueobjectc.getProductList();
		productcatalogueobjectc.addProductToCart(input.get("product"));
		CartPage cartpageobject = productcatalogueobjectc.goToCartPage();
		Boolean match = cartpageobject.verifyProductDisplay(input.get("product"));
		//Assert.assertTrue(match);// validations do not go in page object file only actions go there 
		Thread.sleep(5000);
		checkoutPage checkoutpageobject = cartpageobject.goToCheckout();
		Thread.sleep(3000);
		checkoutpageobject.selectCountry("Guinea");
		//checkoutpageobject.submitOrder();
		confirmatioPage confirmatiopageobject = checkoutpageobject.submitOrder();
		String confirmMessage = confirmatiopageobject.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(6000);
		checkoutpageobject.checkOut();	
		
		}
	//To verify that Zara coat 3 is displayed in order page 
	@Test(dependsOnMethods = {"submitOrder"}) 
		public void orderHistoryTest() throws InterruptedException {
		ProductCatalogue productcatalogueobjectc = landingpageobject.loginApplication("mariam.kaba@gmail.com", "Udemy2023");
		OrderPage orderpageobject =productcatalogueobjectc.goToOrderPage();
	Assert.assertTrue(orderpageobject.verifyOrderDisplay(productName));
	}
	
	
	
	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
	File source = 	ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
	FileUtils.copyFile(source, file );
	return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "mariam.kaba@gmail.com");
		map.put("pwd", "Udemy2023");
		map.put("product", "ZARA COAT 3");
		
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "sekou@gmail.com");
		map1.put("pwd", "Udemy2023");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String, String>> data = DataReader.getJsonDataToMap(System.getProperty("user.dir")+"//src\\test\\java\\rahulshettyacademy\\data\\purchaseOrder.json");
		//return new Object[][] {{map},{map1}};
		return new Object[] [] {{data.get(0)},{data.get(1)}};
		
		
		/*@DataProvider
		public Object[][] getData() {
		
		//return new Object [][] { {"mariam.kaba@gmail.com","Udemy2023","ZARA COAT 3"}, {"sekou@gmail.com","Udemy2023","ADIDAS ORIGINAL"} }  for @dapprovider;
		
	*/}
	
	
}

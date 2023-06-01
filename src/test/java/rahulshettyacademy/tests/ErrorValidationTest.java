package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahulshettyacademy.PageObject.checkoutPage;
import rahulshettyacademy.PageObject.confirmatioPage;
import rahulshettyacademy.testcomponent.BaseTest;
import rahulshettyacademy.testcomponent.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	
	public void loginErrorValidation() throws InterruptedException, IOException {
	
		
		
				 //div[@aria-label='Incorrect email or password.']
		 landingpageobject.loginApplication("mariam@gmail.com", "Udemy2023");// giving wrong username and pwd to fail the test 
	Assert.assertEquals("Incorrect email or password.",landingpageobject.getErrorMessage());
		
		
	}
@Test
	
	public void productErrorValidation() throws InterruptedException, IOException {
	
		String productName = "ZARA COAT 3";
		
				
		ProductCatalogue productcatalogueobjectc = landingpageobject.loginApplication("mariam.kaba@gmail.com","Udemy202" );
		//waitForElementToAppear(By FindBy);
		
		List<WebElement> products = productcatalogueobjectc.getProductList();
		
		productcatalogueobjectc.addProductToCart(productName);
		CartPage cartpageobject = productcatalogueobjectc.goToCartPage();
		Thread.sleep(5000);
		Boolean match = cartpageobject.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);// validations do not go in page object file only actions go there 
		
		
}
	
		

}

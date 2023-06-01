package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.LandingPage;
import rahulshettyacademy.PageObject.ProductCatalogue;
import rahulshettyacademy.PageObject.checkoutPage;
import rahulshettyacademy.PageObject.confirmatioPage;
import rahulshettyacademy.testcomponent.BaseTest;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingpageobject;// to have access to it through the class not just inside of a method 
	public  ProductCatalogue productcatalogueobjectc ;
	public confirmatioPage confirmatiopageobject ;
	
 @Given("I landed on ECommerce page")
 public void I_landed_on_ECommerce_page() throws IOException {
	 landingpageobject = launchApplication();
	 
 }
 
 //Given( logged in with userName  <name> and password <pwd>) we have to eplace name and pwe by resular expression (.+)
 
 @Given ("^Logged in with userName(.+) and password (.+)$")
 public void Logged_in__with_userName_and_password(String userName, String pwd) {
	 productcatalogueobjectc = landingpageobject.loginApplication(userName, pwd);
	 }
 @When("^I add product (.+) to cart$")
 public void I_add_product_to_cart(String productName) {
		List<WebElement> products = productcatalogueobjectc.getProductList();
		productcatalogueobjectc.addProductToCart(productName);
 }
 @When("^checkout (.+) and submit the order$")
 public void checkout_and_submit_the_order(String productName) throws InterruptedException {
	 CartPage cartpageobject = productcatalogueobjectc.goToCartPage();
		Boolean match = cartpageobject.verifyProductDisplay(productName);
		//Assert.assertTrue(match);// validations do not go in page object file only actions go there 
		Thread.sleep(5000);
		checkoutPage checkoutpageobject = cartpageobject.goToCheckout();
		Thread.sleep(3000);
		checkoutpageobject.selectCountry("Guinea");
		//checkoutpageobject.submitOrder();
		confirmatiopageobject = checkoutpageobject.submitOrder();
 }
 @Then ("{string} message is displayed on confirmation page") 
 public void message_displayed_On_confirmation_page(String string ) {
	 String confirmMessage = confirmatiopageobject.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string ));
		driver.close();
		
	 
 }
 @Then("^\"([^\"]*)\" message is diplayed$")
 public void something_message_is_diplayed(String strArg1) {
Assert.assertEquals(strArg1,landingpageobject.getErrorMessage());
driver.close();
 }
}

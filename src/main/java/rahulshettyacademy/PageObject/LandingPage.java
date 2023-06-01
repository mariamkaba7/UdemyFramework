package rahulshettyacademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	//creation of contructor
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		 
	}
	//WebElement userEmails= driver.findElement(By.id("userEmail"));
// page factory pattern
	
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement  userPassword;
	
	@FindBy(id="login")
	WebElement  submit;
	
	
	
	//By.id("userPassword"))
	// creation of action method
	public ProductCatalogue  loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productcatalogueobject = new ProductCatalogue(driver);
		return productcatalogueobject ;
		
	}
	
	//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		// let's use customized cssselector 
		@FindBy (css = "[class*='flyInOut']")
		WebElement  errorMessage ;
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	
	}
}

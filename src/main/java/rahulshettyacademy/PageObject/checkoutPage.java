package rahulshettyacademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public checkoutPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		 PageFactory.initElements(driver,this);// to initialize page factory 
	}

	
	//initilization in factory object 
	//driver.findElement(By.cssSelector("[placeholder='Select Country']"))
	@FindBy (css= "[placeholder='Select Country")
	WebElement country;
	
//	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry ;
	
	//driver.findElement(By.cssSelector(".action__submit"));
	@FindBy (css = ".action__submit")
	WebElement submit ;
	
	//By.cssSelector(".ta-results")
	By results = By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
		Thread.sleep(5000);
	}
	public confirmatioPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		submit.click();// once we click on submit we have confrimation page so we return that page here 
		return new confirmatioPage(driver);
		
	}
	public void checkOut() {
		driver.findElement(By.xpath("//button[normalize-space()='Sign Out']")).click();
		
	}
}

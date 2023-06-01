package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.PageObject.CartPage;
import rahulshettyacademy.PageObject.OrderPage;

public class AbstractComponent {
	// in abstract class we write all codes that are reussizable like implicite , explicite wait
	WebDriver driver;
	
 public AbstractComponent(WebDriver driver) {
	 this.driver=driver;
	 PageFactory.initElements(driver,this);// to initialize page factory 
		
	}

	// put this webelement in factory page :driver.findElement(By.cssSelector("[routerlink*='cart']")).click();// customize cssSelectors
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader ;
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader ; 
	
public void waitForElementToAppear(By findBY) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
 }

public void waitForWebElementToAppear(WebElement findBY) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(findBY));
 }
public void waitForElemetToDisappear(WebElement we) throws InterruptedException {
	Thread.sleep(2000);
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 //wait.until(ExpectedConditions.invisibilityOf(we));
}

public CartPage goToCartPage() {
	JavascriptExecutor js =(JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", cartHeader);
	//cartHeader.click();
	CartPage cartpageobject = new CartPage(driver);
	return cartpageobject;
}

public OrderPage goToOrderPage() {
	//JavascriptExecutor js =(JavascriptExecutor) driver;
	//js.executeScript("arguments[0].click();", cartHeader);
	orderHeader.click();
	OrderPage ordertpageobject = new OrderPage(driver);
return ordertpageobject;
}
}

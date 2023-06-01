package rahulshettyacademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	//driver.findElement(By.cssSelector(".totalRow button"))  convert in factory page .click();
	@FindBy (css =".totalRow button" )
	WebElement checkoutEle ;
	@FindBy (css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		 PageFactory.initElements(driver,this);// to initialize page factory 
	}

	public Boolean verifyProductDisplay(String productName) throws InterruptedException {
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
				return match ;
		
	}
	

	public checkoutPage goToCheckout() {
		checkoutEle.click();
		return new checkoutPage(driver);
	}
	

}

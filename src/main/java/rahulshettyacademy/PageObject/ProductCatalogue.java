package rahulshettyacademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	//creation of contructor
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		 
	}
	
// page factory pattern is possible with webelement like driver.findElement(By.id("name")
	//List<WebElement> products =	driver.findElements(By.xpath("//div/h5"));
		//@FindBy(xpath="//div/h5")
		//List<WebElement> products;
	@FindBy(css = ".mb-3")
	List<WebElement> products;
		
		@FindBy(css = ".ng-animating")
		WebElement  spinner;
	
	// factory method is not possible with by locator exple: By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2])"
	//List<WebElement>products ;
	By productsBy = By.xpath("//div/h5");
	By addToCart = By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]");
	By toasMessage = By.cssSelector("#toast-container");
	
		//Action class to get productslist
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod =	getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toasMessage);
		//waitForElemetToDisappear(spinner);
		
	}
	
}

package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.LandingPage;

public class StandaAloneTest {

public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3" ;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("mariam.kaba@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Udemy2023");
		driver.findElement(By.cssSelector("#login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h5")));
	//List<WebElement> products =	driver.findElements(By.xpath("//div/h5"));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		
	//convert into stream
WebElement prod =	products.stream().filter(product->
product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//prod.findElement(By.xpath("//div[@class='container']//div[1]//div[1]//div[1]//button[2]")).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();// customize cssSelectors
		
		
		
		 List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		Actions a = new Actions(driver);
	    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Gabon").build().perform();
	    
	    
	    /* wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	*/
	
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		 driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		//driver.findElement(By.cssSelector(".action__submit")).click();
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", submit);
	System.out.println(	driver.findElement(By.cssSelector(".hero-primary")).getText());
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
		
		
			}

}

package rahulshettyacademy.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class confirmatioPage extends AbstractComponent{
	WebDriver driver;

	public confirmatioPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		 PageFactory.initElements(driver,this);// to initialize page factory 
	}
	//driver.findElement(By.cssSelector(".hero-primary")).getText());
	 @FindBy (css = ".hero-primary")
	 WebElement confirmMessage;
	

	public String getConfirmationMessage()
	{
	return	confirmMessage.getText();
	}
}

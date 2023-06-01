package rahulshettyacademy.testcomponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObject.LandingPage;

public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage landingpageobject;
@Test
	public WebDriver initializeDriver() throws IOException {

		// properties class object creation
		Properties prop = new Properties();
//convert properties file into FileInputStream
		
		
		//FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"C:\\Users\\maria\\eclipse-workspace\\SeleniumUdemy\\seleniumframework");
				
		//"C:\\Users\\maria\\eclipse-workspace\\SeleniumUdemy\\seleniumframework\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		
		FileInputStream fis = new FileInputStream("./src/main/java/rahulshettyacademy/resources/GlobalData.properties");
		prop.load(fis);
		// if we want to pass the browser through -Dbrowser in command line 
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); 
		
		// prop.getProperty("browser"); browser from global data file 
if(browserName.contains("chrome")) {
	ChromeOptions options = new ChromeOptions();// to execute test in headless mode 
	WebDriverManager.chromedriver().setup();
	if(browserName.contains("headless")) {
		options.addArguments("headless");
	}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension (1440,900));//full screen 
		 
		
	}
else if(browserName.equalsIgnoreCase("firefox")) {
	//System.setProperty("webdriver.gecko.driver","C:\\Users\\maria\\OneDrive\\Documents\\Drivers\\Firefox.exe");
	WebDriverManager.firefoxdriver().setup();
	driver = new FirefoxDriver(); //firefox setup
	
}
else if(browserName.equalsIgnoreCase("edge")){
	System.setProperty("webdriver.edge.driver","edge.exepath");
	WebDriverManager .edgedriver().setup();
	WebDriver driver = new EdgeDriver();
	//edge
}
driver.manage().window().maximize();// this apply to all cases so we are not putting in any if statement 
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//this apply to all cases so we are not putting in any if statement 
return driver;
	}
	
public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
	
	
	
	//json file path C:\Users\maria\eclipse-workspace\SeleniumUdemy\seleniumframework\src\test\java\rahulshettyacademy\data\purchaseOrder.json
	// read json to string 
String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src\\test\\java\\rahulshettyacademy\\data\\purchaseOrder.json"),StandardCharsets.UTF_8);;


//String to HashMap Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data ;


	
}
	@BeforeTest (alwaysRun=true)// this code is run in every test case because it launch the application that is why we are using @beforeMethod annotation and alwaysRun
	//= true
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		 landingpageobject = new LandingPage(driver);
		landingpageobject.goTo();
		return landingpageobject ;
	}
	@AfterTest(alwaysRun=true)
public void teardown() {
	driver.close();
}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
	File source = 	ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
	FileUtils.copyFile(source, file );
	return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		
	}
}

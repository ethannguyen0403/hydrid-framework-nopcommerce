package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
private WebDriver driver;

	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("--headless");
	        WebDriver driver =  new FirefoxDriver(options);
//			driver = new FirefoxDriver();
		} else if(browserName.equals("chrome")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
		} else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
//		} else if(browserName.equals("edge")){
//			
//		}
		else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		return driver;
	}
	
}

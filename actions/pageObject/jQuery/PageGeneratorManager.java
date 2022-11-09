package pageObject.jQuery;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageObject.admin.AdminLoginPageObject;
import pageObject.admin.DashboardPageObject;

public class PageGeneratorManager {

	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static DataTablePageObject getDataTablePageObject(WebDriver driver) {
		return new DataTablePageObject(driver);
	}

	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	
	
}

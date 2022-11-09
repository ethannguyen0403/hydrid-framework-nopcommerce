package pageObject.jQuery;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.jQuery.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public DataTablePageObject loginToDataTableScreen(String userName, String passWord) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
		
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passWord);
		
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDataTablePageObject(driver);
	}
	
}

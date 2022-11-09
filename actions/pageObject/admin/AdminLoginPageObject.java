package pageObject.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageObject.user.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public DashboardPageObject clickToLoginButton() {
		waitForElementVisible(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDashboardPageObject(driver);
	}

	public void inputToEmailTextbox(String addressEmail) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, addressEmail);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public DashboardPageObject loginAsAdmin(String adminAddressEmail, String adminPassword) {
		inputToEmailTextbox(adminAddressEmail);
		inputToPasswordTextbox(adminPassword);
		return clickToLoginButton();
	}

	
}

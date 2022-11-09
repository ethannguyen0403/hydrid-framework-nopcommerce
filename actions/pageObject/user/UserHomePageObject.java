package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import io.qameta.allure.Step;
import pageUIs.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click Register Link")
	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPageObject(driver);
	}

	@Step("Open User Login Page")
	public UserLoginPageObject openUserLoginPage() {
		waitForElementVisible(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPageObject(driver);
	}

	@Step("My Account Displayed")
	public boolean isMyAccountDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return isControlDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	@Step("Open CustomerInfor Page")
	public CustomerInforPage openCustomerInforPage() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInforPage(driver);
	}

}

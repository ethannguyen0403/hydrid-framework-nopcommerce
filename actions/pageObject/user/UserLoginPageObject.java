package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import io.qameta.allure.Step;
import pageUIs.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click To Login Button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePageObject(driver);
	}

	@Step("Get ErrorMes At Email Textbox")
	public String getErrorMesAtEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Input To Email Textbox with value: {0}")
	public void inputToEmailTextbox(String wrongAddressEmail) {
		waitForElementVisible(driver, UserLoginPageUI.INPUT_EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.INPUT_EMAIL_TEXTBOX, wrongAddressEmail);
	}

	@Step("Input To Password Textbox with value: {0}")
	public void inputToPasswordTextbox(String passWord) {
		waitForElementVisible(driver, UserLoginPageUI.INPUT_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.INPUT_PASSWORD_TEXTBOX, passWord);
	}
	@Step("Get Error Message Above Email Textbox")
	public String getErrorMesAboveEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_MESSAGE_ABOVE_EMAIL_TEXTBOX);
		return getTextElement(driver, UserLoginPageUI.ERROR_MESSAGE_ABOVE_EMAIL_TEXTBOX);
	}

	public UserHomePageObject loginAsUser(String userAddressEmail, String userPassword) {
		inputToEmailTextbox(userAddressEmail);
		inputToPasswordTextbox(userPassword);
		return clickToLoginButton();
	}

}

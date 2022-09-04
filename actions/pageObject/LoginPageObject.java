package pageObject;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMesAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputToEmailTextbox(String wrongAddressEmail) {
		waitForElementVisible(driver, LoginPageUI.INPUT_EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.INPUT_EMAIL_TEXTBOX, wrongAddressEmail);
	}

	public void inputToPasswordTextbox(String passWord) {
		waitForElementVisible(driver, LoginPageUI.INPUT_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.INPUT_PASSWORD_TEXTBOX, passWord);
	}

	public String getErrorMesAboveEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.ERROR_MESSAGE_ABOVE_EMAIL_TEXTBOX);
		return getTextElement(driver, LoginPageUI.ERROR_MESSAGE_ABOVE_EMAIL_TEXTBOX);
	}

	
}

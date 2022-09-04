package pageObject;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMesAtFirstNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getTextElement(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMesAtLastNameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getTextElement(driver, RegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMesAtEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMesAtPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getTextElement(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMesAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getTextElement(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextbox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_FIRSTNAME_TEXTBOX, string);
	}

	public void inputToLastNameTextbox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_LASTNAME_TEXTBOX, string);
	}

	public void inputToPasswordTextbox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_PASSWORD_TEXTBOX, string);
	}

	public void inputToEmailTextbox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_EMAIL_TEXTBOX, string);
	}

	public void inputToConfirmPasswordTextbox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_CONFIRM_PASSWORD_TEXTBOX, string);
	}

	public String getRegisterSuccessMes() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getTextElement(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public void clickToLogoutLink() {
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
	}

	public String getErrorExistMes() {
		waitForElementVisible(driver, RegisterPageUI.ERROR_EXIST_MESSAGE);
		return getTextElement(driver, RegisterPageUI.ERROR_EXIST_MESSAGE);
	}

}

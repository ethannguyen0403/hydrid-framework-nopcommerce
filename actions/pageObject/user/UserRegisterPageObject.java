package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.GlobalConstants;
import io.qameta.allure.Step;
import pageUIs.user.BasePageUI;
import pageUIs.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click Register Button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMesAtFirstNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMesAtLastNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMesAtEmailTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMesAtPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMesAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Input To FirstName Textbox")
	public void inputToFirstNameTextbox(String string) {
		sendkeyToElement(driver, UserRegisterPageUI.INPUT_FIRSTNAME_TEXTBOX, string);
	}

	@Step("Input To LastName Textbox")
	public void inputToLastNameTextbox(String string) {
		sendkeyToElement(driver, UserRegisterPageUI.INPUT_LASTNAME_TEXTBOX, string);
	}

	@Step("Input To Password Textbox")
	public void inputToPasswordTextbox(String string) {
		sendkeyToElement(driver, UserRegisterPageUI.INPUT_PASSWORD_TEXTBOX, string);
	}

	@Step("Input To Email Textbox")
	public void inputToEmailTextbox(String string) {
		sendkeyToElement(driver, UserRegisterPageUI.INPUT_EMAIL_TEXTBOX, string);
	}

	@Step("Input To Confirm Password Textbox")
	public void inputToConfirmPasswordTextbox(String string) {
		sendkeyToElement(driver, UserRegisterPageUI.INPUT_CONFIRM_PASSWORD_TEXTBOX, string);
	}

	public String getRegisterSuccessMes() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToLogoutLink() {
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePageObject(driver);
	}

	public String getErrorExistMes() {
		waitForElementVisible(driver, UserRegisterPageUI.ERROR_EXIST_MESSAGE);
		return getTextElement(driver, UserRegisterPageUI.ERROR_EXIST_MESSAGE);
	}

}

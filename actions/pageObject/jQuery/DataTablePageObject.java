package pageObject.jQuery;

import javax.xml.crypto.Data;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.admin.DashboardPageUI;
import pageUIs.jQuery.DataTablePageUI;

public class DataTablePageObject extends BasePage {
	WebDriver driver;

	public DataTablePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closePopup() {
		if (isControlDisplayed(driver, DataTablePageUI.POPUP_FORM)) {
			waitForElementClickable(driver, DataTablePageUI.CLOSE_POPUP_BUTTON);
			clickToElement(driver, DataTablePageUI.CLOSE_POPUP_BUTTON);

		} else {
			System.out.println("Do not match code about Popup");
		}
	}

	public void enterToHeaderTextboxByLabel(String headerName, String value) {
		waitForElementVisible(driver, DataTablePageUI.HEADER_TEXTBOX, headerName);
		sendkeyToElement(driver, DataTablePageUI.HEADER_TEXTBOX, value, headerName);

	}

	public void resetFilter() {
		waitForElementClickable(driver, DataTablePageUI.RESET_FILTER_BUTTON);
		clickToElement(driver, DataTablePageUI.RESET_FILTER_BUTTON);
		sleepInSecond(1);
	}

	public void reseachFilter() {
		waitForElementClickable(driver, DataTablePageUI.SEARCH_BUTTON);
		clickToElement(driver, DataTablePageUI.SEARCH_BUTTON);
		sleepInSecond(1);
	}

}

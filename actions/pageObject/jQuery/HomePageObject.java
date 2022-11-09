package pageObject.jQuery;

import java.util.List;

import javax.xml.crypto.Data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;
import pageUIs.admin.DashboardPageUI;
import pageUIs.jQuery.DataTablePageUI;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED,fileName);
		return isControlDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
	}

	public void clickToStartButton() {
		List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON);
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}
	}

	public boolean isFileLinkUpLoadByName(String fileName) {
		waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED_LINK,fileName);
		return isControlDisplayed(driver, HomePageUI.FILE_NAME_LOADED_LINK, fileName);
	}

	public boolean isFileImageUploadedByName(String fileName) {
		waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED_IMAGE, fileName);
		return isImageLoaded(driver, HomePageUI.FILE_NAME_LOADED_IMAGE, fileName);
	}

}

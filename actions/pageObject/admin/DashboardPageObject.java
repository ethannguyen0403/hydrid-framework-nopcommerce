package pageObject.admin;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.admin.DashboardPageUI;

public class DashboardPageObject extends BasePage {
	private WebDriver driver;

	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDashboardPageDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.DASHBOARD_TITLE_DISPLAY);
		return isControlDisplayed(driver, DashboardPageUI.DASHBOARD_TITLE_DISPLAY);
	}

	
}

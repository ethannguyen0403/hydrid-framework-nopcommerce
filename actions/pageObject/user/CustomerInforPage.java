package pageObject.user;

import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import common.BasePage;
import pageUIs.user.CustomerInforUI;

public class CustomerInforPage extends BasePage {
	private WebDriver driver;

	public CustomerInforPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInforPageDisplayed() {
		waitForElementVisible(driver, CustomerInforUI.CUSTOMER_INFOR_TEXT);
		return isControlDisplayed(driver, CustomerInforUI.CUSTOMER_INFOR_TEXT);
	}

	
}

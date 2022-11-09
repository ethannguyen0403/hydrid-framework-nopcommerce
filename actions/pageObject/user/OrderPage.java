package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;

public class OrderPage extends BasePage {
	private WebDriver driver;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
	}
}

package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;

public class MyProductViewPage extends BasePage{
	private WebDriver driver;

	public MyProductViewPage(WebDriver driver) {
		this.driver = driver;
	}
}

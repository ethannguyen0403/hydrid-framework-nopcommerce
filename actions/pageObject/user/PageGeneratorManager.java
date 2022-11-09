package pageObject.user;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageObject.admin.AdminLoginPageObject;
import pageObject.admin.DashboardPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePageObject(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPageObject(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPageObject(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static CustomerInforPage getCustomerInforPage(WebDriver driver) {
		return new CustomerInforPage(driver);
	}
	
	public static AddressesPage getAddressesPage(WebDriver driver) {
		return new AddressesPage(driver);
	}
	
	public static OrderPage getOrderPage(WebDriver driver) {
		return new OrderPage(driver);
	}
	
	public static MyProductViewPage getMyProductViewPage(WebDriver driver) {
		return new MyProductViewPage(driver);
	}
	
	public static RewardPointPage getRewardPointPage(WebDriver driver) {
		return new RewardPointPage(driver);
	}

	public static AdminLoginPageObject getAdminLoginPageObject(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static DashboardPageObject getDashboardPageObject(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
}

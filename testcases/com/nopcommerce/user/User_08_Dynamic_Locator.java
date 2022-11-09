package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BasePage;
import common.BaseTest;
import common.GlobalConstants;
import pageObject.admin.AdminLoginPageObject;
import pageObject.admin.DashboardPageObject;
import pageObject.user.AddressesPage;
import pageObject.user.CustomerInforPage;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.MyProductViewPage;
import pageObject.user.OrderPage;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserRegisterPageObject;
import pageUIs.user.UserHomePageUI;
import pageObject.user.RewardPointPage;

public class User_08_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String userAddressEmail, userPassword;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private CustomerInforPage customerInforPage;
	private AddressesPage addressesPage;
	private OrderPage orderPage;
	private MyProductViewPage myProductViewPage;
	private RewardPointPage rewardPointPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		userHomePage = PageGeneratorManager.getUserHomePageObject(driver);

		userAddressEmail = "anh.Thu2301@mail.tivi";
		userPassword = "123456";

	}

	@Test
	public void Dynamic_Page_01_Login() {

		// User Home Page -> Login Page (User)
		userLoginPage = userHomePage.openUserLoginPage();

		// Login as User role
		userHomePage = userLoginPage.loginAsUser(userAddressEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountDisplayed());

		// Home Page -> Cus Infor
		customerInforPage = userHomePage.openCustomerInforPage();
	}

	@Test
	public void Dynamic_Page_02_Role() {
		// Cus -> Address
		addressesPage = (AddressesPage) customerInforPage.openPagesAtMyAccount(driver, "Addresses");

		// Address -> My Product View
		myProductViewPage = (MyProductViewPage) addressesPage.openPagesAtMyAccount(driver, "My product reviews");

		// My Product View - > Reward Point
		rewardPointPage = (RewardPointPage) myProductViewPage.openPagesAtMyAccount(driver, "Reward points");

		// Reward Point -> Address
		addressesPage = (AddressesPage) rewardPointPage.openPagesAtMyAccount(driver, "Addresses");

		// Address -> Reward Point
		rewardPointPage = (RewardPointPage) addressesPage.openPagesAtMyAccount(driver, "Reward points");

		// Reward Point -> My Product View
		myProductViewPage = (MyProductViewPage) rewardPointPage.openPagesAtMyAccount(driver, "My product reviews");

		// My Product View -> Address
		addressesPage = (AddressesPage) myProductViewPage.openPagesAtMyAccount(driver, "Addresses");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generation() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}

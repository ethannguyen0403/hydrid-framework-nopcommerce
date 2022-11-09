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

public class User_07_Switch_Role extends BaseTest {
	private WebDriver driver;
	private String userAddressEmail, userPassword;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private CustomerInforPage customerInforPage;
	private String adminAddressEmail, adminPassword;
	private AdminLoginPageObject adminLoginPage;
	private DashboardPageObject dashboardPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		userHomePage = PageGeneratorManager.getUserHomePageObject(driver);

		userAddressEmail = "anh.Thu2301@mail.tivi";
		userPassword = "123456";
		adminAddressEmail = "admin@yourstore.com";
		adminPassword = "admin";
		
	}

	@Test
	public void Role_01_User_To_Admin() {
		
		//User Home Page -> Login Page (User)
		userLoginPage = userHomePage.openUserLoginPage();
		
		//Login as User role
		userHomePage = userLoginPage.loginAsUser(userAddressEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountDisplayed());
		
		//Home Page -> Cus Infor
		customerInforPage = userHomePage.openCustomerInforPage();
		
		//Cus Infor -> Click logout -> User Home Page
		userHomePage = customerInforPage.clickToLogoutLinkAtUserPage(driver);
		
		//User Home Page -> Open Admin Page -> Login Page (Admin)
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_LOGIN_LINK);
		adminLoginPage = PageGeneratorManager.getAdminLoginPageObject(driver);
		
		//Login as Admin role
		dashboardPage = adminLoginPage.loginAsAdmin(adminAddressEmail, adminPassword);
		Assert.assertTrue(dashboardPage.isDashboardPageDisplayed());
		
		//Dashboard Page -> Click logout -> Login Page (Admin)
		adminLoginPage = dashboardPage.clickToLogoutLinkAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin_To_User() {
		//Login Page (Admin) -> Open User url -> User Home Page
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_HOME_PAGE_LINK);
		userHomePage = PageGeneratorManager.getUserHomePageObject(driver);
				
		//User Home Page -> Login Page (User)
		userLoginPage = userHomePage.openUserLoginPage();
		
		//Login as User role
		userHomePage = userLoginPage.loginAsUser(userAddressEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountDisplayed());
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

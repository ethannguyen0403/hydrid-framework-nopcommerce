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
import pageObject.user.AddressesPage;
import pageObject.user.CustomerInforPage;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.MyProductViewPage;
import pageObject.user.OrderPage;
import pageObject.user.PageGeneratorManager;
import pageObject.user.UserRegisterPageObject;
import pageObject.user.RewardPointPage;

public class User_06_Switch_Page_UI extends BaseTest {
	private WebDriver driver;
//	private String projectPath = System.getProperty("user.dir");
	private String addressEmail, firstName, lastName, passWord, confirmPassword, wrongAddressEmail,
			notExistAddressEmail, wrongPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private CustomerInforPage customerInforPage;
	private AddressesPage addressesPage;
	private OrderPage orderPage;
	private MyProductViewPage myProductViewPage;
	private RewardPointPage rewardPointPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePageObject(driver);

		addressEmail = "automation.Phuong" + generation() + "@mail.tivi";
		firstName = "Cao";
		lastName = "Phuong";
		passWord = "Sml123456@"; confirmPassword = "Sml123456@";
		wrongAddressEmail = "hochiminh@123!"; wrongPassword = "321321";
		notExistAddressEmail = "caothaison@gmail.com";
	}

	@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(addressEmail);
		registerPage.inputToPasswordTextbox(passWord);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMes(), "Your registration completed");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.openUserLoginPage();

		loginPage.inputToEmailTextbox(addressEmail);
		loginPage.inputToPasswordTextbox(passWord);

		homePage = loginPage.clickToLoginButton();
	}

	@Test
	public void User_03_My_Account() {
		customerInforPage = homePage.openCustomerInforPage();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		// Cus -> Address
		addressesPage = customerInforPage.openAddressesPage(driver);

		// Address -> My Product View
		myProductViewPage = addressesPage.openMyProductViewPage(driver);
		
		// My Product View - > Reward Point
		rewardPointPage = myProductViewPage.openRewardPointPage(driver);
		
		// Reward Point -> Address
		addressesPage = rewardPointPage.openAddressesPage(driver);
		
		// Address -> Reward Point
		rewardPointPage = addressesPage.openRewardPointPage(driver);
		
		// Reward Point -> My Product View
		myProductViewPage = rewardPointPage.openMyProductViewPage(driver);
		
		// My Product View -> Address
		addressesPage = myProductViewPage.openAddressesPage(driver);
		
	}

	@Test
	public void User_05_Switch_Role() {
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

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
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.UserRegisterPageObject;

public class User_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
//	private String projectPath = System.getProperty("user.dir");
	private String addressEmail, firstName, lastName, passWord, confirmPassword, wrongAddressEmail, notExistAddressEmail, wrongPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println(browserName);
		driver = getBrowserDriver(browserName);
		
		homePage = new UserHomePageObject(driver);

		addressEmail = "automation.Phuong" + generation() + "@mail.tivi";
		firstName = "Cao";
		lastName = "Phuong";
		passWord = "Sml123456@"; confirmPassword = "Sml123456@";
		wrongAddressEmail = "hochiminh@123!"; wrongPassword = "321321";
		notExistAddressEmail = "caothaison@gmail.com";

		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(addressEmail);
		registerPage.inputToPasswordTextbox(passWord);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMes(), "Your registration completed");

		registerPage.clickToLogoutLink();
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.openUserLoginPage();
		loginPage = new UserLoginPageObject(driver);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMesAtEmailTextbox(), "Please enter your email");
	}

//	@Test
//	public void Login_02_Invalid_Email() {
//		homePage.clickToLoginLink();
//		loginPage = new LoginPageObject(driver);
//
//		loginPage.inputToEmailTextbox(wrongAddressEmail);
//		loginPage.inputToPasswordTextbox(passWord);
//
//		loginPage.clickToLoginButton();
//
//		Assert.assertEquals(loginPage.getErrorMesAtEmailTextbox(), "Wrong email");
//
//	}
//
//	@Test
//	public void Login_03_Email_Not_Exist() {
//		homePage.clickToLoginLink();
//		loginPage = new LoginPageObject(driver);
//
//		loginPage.inputToEmailTextbox(notExistAddressEmail);
//		loginPage.inputToPasswordTextbox(passWord);
//
//		loginPage.clickToLoginButton();
//
//		Assert.assertEquals(loginPage.getErrorMesAboveEmailTextbox(),
//				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
//	}
//
//	@Test
//	public void Login_04_Email_Exist_Not_Password() {
//		homePage.clickToLoginLink();
//		loginPage = new LoginPageObject(driver);
//
//		loginPage.inputToEmailTextbox(addressEmail);
//
//		loginPage.clickToLoginButton();
//
//		Assert.assertEquals(loginPage.getErrorMesAboveEmailTextbox(),
//				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
//	}
//
//	@Test
//	public void Login_05_Email_Exist_Wrong_Password() {
//		homePage.clickToLoginLink();
//		loginPage = new LoginPageObject(driver);
//
//		loginPage.inputToEmailTextbox(addressEmail);
//		loginPage.inputToPasswordTextbox(wrongPassword);
//
//		loginPage.clickToLoginButton();
//
//		Assert.assertEquals(loginPage.getErrorMesAboveEmailTextbox(),
//				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
//	}
//
//	@Test
//	public void Login_06_Valid_Email() {
//		homePage.clickToLoginLink();
//		loginPage = new LoginPageObject(driver);
//
//		loginPage.inputToEmailTextbox(addressEmail);
//		loginPage.inputToPasswordTextbox(passWord);
//
//		loginPage.clickToLoginButton();
//		homePage = new HomePageObject(driver);
//		
//		Assert.assertTrue(homePage.isMyAccountDisplayed());
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generation() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}

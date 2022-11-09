package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserLoginPageObject;
import pageObject.user.UserRegisterPageObject;
import pageObject.user.AddressesPage;
import pageObject.user.CustomerInforPage;
import pageObject.user.MyProductViewPage;
import pageObject.user.OrderPage;
import pageObject.user.PageGeneratorManager;
import pageObject.user.RewardPointPage;

public class User_12_Allure extends BaseTest {
	private WebDriver driver;
//	private String projectPath = System.getProperty("user.dir");
	private String addressEmail, firstName, lastName, passWord, confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		homePage = PageGeneratorManager.getUserHomePageObject(driver);

		addressEmail = "automation.Phuong" + generation() + "@mail.tivi";
		firstName = "Cao";
		lastName = "Phuong";
		passWord = "Sml123456@";
		confirmPassword = "Sml123456@";

	}

	@Description("Register")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void Register_01_Valid_Information() {
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

	@Description("Login")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void Register_02_Email_Already_Exist() {
		loginPage = homePage.openUserLoginPage();

		loginPage.inputToEmailTextbox(addressEmail);
		loginPage.inputToPasswordTextbox(passWord);

		homePage = loginPage.clickToLoginButton();
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

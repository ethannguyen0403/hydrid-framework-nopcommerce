package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BasePage;
import pageObject.user.UserHomePageObject;
import pageObject.user.UserRegisterPageObject;

public class User_03_Page_Object_Register {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String addressEmail;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		addressEmail = "automation.Phuong" + generation() + "@mail.tivi";
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);
	}

	@Test
	public void Register_01_Empty_Data() {
		homePage.clickToRegisterLink();
//		basePage.clickToElement(driver, "//a[@class='ico-register']");

		registerPage.clickToRegisterButton();
//		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getErrorMesAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMesAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMesAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMesAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMesAtConfirmPasswordTextbox(), "Password is required.");

//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='FirstName-error']"),
//				"First name is required.");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='LastName-error']"), "Last name is required.");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Email-error']"), "Email is required.");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Password-error']"), "Password is required.");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='ConfirmPassword-error']"),
//				"Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		homePage.clickToRegisterLink();
//		basePage.clickToElement(driver, "//a[@class='ico-register']");

		registerPage.inputToFirstNameTextbox("Cao");
		registerPage.inputToLastNameTextbox("Phuong");
		registerPage.inputToEmailTextbox("adsd");
		registerPage.inputToPasswordTextbox("Sml123456@");
		registerPage.inputToConfirmPasswordTextbox("Sml123456@");

//		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
//		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
//		basePage.sendkeyToElement(driver, "//input[@id='Email']", "adsd");
//		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
//		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml123456@");

		registerPage.clickToRegisterButton();
//		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getErrorMesAtEmailTextbox(), "Wrong email");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void Register_03_Valid_Information() {
		homePage.clickToRegisterLink();
//		basePage.clickToElement(driver, "//a[@class='ico-register']");

		registerPage.inputToFirstNameTextbox("Cao");
		registerPage.inputToLastNameTextbox("Phuong");
		registerPage.inputToEmailTextbox(addressEmail);
		registerPage.inputToPasswordTextbox("Sml123456@");
		registerPage.inputToConfirmPasswordTextbox("Sml123456@");

//		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
//		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
//		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
//		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
//		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml123456@");

		registerPage.clickToRegisterButton();
//		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getRegisterSuccessMes(), "Your registration completed");
//		Assert.assertEquals(basePage.getTextElement(driver, "//div[text()='Your registration completed']"),
//				"Your registration completed");

		registerPage.clickToLogoutLink();
//		basePage.clickToElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void Register_04_Email_Already_Exist() {
		homePage.clickToRegisterLink();
//		basePage.clickToElement(driver, "//a[@class='ico-register']");

		registerPage.inputToFirstNameTextbox("Cao");
		registerPage.inputToLastNameTextbox("Phuong");
		registerPage.inputToEmailTextbox(addressEmail);
		registerPage.inputToPasswordTextbox("Sml123456@");
		registerPage.inputToConfirmPasswordTextbox("Sml123456@");

//		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
//		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
//		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
//		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
//		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml123456@");
		
		registerPage.clickToRegisterButton();
//		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getErrorExistMes(), "The specified email already exists");
//		Assert.assertEquals(basePage.getTextElement(driver, "//li[text()='The specified email already exists']"),
//				"The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6char() {
		homePage.clickToRegisterLink();
//		basePage.clickToElement(driver, "//a[@class='ico-register']");

		registerPage.inputToFirstNameTextbox("Cao");
		registerPage.inputToLastNameTextbox("Phuong");
		registerPage.inputToEmailTextbox(addressEmail);
		registerPage.inputToPasswordTextbox("Sml");
		registerPage.inputToConfirmPasswordTextbox("Sml");

//		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
//		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
//		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
//		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml");
//		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml");
		
		registerPage.clickToRegisterButton();
//		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getErrorMesAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Password-error']"),
//				"Password must meet the following rules:\rmust have at least 6 characters");
	}

	@Test
	public void Register_06_Confirm_Password_Wrongly() {
		homePage.clickToRegisterLink();
//		basePage.clickToElement(driver, "//a[@class='ico-register']");

		registerPage.inputToFirstNameTextbox("Cao");
		registerPage.inputToLastNameTextbox("Phuong");
		registerPage.inputToEmailTextbox(addressEmail);
		registerPage.inputToPasswordTextbox("Sml123456@");
		registerPage.inputToConfirmPasswordTextbox("Smldadsa");

//		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
//		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
//		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
//		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
//		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Smldadsa");
		
		registerPage.clickToRegisterButton();
//		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(registerPage.getErrorMesAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");
//		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='ConfirmPassword-error']"),
//				"The password and confirmation password do not match.");
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

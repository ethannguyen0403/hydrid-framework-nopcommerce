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

public class User_02_Apply_BasePage_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String addressEmail;
	BasePage basePage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();

		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
		addressEmail = "automation.Phuong" + generation() + "@mail.tivi";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", "adsd");
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml123456@");
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_valid_Information() {
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml123456@");
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getTextElement(driver, "//div[text()='Your registration completed']"),
				"Your registration completed");
		basePage.clickToElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_04_Register_Email_Already_Exist() {
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml123456@");
		basePage.clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(basePage.getTextElement(driver, "//li[text()='The specified email already exists']"),
				"The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6char() {
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Sml");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='Password-error']"),
				"Password must meet the following rules:\rmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Confirm_Password_Wrongly() {
		basePage.clickToElement(driver, "//a[@class='ico-register']");

		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Cao");
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", "Phuong");
		basePage.sendkeyToElement(driver, "//input[@id='Email']", addressEmail);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", "Sml123456@");
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "Smldadsa");
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getTextElement(driver, "//span[@id='ConfirmPassword-error']"),
				"The password and confirmation password do not match.");
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

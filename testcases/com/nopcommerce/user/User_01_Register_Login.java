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

public class User_01_Register_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String addressEmail;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		addressEmail = "automation.Phuong" + generation() + "@mail.tivi";
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='FirstName-error']")).getText(),
				"First name is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='LastName-error']")).getText(),
				"Last name is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Password-error']")).getText(),
				"Password is required.");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ConfirmPassword-error']")).getText(),
				"Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Cao");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Phuong");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("adsd");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Sml123456@");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Sml123456@");

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Wrong email");
	}

	@Test
	public void TC_03_Register_valid_Information() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Cao");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Phuong");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(addressEmail);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Sml123456@");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Sml123456@");

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(),
				"Your registration completed");

		driver.findElement(By.cssSelector("a.ico-logout")).click();
	}

	@Test
	public void TC_04_Register_Email_Already_Exist() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Cao");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Phuong");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(addressEmail);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Sml123456@");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Sml123456@");

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//li[text()='The specified email already exists']")).getText(),
				"The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6char() {
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Cao");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Phuong");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(addressEmail);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Sml");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Sml");

		driver.findElement(By.cssSelector("button#register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(),
				"Password must meet the following rules:\rmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Confirm_Password_Wrongly() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Cao");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Phuong");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(addressEmail);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Sml123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Smldadsa");

		driver.findElement(By.cssSelector("button#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),
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

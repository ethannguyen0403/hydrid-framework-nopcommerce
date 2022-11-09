package com.jquery.datatable;

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
import pageObject.jQuery.DataTablePageObject;
import pageObject.jQuery.HomePageObject;
import pageObject.jQuery.LoginPageObject;
import pageObject.jQuery.PageGeneratorManager;
import pageUIs.jQuery.DataTablePageUI;

public class User_11_Assert_Verify extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	String smileBB = "smilebabie.jpg";
	String avatarBB = "avatarbabie.jpg";
	String indiaBB = "indiababie.jpg";
	String dalatBB = "dalatbabie.jpg";
	String[] multiFileNames = { smileBB, avatarBB, indiaBB, dalatBB };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		homePage = PageGeneratorManager.getHomePageObject(driver);

	}

	@Test
	public void Update_01_One_File() {
		homePage.uploadMultipleFile(driver, dalatBB);

		verifyTrue(homePage.isFileLoadedByName(dalatBB));

		homePage.clickToStartButton();

		verifyTrue(homePage.isFileLinkUpLoadByName(dalatBB));
		verifyTrue(homePage.isFileImageUploadedByName(dalatBB));
	}

	@Test
	public void Update_02_Multiple_File() {
		homePage.refreshCurrentPage(driver);
		homePage.uploadMultipleFile(driver, multiFileNames);

		verifyTrue(homePage.isFileLoadedByName(smileBB));
		verifyTrue(homePage.isFileLoadedByName(avatarBB));
		verifyTrue(homePage.isFileLoadedByName(indiaBB));
		verifyTrue(homePage.isFileLoadedByName(dalatBB));

		homePage.clickToStartButton();
		
		verifyTrue(homePage.isFileLinkUpLoadByName(smileBB));
		verifyTrue(homePage.isFileImageUploadedByName(smileBB));
		verifyTrue(homePage.isFileLinkUpLoadByName(avatarBB));
		verifyTrue(homePage.isFileImageUploadedByName(avatarBB));
		verifyTrue(homePage.isFileLinkUpLoadByName(indiaBB));
		verifyTrue(homePage.isFileImageUploadedByName(indiaBB));
		verifyTrue(homePage.isFileLinkUpLoadByName(dalatBB));
		verifyFalse(homePage.isFileImageUploadedByName(dalatBB));
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

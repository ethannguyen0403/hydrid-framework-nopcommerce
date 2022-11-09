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
import pageObject.jQuery.LoginPageObject;
import pageObject.jQuery.PageGeneratorManager;
import pageUIs.jQuery.DataTablePageUI;

public class User_09_DataTable_DataGrid extends BaseTest {
	private WebDriver driver;
	private String idLoginAtAdminPage, passwordLoginAtAdminPage;
	private LoginPageObject loginPage;
	private DataTablePageObject dataTablePage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		idLoginAtAdminPage = "user01";
		passwordLoginAtAdminPage = "guru99com";

		dataTablePage = loginPage.loginToDataTableScreen(idLoginAtAdminPage, passwordLoginAtAdminPage);
		dataTablePage.closePopup();
	}

	@Test
	public void Data_Table_01() {
		dataTablePage.enterToHeaderTextboxByLabel("email", "theron.jerde@yahoo.com");
		dataTablePage.reseachFilter();
		Assert.assertTrue(dataTablePage.isControlDisplayed(driver, DataTablePageUI.COLUMN_INDEX_BY_NAME,
				"Rickie Lael Williamson Douglas Hirthe", "theron.jerde@yahoo.com"));
		dataTablePage.resetFilter();

		dataTablePage.enterToHeaderTextboxByLabel("name", "Dinh Duc Thuan");
		dataTablePage.reseachFilter();
		Assert.assertTrue(dataTablePage.isControlDisplayed(driver, DataTablePageUI.COLUMN_INDEX_BY_NAME,
				"Dinh Duc Thuan", "thuanduc@gmail.com"));
		dataTablePage.resetFilter();

		dataTablePage.enterToHeaderTextboxByLabel("email", "Starkalooto3@yopmail.com");
		dataTablePage.reseachFilter();
		Assert.assertTrue(dataTablePage.isControlDisplayed(driver, DataTablePageUI.COLUMN_INDEX_BY_NAME,
				"Tony Stark", "Starkalooto3@yopmail.com"));
		dataTablePage.resetFilter();
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

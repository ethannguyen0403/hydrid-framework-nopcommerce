package common;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.admin.AdminLoginPageObject;
import pageObject.user.AddressesPage;
import pageObject.user.CustomerInforPage;
import pageObject.user.MyProductViewPage;
import pageObject.user.PageGeneratorManager;
import pageObject.user.RewardPointPage;
import pageObject.user.UserHomePageObject;
import pageUIs.jQuery.HomePageUI;
import pageUIs.user.BasePageUI;

public class BasePage {
	protected final Log log;

	protected BasePage() {
		log = LogFactory.getLog(getClass());
	}

	public void openPageUrl(WebDriver driver, String linkUrl) {
		driver.get(linkUrl);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
		;
	}

	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchToWindowByID(WebDriver driver, String currentID) {
		Set<String> allWindown = driver.getWindowHandles();
		for (String currentWindow : allWindown) {
			if (!currentWindow.equals(currentID)) {
				driver.switchTo().window(currentWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindown = driver.getWindowHandles();

		for (String currentWindow : allWindown) {
			if (!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getBylocatorType(locatorType));
	}

	private By getBylocatorType(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("locatorType Type is not supported!");
		}

		return by;
	}

	private String getDynamicXpath(String dynamicLocator, String... dynamicValue) {
		if (dynamicLocator.startsWith("xpath=")) {
			dynamicLocator = String.format(dynamicLocator, (Object[]) dynamicValue);
		}
		return dynamicLocator;
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getBylocatorType(locatorType));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String dynamicLocator, String textValue, String... dynamicValue) {
		WebElement element = getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue));
		element.clear();
		element.sendKeys(textValue);
	}

	public String getTextElement(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getTextElement(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)).getText();
	}

	public void selectItemInDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDropdown(WebDriver driver, String dynamicLocator, String textItem, String... dynamicValue) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)));
		select.selectByVisibleText(textItem);
	}

	public String getSelectItemInDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectItemInDropdown(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String textSearch,
			String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getBylocatorType(childXpath)));
		List<WebElement> allDropDownItem = driver.findElements(getBylocatorType(childXpath));
		for (WebElement item : allDropDownItem) {
			String actualTextItem = item.getText();
			if (actualTextItem.equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String textSearch,
			String expectedTextItem, String... dynamicValue) {
		getWebElement(driver, getDynamicXpath(parentXpath, dynamicValue)).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(getBylocatorType(getDynamicXpath(childXpath, dynamicValue))));
		List<WebElement> allDropDownItem = driver
				.findElements(getBylocatorType(getDynamicXpath(childXpath, dynamicValue)));
		for (WebElement item : allDropDownItem) {
			String actualTextItem = item.getText();
			if (actualTextItem.equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getAttributeValue(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getAttributeValue(WebDriver driver, String dynamicLocator, String attributeName,
			String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)).getAttribute(attributeName);
	}

	public String getCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexaFromColorRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementsSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementsSize(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		return getListWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)).size();
	}

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		WebElement element = getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		WebElement element = getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		return getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)).isDisplayed();

	}

	public boolean isControlUnDisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isControlUnDisplayed(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue));
		overrideImplicitTimeout(driver, longTimeout);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	private void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isControlSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public void switchToFrame(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void highlightElement(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void clickToElementByJS(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isImageLoaded(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(dynamicLocator, dynamicValue)));
		return status;
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getBylocatorType(locatorType)));
		} catch (Exception e) {
			log.debug("Wait for element Visible with error: " + e.getMessage());
		}
	}

	public void waitForElementVisible(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions
					.visibilityOfElementLocated(getBylocatorType(getDynamicXpath(dynamicLocator, dynamicValue))));
		} catch (Exception e) {
			log.debug("Wait for element Visible with error: " + e.getMessage());
		}
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBylocatorType(locatorType)));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getBylocatorType(locatorType)));
	}

	public void waitForElementInvisible(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getBylocatorType(getDynamicXpath(dynamicLocator, dynamicValue))));
	}

	/*
	 * Wait for element undisplayed in DOM or not in DOM and override implicit
	 * timeout
	 */

	public void waitForElementUnDisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getBylocatorType(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getBylocatorType(locatorType)));
		} catch (Exception e) {
			log.debug("Wait for element clickable with error: " + e.getMessage());
		}
	}

	public void waitForElementClickable(WebDriver driver, String dynamicLocator, String... dynamicValue) {
		try {
			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			explicitWait.until(ExpectedConditions
					.elementToBeClickable(getBylocatorType(getDynamicXpath(dynamicLocator, dynamicValue))));
		} catch (Exception e) {
			log.debug("Wait for element clickable with error: " + e.getMessage());
		}
	}
	
	public void uploadMultipleFile(WebDriver driver, String... fileName) {
		String fullFileName = "";
		for (String file : fileName) {
			fullFileName = fullFileName + GlobalConstants.UPDATE_FILE + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, HomePageUI.UPDATE_FILE).sendKeys(fullFileName);
	}

	public CustomerInforPage openCustomerInforPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.CUSTOMER_INFOR_PAGE);
		clickToElement(driver, BasePageUI.CUSTOMER_INFOR_PAGE);
		return PageGeneratorManager.getCustomerInforPage(driver);
	}

	public AddressesPage openAddressesPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.ADDRESSES_PAGE);
		clickToElement(driver, BasePageUI.ADDRESSES_PAGE);
		return PageGeneratorManager.getAddressesPage(driver);
	}

	public MyProductViewPage openMyProductViewPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.MY_PRODUCT_VIEW_PAGE);
		clickToElement(driver, BasePageUI.MY_PRODUCT_VIEW_PAGE);
		return PageGeneratorManager.getMyProductViewPage(driver);
	}

	public RewardPointPage openRewardPointPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.MY_PRODUCT_VIEW_PAGE);
		clickToElement(driver, BasePageUI.MY_PRODUCT_VIEW_PAGE);
		return PageGeneratorManager.getRewardPointPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePageObject(driver);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPageObject(driver);
	}

	public BasePage openPagesAtMyAccount(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_AT_MY_ACCOUNT, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_AT_MY_ACCOUNT, pageName);

		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getCustomerInforPage(driver);
		case "Addresses":
			return PageGeneratorManager.getAddressesPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getMyProductViewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getRewardPointPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account area");
		}
	}

	private long shortTimeout = 5;
	private long longTimeout = 30;

}

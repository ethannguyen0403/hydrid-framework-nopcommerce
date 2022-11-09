package pageUIs.user;

public class BasePageUI {
	public static final String REGISTER_LINK = "css=a[class='ico-register']";
	public static final String CUSTOMER_INFOR_PAGE = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='Customer info']";
	public static final String ADDRESSES_PAGE = "xpath=//li[contains(@class,'customer-addresses')]/a";
	public static final String MY_PRODUCT_VIEW_PAGE = "xpath=//li[contains(@class,'customer-reviews')]/a";
	public static final String REWARD_POINT_PAGE = "xpath=//li[contains(@class,'reward-points')]/a";
	public static final String LOGOUT_LINK_AT_USER = "class=ico-logout";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	public static final String DYNAMIC_AT_MY_ACCOUNT = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='%s']";
}

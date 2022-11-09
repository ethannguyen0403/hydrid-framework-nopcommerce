package pageUIs.jQuery;

public class DataTablePageUI {
	public static final String POPUP_FORM = "css=div.message-popup-content";
	public static final String CLOSE_POPUP_BUTTON = "xpath=//span[text()='close']";
	public static final String HEADER_TEXTBOX = "xpath=//input[contains(@id,'%s')]";
	public static final String SEARCH_BUTTON = "xpath=//span[text()='Search']";
	public static final String RESET_FILTER_BUTTON = "xpath=//span[text()='Reset Filter']";
	public static final String COLUMN_INDEX_BY_NAME = "xpath=//td[contains(text(),'%s')]//following-sibling::td[contains(text(),'%s')]";

}

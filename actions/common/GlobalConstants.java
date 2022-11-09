package common;

import java.io.File;

import bsh.commands.dir;

public class GlobalConstants {
	public static final String USER_HOME_PAGE_LINK = "https://demo.nopcommerce.com/";
	public static final String ADMIN_LOGIN_LINK = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
	public static final String ADMIN_TESTING_URL = "https://admin-demo.nopcommerce.com";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("javar.version");
	
	public static final String UPDATE_FILE = PROJECT_PATH + File.separator + "updateFiles" + File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTLM5";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	
	public static final String DB_DEV_URL = "";
	public static final String DB_DEV_USER = "automationfc";
	public static final String DB_DEV_PASS = "P@ssw0rd1";
	public static final String DB_TEST_URL = "";
	public static final String DB_TEST_USER = "automationfc";
	public static final String DB_TEST_PASS = "P@ssw0rd1";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final int RETRY_TEST_FAIL = 3;
	
	
}

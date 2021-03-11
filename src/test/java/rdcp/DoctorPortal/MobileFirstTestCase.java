package rdcp.DoctorPortal;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileFirstTestCase extends TestBase {
	private final String expectedValueAppName                       = "Accu-Chek Smart Pix Online";

    @DataProvider(name = "data-provider-1")
    public Object[][] dataProviderMethod1() {
    	return new Object[][] { 
    		{ "https://patientportal-qaeu.rochedc.accentureanalytics.com/", "ecifopunviwatuto@yopmail.com", "Roche123.-" }, 
    		{ "https://patientportal-qaeu.rochedc.accentureanalytics.com/", "lopababguzihipka@yopmail.com", "Roche123.-" }, 
    		{ "https://univ-uploader-qa-eu.rochedc.accentureanalytics.com/", "hcp_002_001@yopmail.com", "Roche123.-" }, 
    		{ "https://univ-uploader-qa-eu.rochedc.accentureanalytics.com/", "hcp_002_002@yopmail.com", "Roche123.-" }, 
    	};
    }

	@SuppressWarnings("unchecked")
    public void PerformTask(String url, String username, String password) {
    	// WebDriver mainWebDriver;

    	LoginPageObject loginPage;

		String timeStamp = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss").format(new Date());
		String result = "DEBUG: CheckPoint_1 -> url: " + url + ";" + timeStamp + ";" + username + ";";
		System.out.println(result);

		AppiumDriver<MobileElement> mainWebDriver = (AppiumDriver<MobileElement>) TestBase.getDriver(TestBase.APP_TYPE_MOBILE_WEB, TestBase.OS_ANDROID, TestBase.getBrowserType());
    	mainWebDriver.get(url);

		loginPage = new LoginPageObject(expectedValueAppName, mainWebDriver);
		loginPage.inputLogIn().sendKeys(username);
		loginPage.inputPassword().sendKeys(password);
		loginPage.inputPassword().sendKeys(Keys.ENTER);

		mainWebDriver.quit();
		TestBase.clearDriver(mainWebDriver);    				
    }

	@Test(dataProvider = "data-provider-1")
	public void tc01_verifyLogin(String url, String username, String password) {
		System.out.println("DEBUG: data-provider-1 ->  Initiating PerformTask(url, username, password) with: '" + url + "', '" + username + "', '" + password + "'");
		PerformTask(url, username, password);
		System.out.println("DEBUG: data-provider-1 ->  Finished PerformTask(url, username, password)");
	}
}

package pages.NativeMobile.Android.GluciCheck.v4_1_0_rc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import pages.BasePageMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class SettingsPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";

	private final static By loctxtWindowTitle          = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/settingsText\")");
	private final static By locbtnPrivacySettings      = MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(10).scrollIntoView(new UiSelector().textContains(\"Privacy settings\"))");
	
	public SettingsPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement txtWindowTitle() {
		MobileElement r = getElement(loctxtWindowTitle); 
		
		return r;
	}

	public MobileElement btnPrivacySettings() throws InterruptedException {
		MobileElement r = getElement(locbtnPrivacySettings); 		
		
		return r;		
	}
}
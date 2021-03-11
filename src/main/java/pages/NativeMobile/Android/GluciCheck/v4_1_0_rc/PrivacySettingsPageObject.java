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

public class PrivacySettingsPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";

	private final static By locbtnCrashReporting = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.Switch");
	private final static By loctxtWindowTitle = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Privacy settings\")");

	public PrivacySettingsPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement btnCrashReporting() {
		MobileElement r = getElement(locbtnCrashReporting); 
		
		return r;
	}

	public MobileElement txtWindowTitle() {
		MobileElement r = getElement(loctxtWindowTitle); 
		
		return r;
	}

}
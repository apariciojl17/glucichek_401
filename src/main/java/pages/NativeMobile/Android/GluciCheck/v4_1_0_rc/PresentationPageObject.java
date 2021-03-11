package pages.NativeMobile.Android.GluciCheck.v4_1_0_rc;

import java.util.List;
import org.openqa.selenium.By;

import pages.BasePageMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class PresentationPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";

	private final static By locSkipButton            = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/skipButton\")");
	private final static By locTitlePageOne          = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/on_boarding_title\")");
	/* private final static By locTitlePageThree        = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Visualize your glycemia\")");
	private final static By locTitlePageFour         = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"An application that suits you\")");
	private final static By locTitlePageFive         = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Share your data\")");
	private final static By locTitlePageSix          = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Pair your meter\")");*/

	private final static By locButtonNext          = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/arrowNext\")");

	public PresentationPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement titlePageOne() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement titlePageTwo() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement titlePageThree() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement titlePageFour() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement titlePageFive() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement titlePageSix() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}
	
	public MobileElement buttonNext() {
		MobileElement r = getElement(locButtonNext); 
		
		return r; 
	}

	public MobileElement buttonSkip() {
		MobileElement r = getElement(locSkipButton); 
		
		return r;
	}

	public MobileElement buttonGotIt() {
		MobileElement r = getElement(locSkipButton); 
		
		return r;
	}

	public void waitUntilSkipButtonAppears() {
		waitForPresence(30, buttonSkip());
	}
	
}
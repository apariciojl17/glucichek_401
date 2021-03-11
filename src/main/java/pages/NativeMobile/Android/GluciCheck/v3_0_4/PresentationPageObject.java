package pages.NativeMobile.Android.GluciCheck.v3_0_4;

import java.util.List;
import org.openqa.selenium.By;

import pages.BasePageMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class PresentationPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";

	private final static By locTitlePageOne          = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Food holds no secrets for you\")");
	private final static By locTitlePageTwo          = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Your diabetes logbook\")");
	private final static By locTitlePageThree        = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Visualize your glycemia\")");
	private final static By locTitlePageFour         = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"An application that suits you\")");
	private final static By locTitlePageFive         = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Share your data\")");
	private final static By locTitlePageSix          = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Pair your meter\")");

	private final static String locButtonNext        = "android.view.ViewGroup";

	public PresentationPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement titlePageOne() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement titlePageTwo() {
		MobileElement r = getElement(locTitlePageTwo); 
		
		return r;
	}

	public MobileElement titlePageThree() {
		MobileElement r = getElement(locTitlePageThree); 
		
		return r;
	}

	public MobileElement titlePageFour() {
		MobileElement r = getElement(locTitlePageFour); 
		
		return r;
	}

	public MobileElement titlePageFive() {
		MobileElement r = getElement(locTitlePageFive); 
		
		return r;
	}

	public MobileElement titlePageSix() {
		MobileElement r = getElement(locTitlePageSix); 
		
		return r;
	}
	
	public MobileElement buttonNext() {
		List<MobileElement> r = getElementsbyClassName(locButtonNext); 
		
		if (r.size() > 3) {
			return r.get(3);
		} else
			return null;
	}

	public MobileElement buttonNextPageFiveOrSix() {
		List<MobileElement> r = getElementsbyClassName(locButtonNext); 
		
		if (r.size() > 4) {
			return r.get(4);
		} else
			return null;
	}
}
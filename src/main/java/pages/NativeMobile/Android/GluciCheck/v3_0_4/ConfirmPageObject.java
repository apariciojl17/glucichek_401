package pages.NativeMobile.Android.GluciCheck.v3_0_4;

import java.util.List;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

import pages.BasePageMobile;

public class ConfirmPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locAgreeTerms            = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek:id/titanium_ui_switchcompat\")");
	private final static By locFirebaseTerms         = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek:id/titanium_ui_switchcompat\")");
	private final static String locButtonConfirm     = "android.view.ViewGroup";
	private final static By locTitlePageOne          = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Food holds no secrets for you\")");
	private final static String locButtonNext        = "android.view.ViewGroup";

	public ConfirmPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement toggleAgreeTerms() {
		List<MobileElement> r = getElements(locAgreeTerms); 

		if (r.size() > 0) {
			return r.get(0);
		} else
			return null;
	}

	public MobileElement titlePageOne() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement buttonConfirm() {
		List<MobileElement> r = getElementsbyClassName(locButtonConfirm); 
		
		if (r.size() > 19) {
			return r.get(19);
		} else
			return null;
	}

	public MobileElement buttonNext() {
		List<MobileElement> r = getElementsbyClassName(locButtonNext); 
		
		if (r.size() > 3) {
			return r.get(3);
		} else
			return null;
	}
	
	public MobileElement toggleFirebaseTerms() {
		List<MobileElement> r = getElements(locFirebaseTerms); 
		
		if (r.size() > 1) {
			return r.get(1);
		} else
			return null;
	}

	public void waitUntilAgreeTermsAppears() {
		waitForPresence(30, toggleAgreeTerms());
	}
	
	public void waitUntilFirebaseTermsAppears() {
		waitForPresence(30, toggleFirebaseTerms());
	}

	public void waitUntilConfirmButtonAppears() {
		waitForPresence(30, buttonConfirm());
	}
}
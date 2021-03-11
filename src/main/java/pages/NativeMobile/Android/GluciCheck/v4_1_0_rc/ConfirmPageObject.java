package pages.NativeMobile.Android.GluciCheck.v4_1_0_rc;

import java.util.List;
import org.openqa.selenium.By;

import pages.BasePageMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class ConfirmPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locTermsOfUse            = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/terms_of_use_switch\")");
	private final static By locPrivacyPolicy         = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/privacy_policy_switch\")");
	private final static By locAnalyticsConsent      = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/analytics_consent_switch\")");
	private final static By locCrashReportConsent    = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/crash_report_consent_switch\")");
	private final static By locButtonConfirm         = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/confirmBtn\")");
	private final static By locTitlePageOne          = MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Food holds no secrets for you\")");

	private final static String locButtonNext        = "android.view.ViewGroup";

	public ConfirmPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement toggleTermsOfUse() {
		MobileElement r = getElement(locTermsOfUse); 
		
		return r;
	}

	public MobileElement togglePrivacyPolicy() {
		MobileElement r = getElement(locPrivacyPolicy); 
		
		return r;	
	}

	public MobileElement toggleAnalyticsConsent() {
		MobileElement r = getElement(locAnalyticsConsent); 
		
		return r;	
	}

	public MobileElement toggleCrashReportConsent() {
		MobileElement r = getElement(locCrashReportConsent); 
		
		return r;	
	}

	public MobileElement titlePageOne() {
		MobileElement r = getElement(locTitlePageOne); 
		
		return r;
	}

	public MobileElement buttonConfirm() {
		MobileElement r = getElement(locButtonConfirm); 
		
		return r;
	}

	public MobileElement buttonNext() {
		List<MobileElement> r = getElementsbyClassName(locButtonNext); 
		
		if (r.size() > 3) {
			return r.get(3);
		} else
			return null;
	}
	
	public void waitUntilAgreeTermsOfUseAppears() {
		waitForPresence(30, toggleTermsOfUse());
	}

	public void waitUntilAgreePrivacyPolicyAppears() {
		waitForPresence(30, togglePrivacyPolicy());
	}

	public void waitUntilAnalyticsConsentAppears() {
		waitForPresence(30, toggleAnalyticsConsent());
	}

	public void waitUntilCrashReportConsentAppears() {
		waitForPresence(30, toggleCrashReportConsent());
	}

	public void waitUntilConfirmButtonAppears() {
		waitForPresence(30, buttonConfirm());
	}
}
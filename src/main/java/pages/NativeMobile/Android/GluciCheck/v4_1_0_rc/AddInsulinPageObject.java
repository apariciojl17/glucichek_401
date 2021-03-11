package pages.NativeMobile.Android.GluciCheck.v4_1_0_rc;

import java.util.List;
import org.openqa.selenium.By;

import pages.BasePageMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class AddInsulinPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locLabelUnits            = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Units\")");
	private final static By locLabelInsulinDose      = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Insulin dose\")");
	private final static By locButtonRapidActing     = MobileBy.AndroidUIAutomator("new UiSelector().text(\"RAPID-ACTING\")");
	private final static By locButtonLongActing      = MobileBy.AndroidUIAutomator("new UiSelector().text(\"LONG-ACTING\")");
	private final static By locButtonMixed           = MobileBy.AndroidUIAutomator("new UiSelector().text(\"MIXED\")");
	private final static By locButtonConfirm         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Confirm\")");
	private final static String locLabelDateTime     = "android.widget.TextView";
	private final static By locinputUnits            = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek:id/titanium_ui_edittext\")");
	private final static By locinputAddANote         = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek:id/titanium_ui_edittext\")");

	public AddInsulinPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement inputUnits() {
		List<MobileElement> r = getElements(locinputUnits); 
		
		if (r.size() > 0) {
			return r.get(0);
		} else
			return null;
	}

	public MobileElement inputAddANote() {
		List<MobileElement> r = getElements(locinputAddANote); 
		
		if (r.size() > 1) {
			return r.get(1);
		} else
			return null;
	}

	public MobileElement labelDateTime() {
		List<MobileElement> r = getElementsbyClassName(locLabelDateTime); 
		
		if (r.size() > 3) {
			return r.get(3);
		} else
			return null;
	}

	public MobileElement buttonLongActing() {
		MobileElement r = getElement(locButtonLongActing); 
		
		return r;
	}

	public MobileElement buttonMixed() {
		MobileElement r = getElement(locButtonMixed); 
		
		return r;
	}

	public MobileElement buttonRapidActing() {
		MobileElement r = getElement(locButtonRapidActing); 
		
		return r;
	}

	public MobileElement labelInsulinDose() {
		MobileElement r = getElement(locLabelInsulinDose); 
		
		return r;
	}

	public MobileElement labelUnits() {
		MobileElement r = getElement(locLabelUnits); 
		
		return r;
	}

	public MobileElement buttonConfirm() {
		MobileElement r = getElement(locButtonConfirm); 
		
		return r;
	}

	public void waitUntilConfirmButtonAppears() {
		waitForPresence(30, buttonConfirm());
	}
}
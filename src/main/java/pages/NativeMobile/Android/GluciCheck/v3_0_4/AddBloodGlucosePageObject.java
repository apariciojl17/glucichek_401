package pages.NativeMobile.Android.GluciCheck.v3_0_4;

import java.util.List;
import org.openqa.selenium.By;

import pages.BasePageMobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class AddBloodGlucosePageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locLabelUnits            = MobileBy.AndroidUIAutomator("new UiSelector().text(\"mg/dL\")");
	private final static By locLabelBloodGlucosetest = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Blood glucose test\")");

	private final static By locButtonBefore          = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Before\")");
	private final static By locButtonAfter           = MobileBy.AndroidUIAutomator("new UiSelector().text(\"After\")");
	private final static By locButtonBedtime         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Bedtime\")");
	private final static By locButtonFasting         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Fasting\")");

	// private final static String locLabelDateTime     = "android.widget.TextView";

	private final static By locButtonStress           = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Stress\")");
	private final static By locButtonPhysicalActivity = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Physical activity\")");
	private final static By locButtonSnacking         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Snacking\")");
	private final static By locButtonLightMeal        = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Light meal\")");
	private final static By locButtonBigMeal          = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Big meal\")");
	private final static By locButtonAlcoholicDrink   = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Alcoholic drink\")");

	private final static By locinputUnits            = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek:id/titanium_ui_edittext\")");
	private final static By locinputAddANote         = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek:id/titanium_ui_edittext\")");

	private final static By locButtonConfirm         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Confirm\")");
	
	public AddBloodGlucosePageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
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

/*	public MobileElement labelDateTime() {
		List<MobileElement> r = getElementsbyClassName(locLabelDateTime); 
		
		if (r.size() > 3) {
			return r.get(3);
		} else
			return null;
	}  */

	public MobileElement buttonBefore() {
		MobileElement r = getElement(locButtonBefore); 
		
		return r;
	}

	public MobileElement buttonAfter() {
		MobileElement r = getElement(locButtonAfter); 
		
		return r;
	}

	public MobileElement buttonBedtime() {
		MobileElement r = getElement(locButtonBedtime); 
		
		return r;
	}

	public MobileElement buttonFasting() {
		MobileElement r = getElement(locButtonFasting); 
		
		return r;
	}

	public MobileElement buttonStress() {
		MobileElement r = getElement(locButtonStress); 
		
		return r;
	}

	public MobileElement buttonPhysicalActivity() {
		MobileElement r = getElement(locButtonPhysicalActivity); 
		
		return r;
	}

	public MobileElement buttonSnacking() {
		MobileElement r = getElement(locButtonSnacking); 
		
		return r;
	}

	public MobileElement buttonLightMeal() {
		MobileElement r = getElement(locButtonLightMeal); 
		
		return r;
	}

	public MobileElement buttonBigMeal() {
		MobileElement r = getElement(locButtonBigMeal); 
		
		return r;
	}

	public MobileElement buttonAlcoholicDrink() {
		MobileElement r = getElement(locButtonAlcoholicDrink); 
		
		return r;
	}
	
	public MobileElement labelBloodGlucoseTest() {
		MobileElement r = getElement(locLabelBloodGlucosetest); 
		
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
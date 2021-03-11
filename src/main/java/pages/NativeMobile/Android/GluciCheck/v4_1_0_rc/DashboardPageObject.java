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

public class DashboardPageObject extends BasePageMobile {
	private final static String expectedPageTitle    = "Not configured yet";

	private final static By locbtnSettings             = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/menu_settings\")");
	private final static By locLabelBloodGlucose       = MobileBy.AndroidUIAutomator("new UiSelector().text(\"BLOOD GLUCOSE\")");
	/*private final static By locLabelGluciCheck         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"GLUCI-CHEK\")");
	
	private final static By locLabelMyFood             = MobileBy.AndroidUIAutomator("new UiSelector().text(\"MY FOOD\")");
	private final static By locLabelFindAFoodDish      = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Find a food / dish\")");
	private final static By locLabelViewLog            = MobileBy.AndroidUIAutomator("new UiSelector().text(\"View Log\")");
	private final static By locLabelMyMonth            = MobileBy.AndroidUIAutomator("new UiSelector().text(\"MY MONTH\")");
	private final static By locLabelObj                = MobileBy.AndroidUIAutomator("new UiSelector().text(\"OBJ.\")");
	private final static By locLabelSix                = MobileBy.AndroidUIAutomator("new UiSelector().text(\"6\")");
	private final static By locLabelNA                 = MobileBy.AndroidUIAutomator("new UiSelector().text(\"na\")");
	
	private final static By locLabelDayOnAverage       = MobileBy.AndroidUIAutomator("new UiSelector().text(\"/ day on average\")");
	private final static By locLabelInsulin            = MobileBy.AndroidUIAutomator("new UiSelector().text(\"RAPID-ACTING INSULIN\")");
	private final static By locLabelInjections         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"injections / day on average\")");
	private final static By locLabelHypoglycemia       = MobileBy.AndroidUIAutomator("new UiSelector().text(\"HYPOGLYCEMIA\")");
	private final static By locLabelHypoglycemiaValue  = MobileBy.AndroidUIAutomator("new UiSelector().text(\"0\")");
	
	private final static By locButtonPlus              = MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)");
	private final static By locOptionInsulin           = MobileBy.AndroidUIAutomator("new UiSelector().text(\"INSULIN\")");
	private final static By locOptionMeter             = MobileBy.AndroidUIAutomator("new UiSelector().text(\"METER\")");
	private final static By locOptionBloodGlucose      = MobileBy.AndroidUIAutomator("new UiSelector().text(\"BLOOD GLUCOSE\")");
		
	private final static By locInsulinAdded            = MobileBy.AndroidUIAutomator("new UiSelector().text(\"17\")");
	private final static By locBloodGlucoseAdded       = MobileBy.AndroidUIAutomator("new UiSelector().text(\"134\")");
	private final static By locInsulinAddedUnits       = MobileBy.AndroidUIAutomator("new UiSelector().text(\"mg/dL\")");
	private final static By locBloodGlucoseAddedUnits  = MobileBy.AndroidUIAutomator("new UiSelector().text(\"units\")");
*/	
	private final static By loclabelMyJournal          = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.roche.glucichek.rc:id/my_journal_title_tv\")");

	public DashboardPageObject(String _expectedPageTitle, AppiumDriver<MobileElement> _driver) {
		super(_expectedPageTitle, _driver);
	}

	public MobileElement btnSettings() {
		MobileElement r = getElement(locbtnSettings); 
		
		return r;
	}

	public MobileElement labelBloodGlucose() {
		MobileElement r = getElement(locLabelBloodGlucose); 
		
		return r;
	}

	public MobileElement labelMyJournal() {
		MobileElement r = getElement(loclabelMyJournal); 
		
		return r;
	}
	
/*	public MobileElement labelMyFood() {
		MobileElement r = getElement(locLabelMyFood); 
		
		return r;
	}

	public MobileElement labelFindAFoodDish() {
		MobileElement r = getElement(locLabelFindAFoodDish); 
		
		return r;
	}

	public MobileElement labelViewLog() {
		MobileElement r = getElement(locLabelViewLog); 
		
		return r;
	}

	public MobileElement labelMyMonth() {
		MobileElement r = getElement(locLabelMyMonth); 
		
		return r;
	}


	public MobileElement labelObj() {
		MobileElement r = getElement(locLabelObj); 
		
		return r;
	}
	public MobileElement labelSix() {
		MobileElement r = getElement(locLabelSix); 
		
		return r;
	}
	public MobileElement labelNA() {
		MobileElement r = getElement(locLabelNA); 
		
		return r;
	}

	public MobileElement labelDayOnAverage() {
		MobileElement r = getElement(locLabelDayOnAverage); 
		
		return r;
	}

	public MobileElement labelInsulin() {
		MobileElement r = getElement(locLabelInsulin); 
		
		return r;
	}

	public MobileElement labelInjections() {
		MobileElement r = getElement(locLabelInjections); 
		
		return r;
	}

	public MobileElement labelHypoglycemia() {
		MobileElement r = getElement(locLabelHypoglycemia); 
		
		return r;
	}
	
	public MobileElement labelHypoglycemiaValue() {
		MobileElement r = getElement(locLabelHypoglycemiaValue); 
		
		return r;
	}

	public MobileElement buttonPlus() {
		List<MobileElement> r = getElements(locButtonPlus); 
		
		return r.get(r.size()-1);
	}

	public MobileElement menuOptionInsulin() {
		MobileElement r = getElement(locOptionInsulin); 
		
		return r;
	}

	public MobileElement menuOptionMeter() {
		MobileElement r = getElement(locOptionMeter); 
		
		return r;
	}

	public MobileElement insulinAdded() {
		MobileElement r = getElement(locInsulinAdded); 
		
		return r;
	}

	public MobileElement bloodGlucoseAdded() {
		MobileElement r = getElement(locBloodGlucoseAdded); 
		
		return r;
	}

	public MobileElement insulinAddedUnits() {
		MobileElement r = getElement(locInsulinAddedUnits); 
		
		return r;
	}

	public MobileElement bloodGlucoseAddedUnits() {
		MobileElement r = getElement(locBloodGlucoseAddedUnits); 
		
		return r;
	}
	
	public MobileElement menuOptionBloodGlucose() {
		List<MobileElement> r = getElements(locOptionBloodGlucose); 
		
		if (r.size() > 1) {
			return r.get(1);
		} else
			return null;
	}*/

	// This is dynamic, so it will be generated when is requested  
	public MobileElement labelMyJournalDate() {
		ZoneId defaultZoneId1 = ZoneId.systemDefault();
		// LocalDate localDate1 = LocalDate.now().minusDays(minusDays);
		LocalDate localDate1 = LocalDate.now();

		Date da1 = Date.from(localDate1.atStartOfDay(defaultZoneId1).toInstant());
		String selector = new SimpleDateFormat("EEEE MMMMM d, YYYY").format(da1);

		MobileElement r = getElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + selector + "\")")); 
		
		return r;
	}

	// This is dynamic, so it will be generated when is requested  
	public MobileElement labelMyMonthDate() {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.now().minusMonths(1);
		Date da = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        String timeStamp4 = new SimpleDateFormat("MMMMM dd").format(da);

        String selector = "From " + timeStamp4 + " to today";

		MobileElement r = getElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + selector + "\")")); 
		
		return r;
	}	
}
package nativeMobile.android.glucichek.v3_0_4;

/* import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date; */
import java.util.concurrent.TimeUnit;
/* import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; */
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import base.TestService;

import pages.NativeMobile.Android.GluciCheck.v3_0_4.*;

/*import NativeMobile.Android.GluciCheck.v3_0_4.AddBloodGlucosePageObject;
import NativeMobile.Android.GluciCheck.v3_0_4.AddInsulinPageObject;
import NativeMobile.Android.GluciCheck.v3_0_4.ConfirmPageObject;
import NativeMobile.Android.GluciCheck.v3_0_4.DashboardPageObject;
import NativeMobile.Android.GluciCheck.v3_0_4.PresentationPageObject; */
// import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
// import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.appmanagement.ApplicationState;

public class ConfirmAndPresentation extends TestBase {
	private final String expectedValueAppName                       = "Accu-Chek Smart Pix Online";
	// private final int minusDays = 0;
	private final String expectedAppName = "com.roche.glucichek.rc";
    private String appId = "";
    private AndroidDriver<MobileElement> driver = null;
    private ConfirmPageObject ConfirmPage;
    private PresentationPageObject PresentationPage;
    private DashboardPageObject dashboardPage;
    private AddInsulinPageObject addInsulinPage;
    private AddBloodGlucosePageObject addBloodGlucosePage;
    private String messageAppWentToBackground = "The App unexpectedly went to the backgroung";

	private void CheckIfAppIsStillRunning() throws InterruptedException {
		if (driver.queryAppState(appId) == ApplicationState.RUNNING_IN_BACKGROUND) {
	        TestService.WriteAWarning(messageAppWentToBackground);
	    	driver.activateApp(appId);
	        Thread.sleep(500);
	    }

		TestService.checkExactMatch(driver.queryAppState(appId).toString(), ApplicationState.RUNNING_IN_FOREGROUND.toString(), "App running state");	
	}

	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setUp(ITestContext ctx) {
		driver = (AndroidDriver<MobileElement>) TestBase.getDriver(TestBase.APP_TYPE_MOBILE_NATIVE, TestBase.OS_ANDROID, null);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		ConfirmPage = new ConfirmPageObject(expectedValueAppName, driver);
		PresentationPage = new PresentationPageObject(expectedValueAppName, driver);
		dashboardPage = new DashboardPageObject(expectedValueAppName, driver);
		addInsulinPage = new AddInsulinPageObject(expectedValueAppName, driver);
		addBloodGlucosePage = new AddBloodGlucosePageObject(expectedValueAppName, driver);

		appId = driver.getCurrentPackage();
	}

	@Test
	public void tc01_CheckAppPackageName() {
        TestService.checkExactMatch(appId, expectedAppName, "App package name");	
	}

	@Test
	public void tc02_WaitForConfirmPageObjectsToAppear() {
        ConfirmPage.waitUntilAgreeTermsAppears();
        ConfirmPage.waitUntilFirebaseTermsAppears();
        ConfirmPage.waitUntilConfirmButtonAppears();
	}

	@Test
	public void tc03_ClickAllConfirmPageObjects() {
        ConfirmPage.toggleAgreeTerms().click();
        ConfirmPage.toggleFirebaseTerms().click();
        ConfirmPage.buttonConfirm().click();
	}

	@Test
	public void tc04_CheckIfAppStillRunning() throws InterruptedException {
		CheckIfAppIsStillRunning();
	}

	@Test
	public void tc05_CheckPresentationPageOne() {
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageOne());
        TestService.checkExactMatch(PresentationPage.titlePageOne(), "Food holds no secrets for you", "Presentation's Page One Title");	
  
        PresentationPage.buttonNext().click();
	}

	@Test
	public void tc06_CheckPresentationPageTwo() {
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageTwo());
        TestService.checkExactMatch(PresentationPage.titlePageTwo(), "Your diabetes logbook", "Presentation's Page Two Title");	
  
        PresentationPage.buttonNext().click();
	}

	@Test
	public void tc07_CheckPresentationPageThree() {
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageThree());
        TestService.checkExactMatch(PresentationPage.titlePageThree(), "Visualize your glycemia", "Presentation's Page Three Title");	
  
        PresentationPage.buttonNext().click();
	}

	@Test
	public void tc08_CheckPresentationPageFour() {
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageFour());
        TestService.checkExactMatch(PresentationPage.titlePageFour(), "An application that suits you", "Presentation's Page Four Title");	
  
        PresentationPage.buttonNext().click();
	}

	@Test
	public void tc09_CheckPresentationPageFive() {
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageFive());
        TestService.checkExactMatch(PresentationPage.titlePageFive(), "Share your data", "Presentation's Page Five Title");	
  
        PresentationPage.buttonNextPageFiveOrSix().click();
	}

	@Test
	public void tc10_CheckPresentationPageSix() {
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageSix());
        TestService.checkExactMatch(PresentationPage.titlePageSix(), "Pair your meter", "Presentation's Page Six Title");	
  
        PresentationPage.buttonNextPageFiveOrSix().click();
	}

	@Test
	public void tc11_CheckAppStillRunning() throws InterruptedException {
		CheckIfAppIsStillRunning();
	}

	@Test
    public void tc12_CheckEmptyDashboard() throws InterruptedException {
        TestService.checkExactMatch(dashboardPage.labelMyFood(), "MY FOOD", "Dashboard - 'MY FOOD' label");	
        TestService.checkExactMatch(dashboardPage.labelFindAFoodDish(), "Find a food / dish", "Dashboard - 'Find a food / dish' label");	
        TestService.checkExactMatch(dashboardPage.labelMyJournal(), "MY JOURNAL", "Dashboard - 'MY JOURNAL' label");	
        TestService.checkExactMatch(dashboardPage.labelViewLog(), "View Log", "Dashboard - 'View log' label");	
        TestService.checkExactMatch(dashboardPage.labelMyMonth(), "MY MONTH", "Dashboard - 'MY MONTH' label");	

        TestService.checkPresenceAndVisibility(dashboardPage.labelMyJournalDate(), "My Journal Date");
        TestService.checkPresenceAndVisibility(dashboardPage.labelMyMonthDate(), "My Month Date");

        TestService.checkExactMatch(dashboardPage.labelBloodGlucose(), "BLOOD GLUCOSE", "Dashboard - 'BLOOD GLUCOSE' label");	
        TestService.checkExactMatch(dashboardPage.labelObj(), "OBJ.", "Dashboard - 'OBJ.' label");	
        TestService.checkExactMatch(dashboardPage.labelSix(), "6", "Dashboard - 'Number 6' label");	
        TestService.checkExactMatch(dashboardPage.labelNA(), "na", "Dashboard - 'BloodGlucose Qty.' label");	
        TestService.checkExactMatch(dashboardPage.labelDayOnAverage(), "/ day on average", "Dashboard - '/ day on average' label");	
        TestService.checkExactMatch(dashboardPage.labelInsulin(), "RAPID-ACTING INSULIN", "Dashboard - 'RAPID-ACTING INSULIN' label");	
        TestService.checkExactMatch(dashboardPage.labelInjections(), "injections / day on average", "Dashboard - 'injections / day on average' label");	
        TestService.checkExactMatch(dashboardPage.labelHypoglycemia(), "HYPOGLYCEMIA", "Dashboard - 'HYPOGLYCEMIA' label");	
        TestService.checkExactMatch(dashboardPage.labelHypoglycemiaValue(), "0", "Dashboard - 'Hypoglycemia Value' label");	
	}

	@Test
	public void tc13_VerifyMenuPlus() {
        dashboardPage.buttonPlus().click();

        // System.out.println(driver.getPageSource().toString());
        
        TestService.checkPresenceAndVisibility(dashboardPage.menuOptionInsulin(), "Menu option: Insulin");
        TestService.checkExactMatch(dashboardPage.menuOptionInsulin(), "INSULIN", "Menu option text: INSULIN");
        TestService.checkPresenceAndVisibility(dashboardPage.menuOptionMeter(), "Menu option: Meter");
        TestService.checkExactMatch(dashboardPage.menuOptionMeter(), "METER", "Menu option text: METER");
        TestService.checkPresenceAndVisibility(dashboardPage.menuOptionBloodGlucose(), "Menu option: Blood Glucose");
        TestService.checkExactMatch(dashboardPage.menuOptionBloodGlucose(), "BLOOD GLUCOSE", "Menu option text: BLOOD GLUCOSE");
        // dashboardPage.optionInsulin().click();
	}

	@Test
	public void tc14_OpenMenuInsulin() {
		dashboardPage.menuOptionInsulin().click();
        // System.out.println(driver.getPageSource().toString());
	}

	@Test
	public void tc15_checkElementsAddInsulinInterface() {
        TestService.checkExactMatch(addInsulinPage.labelInsulinDose(), "Insulin dose", "Insulin dose - Interface title");	
        TestService.checkExactMatch(addInsulinPage.labelUnits(), "Units", "Insulin dose - Units label");	
        TestService.checkExactMatch(addInsulinPage.buttonRapidActing(), "RAPID-ACTING", "Insulin dose - 'RAPID-ACTING' button text");	
        TestService.checkExactMatch(addInsulinPage.buttonLongActing(), "LONG-ACTING", "Insulin dose - 'LONG-ACTING' button text");	
        TestService.checkExactMatch(addInsulinPage.buttonMixed(), "MIXED", "Insulin dose - 'MIXED' button text");	
        TestService.checkExactMatch(addInsulinPage.inputAddANote(), "Add a note", "Insulin dose - 'Add a note' input textbox default grayed text");	

        // The dateTime itself is being constructed in a wrong way, when this test was written, it says "Apr 15, 2020 22:43PM" (24 hour format and PM?) Besides, the emulator doesn't have the 24 Hour datetime setting activated...
	    // Additionally, the selected datetime in the pop-up window is not copied exactly in the textbox... 
	}

	@Test
	public void tc16_fillAndSelectOnInsulinDose() {
        addInsulinPage.inputUnits().sendKeys("17");	
        addInsulinPage.buttonLongActing().click();	
        addInsulinPage.inputAddANote().sendKeys("Insulin note filled By the QA Automated script...");	
        addInsulinPage.buttonConfirm().click();
        // The dateTime itself is being constructed in a wrong way, when this test was written, it says "Apr 15, 2020 22:43PM" (24 hour format and PM?) Besides, the emulator doesn't have the 24 Hour datetime setting activated...
	    // Additionally, the selected datetime in the pop-up window is not copied exactly in the textbox... 
	}

	@Test
	public void tc17_OpenMenuBloodGlucose() {
        dashboardPage.buttonPlus().click();
		
        dashboardPage.waitUntilElementAppears(dashboardPage.menuOptionBloodGlucose());
		dashboardPage.menuOptionBloodGlucose().click();
        // System.out.println(driver.getPageSource().toString());
	}

	@Test
	public void tc18_checkElementsAddBloodGlucoseInterface() {
        TestService.checkExactMatch(addBloodGlucosePage.labelBloodGlucoseTest(), "Blood glucose test", "Blood glucose test - Interface title");	

        TestService.checkExactMatch(addBloodGlucosePage.labelUnits(), "mg/dL", "Blood glucose test - Units label");	

        TestService.checkExactMatch(addBloodGlucosePage.buttonBefore(),           "Before", "Blood glucose test - Before button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonAfter(),            "After", "Blood glucose test - After button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonBedtime(),          "Bedtime", "Blood glucose test - Bedtime button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonFasting(),          "Fasting", "Blood glucose test - Fasting button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonStress(),           "Stress", "Blood glucose test - Stress button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonPhysicalActivity(), "Physical activity", "Blood glucose test - Physical activity button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonSnacking(),         "Snacking", "Blood glucose test - Snacking button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonLightMeal(),        "Light meal", "Blood glucose test - Light meal button text");	
        TestService.checkExactMatch(addBloodGlucosePage.buttonBigMeal(),          "Big meal", "Blood glucose test - Big meal button text");	
        // TestService.checkExactMatch(addBloodGlucosePage.buttonAlcoholicDrink(),   "Alcoholic drink", "Blood glucose test - Alcoholic drink button text");	

        TestService.checkExactMatch(addBloodGlucosePage.inputAddANote(), "Add a note", "Blood glucose test - 'Add a note' input textbox default grayed text");	

        // The dateTime itself is being constructed in a wrong way, when this test was written, it says "Apr 15, 2020 22:43PM" (24 hour format and PM?) Besides, the emulator doesn't have the 24 Hour datetime setting activated...
	    // Additionally, the selected datetime in the pop-up window is not copied exactly in the textbox... 
	}

	@Test
	public void tc19_fillAndSelectOnBloodGlucose() {
		addBloodGlucosePage.inputUnits().sendKeys("134");	
        addBloodGlucosePage.buttonBedtime().click();	
        addBloodGlucosePage.buttonSnacking().click();	
        addBloodGlucosePage.inputAddANote().sendKeys("Blood Glucose note filled By the QA Automated script...");	

        addBloodGlucosePage.buttonConfirm().click();
	}

	@Test
	public void tc20_GoBackToDashboard() {
		driver.navigate().back();
	}

	@Test
	public void tc21_ValidateCurrentPageIsDashboard() {
	    TestService.checkExactMatch(dashboardPage.labelMyFood(), "MY FOOD", "Dashboard - 'MY FOOD' label");	
	    TestService.checkExactMatch(dashboardPage.labelMyJournal(), "MY JOURNAL", "Dashboard - 'MY JOURNAL' label");	
	    TestService.checkExactMatch(dashboardPage.labelMyMonth(), "MY MONTH", "Dashboard - 'MY MONTH' label");	

	    // System.out.println(driver.getPageSource().toString());
	}

	@Test
	public void tc22_CheckInsulinAndBGAppearsInDashboard() {
	    TestService.checkExactMatch(dashboardPage.insulinAdded(), "17", "Dashboard - '17' value for insulin added label");	
	    TestService.checkExactMatch(dashboardPage.bloodGlucoseAdded(), "134", "Dashboard - '134' value for blood glucose added label");	
	    TestService.checkExactMatch(dashboardPage.insulinAddedUnits(), "mg/dL", "Dashboard - 'mg/dL' units for insulin added label");	
	    TestService.checkExactMatch(dashboardPage.bloodGlucoseAddedUnits(), "units", "Dashboard - 'units' units for blood glucose added label");	
	}

	@Test
	public void tc23_VerifyAppWasUnInstalled() {
        driver.removeApp(appId);

        TestService.checkExactMatch(driver.isAppInstalled(expectedAppName), false, "App is installed");	
    	TestBase.clearDriver(driver);    				
	}
}

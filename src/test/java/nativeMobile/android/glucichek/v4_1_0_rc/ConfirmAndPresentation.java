package nativeMobile.android.glucichek.v4_1_0_rc;

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

import pages.NativeMobile.Android.GluciCheck.v4_1_0_rc.*;

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
    private SettingsPageObject settingsPage;
    private PrivacySettingsPageObject privacySettingsPage;

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
		settingsPage = new SettingsPageObject(expectedValueAppName, driver);
		privacySettingsPage = new PrivacySettingsPageObject(expectedValueAppName, driver);

		appId = driver.getCurrentPackage();
	}

	@Test
	public void tc01_CheckAppPackageName() {
        TestService.checkExactMatch(appId, expectedAppName, "App package name");	
	}

	@Test
	public void tc02_WaitForConfirmPageObjectsToAppear() {
        ConfirmPage.waitUntilAgreeTermsOfUseAppears();
       	ConfirmPage.waitUntilAgreePrivacyPolicyAppears();
      	ConfirmPage.waitUntilAnalyticsConsentAppears();
        // ConfirmPage.waitUntilCrashReportConsentAppears();
	}

	@Test
	public void tc03_ClickAllConfirmPageObjects() {
        ConfirmPage.toggleTermsOfUse().click();
        ConfirmPage.togglePrivacyPolicy().click();
        ConfirmPage.toggleAnalyticsConsent().click();
        // ConfirmPage.toggleCrashReportConsent().click();
	}

	@Test
	public void tc05_CheckPresentationPageOne() {
        ConfirmPage.buttonConfirm().click();
        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageOne());
        TestService.checkExactMatch(PresentationPage.titlePageOne(), "Finding food is at your fingertips", "Presentation's Page One Title");	
  
        PresentationPage.waitUntilElementAppears(PresentationPage.buttonNext());
	}

	@Test
	public void tc06_CheckPresentationPageTwo() {
        PresentationPage.buttonNext().click();

        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageTwo());
        TestService.checkExactMatch(PresentationPage.titlePageTwo(), "Your diabetes journal", "Presentation's Page Two Title");	  
	}

	@Test
	public void tc07_CheckPresentationPageThree() {
        PresentationPage.buttonNext().click();

        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageThree());
        TestService.checkExactMatch(PresentationPage.titlePageThree(), "Keep your diabetes on track", "Presentation's Page Three Title");	  
	}

	@Test
	public void tc08_CheckPresentationPageFour() {
        PresentationPage.buttonNext().click();

        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageFour());
        TestService.checkExactMatch(PresentationPage.titlePageFour(), "An application that suits you", "Presentation's Page Four Title");	  
	}

	@Test
	public void tc09_CheckPresentationPageFive() {
        PresentationPage.buttonNext().click();

        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageFive());
        TestService.checkExactMatch(PresentationPage.titlePageFive(), "Share your data", "Presentation's Page Five Title");	
	}

	@Test
	public void tc10_CheckPresentationPageSix() {
        PresentationPage.buttonNext().click();

        PresentationPage.waitUntilElementAppears(PresentationPage.titlePageSix());
        TestService.checkExactMatch(PresentationPage.titlePageSix(), "Connect your device", "Presentation's Page Six Title");	
	}

	@Test
	public void tc11_ClosePresentationPage() {
        PresentationPage.buttonGotIt().click();

        dashboardPage.waitUntilElementAppears(dashboardPage.labelBloodGlucose());
        TestService.checkExactMatch(dashboardPage.labelMyJournal(), "My journal", "Label 'My journal'");	
	}

	@Test
	public void tc12_ClickOnSettingsButton() {
        dashboardPage.btnSettings().click();

        settingsPage.waitUntilElementAppears(settingsPage.txtWindowTitle());
        TestService.checkExactMatch(settingsPage.txtWindowTitle(), "Settings", "Window title 'Settings'");	
	}

	@Test
	public void tc13_ScrollDownToPrivacySettings() throws InterruptedException {
        settingsPage.waitUntilElementAppears(settingsPage.btnPrivacySettings());

        TestService.checkPresenceAndVisibility(settingsPage.btnPrivacySettings(), "'Privacy settings' menu option");
	}

	@Test
	public void tc14_CheckPrivacySettings() throws InterruptedException {
		settingsPage.btnPrivacySettings().click();

        privacySettingsPage.waitUntilElementAppears(privacySettingsPage.btnCrashReporting());
        TestService.checkExactMatch(privacySettingsPage.txtWindowTitle(), "Privacy settings", "Window title 'Privacy settings'");

        TestService.checkIsChecked(privacySettingsPage.btnCrashReporting(), "false", "Button 'App crash reporting'");
	}

	@Test
	public void tc15_VerifyAppWasUnInstalled() {
        driver.removeApp(appId);

        TestService.checkExactMatch(driver.isAppInstalled(expectedAppName), false, "App is installed");	
    	TestBase.clearDriver(driver);    				
	}
}

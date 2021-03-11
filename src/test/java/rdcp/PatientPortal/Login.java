package rdcp.PatientPortal;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.NavigationService;
import base.TestBase;
import base.TestService;
import pages.rdcp.pageObjects.PatientPortal.DashboardPageObject;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;
import rdcp.DoctorPortal.AddPatient;

public class Login extends TestBase {
	// private SignInPage signInPage;
	private LoginPageObject loginPage;
	private DashboardPageObject dashboardPage;
	private final String expectedValueAppName            = "Roche Diabetes Care Platform";  // changed on  September 2, 2019, changed again on October 11, 2019 
	private final String expectedValueLabelEmail         = "email";
	private final String expectedValueLabelPassword      = "password";	
	private final String expectedValueLabelSignIn        = "sign in";
	private final String expectedValueLinkForgotPassword = "forgot password";
	private final String expectedValueButtonLogIn        = "sign in";
	private WebDriver mainWebDriver;

	private void processVariablesPassingBetweenClases(ITestContext _ctx) {
		String patientEmail = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_EMAIL);

    	if (patientEmail != null) {  // this means some other test set this variable and it should be used in this test, otherwise use the ones that came trough a system console parameter -D... 
    		TestBase.patientLoginUsername = patientEmail; 
    		TestBase.patientLoginPassword = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_PASSWORD);
    		TestBase.patientLoginName = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_FULLNAME);
    	}
	}

	@BeforeClass
	public void setUp(ITestContext ctx) {
		processVariablesPassingBetweenClases(ctx);
		// mainWebDriver = TestBase.getDriver();
		mainWebDriver = (WebDriver) TestBase.getDriver(TestBase.APPTYPE_WEB, TestBase.getBrowserType());
		
		loginPage = new LoginPageObject(expectedValueAppName, mainWebDriver);
		dashboardPage = new DashboardPageObject(expectedValueAppName, mainWebDriver);
	}

	@Test
	public void tc1_verifyPageElements() {	
		NavigationService.goToAPage(TestBase.patientAppURL, mainWebDriver);
		
		loginPage.waitUntilLoadComplete();
		
		TestService.checkContainsTitle(loginPage);
		TestService.checkPresenceAndVisibilityAndValue(loginPage.labelSignIn(), expectedValueLabelSignIn, "SignIn label");
		TestService.checkPresenceAndVisibilityAndValue(loginPage.labelEmail(), expectedValueLabelEmail, "Label for Email input");
		TestService.checkPresenceAndVisibilityAndValue(loginPage.labelPassword(), expectedValueLabelPassword, "Label for Password input");
		TestService.checkPresenceAndVisibilityAndValue(loginPage.linkForgotPassword(), expectedValueLinkForgotPassword, "Forgot Password label");
		TestService.checkPresenceAndVisibilityAndValueByAttribute(loginPage.buttonLogIn(), expectedValueButtonLogIn, "LogIn Button");		
		TestService.checkDisabled(loginPage.buttonLogIn(), "Button Sign In");

		loginPage.inputLogIn().sendKeys("X");
		loginPage.inputPassword().sendKeys("Y");
		loginPage.inputLogIn().click();
		
		TestService.checkEnabled(loginPage.buttonLogIn(), "Button Sign In");
	}

	@Test
	public void tc2_verifyLogin() {
		// pass null as the URL because the current page should be the login page
		NavigationService.Login(null, loginPage, dashboardPage.locDeliveryLabel, TestBase.patientLoginUsername, TestBase.patientLoginPassword);

		dashboardPage.waitUntilLoadComplete();

		dashboardPage.waitUntilPatientFullname(TestBase.patientLoginName.toUpperCase());

		// This may seem redundant but it's not, because this method call will reflect on the report that the check was effectively performed
		// In the method dashboardPage.waitUntilPatientFullname should not be any Asserts because is a Page Object
		TestService.checkExactMatch(dashboardPage.labelPatientName(), TestBase.patientLoginName.toUpperCase(), "Patient Name");		
	}

	@Test
	public void tc3_verifyLogout() {			
		NavigationService.Logout(dashboardPage, loginPage);
		
		loginPage.waitUntilLoadComplete();
	}

	@AfterClass
	public void cleanUp(ITestContext ctx) {
		// Note: to accelerate the execution of chained tests, this driver can be reused
		mainWebDriver.close();
		
		TestBase.clearDriver(mainWebDriver);
	}
}
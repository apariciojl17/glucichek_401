package rdcp.DoctorPortal;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.NavigationService;
import base.TestBase;
import base.TestService;
import base.UtilService;
import pages.PopUpModalPageObject;
import pages.rdcp.pageObjects.DoctorPortal.DeactivatePatientPageObject;
import pages.rdcp.pageObjects.DoctorPortal.DoctorDashboardPageObject;
import pages.rdcp.pageObjects.DoctorPortal.PatientDashboardPageObject;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;

public class DeactivatePatient extends TestBase {
	private WebDriver mainWebDriver;
	
	private LoginPageObject loginPage;
	private DoctorDashboardPageObject doctorDashboardPage;
	private PatientDashboardPageObject patientDashboardPage;
	private DeactivatePatientPageObject patientDeactivatePage;
	private PopUpModalPageObject popupModal;
	private final String expectedValueAppName                       = "Accu-Chek Smart Pix Online";
	private final String messageAreYouSure = "do you want to deactivate this patient?";
	private final String messageNoResults = "no results.";
	private final String messageSuccessfullySaved = "Successfully saved";
	private final String titleDeactivatePatient = "Deactivate Patient";
	private final String reason = "Change professional";
	private final String comments = "-- This text is 251 characters long and ends with a trail -- 234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890-";
	private final int maxLengthCommentsField = 250;
	private final String titlePatientInfoSavedWindow = "Deactivate Patient";

	public static final String NEW_PATIENT_HEALTHCAREID = "newPatientHealthCareID";
	
	private void processVariablesPassingBetweenClases(ITestContext _ctx) {
		String temp_patientEmail = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_EMAIL);

		if (temp_patientEmail != null) {  // this means some other test set this variable and it should be used in this test, otherwise use the ones that came trough a system console parameter -D... 
    		patientLoginUsername = temp_patientEmail; 
    		
    		patientLoginPassword = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_PASSWORD);
    		patientLoginName = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_FULLNAME);
    		patientFirstName = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_FIRSTNAME);
    		patientLastName = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_LASTNAME);
    		patientHealthCareID = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_HEALTHCAREID);
    		patientDateOfBirthDay = Integer.valueOf( (String)_ctx.getAttribute(AddPatient.NEW_PATIENT_DATEOFBIRTH_DAY));
    		patientDateOfBirthMonth = Integer.valueOf( (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_DATEOFBIRTH_MONTH));
    		patientDateOfBirthYear = Integer.valueOf( (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_DATEOFBIRTH_YEAR));
    		patientDiabetesType = (String) _ctx.getAttribute(AddPatient.NEW_PATIENT_DIABETES_TYPE);
    	}

		String temp_professionalLoginUsername = (String) _ctx.getAttribute(AddPatient.NEW_PROFESSIONAL_LOGIN);
		if (temp_professionalLoginUsername != null) {
			professionalLoginUsername = temp_professionalLoginUsername;
			professionalLoginPassword = (String) _ctx.getAttribute(AddPatient.NEW_PROFESSIONAL_PASSWORD);
			professionalLoginName = (String) _ctx.getAttribute(AddPatient.NEW_PROFESSIONAL_NAME);
		}		
	}

	@BeforeClass
	public void setUp(ITestContext ctx) {
		processVariablesPassingBetweenClases(ctx);

		mainWebDriver = (WebDriver) TestBase.getDriver(TestBase.APPTYPE_WEB, TestBase.getBrowserType());
		
		loginPage = new LoginPageObject(expectedValueAppName, mainWebDriver);
		doctorDashboardPage = new DoctorDashboardPageObject(expectedValueAppName, mainWebDriver);
		patientDashboardPage = new PatientDashboardPageObject(expectedValueAppName, mainWebDriver);
		patientDeactivatePage = new DeactivatePatientPageObject(titleDeactivatePatient, mainWebDriver); 
		popupModal = new PopUpModalPageObject(titlePatientInfoSavedWindow, PopUpModalPageObject.DEACTIVATE_PATIENT, mainWebDriver);
	}

	@Test
	public void tc01_verifyLogin() {
		NavigationService.Login(TestBase.professionalAppURL, loginPage, doctorDashboardPage.locCreateNewPatientButton, professionalLoginUsername, professionalLoginPassword);

		doctorDashboardPage.waitUntilLoadComplete();		
		
		TestService.checkExactMatch(doctorDashboardPage.labelProfessionalName(), professionalLoginName, "Doctor Name");		
	}
	
	@Test
	public void tc02_SearchPatient() {
		TestService.setAndCheckInput(doctorDashboardPage.inputPatientID(), patientHealthCareID, "Patient ID", false);  // Let this time test the "Search button" (not the "Press Enter to Search" functionality)
		
		doctorDashboardPage.buttonSearch().click();
		
		doctorDashboardPage.waitUntilSearchIsDone();
	}
	
	@Test
	public void tc03_ValidateSearchGrid() {
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsRows().size(), 1, "Search table header - number of patients found");
	}	

	@Test
	public void tc04_ValidatePatientDashboard() {
		doctorDashboardPage.buttonViewPatientProfile(0).click();

		patientDashboardPage.waitUntilLoadComplete();
		
		TestService.checkExactMatch(patientDashboardPage.labelFirstNameLastName(), patientFirstName + "\n" + patientLastName, "Patient Dashboard - Patient First & Last name");

		patientDashboardPage.labelPatientSettingMenu().click();

		patientDashboardPage.waitUntilPatientMenuDisplays();
	}	

	@Test
	public void tc05_SetReasonAndComments() {
		patientDashboardPage.menuOptionDeactivatePatient().click();

		patientDeactivatePage.waitUntilDeactivateLoadComplete();

		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, patientDeactivatePage.labelDeactivatePatient(), "Deactivate Patient");

		TestService.checkExactMatch(patientDeactivatePage.labelReason(), "Reason", "Deactivate Patient - Reason label");
		TestService.checkExactMatch(patientDeactivatePage.labelComments(), "Comments", "Deactivate Patient - Comments label");

		patientDeactivatePage.comboboxReason().click();
		UtilService.selectComboBoxOption(patientDeactivatePage.comboboxReason(), reason, "Reason");
		TestService.checkExactMatch(patientDeactivatePage.inputReasonForRead(), reason, "Deactivate Patient - Reason");

		TestService.setAndCheckInput(patientDeactivatePage.inputComments(), comments.substring(0, maxLengthCommentsField), "Deactivate Patient - Comments", false);
	}

	@Test
	public void tc06_CancelDeactivate() {
		patientDeactivatePage.buttonCancel().click();

		patientDashboardPage.waitUntilLoadComplete();		

		TestService.checkExactMatch(patientDashboardPage.labelFirstNameLastName(), patientFirstName + "\n" + patientLastName, "Patient Dashboard - Patient First & Last name");
		
		doctorDashboardPage.buttonHome().click();
		
		doctorDashboardPage.waitUntilLoadComplete();
	}

	@Test
	public void tc07_SearchPatientAgain() {
		TestService.setAndCheckInput(doctorDashboardPage.inputPatientID(), patientHealthCareID, "Patient ID", false);  // Let this time test the "Search button" (not the "Press Enter to Search" functionality)
		
		doctorDashboardPage.buttonSearch().click();
		
		doctorDashboardPage.waitUntilSearchIsDone();
	}

	@Test
	public void tc08_ValidatePatientDashboard() {
		doctorDashboardPage.buttonViewPatientProfile(0).click();

		patientDashboardPage.waitUntilLoadComplete();   

		TestService.checkExactMatch(patientDashboardPage.labelFirstNameLastName(), patientFirstName + "\n" + patientLastName, "Patient Dashboard - Patient First & Last name");

		patientDashboardPage.labelPatientSettingMenu().click();

		patientDashboardPage.waitUntilPatientMenuDisplays();
	}	

	@Test
	public void tc09_ClickOnDeactivatePatient() {
		patientDashboardPage.menuOptionDeactivatePatient().click();
		
		patientDeactivatePage.waitUntilDeactivateLoadComplete();

		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, patientDeactivatePage.labelDeactivatePatient(), "Deactivate Patient");

		TestService.checkExactMatch(patientDeactivatePage.labelReason(), "Reason", "Deactivate Patient - Reason label");
		TestService.checkExactMatch(patientDeactivatePage.labelComments(), "Comments", "Deactivate Patient - Comments label");
		patientDeactivatePage.comboboxReason().click();
		UtilService.selectComboBoxOption(patientDeactivatePage.comboboxReason(), reason, "Reason");
		TestService.checkExactMatch(patientDeactivatePage.inputReasonForRead(), reason, "Deactivate Patient - Reason");

		TestService.setAndCheckInput(patientDeactivatePage.inputComments(), comments.substring(0, maxLengthCommentsField), "Deactivate Patient - Comments", false);	
	}	

	@Test
	public void tc10_DeactivatePatient() {
		patientDeactivatePage.buttonDeactivatePatient().click();
		
		popupModal.waitUntilAcceptButtonAppears();
		
		TestService.checkExactMatch(popupModal.getPageTitle(), titlePatientInfoSavedWindow, "Deactivate Patient -  Window Title");
		TestService.checkExactMatch(popupModal.labelMessage(), messageAreYouSure, "Deactivate Patient - Confirmation Pop Up Message");
	}

	@Test
	public void tc11_DeactivatePatientClickCancel() {
		popupModal.buttonCancel().click();
		
		TestService.checkPresenceAndVisibility(patientDeactivatePage.labelComments(), "Deactivate Patient - Reason & Comments input");
		TestService.checkPresenceAndVisibility(patientDeactivatePage.labelReason(), "Deactivate Patient - Reason & Comments input");		
	}

	@Test
	public void tc12_ClickOnDeactivatePatient() {
		patientDeactivatePage.buttonDeactivatePatient().click();
		
		popupModal.waitUntilAcceptButtonAppears();		
	}

	@Test
	public void tc13_DeactivatedPatient() {
		popupModal.buttonAccept().click();

		PopUpModalPageObject popupModal2 = new PopUpModalPageObject(titlePatientInfoSavedWindow, PopUpModalPageObject.DEACTIVATE_PATIENT_SUCCESFULLY_SAVED, mainWebDriver);
		popupModal2.waitUntilOKButtonAppears();		
		TestService.checkExactMatch(popupModal.labelMessage(), messageSuccessfullySaved, "Deactivate Patient - Successfully Saved message"); // TODO: VERIFY THIS CHANGE
	}
	
	@Test
	public void tc14_SearchForDeactivatedPatient() {
		popupModal.buttonOK().click();
		
		doctorDashboardPage.waitUntilLoadComplete();		

		TestService.setAndCheckInput(doctorDashboardPage.inputPatientID(), patientHealthCareID, "Patient ID", false);  // Let this time test the "Search button" (not the "Press Enter to Search" functionality)		
		doctorDashboardPage.buttonSearch().click();
		
		doctorDashboardPage.waitUntilNoResultsAppears();
				
		TestService.checkPresenceAndVisibility(doctorDashboardPage.labelNoResults(), "Deactivate Patient - 'No results' message label");
		TestService.checkExactMatch(doctorDashboardPage.labelNoResults(), messageNoResults, "Deactivate Patient - 'No results' message label");
	}

    @AfterClass(alwaysRun = true)
    public void insertValueIntoAttribute(ITestContext ctx) {
        ctx.setAttribute(NEW_PATIENT_HEALTHCAREID, patientHealthCareID);

		// Note: to accelerate the execution of chained tests, this driver can be reused
		mainWebDriver.close();
		
		TestBase.clearDriver(mainWebDriver);
    } 
}

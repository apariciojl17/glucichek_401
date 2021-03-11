package rdcp.DoctorPortal;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.NavigationService;
import base.TestBase;
import base.TestService;
import pages.PopUpModalPageObject;
import pages.rdcp.pageObjects.DoctorPortal.DoctorDashboardPageObject;
import pages.rdcp.pageObjects.DoctorPortal.ModifyPatientPageObject;
import pages.rdcp.pageObjects.DoctorPortal.PatientDashboardPageObject;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;

public class ModifyPatient extends TestBase {
	private WebDriver mainWebDriver; 
	
	private LoginPageObject loginPage;
	private DoctorDashboardPageObject doctorDashboardPage;
	private PatientDashboardPageObject patientDashboardPage;
	private ModifyPatientPageObject patientEditPage;
	private PopUpModalPageObject popupModal;
	private final String expectedValueAppName                       = "Accu-Chek Smart Pix Online";
	private final String[] searchTableHeaders                       = {"PATIENT NAME", "ID NUMBER", "DATE OF BIRTH", "DIABETES TYPE", ""};
	private final String[] menuPatientSettings                      = {"Edit Patient", "Time Blocks", "Glucose Target Ranges", "Devices", "Deactivate Patient"};
	private final String[] titlesFramePatientInfo                   = {"patient number", "date of birth:", "diabetes type:", "center:", "patient settings"};

	private final String expectedButtonViewPatientProfile           = "View Patient Profile";
	private final String messageSuccesfullySaved = "Successfully saved";
	private final String titlePatientInfoSavedWindow = "Patient Info";
	private final String messageThisFieldIsRequired                 = "This field is required.";
	
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
		patientEditPage = new ModifyPatientPageObject(expectedValueAppName, mainWebDriver);
		popupModal = new PopUpModalPageObject(titlePatientInfoSavedWindow, PopUpModalPageObject.MODIFY_PATIENT, mainWebDriver);
	}

	@Test
	public void tc01_verifyLogin() {
		NavigationService.Login(TestBase.professionalAppURL, loginPage, doctorDashboardPage.locCreateNewPatientButton, professionalLoginUsername, professionalLoginPassword);

		doctorDashboardPage.waitUntilLoadComplete();		
		
		TestService.checkExactMatch(doctorDashboardPage.labelProfessionalName(), professionalLoginName, "Doctor Name");		
	}
	
	@Test
	public void tc02_NavigateToModifyPatient() {
		TestService.setAndCheckInput(doctorDashboardPage.inputPatientID(), patientHealthCareID, "Patient ID", false);  // Let this time test the "Search button" (not the "Press Enter to Search" functionality)
		
		doctorDashboardPage.buttonSearch().click();
		
		// TestBase.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		doctorDashboardPage.waitUntilSearchIsDone();
	}
	
	@Test
	public void tc03_ValidateSearchGrid() {
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsRows().size(), 1, "Search table header - number of patients found");
		
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsHeaders().size(), 5, "Search table header - number of columns");
		
		for(int i=0; i<searchTableHeaders.length; i++) {
			TestService.checkExactMatch(doctorDashboardPage.tablePatientsHeaders().get(i), searchTableHeaders[i], "Search table header column #" + i + " ('" + searchTableHeaders[i] + "')");			
		} 

		String patientDateOfBirth = patientDateOfBirthDay + "/" + String.format("%02d", patientDateOfBirthMonth) + "/" + patientDateOfBirthYear;
		
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsCols(0).get(0), patientLoginName, "Search table header results - Patient Name");
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsCols(0).get(1), patientHealthCareID, "Search table header results - Patient HealthCare ID");
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsCols(0).get(2), patientDateOfBirth, "Search table header results - Patient Date of Birth");
		TestService.checkExactMatch(doctorDashboardPage.tablePatientsCols(0).get(3), patientDiabetesType, "Search table header results - Patient Diabetes Type");		
		
		TestService.checkExactMatch(doctorDashboardPage.buttonViewPatientProfile(0), expectedButtonViewPatientProfile, "Search table header - Button View Patient Text");
	}	

	@Test
	public void tc04_ValidatePatientDashboard() {
		doctorDashboardPage.buttonViewPatientProfile(0).click();
		// TestBase.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		patientDashboardPage.waitUntilLoadComplete();

		// HCPSPOL-437
		List<WebElement> x = patientDashboardPage.framePatientInfoTitles();
		for(int i=0; i<titlesFramePatientInfo.length; i++) {
			TestService.checkExactMatch(x.get(i), titlesFramePatientInfo[i], "Patient Info Frame Titles - Option #" + i + " ('" + titlesFramePatientInfo[i] + "')");			
		} 
		// End of: HCPSPOL-437
		
		TestService.checkExactMatch(patientDashboardPage.labelFirstNameLastName(), patientFirstName + "\n" + patientLastName, "Patient Dashboard - Patient First & Last name");
		// TestService.checkExactMatch(patientDashboardPage.labelFirstNameLastName(), patientLoginName, "Patient Dashboard - Patient First & Last name");

		patientDashboardPage.labelPatientSettingMenu().click();

		patientDashboardPage.waitUntilPatientMenuDisplays();
		
		for(int i=0; i<menuPatientSettings.length; i++) {
			TestService.checkExactMatch(patientDashboardPage.menuPatientSettings().get(i), menuPatientSettings[i], "Patient Settings Menu - Option #" + i + " ('" + menuPatientSettings[i] + "')");			
		} 
	}	

	@Test
	public void tc05_ChangeHealthCareID() {
		patientDashboardPage.menuOptionEditPatient().click();
		
		patientEditPage.waitUntilLoadComplete();

		patientEditPage.tabPatientInfo().click();		

		patientEditPage.waitUntilPatientInfoDisplays();	
		
		/* Begin of: Validate mandatory fields:
		At this stage, some fields are empty, therefore the button "Next" should be disabled if those fields are mandatory
		Additionally, set the focus on this field and retiring the focus, should put visible a label with a message like "This field is required..." 
		*/

		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, patientEditPage.labelPatientInfo(), "Edit Patient Info");

		String finalHealthCareID = patientHealthCareID;
		// finalHealthCareID = finalHealthCareID.replace("new", "mod");
		finalHealthCareID = finalHealthCareID + "mod";
		// 	Begin of: Validating "HealthCare ID
			patientEditPage.inputHealthCareID().sendKeys(Keys.CONTROL,"a");
			patientEditPage.inputHealthCareID().sendKeys(Keys.BACK_SPACE);
			patientEditPage.inputLastName().click();
			
			// These three previous sentences should trigger the appearance of the message label indicating the required field		
			// Randomly, this test fails for no apparent reason, saying that the expected differs from the actual value when at the assert time
			// Therefore, a "wait for some text to appear" has been implemented here for the labelHealthCareIDRequired() field
			patientEditPage.waitUntilHealthCareIDRequiredApeears(messageThisFieldIsRequired);
			TestService.checkExactMatch(patientEditPage.labelHealthCareIDRequired(), messageThisFieldIsRequired, "HealthCare ID is required Message label"); 		
			TestService.checkDisabled(patientEditPage.buttonSaveEditPatientInfo(), "Edit Patient Info - Button Save");
			TestService.setAndCheckInput(patientEditPage.inputHealthCareID(), finalHealthCareID, "Patient - HealthCare ID", false);
		// End of: Validating "HealthCare ID
	
		//  End of:  Validate mandatory fields
		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, patientEditPage.labelPatientInfo(), "Edit Patient Info");
		
		patientHealthCareID = finalHealthCareID;
	}	

	@Test
	public void tc06_SaveHealthCareIDChanges() {
		// This assumes the button Save is enabled (the previous test should end correctly in order for this to continue...) 
		patientEditPage.buttonSaveEditPatientInfo().click();
		
		popupModal.waitUntilOKButtonAppears();
		
		TestService.checkExactMatch(popupModal.getPageTitle(), titlePatientInfoSavedWindow, "Patient Info Saved -  Window Title");
		TestService.checkExactMatch(popupModal.labelMessage(), messageSuccesfullySaved, "Patient Info Saved -  Confirmation Pop Up Message");
	}

    @AfterClass(alwaysRun = true)
    public void insertValueIntoAttribute(ITestContext ctx) {
        ctx.setAttribute(AddPatient.NEW_PATIENT_EMAIL, patientLoginUsername);
        ctx.setAttribute(AddPatient.NEW_PATIENT_HEALTHCAREID, patientHealthCareID);

        ctx.setAttribute(AddPatient.NEW_PATIENT_PASSWORD, patientLoginPassword);
        ctx.setAttribute(AddPatient.NEW_PATIENT_FULLNAME, patientLoginName);
        ctx.setAttribute(AddPatient.NEW_PATIENT_FIRSTNAME, patientFirstName);
        ctx.setAttribute(AddPatient.NEW_PATIENT_LASTNAME, patientLastName);
        ctx.setAttribute(AddPatient.NEW_PATIENT_DATEOFBIRTH_DAY, patientDateOfBirthDay.toString());
        ctx.setAttribute(AddPatient.NEW_PATIENT_DATEOFBIRTH_MONTH, patientDateOfBirthMonth.toString());
        ctx.setAttribute(AddPatient.NEW_PATIENT_DATEOFBIRTH_YEAR, patientDateOfBirthYear.toString());
        ctx.setAttribute(AddPatient.NEW_PATIENT_DIABETES_TYPE, patientDiabetesType);
        
    	ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_LOGIN, professionalLoginUsername);        
        ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_PASSWORD, professionalLoginPassword);        
        ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_NAME, professionalLoginName);

		// Note: to accelerate the execution of chained tests, this driver can be reused
		mainWebDriver.close();
		
		TestBase.clearDriver(mainWebDriver);
    } 
}

package rdcp.DoctorPortal;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.rdcp.pageObjects.DoctorPortal.CreateNewPatientPageObject;
import base.NavigationService;
import base.TestBase;
import base.TestService;
import base.UtilService;
import pages.rdcp.pageObjects.DoctorPortal.DoctorDashboardPageObject;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;
import pages.rdcp.pageObjects.PatientPortal.PatientDashboardPageObject;
import pages.rdcp.pageObjects.PatientPortal.PatientSetPasswordPageObject;

public class AddPatient extends TestBase {
	// private SignInPage signInPage;
	private WebDriver mainWebDriver;
	
	private LoginPageObject loginPage;
	private DoctorDashboardPageObject doctorDashboardPage;
	private CreateNewPatientPageObject CreateNewPatientPage;
	private PatientDashboardPageObject patientDashboardPage;
	private PatientSetPasswordPageObject patientSetPasswordPage;
	private final String expectedValueAppName                       = "RocheDiabetes Care Platform";
	private final String expectedValueLabelSearchForExistingPatient = "search for existing patient";
	private final String expectedValueLabelEnterPatientID           = "enter patient id and/or patient's full name";	
	private final String expectedValueLabelPatientID                = "patient id";
	private final String expectedValueLabelName                     = "name";
	private final String expectedValueButtonSearch                  = "search";
	private final String expectedValueButtonGoToDashBoard           = "go to dashboard";
	private final String expectedValueButtonCreateNewPatient        = "create new patient";
	private String firstName                                        = "First";
	private String lastName                                         = "Last";	
	private final String healthCareID                               = "HCID";	

	private String emailTemplate                                    = null;	
	private String temporaryEmail                                   = null;
	private String finalEmail                                       = null;
	private String finalPassword                                    = null;
	private String finalFullName                                    = null;
	private String finalFirstName                                   = null;
	private String finalLastName                                    = null;
	private String finalHealthCareID                                = null;
	// private String patientURLToSetAPassword 					    = null;
	private final String phoneNumber                                = "+34";	
	private final String dateOfBirthMonth                           = "Aug";	
	private final String dateOfBirthMonthNumber                    = "8";	
	private final String dateOfBirthDay                             = "17";	
	private final String dateOfBirthYear                            = "2020";	
	private final String language                                   = "English (GB)";	
	private final String street										= "Evita Peron";
	private final String province									= "Province";
	private final String postalcode									= "ZipCode";
	private final String city										= "Buenos Aires";
	private final String country									= "Argentina";
	private final String diabetesType								= "Type 2 MDI";
	// test:  private final String[] associatedProfessionals                  = {"Joanne Batten", "Jane Smith", "Merce Vila"};
	// qa spanish: private final String[] associatedProfessionals                  = {"nombre12566 apellido1 apellido2", "nombre12666 apellido1 apellido2", "nombre12667 apellido1 apellido2"};
	// qa spanish: private final String[] associatedProfessionals                  = {"nombre12566 apellido1 apellido2", "nombre12666 apellido1 apellido2", "nombre12667 apellido1 apellido2"};
	private final String[] associatedProfessionals                  = {"00018First 00018Surname 00018SecondSurname", "00144First 00144Surname 00144SecondSurname"};   // qa english: 
	// private final String[] associatedProfessionals                  = {"Rex Dev Master 2", "connect App", "Who ARE YOU DOCTOR", "ZALI ZAL ZALA"}; // test
	private final String[] menuOptionsDiabetesType                  = {"type 1", "type 2", "gestational", "type 2 mdi"};
	private final String messageTaskComplete                        = "Task Complete";
	// private final String expectedValueLabelCreationPassword			= "You have successfully created a new password.";   // this message was changed on September 16 or before...
	private final String expectedValueLabelCreationPassword			= "Success. You have created a new password for your Account";
	private final String messageExperiencingIssues                  = "looks like we are experiencing some issues.";
	private final String messageThisFieldIsRequired                 = "This field is required.";
	private final String subjectPasswordResetConfirmation           = "Password reset confirmation";
	// private boolean continueTheTest = true;	

	public static final String NEW_PATIENT_EMAIL_TEMPLATE = "newPatientEmailTemplate";
	public static final String NEW_PATIENT_EMAIL = "newPatientEmail";
	public static final String NEW_PATIENT_PASSWORD = "finalPassword";
	public static final String NEW_PATIENT_FULLNAME = "newPatientFullname";
	public static final String NEW_PATIENT_FIRSTNAME = "newPatientFirstname";
	public static final String NEW_PATIENT_LASTNAME = "newPatientLastname";
	public static final String NEW_PATIENT_HEALTHCAREID = "newPatientHealthCareID";
	public static final String NEW_PATIENT_DATEOFBIRTH_DAY = "newPatientDateOfBirthDay";
	public static final String NEW_PATIENT_DATEOFBIRTH_MONTH = "newPatientDateOfBirthMonth";
	public static final String NEW_PATIENT_DATEOFBIRTH_YEAR = "newPatientDateOfBirthYear";
	public static final String NEW_PATIENT_DIABETES_TYPE = "newPatientDiabetesType";
	public static final String NEW_PROFESSIONAL_LOGIN = "newProfessionalLogin";
	public static final String NEW_PROFESSIONAL_PASSWORD = "newProfessionalPassword";
	public static final String NEW_PROFESSIONAL_NAME = "newProfessionalName";

	private void processVariablesPassingBetweenClases(ITestContext _ctx) {
		emailTemplate = TestBase.getEmailTemplate();
		finalPassword = TestBase.getPasswordTemplate();

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
		CreateNewPatientPage = new CreateNewPatientPageObject(expectedValueAppName, mainWebDriver); 
		patientDashboardPage = new PatientDashboardPageObject(expectedValueAppName, mainWebDriver);
		patientSetPasswordPage = new PatientSetPasswordPageObject(expectedValueAppName, mainWebDriver);
	}

	@Test
	public void tc01_verifyLogin() {
		NavigationService.Login(TestBase.professionalAppURL, loginPage, doctorDashboardPage.locCreateNewPatientButton, professionalLoginUsername, professionalLoginPassword);

		doctorDashboardPage.waitUntilLoadComplete();
		
		TestService.checkExactMatch(doctorDashboardPage.labelProfessionalName(), professionalLoginName, "Doctor Name");		
	}

	@Test
	public void tc02_verifyPageElements() {	
		TestService.checkContainsTitle(doctorDashboardPage);		
		
		TestService.checkPresenceAndVisibilityAndValue(doctorDashboardPage.labelSearchForExistingPatient(), expectedValueLabelSearchForExistingPatient, "Search for Existing Patient label");

		TestService.checkPresenceAndVisibilityAndValue(doctorDashboardPage.labelEnterPatientID(), expectedValueLabelEnterPatientID, "Enter Patient ID and/or patient's full name label");
		
		TestService.checkPresenceAndVisibilityAndValue(doctorDashboardPage.labelPatientID(), expectedValueLabelPatientID, "Label for Patient ID");
		
		TestService.checkPresenceAndVisibilityAndValue(doctorDashboardPage.labelPatientName(), expectedValueLabelName, "Label for Name input");

		TestService.checkPresenceAndVisibilityAndValue(doctorDashboardPage.buttonSearch(), expectedValueButtonSearch, "Search Button");

		TestService.checkPresenceAndVisibilityAndValue(doctorDashboardPage.buttonCreateNewPatient(), expectedValueButtonCreateNewPatient, "Create New Patient Button");

		TestService.checkEnabled(doctorDashboardPage.buttonSearch(), "Button Search");		

		TestService.checkEnabled(doctorDashboardPage.buttonCreateNewPatient(), "Button Create New Patient");		
	}

	@Test
	public void tc03_NavigateToCreateNewPatient() {
		doctorDashboardPage.buttonCreateNewPatient().click();				
		
		try {
			// Wait for 3 elements to appear to consider the page load is complete
			CreateNewPatientPage.waitUntilStepONEPickUpAtCenterAppears();
			CreateNewPatientPage.waitUntilStepONEPatientPortalAccessAppears();
			CreateNewPatientPage.waitUntilStepONENextButtonAppears();

			TestService.checkExactMatch(CreateNewPatientPage.labelCreateNewPatient(), expectedValueButtonCreateNewPatient, "Create New Patient Label");				
		} catch(Exception e) {
			// find the label that indicates a problem: "Looks like we are experiencing some issues."
			if (CreateNewPatientPage.labelMessageExperiencingIssues().isDisplayed()) {
				TestService.checkExactMatch(CreateNewPatientPage.labelMessageExperiencingIssues(), messageExperiencingIssues, "Label 'Experiencing issues'");
				TestBase.setAbortTestSuite(true);
				throw new SkipException("The functionality 'Add a patient' is experiencing issues.");
			}
		}
	}

	@Test
	public void tc04_AddPatientStepONE() {
		// Step 1: Profile Type
		TestService.clickUsingJavascript(mainWebDriver, CreateNewPatientPage.radioPickUpAtCenter(), "Pickup at Center");
		TestService.checkRadioButtonOrCheckBox(CreateNewPatientPage.radioPickUpAtCenter(), "Pickup at Center", true);

		TestService.setCheckedValue(CreateNewPatientPage.checkboxPatientPortalAccess(), true, "Patient Portal Access");
		TestService.checkRadioButtonOrCheckBox(CreateNewPatientPage.checkboxPatientPortalAccess(), "Patient Portal Access", true);

		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, CreateNewPatientPage.buttonNextStepONE(), "Button Next");
	}

	@Test
	public void tc05_AddPatientStepTWO() {
		// Let the "Next" click on this step so the previous step can take the screenshot exactly as all the options were selected on the page
		CreateNewPatientPage.buttonNextStepONE().click();

		// Get a random number and concatenate it to all fields
		String random = UtilService.getRandom();
		
		TestService.drawRectangle(CreateNewPatientPage.driver, CreateNewPatientPage.labelFirstName() , CreateNewPatientPage.inputFirstName(), "message");
		// Step 2: Patient info
		firstName = random + firstName;
		TestService.setAndCheckInput(CreateNewPatientPage.inputFirstName(), firstName, "Patient - First Name", false);
		TestService.unDrawRectangle(CreateNewPatientPage.driver);
		
		TestService.drawRectangle(CreateNewPatientPage.driver, CreateNewPatientPage.labelLastName() , CreateNewPatientPage.inputLastName(), "message");
		lastName = random + lastName;
		TestService.setAndCheckInput(CreateNewPatientPage.inputLastName(), lastName, "Patient - Last Name", false);
		TestService.unDrawRectangle(CreateNewPatientPage.driver);
		
		finalFullName = firstName + " " + lastName;  
		finalFirstName = firstName;  
		finalLastName = lastName;
		
		TestService.setInput(CreateNewPatientPage.inputDateOfBirthMonth(), dateOfBirthMonth, "Patient - DOB (Month)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputDateOfBirthMonthForRead(), dateOfBirthMonth, "Patient - DOB (Month)");

		TestService.setInput(CreateNewPatientPage.inputDateOfBirthDay(), dateOfBirthDay, "Patient - DOB (Day)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputDateOfBirthDayForRead(), dateOfBirthDay, "Patient - DOB (Day)");

		TestService.setInput(CreateNewPatientPage.inputDateOfBirthYear(), dateOfBirthYear, "Patient - DOB (Year)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputDateOfBirthYearForRead(), dateOfBirthYear, "Patient - DOB (Year)");
		
		TestService.drawRectangle(CreateNewPatientPage.driver, CreateNewPatientPage.labelEmail(), CreateNewPatientPage.inputEmail(), "message");
		temporaryEmail = random + emailTemplate;
		TestService.setAndCheckInput(CreateNewPatientPage.inputEmail(), random + emailTemplate, "Patient - Email", false);
		TestService.unDrawRectangle(CreateNewPatientPage.driver);

		TestService.drawRectangle(CreateNewPatientPage.driver, CreateNewPatientPage.labelPhoneNumber() , CreateNewPatientPage.inputPhoneNumber(), "message");
		TestService.setAndCheckInput(CreateNewPatientPage.inputPhoneNumber(), phoneNumber + random, "Patient - Phone Number", false);
		TestService.unDrawRectangle(CreateNewPatientPage.driver);

		/*CreateNewPatientPage.comboboxLanguage2().click();
		UtilService.selectComboBoxOption(CreateNewPatientPage.itemsListComboboxLanguage2(), language, "Language");
		TestService.checkExactMatch(CreateNewPatientPage.inputLanguageForRead2(), language, "Patient - Language");*/

		CreateNewPatientPage.comboboxLanguage().click();
		UtilService.selectComboBoxOption(CreateNewPatientPage.itemsListComboboxLanguage(), language, "Language");
		TestService.checkExactMatch(CreateNewPatientPage.inputLanguageForRead(), language, "Patient - Language");
		
		TestService.setAndCheckInput(CreateNewPatientPage.inputStreet(), random + street, "Patient - Street", false);
		TestService.setAndCheckInput(CreateNewPatientPage.inputProvince(), random + province, "Patient - Province", false);
		TestService.setAndCheckInput(CreateNewPatientPage.inputPostalCode(), random + postalcode, "Patient - Postal Code", false);
		TestService.setAndCheckInput(CreateNewPatientPage.inputCity(), random + city, "Patient - City", false);

		CreateNewPatientPage.comboboxCountry().click();
		UtilService.selectComboBoxOption(CreateNewPatientPage.itemsListComboboxCountry(), country, "Country");
		TestService.checkExactMatch(CreateNewPatientPage.inputCountryForRead(), country, "Patient - Country");

		/* Begin of: Validate mandatory fields:
			At this stage, some fields are empty, therefore the button "Next" should be disabled if those fields are mandatory
			Additionally, set the focus on this field and retiring the focus, should put visible a label with a message like "This field is required..." 
		*/
		
			// Begin of: Validating "HealthCare ID
				TestService.ensureItsVisibleUsingJavascript(mainWebDriver, CreateNewPatientPage.inputLastName(), "Trying to put the HealthCare ID into the current viewport");
				TestService.drawRectangle(CreateNewPatientPage.driver, CreateNewPatientPage.labelHealthCareID(), CreateNewPatientPage.inputHealthCareID(), 20, 0, "message");
			
				CreateNewPatientPage.inputHealthCareID().sendKeys("X");
				// CreateNewPatientPage.inputHealthCareID().clear();
				CreateNewPatientPage.inputHealthCareID().sendKeys(Keys.CONTROL,"a");
				CreateNewPatientPage.inputHealthCareID().sendKeys(Keys.BACK_SPACE);
				// CreateNewPatientPage.inputHealthCareID().sendKeys();
				// TestService.clearUsingJavascript(CreateNewPatientPage.inputHealthCareID(), "HealthCare ID");
				
				CreateNewPatientPage.inputPhoneNumber().click();
				
				TestService.checkExactMatch(CreateNewPatientPage.labelHealthCareIDRequired(), messageThisFieldIsRequired, "HealthCare ID is required Message label"); 		
				TestService.checkDisabled(CreateNewPatientPage.buttonNextStepTWO(), "Step TWO - Button Next");
				TestService.setAndCheckInput(CreateNewPatientPage.inputHealthCareID(), random + healthCareID, "Patient - HealthCare ID", false);

				TestService.unDrawRectangle(CreateNewPatientPage.driver);
			// End of: Validating "HealthCare ID

		//  End of:  Validate mandatory fields		

		finalHealthCareID = random + healthCareID;

		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, CreateNewPatientPage.labelEmail(), "Label Email");
	}

	@Test
	public void tc06_AddPatientStepTHREE() {
		// Let the "Next" click on this step so the previous step can take the screenshot exactly as all the options were selected on the page
		CreateNewPatientPage.buttonNextStepTWO().click();

		// Step 3: Health Info
		CreateNewPatientPage.comboboxDiabetesType().click();

		String[] menuOptions = CreateNewPatientPage.itemsListComboboxDiabetesType().getText().toLowerCase().split("\\n");

		UtilService.selectComboBoxOption(CreateNewPatientPage.itemsListComboboxDiabetesType(), diabetesType, "Diabetes type");
		TestService.checkExactMatch(CreateNewPatientPage.inputDiabetesTypeForRead(), diabetesType, "Patient - Diabetes Type Combobox");

 		// Check Diabetes type menu options     HCPSPOL-552
		TestService.checkExactMatch(menuOptions, menuOptionsDiabetesType, "Patient - Diabetes Type Combobox Items");
		
		CreateNewPatientPage.waitForitemsListComboboxAssociatedProfessionalToAppear(associatedProfessionals[0]);
		CreateNewPatientPage.comboboxAssociatedProfessional().click();
		UtilService.selectComboBoxOption(CreateNewPatientPage.itemsListComboboxAssociatedProfessional(), associatedProfessionals, "Associated Professionals");
		TestService.checkContains(CreateNewPatientPage.inputAssociatedProfessionalsForRead(), associatedProfessionals, "Patient - Associated Professional(s)");
		// CreateNewPatientPage.comboboxAssociatedProfessional().click();   // in some tests the combobox remains open
		
		TestService.setInput(CreateNewPatientPage.inputDateOfDiagnosisMonth(), dateOfBirthMonth, "Patient - Date Of Diagnosis (Month)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputDateOfDiagnosisMonthForRead(), dateOfBirthMonth, "Patient - Date Of Diagnosis (Month)");

		TestService.setInput(CreateNewPatientPage.inputDateOfDiagnosisDay(), dateOfBirthDay, "Patient - Date Of Diagnosis (Day)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputDateOfDiagnosisDayForRead(), dateOfBirthDay, "Patient - Date Of Diagnosis (Day)");

		TestService.setInput(CreateNewPatientPage.inputDateOfDiagnosisYear(), dateOfBirthYear, "Patient - Date Of Diagnosis (Year)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputDateOfDiagnosisYearForRead(), dateOfBirthYear, "Patient - Date Of Diagnosis (Year)");

		// System.out.println("Male radio button value: '" + CreateNewPatientPage.radioButtonGenderMale().getAttribute("value") + "'");

		TestService.clickUsingJavascript(mainWebDriver, CreateNewPatientPage.radioButtonGenderFemale(), "Patient - Gender - Female");		
		TestService.checkRadioButtonOrCheckBox(CreateNewPatientPage.radioButtonGenderFemale(), "Patient - Gender - Female", true);

		TestService.clickUsingJavascript(mainWebDriver, CreateNewPatientPage.radioButtonPregnantYes(), "Patient - Is Pregnant");		
		TestService.checkRadioButtonOrCheckBox(CreateNewPatientPage.radioButtonPregnantYes(), "Patient - Is Pregnant", true);
		
		TestService.setInput(CreateNewPatientPage.inputGestationalDueDateMonth(), dateOfBirthMonth, "Patient - Gestational Date (Month)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputGestationalDueDateMonthForRead(), dateOfBirthMonth, "Patient - Gestational Date (Month)");

		TestService.setInput(CreateNewPatientPage.inputGestationalDueDateDay(), dateOfBirthDay, "Patient - Gestational Date  (Day)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputGestationalDueDateDayForRead(), dateOfBirthDay, "Patient - Gestational Date  (Day)");

		TestService.setInput(CreateNewPatientPage.inputGestationalDueDateYear(), dateOfBirthYear, "Patient - Gestational Date  (Year)", true);
		TestService.checkExactMatch(CreateNewPatientPage.inputGestationalDueDateYearForRead(), dateOfBirthYear, "Patient - Gestational Date  (Year)");
		
		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, CreateNewPatientPage.labelDiabetesType(), "Label Diabetes type");
	}

	@Test
	public void tc07_AddPatientStepFOUR() {
		// Step 4: Confirm
		CreateNewPatientPage.buttonNextStepTHREE().click();   // This no "Next" button, its a "Create Patient" button! 

		CreateNewPatientPage.waitUntilPatientCreated();

		// Verify the text on the labels to ensure the Patient was created
		TestService.checkExactMatch(CreateNewPatientPage.labelTaskComplete(), messageTaskComplete, "Patient - Task Complete Label");
		TestService.checkExactMatch(CreateNewPatientPage.labelMessageConfirmation(), "New Patient, " + firstName + " " + lastName + " Successfully created", "Patient - Confirmation Label");
		
		TestService.checkPresenceAndVisibilityAndValue(CreateNewPatientPage.buttonGoToDashboard(), expectedValueButtonGoToDashBoard, "Go To Dashboard Button");
		finalEmail = temporaryEmail;
		
		// Use this variable from now on to search for this patient, look for it in the results grid, see its details, etc.
		// If "finalEmail is not null", then the testcases could be executed 
	}

	@Test
	public void tc08_verifyPatientDashboard() {
		CreateNewPatientPage.buttonGoToDashboard().click();

		patientDashboardPage.waitUntilLoadComplete();
	}	

	@Test
	public void tc09_verifyLogout() {
		NavigationService.Logout(doctorDashboardPage, loginPage);		
	}
	
	@Test
	public void tc10_verifyPatientLinkToSetAPassword() {
		// System.out.println("New patient email: " + finalEmail);
		String patientURLToSetAPassword = NavigationService.getLinkFromEmail(finalEmail, "here");
	
		if (patientURLToSetAPassword != null) {
			NavigationService.goToPasswordChangePage(patientURLToSetAPassword, mainWebDriver);
		} else {
			fail("AddPatient: The email for the password set could not be retrieved on time.");
		}
		
		patientSetPasswordPage.waitUntilLoadComplete();
	}

	@Test
	public void tc11_verifyPatientSetPassword() {
		patientSetPasswordPage.inputNewPassword().clear();
		patientSetPasswordPage.inputNewPassword().sendKeys(finalPassword);

		patientSetPasswordPage.inputReEnterPassword().clear();
		patientSetPasswordPage.inputReEnterPassword().sendKeys(finalPassword);

		patientSetPasswordPage.waitUntilContinueIsEnabled();
		patientSetPasswordPage.buttonContinue().click();
		
		// Wait for the confirmation message
		patientSetPasswordPage.waitUntilConfirmationMessage(expectedValueLabelCreationPassword);

		TestService.checkPresenceAndVisibilityAndValue(patientSetPasswordPage.labelCreatedPasswordSucess(), expectedValueLabelCreationPassword, "Label for Password creation was successful");
	}

	@Test
	public void tc12_verifyPatientSetPasswordSuccessEmail() {
		String subject = NavigationService.getPasswordResetConfirmationSubject(finalEmail);
		
    	TestService.checkExactMatch(subject, subjectPasswordResetConfirmation, "Email Inbox - Password Reset Confirmation");
    	
    	// NavigationService.emptyInbox(finalEmail);
		/* Awaitility.with().pollDelay(1000, TimeUnit.MILLISECONDS).and().pollInterval(3000, TimeUnit.MILLISECONDS).await().atMost(60, TimeUnit.SECONDS).until(() -> 
			AHEM_EmailProvider.isNotEmptyInbox(true) == true
		);
	
	    if (AHEM_EmailProvider.isNotEmptyInbox(false)) {
	    	TestService.checkExactMatch(AHEM_EmailProvider.getSubject(), subjectPasswordResetConfirmation, "Email Inbox - Password Reset Confirmation");
	    	
	    	AHEM_EmailProvider.EmptyInbox();
	    }*/
	}

	@Test
	public void tc13_verifyPatientFinalConfirmation() {
		String patientURLToLogIn = NavigationService.getLinkFromEmail(finalEmail, "sign in");

		// NavigationService.LoginWithoutCodeConfirmation(TestBase.patientAppURL, loginPage, finalEmail, finalPassword);
		NavigationService.LoginWithoutCodeConfirmation(patientURLToLogIn, loginPage, finalEmail, finalPassword);
		
		// this will be used to delete the "you have set the password correctly" information email
		// TestBase.setStartTimestamp();
		loginPage.waitUntilFinalConfirmationLabelAppears();
		
		loginPage.labelPatientFinalConfirmation().click();
		loginPage.buttonEnviar().click();
	}

	@Test
	public void tc14_verifyPatientLinkEmailConfirmation() {
		// TestBase.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.waitUntilAccountVerificationLabelAppears("Account verification");

		String patientURLAccountVerification = NavigationService.getLinkFromEmail(finalEmail, "click here");
	
		if (patientURLAccountVerification != null) {
			NavigationService.goToAPage(patientURLAccountVerification, mainWebDriver);

			loginPage.waitUntilLoadComplete();
			// TestBase.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // TODO: change this for a FluentWait, is not working and the printscreen turns out to be empty.
		}		
	}

    @AfterClass(alwaysRun = true)
    public void insertValueIntoAttribute(ITestContext ctx) {
        ctx.setAttribute(NEW_PATIENT_EMAIL, finalEmail);
        ctx.setAttribute(NEW_PATIENT_PASSWORD, finalPassword);
        ctx.setAttribute(NEW_PATIENT_FULLNAME, finalFullName);
        ctx.setAttribute(NEW_PATIENT_FIRSTNAME, finalFirstName);
        ctx.setAttribute(NEW_PATIENT_LASTNAME, finalLastName);
        ctx.setAttribute(NEW_PATIENT_HEALTHCAREID, finalHealthCareID);
        ctx.setAttribute(NEW_PATIENT_DATEOFBIRTH_DAY, dateOfBirthDay);
        ctx.setAttribute(NEW_PATIENT_DATEOFBIRTH_MONTH, dateOfBirthMonthNumber);
        ctx.setAttribute(NEW_PATIENT_DATEOFBIRTH_YEAR, dateOfBirthYear);
        ctx.setAttribute(NEW_PATIENT_DIABETES_TYPE, diabetesType);

    	ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_LOGIN, professionalLoginUsername);        
        ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_PASSWORD, professionalLoginPassword);        
        ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_NAME, professionalLoginName);

		// Note: to accelerate the execution of chained tests, this driver can be reused
		mainWebDriver.close();
		
		TestBase.clearDriver(mainWebDriver);
    } 
}

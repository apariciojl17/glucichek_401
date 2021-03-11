package rdcp.DoctorPortal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.NavigationService;
import base.TestBase;
import base.TestService;
import base.UtilService;
import pages.PopUpModalPageObject;
import pages.rdcp.pageObjects.DoctorPortal.CreateNewProfessionalPageObject;
import pages.rdcp.pageObjects.DoctorPortal.DoctorDashboardPageObject;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;
import pages.rdcp.pageObjects.PatientPortal.PatientSetPasswordPageObject;

public class AddProfessional extends TestBase {
	private WebDriver mainWebDriver;
	
	private LoginPageObject 				loginPage;
	private DoctorDashboardPageObject 		doctorDashboardPage;
	private CreateNewProfessionalPageObject createProfessionalPage;
	private PatientSetPasswordPageObject 	professionalSetPasswordPage;
	private PopUpModalPageObject 			popupModal;

	private final String expectedValueAppName                       = "Accu-Chek Smart Pix Online";
	private String firstName                                        = "First";
	private String surname                                          = "Surname";	
	// private String secondSurname  = "SecondSurname";	
	// private String birthdayDate = "17/05/1980";
	private final String dateOfBirthMonth                           = "Aug";	
	private final String dateOfBirthDay                             = "17";	
	private final String dateOfBirthYear                            = "2020";	

	private String phone = "612354678";
	private String hcpId = "auto";
	private String language = "lish (GB)";
	private String languageForRead = "English (GB)";
	private String professionalType = "ASTER";
	private String menuOptionAddProfessional = "Add A Professional";
	private String typeForRead = "HCP_MASTER";
	private String finalEmail = null;
	private String finalPassword                                    = null;
	private String finalProfessionalName =  null;
	
	private final String expectedValueLabelCreationPassword			= "Success. You have created a new password for your Account";
	private final String subjectPasswordResetConfirmation           = "Password reset confirmation";
	private final String openConnectivityLabel                      = "Open Connectivity";

	private final String messageSuccesfullySaved = "New professional profile added.";
	private final String titlePatientInfoSavedWindow = "Add a Professional";
	
	public static Integer CURRENT_COUNTER = 103;

	private void processVariablesPassingBetweenClases(ITestContext _ctx) {
		finalEmail = TestBase.getEmailTemplatePrefix() + String.format("%03d" , CURRENT_COUNTER) + TestBase.getEmailTemplate();
		finalPassword = TestBase.getPasswordTemplate();
	}

	@BeforeClass
	public void setUp(ITestContext ctx) {		
		processVariablesPassingBetweenClases(ctx);
		
		mainWebDriver = (WebDriver) TestBase.getDriver(TestBase.APPTYPE_WEB, TestBase.getBrowserType());
		
		loginPage = new LoginPageObject(expectedValueAppName, mainWebDriver);
		doctorDashboardPage = new DoctorDashboardPageObject(expectedValueAppName, mainWebDriver);
		createProfessionalPage = new CreateNewProfessionalPageObject(expectedValueAppName, mainWebDriver);
		professionalSetPasswordPage = new PatientSetPasswordPageObject(expectedValueAppName, mainWebDriver);
		popupModal = new PopUpModalPageObject(titlePatientInfoSavedWindow, PopUpModalPageObject.ADD_PROFESSIONAL, mainWebDriver);
	}

	@Test
	public void tc01_verifyLogin() {
		if (!NavigationService.Login(TestBase.professionalAppURL, loginPage, doctorDashboardPage.locCreateNewPatientButton, TestBase.professionalLoginUsername, TestBase.professionalLoginPassword)) {		TestBase.setAbortTestSuite(true);
				throw new SkipException("It seems that the email server where the code for the TFA is obtained is not working now.");			
			};
	
			doctorDashboardPage.waitUntilLoadComplete();		
			System.out.println("First token in cookies: '" + TestBase.extractTokens(TestBase.getCookies(mainWebDriver), ";", "st2.", ".sc3").get(0) + "'");
	
			TestService.checkExactMatch(doctorDashboardPage.labelProfessionalName(), TestBase.professionalLoginName, "Doctor Name");		
		}

	@Test
	public void tc02_OpenProfessionalMenu() {
		TestBase.getNetworkEvents();		
		doctorDashboardPage.labelProfessionalName().click();
		
		doctorDashboardPage.waitUntilProfessionalMenuDisplays();
	}

	@Test
	public void tc03_ClickOnAddProfessional() {
		if (!UtilService.selectMenuOption(doctorDashboardPage.itemsListMenuProfessional(), menuOptionAddProfessional, "Professional  Menu")) {
			TestBase.setAbortTestSuite(true);
			throw new SkipException("This Professional can't add new Professionals.");
		};
		// doctorDashboardPage.menuOptionAddProfessional().click();

		// createProfessionalPage.waitForiFrameAddProfessionalToAppears();

		// createProfessionalPage.switchTo();

		createProfessionalPage.waitUntilAddProfessionalFormLoads();
	}

	@Test
	public void tc04_FillProfessionalFields() {
		// Get a random number and concatenate it to all fields
		String random = UtilService.getRandom();
		
		// Step 2: Patient info
		firstName = random + firstName;
		TestService.setAndCheckInput(createProfessionalPage.inputFirstName(), firstName, "Professional - First Name", false);
		surname = random + surname;
		// secondSurname = random + secondSurname;
		TestService.setAndCheckInput(createProfessionalPage.inputSurname(), surname , "Professional - Surname", false);		
		// TestService.setAndCheckInput(createProfessionalPage.inputSurname2(), secondSurname, "Professional - Second Surname", false);  */		

		finalProfessionalName = firstName + " " + surname;
		
		// TestService.setValueUsingJavascript(mainWebDriver, createProfessionalPage.inputBirthday(), "Professional - Birthday date", birthdayDate);
		// TestService.setValueUsingJavascript(mainWebDriver, createProfessionalPage.inputBirthdayHidden(), "Professional - Birthday date (hidden)", birthdayDate);

		TestService.setInput(createProfessionalPage.inputDateOfBirthMonth(), dateOfBirthMonth, "Patient - DOB (Month)", true);
		TestService.checkExactMatch(createProfessionalPage.inputDateOfBirthMonthForRead(), dateOfBirthMonth, "Patient - DOB (Month)");

		TestService.setInput(createProfessionalPage.inputDateOfBirthDay(), dateOfBirthDay, "Patient - DOB (Day)", true);
		TestService.checkExactMatch(createProfessionalPage.inputDateOfBirthDayForRead(), dateOfBirthDay, "Patient - DOB (Day)");

		TestService.setInput(createProfessionalPage.inputDateOfBirthYear(), dateOfBirthYear, "Patient - DOB (Year)", true);
		TestService.checkExactMatch(createProfessionalPage.inputDateOfBirthYearForRead(), dateOfBirthYear, "Patient - DOB (Year)");
		
		TestService.setAndCheckInput(createProfessionalPage.inputPhone(), phone, "Professional - Phone", false);		

		// finalEmail = random + finalEmail;
		TestService.setAndCheckInput(createProfessionalPage.inputEmail(), finalEmail, "Professional - Email", false);		

		// TestService.setAndCheckInput(createProfessionalPage.inputEmailConfirmation(), finalEmail, "Professional - Email confirmation", false);		

		hcpId = random + hcpId;
		TestService.setAndCheckInput(createProfessionalPage.inputHCPID(), hcpId, "Professional - HCP ID", false);		

		// for a small window size, an element not visible can not be clicked
		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, createProfessionalPage.comboboxLanguage(), "Professional - Language");		
		
		createProfessionalPage.comboboxLanguage().click();
		//TestService.setInput(createProfessionalPage.inputLanguage(), language, "Professional - Language", true);
		// TestService.checkExactMatch(createProfessionalPage.inputLanguageForRead(), languageForRead, "Professional - Language");
		UtilService.selectComboBoxOption(createProfessionalPage.itemsListComboboxLanguage(), language, "Professional - Language", "div");
		TestService.checkExactMatch(createProfessionalPage.inputLanguageForRead(), languageForRead, "Professional - Language");
		
		// for a small window size, an element not visible can not be clicked
		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, createProfessionalPage.comboboxProfessionalType(), "Professional - Type");		

		createProfessionalPage.comboboxProfessionalType().click();
		UtilService.selectComboBoxOption(createProfessionalPage.itemsListComboboxProfessionalType(), professionalType, "Professional  Type", "div");
		TestService.checkExactMatch(createProfessionalPage.inputProfessionalTypeForRead(), typeForRead, "Professional - Type");
		
		/*createProfessionalPage.radiobuttonOpenConectivityNO().click();
		TestService.checkRadioButtonOrCheckBox(createProfessionalPage.radiobuttonOpenConectivityNO(), "Professional - Open Connectivity", true);
		TestService.checkRadioButtonOrCheckBox(createProfessionalPage.radiobuttonOpenConectivityYES(), "Professional - Open Connectivity", false);  */

		TestService.checkExactMatch(createProfessionalPage.labelOpenConectivity(), openConnectivityLabel, "Professional - Open Connectivity");
		
		/*if (createProfessionalPage.radiobuttonOpenConectivityYES().isEnabled() && createProfessionalPage.radiobuttonOpenConectivityNO().isEnabled()) {
			createProfessionalPage.radiobuttonOpenConectivityYES().click();
			TestService.checkRadioButtonOrCheckBox(createProfessionalPage.radiobuttonOpenConectivityYES(), "Professional - Open Connectivity", true);
			TestService.checkRadioButtonOrCheckBox(createProfessionalPage.radiobuttonOpenConectivityNO(), "Professional - Open Connectivity", false);
		} else {
			ExtentTestManager.getTest().log(Status.INFO, "Step: 'Open connectivity' was SKIPPED because is disabled for this professional");
		}*/
		
		TestService.ensureItsVisibleUsingJavascript(mainWebDriver, createProfessionalPage.buttonSave(), "Professional - Button Save");
	}

	@Test
	public void tc05_ClickOnSaveButton() {
		createProfessionalPage.buttonSave().click();
		
		// createProfessionalPage.waitForConfirmationPopUp();
		popupModal.waitUntilOKButtonAppears();
		TestService.checkExactMatch(popupModal.getPageTitle(), titlePatientInfoSavedWindow, "Professional profile Saved -  Window Title");
		TestService.checkExactMatch(popupModal.labelMessage(), messageSuccesfullySaved, "Professional profile Saved -  Confirmation Pop Up Message");
		// TestService.checkPresenceAndVisibility(createProfessionalPage.popUpConfirmationWindow(), "Confirmation popup window");

		// TestService.checkExactMatch(createProfessionalPage.popUpConfirmationMessage(), "Do you want to register this professional?", "Confirmation popup window - Question");
		// TestService.checkExactMatch(createProfessionalPage.popUpConfirmationButton(createProfessionalPage.BUTTON_ACCEPT), "Accept", "Confirmation popup window - Button Accept");
		// TestService.checkExactMatch(createProfessionalPage.popUpConfirmationButton(createProfessionalPage.BUTTON_CANCEL), "Cancel", "Confirmation popup window - Button Cancel");
	}	

	@Test
	public void tc06_ClickOnAcceptConfirmation() {
		// createProfessionalPage.popUpConfirmationButton(createProfessionalPage.BUTTON_ACCEPT).click();
		popupModal.buttonOK().click();

		// createProfessionalPage.waitForInformationPopUp();
		createProfessionalPage.waitUntilAddProfessionalFormLoads();
	}	

	/* @Test
	public void tc07_ClickOnAcceptInformation() {
		// popUpInformationWindow
		TestService.checkPresenceAndVisibility(createProfessionalPage.popUpInformationWindow(), "Information popup window");

		TestService.checkExactMatch(createProfessionalPage.popUpInformationMessage(), "Professional registered in the platform", "Information popup window - Question");
		TestService.checkExactMatch(createProfessionalPage.popUpInformationButton(createProfessionalPage.BUTTON_ACCEPT), "Accept", "Information popup window - Button Accept");

		createProfessionalPage.popUpInformationButton(createProfessionalPage.BUTTON_ACCEPT).click();

		createProfessionalPage.unSwitch();
	}*/

	@Test
	public void tc08_verifyLogOffFromInitialProfessional() {
		// 	Before an attempt to log in with the new professional, an explicit logout from the previous professional must be performed 
		doctorDashboardPage.buttonLogOff().click();
		
		loginPage.waitUntilLoadComplete();
	}

	@Test
	public void tc09_verifyProfessionalLinkToSetAPassword() {
		// System.out.println("New professional email: " + finalEmail);
		String professionalURLToSetAPassword = NavigationService.getLinkFromEmail(finalEmail, "here");
	
		if (professionalURLToSetAPassword != null) {
			NavigationService.goToAPage(professionalURLToSetAPassword, mainWebDriver);
		} else {
			TestService.fail("AddProfessional: The email for the password set could not be retrieved on time.");
		}
		
		// patientSetPasswordPage.waitUntilLoadComplete();
		professionalSetPasswordPage.waitUntilLoadComplete();
	}

	@Test
	public void tc10_verifyProfessionalSetPassword() {
		professionalSetPasswordPage.inputNewPassword().clear();
		professionalSetPasswordPage.inputNewPassword().sendKeys(finalPassword);

		professionalSetPasswordPage.inputReEnterPassword().clear();
		professionalSetPasswordPage.inputReEnterPassword().sendKeys(finalPassword);

		professionalSetPasswordPage.waitUntilContinueIsEnabled();
		professionalSetPasswordPage.buttonContinue().click();
		
		// Wait for the confirmation message
		professionalSetPasswordPage.waitUntilConfirmationMessage(expectedValueLabelCreationPassword);

		TestService.checkPresenceAndVisibilityAndValue(professionalSetPasswordPage.labelCreatedPasswordSucess(), expectedValueLabelCreationPassword, "Label for Password creation was successful");
	}

	@Test
	public void tc11_verifyProfessionalSetPasswordSuccessEmail() {
		String subject = NavigationService.getPasswordResetConfirmationSubject(finalEmail);
		
    	TestService.checkExactMatch(subject, subjectPasswordResetConfirmation, "Email Inbox - Password Reset Confirmation");
	}

	@Test
	public void tc12_verifyProfessionalFinalConfirmation() {		
		// Now attempt to login with the recently added...
		String professionalURLToLogIn = NavigationService.getLinkFromEmail(finalEmail, "sign in");
    	// NavigationService.emptyInbox(finalEmail);

    	NavigationService.LoginWithoutCodeConfirmation(professionalURLToLogIn.trim(), loginPage, finalEmail, finalPassword);
		
		loginPage.waitUntilFinalConfirmationLabelAppears();
		
		loginPage.labelProfessionalFinalConfirmation().click();
		loginPage.buttonEnviar().click();
	}

	@Test
	public void tc13_verifyProfessionalLinkEmailConfirmation() {
		// TestBase.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.waitUntilAccountVerificationLabelAppears("Account verification");

		String professionalURLAccountVerification = NavigationService.getLinkFromEmail(finalEmail, "professional process");
	
		if (professionalURLAccountVerification != null) {
			NavigationService.goToAPage(professionalURLAccountVerification, mainWebDriver);

			loginPage.waitUntilLoadComplete();
		} else {
			TestService.fail("Final email confirmation never arrived on time.");
		}
	}

    @AfterClass(alwaysRun = true)
    public void insertValueIntoAttribute(ITestContext ctx) {
		try {
		    FileWriter fileWriter = new FileWriter("new_professionals.txt", true); //Set true for append mode
		    PrintWriter writer = new PrintWriter(fileWriter);
		    writer.println(finalEmail);
		    writer.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}

    	ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_LOGIN, finalEmail);        
        ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_PASSWORD, finalPassword);        
        ctx.setAttribute(AddPatient.NEW_PROFESSIONAL_NAME, finalProfessionalName);
        
        // To insert multiple professionals and keep the counter 
        AddProfessional.CURRENT_COUNTER++;

		// Note: to accelerate the execution of chained tests, this driver can be reused
		mainWebDriver.close();
		
		TestBase.clearDriver(mainWebDriver);
    }
}

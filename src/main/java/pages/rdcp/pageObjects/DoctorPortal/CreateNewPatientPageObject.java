package pages.rdcp.pageObjects.DoctorPortal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class CreateNewPatientPageObject extends BasePage {
 	private final static String expectedPageTitle            = "Not configured yet";
	private final static By locCreateNewPatientLabel         = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[1]/div[1]/div/h1");
	private final static By locLogOffButton                  = By.cssSelector("#root > div > div.sc-bwzfXH.bwZXHj > div.sc-bdVaJa.cjdBCW > div.sc-hZSUBg.jMeAxB > div.sc-bwzfXH.hliEbe > button");	
	private final static By locNextButtonStepONE             = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[2]/button[2]");
	                                                               
	private final static By locNextButtonStepTWO             = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/button[3]");	
	private final static By locNextButtonStepTHREE           = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/button[3]");
	
	private final static By locGoToDashboardButtonStepFOUR   = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[5]/button");
	private final static By locGoToDashboardButtonStepFOUR1  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[5]/button > span");
	private final static By locGoToDashboardButtonStepFOUR2  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[5]/button > span > span");

	private final static By locExperiencingIssues = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[1]/div[1]/h2/span");

	// Step 1: Profile type
	private final static By locStepONEPickUpAtCenterInput                  = By.id("pickup");
	private final static By locStepONEAdditionalOptionsPatientPortalAccess = By.id("patient-dashboard");	
	
	private final static By locStepONEPickUpAtCenter                 = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[1]/div[2]/div/div/div/div[2]/label/div[1]/h1");	
	private final static By locStepONEPatientPortalAccess            = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[1]/div[3]/div[1]/div/div[2]/div[1]/label/h5/span");	
	
	// Step 2: Patient Info
	private final static By locStepTWOFirstNameInput                 = By.id("firstName");
	private final static By locStepTWOLastNameInput                  = By.id("lastName");
	private final static By locStepTWODateOfBirthMonthInput          = By.id("dateOfBirthMonth");
	private final static By locStepTWODateOfBirthDayInput            = By.id("dateOfBirthDay");
	private final static By locStepTWODateOfBirthYearInput           = By.id("dateOfBirthYear");
	private final static By locStepTWODateOfBirthMonthInputForRead   = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[3]/div/div[1]/div/div/span[1]/div[1]/span");	
	private final static By locStepTWODateOfBirthDayInputForRead     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[3]/div/div[2]/div/div/span[1]/div[1]/span");	
	private final static By locStepTWODateOfBirthYearInputForRead    = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[3]/div/div[3]/div/div/span[1]/div[1]/span");	
	private final static By locStepTWOCountryInputForRead            = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[14]/div/div/span[1]/div[1]/span");

	private final static By locStepTWOHealthCareIDInput              = By.id("healthCareId");
	private final static By locStepTWOEmailInput                     = By.id("email");
	private final static By locStepTWOPhoneNumberInput               = By.id("phoneNumber");	

	private final static By locStepTWOLanguageComboBox               = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[7]/div/div/span[2]");
	private final static By locStepTWOListItemsLanguageComboBox      = By.id("react-select-5--list");	
	private final static By locStepTWOLanguageInputForRead           = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[7]/div/div/span[1]/div[1]/span");	

 	private final static By locStepTWOLanguageComboBox2              = By.xpath("//*[@data-qa-id='divLanguageCombobox']");
	private final static By locStepTWOListItemsLanguageComboBox2     = By.xpath("//*[@data-qa-id='listLanguageCombobox']");
	private final static By locStepTWOLanguageInputForRead2          = By.xpath("//*[@data-qa-id='inputLanguage']");

	private final static By locStepTWOStreetInput                    = By.id("street");	
	private final static By locStepTWOProvinceInput                  = By.id("province");	
	private final static By locStepTWOPostalCodeInput                = By.id("postalCode");	
	private final static By locStepTWOCityInput                      = By.id("city");	

	private final static By locStepTWOCountryComboBox                = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[14]/div/div/span[2]");
	private final static By locStepTWODivitemsCountryComboBox        = By.id("react-select-6--list");	

	private final static By locStepTWOEmailLabel                     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[5]/label[1]");	
	private final static By locStepTWOHealthCareIDRequiredLabel      = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[4]/label[2]/div");

	private final static By locStepTWOFirstNameLabel                 = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[1]/label[1]");
	private final static By locStepTWOLastNameLabel                  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[2]/label[1]");	
	private final static By locStepTWOHealthCareIDLabel              = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[4]/label[1]");
	private final static By locStepTWOPhoneNumberLabel               = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[6]/label[1]");
	
	// Step 3: Health info
	private final static By locStepTHREEDiabetesTypeComboBox                         = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[1]/div/div/span[2]");	
	private final static By locStepTHREEDivitemsDiabetesTypeComboBox                 = By.id("react-select-7--list");	
	private final static By locStepTHREEDiabetesTypeInputForRead                     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[1]/div/div/span[1]/div[1]/span");	

	private final static By locStepTHREEAssociatedProfessionalComboBox               = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/span[2]");
	private final static By locStepTHREEDivitemsAssociatedProfessionalComboBox       = By.id("react-select-8--list");	
	private final static By locStepTHREEAssociatedProfessionalInputsForRead          = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[2]/div/div/span[1]/div/span[@class='Select-value-label']");
	private final static By locStepTHREEAssociatedProfessionalDefaultSelectedItem    = By.id("react-select-8--value-0");	
	
	private final static By locStepTHREEDateOfDiagnosisMonthInput             = By.id("dateOfDiagnosisMonth");
	private final static By locStepTHREEDateOfDiagnosisDayInput               = By.id("dateOfDiagnosisDay");
	private final static By locStepTHREEDateOfDiagnosisYearInput              = By.id("dateOfDiagnosisYear");
	private final static By locStepTHREEDateOfDiagnosisMonthInputForRead      = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[3]/div/div[1]/div/div/span[1]/div[1]/span");
	private final static By locStepTHREEDateOfDiagnosisDayInputForRead        = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[3]/div/div[2]/div/div/span[1]/div[1]/span");
	private final static By locStepTHREEDateOfDiagnosisYearInputForRead       = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[3]/div/div[3]/div/div/span[1]/div[1]/span");	

	private final static By locStepTHREEGenderMaleRadioButton                 = By.id("gender-male");   // By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(5) > div > div > div:nth-child(1) > label");
	private final static By locStepTHREEGenderFemaleRadioButton               = By.id("gender-female");
	private final static By locStepTHREEGenderUnspecifiedRadioButton          = By.id("gender-unspecified");	
	
	private final static By locStepTHREEPregnantYesRadioButton                = By.id("pregnant-yes");   // By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(5) > div > div > div:nth-child(1) > label");
	private final static By locStepTHREEPregnantNoRadioButton                 = By.id("pregnant-no");	

	private final static By locStepTHREEGestationalDueDateMonthInput          = By.id("gestationalDueDateMonth");
	private final static By locStepTHREEGestationalDueDateDayInput            = By.id("gestationalDueDateDay");
	private final static By locStepTHREEGestationalDueDateYearInput           = By.id("gestationalDueDateYear");
	private final static By locStepTHREEGestationalDueDateMonthInputForRead   = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div/span[1]/div[1]/span");
	private final static By locStepTHREEGestationalDueDateDayInputForRead     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[7]/div/div[2]/div/div/span[1]/div[1]/span");
	private final static By locStepTHREEGestationalDueDateYearInputForRead    = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[7]/div/div[3]/div/div/span[1]/div[1]/span");	

	private final static By locStepTHREEDiabetesTypeLabel                     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[1]/label[1]");

	// Step 4: Confirm
	private final static By locStepFOURTaskCompleteLabel                      = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/h2/span");	
	private final static By locStepFOURMessageConfirmationLabel               = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[1]/h3/span");

	public WebElement itemsListComboboxCountry() {
		return getElement(locStepTWODivitemsCountryComboBox);
	}

	public CreateNewPatientPageObject(WebDriver driver) {
		super(expectedPageTitle);
	}

	public CreateNewPatientPageObject(String _expectedPageTitle, WebDriver mainWebDriver) {
		super(_expectedPageTitle);
		this.driver = mainWebDriver;
	}

	public WebElement labelCreateNewPatient() {
		return getElement(locCreateNewPatientLabel);
	}

	public WebElement labelHealthCareIDRequired() {
		return getElement(locStepTWOHealthCareIDRequiredLabel);
	}
	
	public WebElement labelEmail() {
		return getElement(locStepTWOEmailLabel);
	}

	public WebElement labelPhoneNumber() {
		return getElement(locStepTWOPhoneNumberLabel);
	}

	public WebElement labelHealthCareID() {
		return getElement(locStepTWOHealthCareIDLabel);
	}

	public WebElement labelDiabetesType() {
		return getElement(locStepTHREEDiabetesTypeLabel);
	}

	public WebElement labelTaskComplete() {
		return getElement(locStepFOURTaskCompleteLabel);
	}

	public WebElement labelMessageConfirmation() {
		return getElement(locStepFOURMessageConfirmationLabel);
	}

	
	public WebElement labelMessageExperiencingIssues() {
		return getElement(locExperiencingIssues);
	}
	
	@Override
	public WebElement buttonLogOff() {
		return getElement(locLogOffButton);
	}

	public WebElement buttonNextStepONE() {
		return getElement(locNextButtonStepONE);
	}

	public WebElement buttonNextStepTWO() {
		return getElement(locNextButtonStepTWO);
	}

	public WebElement buttonNextStepTHREE() {
		return getElement(locNextButtonStepTHREE);
	}

	public WebElement buttonGoToDashboard() {
		return getElement(locGoToDashboardButtonStepFOUR);
	}

	public WebElement buttonGoToDashboard1() {
		return getElement(locGoToDashboardButtonStepFOUR1);
	}

	public WebElement buttonGoToDashboard2() {
		return getElement(locGoToDashboardButtonStepFOUR2);
	}

	public WebElement labelPickUpAtCenter() {
		return getElement(locStepONEPickUpAtCenter);
	}

	public WebElement labelPatientPortalAccess() {
		return getElement(locStepONEPatientPortalAccess);
	}

	public WebElement radioPickUpAtCenter() {
		return getElement(locStepONEPickUpAtCenterInput);
	}

	public WebElement checkboxPatientPortalAccess() {
		return getElement(locStepONEAdditionalOptionsPatientPortalAccess);
	}

	public WebElement radioButtonGenderMale() {
		return getElement(locStepTHREEGenderMaleRadioButton);
	}

	public WebElement radioButtonGenderFemale() {
		return getElement(locStepTHREEGenderFemaleRadioButton);
	}

	public WebElement radioButtonGenderUnspecified() {
		return getElement(locStepTHREEGenderUnspecifiedRadioButton);
	}

	public WebElement radioButtonPregnantYes() {
		return getElement(locStepTHREEPregnantYesRadioButton);
	}

	public WebElement radioButtonPregnantNo() {
		return getElement(locStepTHREEPregnantNoRadioButton);
	}

	public WebElement inputFirstName() {
		return getElement(locStepTWOFirstNameInput);
	}

	public WebElement inputLastName() {
		return getElement(locStepTWOLastNameInput);
	}

	public WebElement inputDateOfBirthMonth() {
		return getElement(locStepTWODateOfBirthMonthInput);
	}

	public WebElement inputDateOfBirthDay() {
		return getElement(locStepTWODateOfBirthDayInput);
	}

	public WebElement inputDateOfBirthYear() {
		return getElement(locStepTWODateOfBirthYearInput);
	}

	public WebElement inputDateOfBirthMonthForRead() {
		return getElement(locStepTWODateOfBirthMonthInputForRead);
	}
	
	public WebElement inputDateOfBirthDayForRead() {
		return getElement(locStepTWODateOfBirthDayInputForRead);
	}
	
	public WebElement inputDateOfBirthYearForRead() {
		return getElement(locStepTWODateOfBirthYearInputForRead);
	}

	public WebElement inputCountryForRead() {
		return getElement(locStepTWOCountryInputForRead);
	}

	public WebElement inputHealthCareID() {
		return getElement(locStepTWOHealthCareIDInput);
	}

	public WebElement inputEmail() {
		return getElement(locStepTWOEmailInput);
	}

	public WebElement inputPhoneNumber() {
		return getElement(locStepTWOPhoneNumberInput);
	}

	public WebElement comboboxLanguage() {
		return getElement(locStepTWOLanguageComboBox);
	}

	public WebElement itemsListComboboxLanguage() {
		return getElement(locStepTWOListItemsLanguageComboBox);
	}

	public WebElement inputLanguageForRead() {
		return getElement(locStepTWOLanguageInputForRead);
	}

	public WebElement comboboxLanguage2() {
		return getElement(locStepTWOLanguageComboBox2);
	}
	
	public WebElement itemsListComboboxLanguage2() {
		return getElement(locStepTWOListItemsLanguageComboBox2);
	}	

	public WebElement inputLanguageForRead2() {
		return getElement(locStepTWOLanguageInputForRead2);
	}
	
	public WebElement inputStreet() {
		return getElement(locStepTWOStreetInput);
	}

	public WebElement inputProvince() {
		return getElement(locStepTWOProvinceInput);
	}
	
	public WebElement inputPostalCode() {
		return getElement(locStepTWOPostalCodeInput);
	}

	public WebElement inputCity() {
		return getElement(locStepTWOCityInput);
	}

	public WebElement comboboxCountry() {
		return getElement(locStepTWOCountryComboBox);
	}

	public WebElement inputDiabetesTypeForRead() {
		return getElement(locStepTHREEDiabetesTypeInputForRead);
	}

	public WebElement comboboxDiabetesType() {
		return getElement(locStepTHREEDiabetesTypeComboBox);
	}

	public WebElement itemsListComboboxDiabetesType() {
		return getElement(locStepTHREEDivitemsDiabetesTypeComboBox);
	}

	public List<WebElement> inputAssociatedProfessionalsForRead() {
		// return getElements(getElement(locStepTHREEAssociatedProfessionalInputForReadParent), locStepTHREEAssociatedProfessionalInputForReadChilds);
		return getElements(locStepTHREEAssociatedProfessionalInputsForRead);
	}

	public WebElement comboboxAssociatedProfessional() {
		return getElement(locStepTHREEAssociatedProfessionalComboBox);
	}

	public WebElement itemsListComboboxAssociatedProfessional() {
		return getElement(locStepTHREEDivitemsAssociatedProfessionalComboBox);
	}

	public WebElement inputDateOfDiagnosisMonth() {
		return getElement(locStepTHREEDateOfDiagnosisMonthInput);
	}

	public WebElement inputDateOfDiagnosisDay() {
		return getElement(locStepTHREEDateOfDiagnosisDayInput);
	}

	public WebElement inputDateOfDiagnosisYear() {
		return getElement(locStepTHREEDateOfDiagnosisYearInput);
	}

	public WebElement inputDateOfDiagnosisMonthForRead() {
		return getElement(locStepTHREEDateOfDiagnosisMonthInputForRead);
	}
	
	public WebElement inputDateOfDiagnosisDayForRead() {
		return getElement(locStepTHREEDateOfDiagnosisDayInputForRead);
	}
	
	public WebElement inputDateOfDiagnosisYearForRead() {
		return getElement(locStepTHREEDateOfDiagnosisYearInputForRead);
	}

	public WebElement inputGestationalDueDateMonth() {
		return getElement(locStepTHREEGestationalDueDateMonthInput);
	}

	public WebElement inputGestationalDueDateDay() {
		return getElement(locStepTHREEGestationalDueDateDayInput);
	}

	public WebElement inputGestationalDueDateYear() {
		return getElement(locStepTHREEGestationalDueDateYearInput);
	}

	public WebElement inputGestationalDueDateMonthForRead() {
		return getElement(locStepTHREEGestationalDueDateMonthInputForRead);
	}
	
	public WebElement inputGestationalDueDateDayForRead() {
		return getElement(locStepTHREEGestationalDueDateDayInputForRead);
	}
	
	public WebElement inputGestationalDueDateYearForRead() {
		return getElement(locStepTHREEGestationalDueDateYearInputForRead);
	}

	public WebElement labelFirstName() {
		return getElement(locStepTWOFirstNameLabel);
	}

	public WebElement labelLastName() {
		return getElement(locStepTWOLastNameLabel);
	}
	
	public void waitUntilStepONENextButtonAppears() {
		fluentWait(locNextButtonStepONE);
	}

	public void waitUntilStepONEPickUpAtCenterAppears() {
		fluentWait(locStepONEPickUpAtCenter);
	}

	public void waitUntilStepONEPatientPortalAccessAppears() {
		fluentWait(locStepONEPatientPortalAccess);
	}

	public void waitUntilPatientCreated() {
		fluentWait(locStepFOURTaskCompleteLabel);
	}

	public void waitForitemsListComboboxAssociatedProfessionalToAppear(String firstItem) {
		fluentWait(locStepTHREEAssociatedProfessionalDefaultSelectedItem);
	}
	
}
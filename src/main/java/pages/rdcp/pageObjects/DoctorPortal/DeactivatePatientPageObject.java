package pages.rdcp.pageObjects.DoctorPortal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class DeactivatePatientPageObject extends BasePage {
 	private final static String expectedPageTitle            = "Not configured yet";
	private final static By locCreateNewPatientLabel         = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div.sc-bJTOcE.gXLWtL > div:nth-child(1) > div > h1");
	private final static By locLogOffButton                  = By.cssSelector("#root > div > div.sc-bwzfXH.bwZXHj > div.sc-bdVaJa.cjdBCW > div.sc-hZSUBg.jMeAxB > div.sc-bwzfXH.hliEbe > button");	
	private final static By locNextButtonStepONE             = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div.sc-gJTSre.gWhnEO > button.sc-gipzik.hwVmNg");	
	// private final static By locNextButtonStepTWO             = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-gJTSre.gWhnEO > button.sc-gipzik.hwVmNg");
	private final static By locNextButtonStepTWO             = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/button[3]");	
	private final static By locNextButtonStepTHREE           = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/button[3]");
	
	private final static By locGoToDashboardButtonStepFOUR   = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[5]/button");
	private final static By locGoToDashboardButtonStepFOUR1  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[5]/button > span");
	private final static By locGoToDashboardButtonStepFOUR2  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/div[5]/button > span > span");

	private final static By locExperiencingIssues = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[1]/div[1]/h2/span");

	// Step 1: Profile type
	private final static By locStepONEPickUpAtCenterInput                  = By.id("pickup");
	private final static By locStepONEPickUpAtCenter                       = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div.sc-bJTOcE.gXLWtL > div:nth-child(2) > div > div > div > div.sc-dhVevo.cQOPeR.sc-jGFFOr.kLmBFZ > label > div.sc-cCbPEh.dADUYt");
	private final static By locStepONEAdditionalOptionsPatientPortalAccess = By.id("patient-dashboard");	

	// Step 2: Patient Info
	private final static By locStepTWOFirstNameInput                 = By.id("firstName");
	private final static By locStepTWOLastNameInput                  = By.id("lastName");
	private final static By locStepTWODateOfBirthMonthInput          = By.id("dateOfBirthMonth");
	private final static By locStepTWODateOfBirthDayInput            = By.id("dateOfBirthDay");
	private final static By locStepTWODateOfBirthYearInput           = By.id("dateOfBirthYear");
	private final static By locStepTWODateOfBirthMonthInputForRead   = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(3) > div > div:nth-child(1) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTWODateOfBirthDayInputForRead     = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(3) > div > div:nth-child(2) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTWODateOfBirthYearInputForRead    = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(3) > div > div:nth-child(3) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTWOLanguageInputForRead           = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTWOCountryInputForRead            = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(14) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	
	private final static By locStepTWOEmailInput                     = By.id("email");
	private final static By locStepTWOPhoneNumberInput               = By.id("phoneNumber");	
	private final static By locStepTWOLanguageInput                  = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(7) > div > input[type=hidden]");
	
	private final static By locStepTWOLanguageComboBox               = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div > span.Select-arrow-zone");
	private final static By locStepTWODivitemsLanguageComboBox       = By.id("react-select-5--list");	
	
	private final static By locStepTWOStreetInput                    = By.id("street");	
	private final static By locStepTWOProvinceInput                  = By.id("province");	
	private final static By locStepTWOPostalCodeInput                = By.id("postalCode");	
	private final static By locStepTWOCityInput                      = By.id("city");	

	private final static By locStepTWOCountryComboBox                = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(14) > div > div > span.Select-arrow-zone");
	private final static By locStepTWODivitemsCountryComboBox        = By.id("react-select-6--list");	

	private final static By locStepTWOEmailLabel                     = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(5) > label.sc-jqCOkK.loeePH");
	
	private final static By locStepTWOHealthCareIDRequiredLabel      = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[2]/div/div/div/div[4]/label[2]/div");
	
	// Step 3: Health info
	private final static By locStepTHREEDiabetesTypeComboBox                         = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(1) > div > div > span.Select-arrow-zone");
	private final static By locStepTHREEDivitemsDiabetesTypeComboBox                 = By.id("react-select-7--list");	
	private final static By locStepTHREEDiabetesTypeInputForRead                     = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(1) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");	

	private final static By locStepTHREEAssociatedProfessionalComboBox               = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(2) > div > div > span.Select-arrow-zone");
	private final static By locStepTHREEDivitemsAssociatedProfessionalComboBox       = By.id("react-select-8--list");	
	private final static By locStepTHREEAssociatedProfessionalInputsForRead          = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(2) > div > div > span.Select-multi-value-wrapper > div > .Select-value-label");
	// private final static By locStepTHREEAssociatedProfessionalInputForReadParent     = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(2) > div > div > span.Select-multi-value-wrapper");
	// private final static By locStepTHREEAssociatedProfessionalInputForReadChilds     = By.className("Select-value-label");	
	
	private final static By locStepTHREEDateOfDiagnosisMonthInput             = By.id("dateOfDiagnosisMonth");
	private final static By locStepTHREEDateOfDiagnosisDayInput               = By.id("dateOfDiagnosisDay");
	private final static By locStepTHREEDateOfDiagnosisYearInput              = By.id("dateOfDiagnosisYear");
	private final static By locStepTHREEDateOfDiagnosisMonthInputForRead      = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(3) > div > div:nth-child(1) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTHREEDateOfDiagnosisDayInputForRead        = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(3) > div > div:nth-child(2) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTHREEDateOfDiagnosisYearInputForRead       = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(3) > div > div:nth-child(3) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");	

	private final static By locStepTHREEGenderMaleRadioButton                 = By.id("gender-male");   // By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(5) > div > div > div:nth-child(1) > label");
	private final static By locStepTHREEGenderFemaleRadioButton               = By.id("gender-female");
	private final static By locStepTHREEGenderUnspecifiedRadioButton          = By.id("gender-unspecified");	
	
	private final static By locStepTHREEPregnantYesRadioButton                = By.id("pregnant-yes");   // By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(5) > div > div > div:nth-child(1) > label");
	private final static By locStepTHREEPregnantNoRadioButton                 = By.id("pregnant-no");	

	private final static By locStepTHREEGestationalDueDateMonthInput          = By.id("gestationalDueDateMonth");
	private final static By locStepTHREEGestationalDueDateDayInput            = By.id("gestationalDueDateDay");
	private final static By locStepTHREEGestationalDueDateYearInput           = By.id("gestationalDueDateYear");
	private final static By locStepTHREEGestationalDueDateMonthInputForRead   = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div:nth-child(1) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTHREEGestationalDueDateDayInputForRead     = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div:nth-child(2) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");
	private final static By locStepTHREEGestationalDueDateYearInputForRead    = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(7) > div > div:nth-child(3) > div > div > span.Select-multi-value-wrapper > div.Select-value > span");	

	private final static By locStepTHREEDiabetesTypeLabel                     = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-ctwKVn.dMFwTQ > div > div:nth-child(2) > div > div > div > div:nth-child(1) > label.sc-jqCOkK.loeePH");

	// Step 4: Confirm
	private final static By locStepFOURTaskCompleteLabel                      = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > h2 > span");
	private final static By locStepFOURMessageConfirmationLabel               = By.cssSelector("#AppContainer > div.sc-EHOje.eYfocG > div > div.sc-hokXgN.lhoBPr > div.sc-jGFFOr.dhgkWh > form > div > div.sc-jAaTju.dupxQt > div.sc-jAaTju.bxRFhm > h3 > span");

	private final static By locProfileTypeSaveButton                          = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div[2]/button[2]");
	private final static By locPatientInfoTab                                 = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div/ol/li[2]/button");
	
	private final static By locPatientInfoTabHealthCareID                     = By.id("healthCareId");
	
	private final static By locEditPatientInfoLabel                           = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[1]/div/div[1]/div/h1");	
	private final static By locEditPatientInfoSaveButton                      = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/form/div/div[2]/button[2]");	
	
	private final static By locDeactivatePatientTitle                         = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/div/h1/span");	                                                                                      
	private final static By locReasonLabel                                    = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[1]/span[1]/span");	
	private final static By locCommentsLabel                                  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[1]/span[2]/span");	                                                                                      
	private final static By locCommentsInput                                  = By.id("comment");
	
	private final static By locReasonComboBox                                 = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[1]/div[1]/div/span[2]");
	                                                                                      //*[@id="AppContainer"]/div[1]/div/div[3]/div/div[2]/div/div/form/div[1]/div[1]/div/span[2]/span
	private final static By locReasonComboBoxClosed                           = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[1]/div[1]/div");

	private final static By locReasonDivItemsComboBox                         = By.id("react-select-15--list");	
	private final static By locReasonInputForRead                             = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[1]/div[1]/div/span[1]/div[1]/span");	
	
	private final static By locCancelButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[2]/button[1]");	
	private final static By locDeactivateButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div[2]/div/div/form/div[2]/button[2]");	
	
	public WebElement itemsListComboboxLanguage() {
		return getElement(locStepTWODivitemsLanguageComboBox);
	}

	public WebElement itemsListComboboxCountry() {
		return getElement(locStepTWODivitemsCountryComboBox);
	}

	public DeactivatePatientPageObject(String _expectedPageTitle, WebDriver mainWebDriver) {
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

	public WebElement inputPickUpAtCenter() {
		return getElement(locStepONEPickUpAtCenter);
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

	public WebElement inputLanguageForRead() {
		return getElement(locStepTWOLanguageInputForRead);
	}

	public WebElement inputCountryForRead() {
		return getElement(locStepTWOCountryInputForRead);
	}

	public WebElement inputEmail() {
		return getElement(locStepTWOEmailInput);
	}

	public WebElement inputPhoneNumber() {
		return getElement(locStepTWOPhoneNumberInput);
	}

	public WebElement inputLanguage() {
		return getElement(locStepTWOLanguageInput);
	}

	public WebElement comboboxLanguage() {
		return getElement(locStepTWOLanguageComboBox);
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

	public WebElement labelDeactivatePatient() {
		return getElement(locDeactivatePatientTitle);
	}

	public WebElement labelReason() {
		return getElement(locReasonLabel);
	}
	
	public WebElement labelComments() {
		return getElement(locCommentsLabel);
	}

	public WebElement inputComments() {
		return getElement(locCommentsInput);
	}
	
	public WebElement comboboxReason() {
		return getElement(locReasonComboBox);
	}

	public WebElement comboboxReasonClosed() {
		return getElement(locReasonComboBoxClosed);
	}

	public WebElement itemsListComboboxReason() {
		return getElement(locReasonDivItemsComboBox);
	}
	
	public WebElement inputReasonForRead() {
		return getElement(locReasonInputForRead);
	}
	
	public WebElement inputHealthCareID() {
		return getElement(locPatientInfoTabHealthCareID);
	}

	public WebElement tabPatientInfo() {
		return getElement(locPatientInfoTab);
	}
	
	public WebElement labelPatientInfo() {
		return getElement(locEditPatientInfoLabel);
	}

	public WebElement buttonSaveEditPatientInfo() {
		return getElement(locEditPatientInfoSaveButton);
	}

	public WebElement buttonCancel() {
		return getElement(locCancelButton);
	}

	public WebElement buttonDeactivatePatient() {
		return getElement(locDeactivateButton);
	}

	public void waitUntilLoadComplete() {
		fluentWait(locProfileTypeSaveButton);
	}

	public void waitUntilPatientInfoDisplays() {
		fluentWait(locPatientInfoTabHealthCareID);
	}		
	
	public void waitUntilDeactivateLoadComplete() {
		fluentWait(locCommentsLabel);
	}		
	
}
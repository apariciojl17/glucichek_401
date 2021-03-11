package pages.rdcp.pageObjects.DoctorPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestService;
import pages.BasePage;

public class CreateNewProfessionalPageObject extends BasePage {
	private final static String expectedPageTitle            = "Not configured yet";

 	private final static By locNameInput = By.id("firstName");	
 	private final static By locSurnameInput = By.id("surname");	
 	// private final static By locSurname2Input = By.id("professional.user.surname2");	
	private final static By locDateOfBirthMonthInput          = By.id("dateOfBirthMonth");
	private final static By locDateOfBirthDayInput            = By.id("dateOfBirthDay");
	private final static By locDateOfBirthYearInput           = By.id("dateOfBirthYear");
	// test2
	/* private final static By locDateOfBirthMonthInputForRead   = By.xpath("//*[@id='react-select-2--value']/div[1]");
	private final static By locDateOfBirthDayInputForRead     = By.xpath("//*[@id='react-select-3--value']/div[1]");	
	private final static By locDateOfBirthYearInputForRead    = By.xpath("//*[@id='react-select-4--value']/div[1]"); */	
	// qa
	private final static By locDateOfBirthMonthInputForRead   = By.xpath("//*[@id='react-select-4--value']/div[1]");
	private final static By locDateOfBirthDayInputForRead     = By.xpath("//*[@id='react-select-5--value']/div[1]");	
	private final static By locDateOfBirthYearInputForRead    = By.xpath("//*[@id='react-select-6--value']/div[1]");	

	private final static By locBirthdayInputHidden = By.id("birthday");	
 	private final static By locPhoneInput = By.id("phone");
 	private final static By locEmailInput = By.id("email");
 	// private final static By locEmailConfirmationInput = By.id("emailConfirmation");
 	private final static By locHCPIDInput = By.id("hcpId");

 	private final static By locLanguageCombobox = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[1]/div/div/div/div/div/div[6]/div/div/span[2]");
 	// private final static By locLanguageInput = By.xpath("//*[@id='professional_user_languageId_chosen']/div/div/input");
 	// private final static By locLanguageInputForRead = By.xpath("//*[@id='professional_user_languageId_chosen']/a/span");
	// test2
 	/* private final static By locLanguageComboboxItemsList   = By.id("react-select-5--list");	
 	private final static By locLanguageInputForRead = By.id("react-select-5--value-item");*/
	// qa
 	private final static By locLanguageComboboxItemsList   = By.id("react-select-7--list");	
 	private final static By locLanguageInputForRead = By.id("react-select-7--value-item");

 	private final static By locProfessionalTypeCombobox            = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[1]/div/div/div/div/div/div[8]/div/div/span[2]"); 	
 	// test2
 	/*private final static By locProfessionalTypeComboboxItemsList   = By.id("react-select-7--list");
 	private final static By locProfessionalTypeForRead = By.id("react-select-7--value-item");*/
 	// qa
 	private final static By locProfessionalTypeComboboxItemsList   = By.id("react-select-9--list");
 	private final static By locProfessionalTypeForRead = By.id("react-select-9--value-item");

 	private final static By locOpenConnectivityYESRadioButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[1]/div/div/div/div/div/div[10]/div/div/div[1]/label/span[2]");
 	private final static By locOpenConnectivityNORadioButton = By.id("openConnectivity-false");
 	private final static By locOpenConnectivityYESLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[1]/div/div/div/div/div/div[10]/div/div/div[1]/label");
 	private final static By locOpenConnectivityNOLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[1]/div/div/div/div/div/div[10]/div/div/div[2]/label");

 	private final static By locOpenConnectivityLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[1]/div/div/div/div/div/div[10]/label");	
 	
 	private final static By locConfirmationWindow  = By.xpath("/html/body/div[4]/div[2]");
 	private final static By locConfirmationMessage = By.xpath("/html/body/div[4]/div[2]/div"); 	
 
 	private final static By locInformationWindow   = By.xpath("/html/body/div[3]/div[2]");
 	private final static By locInformationMessage  = By.xpath("/html/body/div[3]/div[2]/div"); 	

 	private final static By locSaveButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[2]/button[2]");
 	private final static By locCancelButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/form/div[2]/button[1]");
 	
 	private final static By lociFrameAddProfessional = By.xpath("//iframe[@title='professional.createProfessional']");
 	
 	public final int BUTTON_ACCEPT = 0;
 	public final int BUTTON_CANCEL = 1;
 	
	public CreateNewProfessionalPageObject(WebDriver driver) {
		super(expectedPageTitle);
	}

	public CreateNewProfessionalPageObject(String _expectedPageTitle, WebDriver wd) {
		super(_expectedPageTitle);
		this.driver = wd;
	}

	public WebElement inputFirstName() {
		return getElement(locNameInput);
	}

	public WebElement inputSurname() {
		return getElement(locSurnameInput);
	}

	/*public WebElement inputSurname2() {
		return getElement(locSurname2Input);
	}*/

	/* public WebElement inputBirthday() {
		return getElement(locBirthdayInput);
	}*/

	public WebElement inputDateOfBirthMonth() {
		return getElement(locDateOfBirthMonthInput);
	}

	public WebElement inputDateOfBirthDay() {
		return getElement(locDateOfBirthDayInput);
	}

	public WebElement inputDateOfBirthYear() {
		return getElement(locDateOfBirthYearInput);
	}

	public WebElement inputDateOfBirthMonthForRead() {
		return getElement(locDateOfBirthMonthInputForRead);
	}
	
	public WebElement inputDateOfBirthDayForRead() {
		return getElement(locDateOfBirthDayInputForRead);
	}
	
	public WebElement inputDateOfBirthYearForRead() {
		return getElement(locDateOfBirthYearInputForRead);
	}

	public WebElement inputBirthdayHidden() {
		return getElement(locBirthdayInputHidden);
	}

	public WebElement inputPhone() {
		return getElement(locPhoneInput);
	}

	public WebElement inputEmail() {
		return getElement(locEmailInput);
	}

	/*public WebElement inputEmailConfirmation() {
		return getElement(locEmailConfirmationInput);
	}*/

	public WebElement inputHCPID() {
		return getElement(locHCPIDInput);
	}

	public WebElement comboboxLanguage() {
		return getElement(locLanguageCombobox);
	}

	public WebElement itemsListComboboxLanguage() {
		return getElement(locLanguageComboboxItemsList);
	}
	
	/*public WebElement inputLanguage() {
		return getElement(locLanguageInput);
	}*/

	public WebElement inputLanguageForRead() {
		return getElement(locLanguageInputForRead);
	}
		
	public WebElement inputProfessionalTypeForRead() {
		return getElement(locProfessionalTypeForRead);
	}
	
	public WebElement comboboxProfessionalType() {
		return getElement(locProfessionalTypeCombobox);
	}
	
	public WebElement itemsListComboboxProfessionalType() {
		return getElement(locProfessionalTypeComboboxItemsList);
	}

	public WebElement radiobuttonOpenConectivityYES() {
		return getElement(locOpenConnectivityYESRadioButton);
	}

	public WebElement radiobuttonOpenConectivityNO() {
		return getElement(locOpenConnectivityNORadioButton);
	}

	public WebElement labelOpenConectivity() {
		return getElement(locOpenConnectivityLabel);
	}

	public WebElement labelOpenConectivityYES() {
		return getElement(locOpenConnectivityYESLabel);
	}

	public WebElement labelOpenConectivityNO() {
		return getElement(locOpenConnectivityNOLabel);
	}

	public WebElement popUpConfirmationWindow() {
		return getElement(locConfirmationWindow);
	}

	public WebElement popUpInformationWindow() {
		return getElement(locInformationWindow);
	}

	public String popUpConfirmationMessage() {
		String temp = null;

		WebElement elem = getElement(locConfirmationMessage);
		if (elem != null) {
			temp = elem.getAttribute("innerHTML");
			int pos = temp.indexOf("<div");
			if (pos >= 0) {
				temp = temp.substring(0, pos);
			}
		}
		return temp;
	}

	public String popUpInformationMessage() {
		String temp = null;

		WebElement elem = getElement(locInformationMessage);
		if (elem != null) {
			temp = elem.getAttribute("innerHTML");
			int pos = temp.indexOf("<div");
			if (pos >= 0) {
				temp = temp.substring(0, pos);
			}
		}
		return temp;	
	}

	public WebElement popUpConfirmationButton(int index) {
		return getElement(locConfirmationWindow).findElements(By.tagName("button")).get(index);
	}

	public WebElement popUpInformationButton(int index) {
		return getElement(locInformationWindow).findElements(By.tagName("button")).get(index);
	}

	public void switchTo() {		
		driver.switchTo().frame(getElement(lociFrameAddProfessional));
	}

	public void unSwitch() {
		driver.switchTo().defaultContent();
	}

	public WebElement buttonSave() {
		return getElement(locSaveButton);
	}

	public WebElement buttonCancel() {
		return getElement(locCancelButton);
	}

	public void waitUntilAddProfessionalFormLoads() {
		fluentWait(locCancelButton);

		TestService.ensureItsVisibleUsingJavascript(this.driver, this.buttonCancel(), "Button Cancel");
		
	    WebDriverWait wait = new WebDriverWait(this.driver, 5);
	    wait.until(ExpectedConditions.elementToBeClickable(locCancelButton));
	    // This interface can be improved, there are a lot of extra space between fields and labels, making the "Cancel" Button way out of the screen, not clickable without scrolling 
	}

	public void waitForiFrameAddProfessionalToAppears() {
		fluentWait(lociFrameAddProfessional);
	}

	public void waitForInformationPopUp() {
		fluentWait(locInformationWindow);
	}

	public void waitForConfirmationPopUp() {
		fluentWait(locConfirmationWindow);
	}
}
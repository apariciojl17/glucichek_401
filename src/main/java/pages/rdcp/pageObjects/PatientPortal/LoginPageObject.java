package pages.rdcp.pageObjects.PatientPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class LoginPageObject extends BasePage {
	private final static String expectedPageTitle    = "";
	private final static By locinputLogintextBox     = By.id("gigya-textbox-loginID");
	private final static By locinputPasswordtextBox  = By.id("gigya-password-10866137136906000");
	private final static By loclabelAppNameID        = By.id("Smart-Pix-Online");
	private final static By loclabelEmail            = By.cssSelector("#gigya-textbox-loginID + label");
	private final static By loclabelEmail2           = By.xpath("//*[@data-qa-id='labelEmail']");
	private final static By loclabelPassword         = By.cssSelector("#gigya-password-10866137136906000 + label");
	private final static By loclabelSingIn           = By.cssSelector("#gigya-login-form > div:nth-child(1) > label");
	private final static By locLinkForgotPassword    = By.cssSelector("#gigya-login-form > div:nth-child(2) > a");
	private final static By locLogInButton           = By.cssSelector("#gigya-login-form > div:nth-child(2) > div.gigya-composite-control.gigya-composite-control-submit.spol-capitalize.spol-push-left > input");
	private final static By locLabelAccountVerif     = By.xpath("//*[@id=\"gigya-resend-verification-code-form\"]/div[1]/h2");

	private final static By locLabelEnterCode     = By.id("gigya-screenset-DF-RegistrationLogin_showTfaUI_1_wrapper-enter-code");	
	public final By locVerificationLabel     = By.xpath("//*[@id='gigya-tfa-verification-screen']/div[1]/h2");   // test SPOL 2.0	
	private final static By locInputEnterCode = By.xpath("//*[@id='gigya-screenset-DF-RegistrationLogin_showTfaUI_1']/div[2]/input");  // qa SPOL 2.0	
	private final static By locSubmitButton = By.xpath("//*[@id='gigya-screenset-DF-RegistrationLogin_showTfaUI_1']/div[2]/div[5]/div");  // qa SPOL 2.0
	private final static By locPatientFinalConfirmationLabel  = By.xpath("//*[@id='gigya-profile-form']/div[1]/div[25]/div/label/span");
	private final static By locProfessionalFinalConfirmationLabel  = By.xpath("//*[@id=\"gigya-profile-form\"]/div[1]/div[26]/div/label");  // changed in QA at November 08, 2019
	private final static By locEnviarButton  = By.xpath("//*[@id='gigya-profile-form']/div[2]/div[1]/input");			
	
	public LoginPageObject() {
		super(expectedPageTitle);
	}

	public LoginPageObject(String _expectedPageTitle, WebDriver wd) {
		super(_expectedPageTitle);
		this.driver = wd;
	}

	public WebElement buttonSubmit() {
		return getElement(locSubmitButton);
	}

	public WebElement inputEnterCode() {
		return getElement(locInputEnterCode);
	}

	public WebElement labelVerification() {
		return getElement(locVerificationLabel);
	}

	public WebElement labelEmail() {
		return getElement(loclabelEmail);
	}

	public WebElement labelEmail2() {
		return getElement(loclabelEmail2);
	}

	public WebElement labelPassword() {
		return getElement(loclabelPassword);
	}

	public WebElement labelSignIn() {
		return getElement(loclabelSingIn);
	}

	public WebElement labelAppName() {
		return getElement(loclabelAppNameID);
	}

	public WebElement labelEnterCode() {
		return getElement(locLabelEnterCode);
	}
	
	public WebElement buttonLogIn() {
		return getElement(locLogInButton);
	}

	public WebElement linkForgotPassword() {
		return getElement(locLinkForgotPassword);
	}

	public WebElement inputLogIn() {
		return getElement(locinputLogintextBox);
	}

	public WebElement inputPassword() {
		return getElement(locinputPasswordtextBox);
	}
	
	public void waitUntilLoadComplete() {
		fluentWait(locLogInButton);
	}

	public void waitUntilAccountVerificationLabelAppears(String textToExpect) {
		fluentWait(locLabelAccountVerif);

		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locLabelAccountVerif, textToExpect));
	}

	public WebElement buttonEnviar() {
		return getElement(locEnviarButton);
	}
	
	public WebElement labelPatientFinalConfirmation() {
		return getElement(locPatientFinalConfirmationLabel);
	}	

	public WebElement labelProfessionalFinalConfirmation() {
		return getElement(locProfessionalFinalConfirmationLabel);
	}
	
	public void waitUntilFinalConfirmationLabelAppears() {
		fluentWait(locProfessionalFinalConfirmationLabel);
	}
}
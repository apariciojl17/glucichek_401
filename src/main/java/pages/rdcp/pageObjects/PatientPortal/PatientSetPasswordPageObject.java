package pages.rdcp.pageObjects.PatientPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class PatientSetPasswordPageObject extends BasePage {
	private final static String expectedPageTitle    = "";

	private final static By locInputPassword         = By.id("gigya-password-newPassword");
	private final static By locInputReTypePassword   = By.id("gigya-passsord-passwordRetype");

	private final static By locContinueButton        = By.xpath("//*[@id='gigya-reset-password-form']/div[1]/div[5]/input");  //qa
	// private final static By locContinueButton        = By.xpath("//*[@id='gigya-reset-password-form']/div[1]/div[6]/input");  // test2
	
	private final static By locNewPasswordLabel      = By.xpath("//*[@id='gigya-reset-password-form']/div[1]/div[3]/label/span");
	private final static By locReEnterPasswordLabel  = By.xpath("//*[@id='gigya-reset-password-form']/div[1]/div[4]/label/span");

	private final static By locYouCreatedPasswordLabel  = By.xpath("//*[@id='gigya-reset-password-form']/div[1]/label");
	
	public PatientSetPasswordPageObject() {
		super(expectedPageTitle);
	}

	public PatientSetPasswordPageObject(String _expectedPageTitle, WebDriver wd) {
		super(_expectedPageTitle);
		this.driver = wd;
	}

	public WebElement buttonContinue() {
		return getElement(locContinueButton);
	}

	public WebElement inputNewPassword() {
		return getElement(locInputPassword);
	}

	public WebElement inputReEnterPassword() {
		return getElement(locInputReTypePassword);
	}

	public WebElement labelNewPassword() {
		return getElement(locNewPasswordLabel);
	}

	public WebElement labelReEnterPassword() {
		return getElement(locReEnterPasswordLabel);
	}

	public WebElement labelCreatedPasswordSucess() {
		return getElement(locYouCreatedPasswordLabel);
	}

	public void waitUntilLoadComplete() {
		fluentWait(locInputPassword);
	}
	
	public void waitUntilConfirmationMessage(String textToExpect) {
		fluentWait(locYouCreatedPasswordLabel);

		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locYouCreatedPasswordLabel, textToExpect));
	}	
	
	public void waitUntilContinueIsEnabled() {
	    WebDriverWait wait = new WebDriverWait(this.driver, 5);
	    wait.until(ExpectedConditions.elementToBeClickable(locContinueButton));
	}	
}
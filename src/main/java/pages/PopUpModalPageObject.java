package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class PopUpModalPageObject extends BasePage {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By[] locMessageLabel = { By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[2]/div[1]/h2/span"), 
			                                      By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[2]/div/div[2]/span"),
			                                      By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[2]/div/div[2]/strong/span")};
	private final static By locOKButton     = By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[2]/div/div[3]/button");
	private final static By locAcceptButton = By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[2]/div[2]/button[2]");	                                                
	private final static By[] locTitle = { By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[1]/span/span"),	                                             
	                                       By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[1]/strong/span"),
	                                       By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[1]/strong/span")};
	private final static By locXToClose = By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[1]/button");		
	private final static By locCancelButton = By.xpath("//*[@id='modal-root']/div/div/div/div/div/div[2]/div[2]/button[1]");		

	public static final int DEACTIVATE_PATIENT = 0;
	public static final int MODIFY_PATIENT = 1;
	public static final int DEACTIVATE_PATIENT_SUCCESFULLY_SAVED = 2;
	public static final int ADD_PROFESSIONAL = 2;
	
	private static int currentMode = 1;	
	
	public PopUpModalPageObject() {
		super(expectedPageTitle);
	}

	public PopUpModalPageObject(String _expectedPageTitle, int mode, WebDriver mainWebDriver) {
		super(_expectedPageTitle);
		currentMode = mode;
		this.driver = mainWebDriver;
	}

	public WebElement buttonAccept() {
		return getElement(locAcceptButton);
	}

	public WebElement buttonCancel() {
		return getElement(locCancelButton);
	}

	public WebElement buttonOK() {
		return getElement(locOKButton);
	}
	
	public WebElement labelMessage() {
		return getElement(locMessageLabel[currentMode]);
	}

	private WebElement labelTitle() {
		return getElement(locTitle[currentMode]);
	}

	public void waitUntilOKButtonAppears() {
		fluentWait(locOKButton);
	}

	public void waitUntilAcceptButtonAppears() {
		fluentWait(locAcceptButton);
		
	    WebDriverWait wait = new WebDriverWait(this.driver, 5);
	    wait.until(ExpectedConditions.elementToBeClickable(locAcceptButton));
	}

	@Override
	public String getPageTitle() {
		String title = labelTitle().getText();
		return title;
	}

	public WebElement buttonXClose() {
		return getElement(locXToClose);
	}
	
}
package pages.rdcp.pageObjects.PatientPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;  

public class DashboardPageObject extends BasePage {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locHomeButton            = By.xpath("//*[@id='root']/div[1]/div[1]/div[1]/div[2]/ul/li[1]/a");
	private final static By locHelpButton            = By.xpath("//*[@id='root']/div[1]/div[1]/div[1]/div[2]/ul/li[2]/a");
	private final static By locUploadDataButton      = By.xpath("//*[@id='root']/div[1]/div[1]/div[1]/div[2]/ul/li[3]");	
	private final static By locProfileButton         = By.xpath("//*[@id='root']/div[1]/div[1]/div[1]/div[3]/div/a/div/div/div/div");	
	private final static By locLogOffButton          = By.xpath("//*[@id='root']/div[1]/div[1]/div[1]/div[4]/button");	
	public final By locDeliveryLabel         = By.xpath("//*[@id='root']/div[1]/div[1]/div[2]/div/div/div/div[2]/div/h2/span");	
	private final static By locPatientNameLabel      = By.xpath("//*[@id='root']/div[1]/div[1]/div[1]/div[3]/div/a/span");
	
	public DashboardPageObject() {
		super(expectedPageTitle);
	}

	public DashboardPageObject(String _expectedPageTitle, WebDriver _driver) {
		super(_expectedPageTitle);
		this.driver = _driver;
	}

	public WebElement buttonHome() {
		return getElement(locHomeButton);
	}

	public WebElement buttonHelp() {
		return getElement(locHelpButton);
	}

	public WebElement buttonUploadData() {
		return getElement(locUploadDataButton);
	}

	public WebElement buttonProfile() {
		return getElement(locProfileButton);
	}

	public WebElement labelPatientName() {
		return getElement(locPatientNameLabel);
	}	

	public WebElement labelDelivery() {
		return getElement(locDeliveryLabel);
	}	
	
	public void waitUntilLoadComplete() {
		fluentWait(locDeliveryLabel);
	}

	public void waitUntilPatientFullname(String textToExpect) {
		fluentWait(locPatientNameLabel);

		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locPatientNameLabel, textToExpect));
	}

	@Override
	public WebElement buttonLogOff() {
		return getElement(locLogOffButton);
	}
}
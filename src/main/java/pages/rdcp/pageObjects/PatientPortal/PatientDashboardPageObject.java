package pages.rdcp.pageObjects.PatientPortal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class PatientDashboardPageObject extends BasePage {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locFirstAndLastNameLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/div/div/div/span");
	
	private final static By locDoctorNameLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[1]/span/span");
	private final static By locSignOutLink = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[2]/div/a/span");

	public static By locHypoglycemiaLabel = By.xpath("//*[@id='root']/div[1]/div[1]/div[2]/div/div/div/div[3]/div/div/div/h2/span");

	public PatientDashboardPageObject() {
		super(expectedPageTitle);
	}

	public PatientDashboardPageObject(String _expectedPageTitle, WebDriver mainWebDriver) {
		super(_expectedPageTitle);
		this.driver = mainWebDriver;
	}

	public WebElement labelFirstNameLastName() {
		return getElement(locFirstAndLastNameLabel);
	}
	
	public WebElement labelDoctorName() {
		return getElement(locDoctorNameLabel);
	}
	
	@Override
	public WebElement buttonLogOff() {
		labelDoctorName().click();
		fluentWait(locSignOutLink);
		
		return getElement(locSignOutLink);
	}
	
	public void waitUntilLoadComplete() {
		fluentWait(locHypoglycemiaLabel);
	}
}
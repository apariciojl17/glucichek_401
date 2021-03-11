package pages.rdcp.pageObjects.DoctorPortal;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.TestService;

import pages.BasePage;

public class DoctorDashboardPageObject extends BasePage {
	private final static String expectedPageTitle    = "Not configured yet";
	// public  final By locCreateNewPatientButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div/button");
	public  final By locCreateNewPatientButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/button");
	private final static By locSearchButton = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div/div/div/form/div[3]/button");		

	private final static By locSearchForExistingPatientLabel     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div/div/div/form/div[1]/h4/span");
	private final static By locPatientIDLabel                    = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div/div/div/form/div[2]/div[1]/span");
	private final static By locPatientNameLabel                  = By.xpath("//*[@id=\"AppContainer\"]/div[1]/div/div[3]/div/div/div/div/form/div[2]/span");
	private final static By locProfessionalMenuLabel             = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[1]/span/span");
	private final static By locProfessionalDivMenu               = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[2]/div/div/ul");

	private final static By locProfessionalMenuAddProfessional   = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[2]/div/div/ul/li[3]/a/span/span");	

	private final static By locEnterPatientIDLabel               = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div/div/div/div/form/div[1]/div/span");		

	private final static By locPatientIDInput = By.id("patient-id");
	private final static By locNameInput = By.id("patient-name");		

	private final static By locSignOutLink = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[2]/div/a/span");

	private final static By locPatientsTable = By.xpath("//*[@id='AppContainer']/div[1]/div/section/table");

	private final static By locHomeButton = By.id("homeIcon");		
	
	private final static By locNoResultsLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/section/div[2]/span");
	
	private final static int POSITION_BUTTON_VIEW_PROFILE = 4;
	
	private final static By locIframe = By.xpath("/html/body/iframe");
	
	public DoctorDashboardPageObject() {
		super(expectedPageTitle);
	}

	public DoctorDashboardPageObject(String _expectedPageTitle, WebDriver wd) {
		super(_expectedPageTitle);
		this.driver = wd;
	}

	public WebElement mainIFrame() {
		return getElement(locIframe);
	}	
	
	public WebElement itemsListMenuProfessional() {
		return getElement(locProfessionalDivMenu);
	}	
	
	public WebElement menuOptionAddProfessional() {
		return getElement(locProfessionalMenuAddProfessional);
	}	
	
	public WebElement buttonCreateNewPatient() {
		return getElement(locCreateNewPatientButton);
	}

	public WebElement buttonSearch() {
		return getElement(locSearchButton);
	}
	
	public WebElement labelProfessionalName() {
		return getElement(locProfessionalMenuLabel);
	}

	public WebElement labelPatientName() {
		return getElement(locPatientNameLabel);
	}
	
	public WebElement labelNoResults() {
		return getElement(locNoResultsLabel);
	}
	
	public WebElement labelPatientID() {
		return getElement(locPatientIDLabel);
	}

	public WebElement labelEnterPatientID() {
		return getElement(locEnterPatientIDLabel);
	}

	public WebElement labelSearchForExistingPatient() {
		return getElement(locSearchForExistingPatientLabel);
	}

	public WebElement inputPatientID() {
		return getElement(locPatientIDInput);
	}

	public WebElement inputName() {
		return getElement(locNameInput);
	}
	
	public void waitUntilLoadComplete() {
		fluentWait(locCreateNewPatientButton);
	}

	public void waitUntilSearchIsDone() {
		// fluentWait(locPatientsTable);
		By elem = null;
		try {
			elem = TestService.whichAppearsFirst(this, locPatientsTable, locNoResultsLabel, 30000, 250);
		} catch (Exception e) {
			TestService.fail("Searching for the patient generates an error.");
		}
		if ((elem == null) || (elem == locNoResultsLabel)) {
			TestService.fail("Searching for the patient generates no results (expected at least one result).");
		}
	}

	public void waitUntilNoResultsAppears() {
		fluentWait(locNoResultsLabel);
	}

	public WebElement buttonHome() {
		return getElement(locHomeButton);
	}
	
	public List<WebElement> tablePatientsHeaders() {
		return getElement(locPatientsTable).findElement(By.tagName("thead")).findElement(By.tagName("tr")).findElements(By.tagName("td"));
	}

	public List<WebElement> tablePatientsRows() {
		return getElement(locPatientsTable).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	}

	public List<WebElement> tablePatientsCols(Integer rowNum) {
		return this.tablePatientsRows().get(rowNum).findElements(By.tagName("td"));
	}
	
	public WebElement buttonViewPatientProfile(Integer rowNum) {
		return this.tablePatientsCols(rowNum).get(POSITION_BUTTON_VIEW_PROFILE).findElement(By.tagName("a"));
	}
	
	@Override
	public WebElement buttonLogOff() {
		labelProfessionalName().click();
		fluentWait(locSignOutLink);
		
		return getElement(locSignOutLink);
	}

	public void waitUntilProfessionalMenuDisplays() {
		fluentWait(locProfessionalMenuAddProfessional);
	}
}
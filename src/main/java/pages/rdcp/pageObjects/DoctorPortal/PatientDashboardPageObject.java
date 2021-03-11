package pages.rdcp.pageObjects.DoctorPortal;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

public class PatientDashboardPageObject extends BasePage {
	private final static String expectedPageTitle    = "Not configured yet";
	private final static By locFirstAndLastNameLabel = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]");
	private final static By locPatientHealthCareID = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[3]/div[2]");
	private final static By locDoctorNameLabel              = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[1]/span/span");	
	private final static By locSignOutLink                  = By.xpath("//*[@id='AppContainer']/div[1]/div/div[2]/div/div[2]/div/a/span");
	public  final static By locHypoglycemiaLabel             = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[2]/div[3]/section/header/h2");
	private final static By locPatientSettingsMenuLabel     = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[1]/div[1]/span");
	private final static By locPatientSettingMenu           = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[2]/div/div/div/ul");
	private final static By locPatientMenuEditPatient       = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[2]/div/div/div/ul/li[1]/a/span/span");	
	private final static By locPatientMenuTimeBlocks        = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[2]/div/div/div/ul/li[2]/a/span/span");	
	private final static By locPatientMenuTargetRange       = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[2]/div/div/div/ul/li[3]/a/span/span");
	private final static By locPatientMenuListDevices       = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[2]/div/div/div/ul/li[4]/a/span/span");
	private final static By locPatientMenuDeactivatePatient = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div/div[11]/div/div[2]/div/div/div/ul/li[5]/a/span/span");
	
	private final static By locFramePatientInfo = By.xpath("//*[@id='AppContainer']/div[1]/div/div[3]/div[1]/div[1]/div/div[2]/div/div");	
	
	public PatientDashboardPageObject() {
		super(expectedPageTitle);
	}

	public PatientDashboardPageObject(String _expectedPageTitle, WebDriver wd) {
		super(_expectedPageTitle);
		this.driver = wd;
	}

	public WebElement labelFirstNameLastName() {
		return getElement(locFirstAndLastNameLabel);
	}

	public WebElement labelHealthCareID() {
		return getElement(locPatientHealthCareID);
	}

	public WebElement labelDoctorName() {
		return getElement(locDoctorNameLabel);
	}
	
	public WebElement labelPatientSettingMenu() {
		return getElement(locPatientSettingsMenuLabel);
	}

	public WebElement menuOptionEditPatient() {
		return getElement(locPatientMenuEditPatient);
	}	

	public WebElement menuOptionTimeBlocks() {
		return getElement(locPatientMenuTimeBlocks);
	}	

	public WebElement menuOptionDeactivatePatient() {
		return getElement(locPatientMenuDeactivatePatient);
	}	

	public WebElement menuOptionListDevices() {
		return getElement(locPatientMenuListDevices);
	}	

	public WebElement menuOptionTargetRange() {
		return getElement(locPatientMenuTargetRange);
	}	
	
	public List<WebElement> menuPatientSettings() {
		return getElement(locPatientSettingMenu).findElements(By.tagName("li"));
	}	
	
	public List<WebElement> framePatientInfoTitles() {
		List<WebElement> toReturn = new ArrayList<WebElement>();
		WebElement temp = null;
		
		List<WebElement> firstLevelDivs = getElement(locFramePatientInfo).findElements(By.xpath("./*"));

		for(int i=0; i<firstLevelDivs.size(); i++) {
			try {
				switch (i) {
					case 0:   // Patient First and Last Name
						break;
					case 2:   // Patient Health Care ID
					case 4:   // Patient Date of Birth
					case 6:   // Diabetes type
					case 8:   // Center
					case 10:   // Patient Settings link
						temp = firstLevelDivs.get(i).findElements(By.tagName("span")).get(0);
						toReturn.add( (WebElement) temp );
						break;
				}
			} catch (Exception e) {
				
			}
		}
		
		return toReturn;	
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

	public void waitUntilPatientMenuDisplays() {
		fluentWait(locPatientMenuDeactivatePatient);
	}
}
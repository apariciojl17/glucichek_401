package base;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.awaitility.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.Status;

import pages.BasePage;
import pages.rdcp.pageObjects.PatientPortal.LoginPageObject;

public class NavigationService {	
	public NavigationService() {
		
	}

	public static void goToAPage(String url, WebDriver wd) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Opening page: " + url);
		wd.get(url);
	}

	public static void goToPasswordChangePage(String url, WebDriver wd) {
		wd.get(url);
		WebElement tempiframe = wd.findElement(By.tagName("iframe"));
		wd.switchTo().frame(tempiframe);
	}

	public static boolean Login(String aURL, LoginPageObject loginpo, By dashboardLocator, String _username, String _password) {
		boolean toReturn = false;

		try {
			// This flow needs to begin with an empty mailbox
			if (emptyInbox(_username)) {
				LoginWithoutCodeConfirmation(aURL, loginpo, _username, _password);

				if (TestService.whichAppearsFirst(loginpo, loginpo.locVerificationLabel, dashboardLocator, 30000, 250) == loginpo.locVerificationLabel) {  
				// if (TestService.isPresent(loginpo.labelVerification())) {
					TestService.drawRectangle(loginpo.driver, loginpo.labelEnterCode(), loginpo.inputEnterCode(), "message");

					String code = getCodeFromEmail(_username);

					TestService.unDrawRectangle(loginpo.driver);
					if ((code != null) && (code.trim() != "")) {
						loginpo.inputEnterCode().sendKeys(code);
						loginpo.buttonSubmit().click();

						toReturn = true;
					} else {
						// TODO:  Throughly test this call to the fail method
						fail("Unable to get the verification code from the email");
					}
				} else {
					toReturn = true;    //  TFA are not enabled  
				}
			} else {
				fail("Unable to empty the mailbox before the login");
			}
		} catch (Exception e) {
			fail("Exception catched: Unable to login.");
		}
		return toReturn;
	}

	public static Boolean emptyInbox(String username) {
		Boolean toReturn = true;

		try {
			String _server = username.substring(username.indexOf("@") + 1).toLowerCase().trim();
			String _username = username.substring(0, username.indexOf("@")).toLowerCase().trim();
			
			switch (_server) {
				case "yopmail.com":
					// TO DO: Empty this mailbox, it's already implemented at the end of the code recovery implementation
					break;
				case "ahem.email":
				case "spol.ddns.net":
					AHEM_EmailProvider.init(_username, _server, TestBase.getStartTime());
			    	AHEM_EmailProvider.EmptyInbox();
					break;
				default:

			} 
		} catch (Exception e) {
			toReturn = false;
		}
	
		return toReturn;
	};

	public static void LoginWithoutCodeConfirmation(String aURL, LoginPageObject loginpo, String _username, String _password) {		
		if (aURL != null) {
			NavigationService.goToAPage(aURL, loginpo.driver);
		}
		loginpo.waitUntilLoadComplete();
		
		ExtentTestManager.getTest().log(Status.INFO, "Step: Attemping to sign in with username: '" + _username + "' and password: '" + _password + "'");
		TestService.drawRectangle(loginpo.driver, loginpo.labelEmail(), loginpo.inputLogIn(), "message");
		loginpo.inputLogIn().clear();
		loginpo.inputLogIn().sendKeys(_username);
		TestService.unDrawRectangle(loginpo.driver);
		
		TestService.drawRectangle(loginpo.driver, loginpo.labelPassword() , loginpo.inputPassword(), "message");
		loginpo.inputPassword().clear();
		loginpo.inputPassword().sendKeys(_password);		
		TestService.unDrawRectangle(loginpo.driver);
		
		loginpo.inputLogIn().click();
		loginpo.buttonLogIn().click();
		
		// TestBase.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static String getCodeFromEmail(String _username) {
		String toReturn = null;

		try {
			String server = _username.substring(_username.indexOf("@") + 1).toLowerCase().trim();
			String username = _username.substring(0, _username.indexOf("@")).toLowerCase().trim();
			
			switch (server) {
				case "yopmail.com":
					WebDriver mailDriver = (WebDriver) TestBase.getDriver(TestBase.APPTYPE_WEB, TestBase.getBrowserType());
					try {
						toReturn =  getYopmailValidationCode(mailDriver, _username);
						// mailDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
						
						mailDriver.close();
						TestBase.clearDriver(mailDriver);
					} catch (Exception e) {
						mailDriver.close();
					}
					break;
				case "ahem.email":
				case "spol.ddns.net":
					toReturn =  getAHEMVerificationCode(username, server, null);
					break;
				default:
					// toReturn = getYopmailValidationCode(mailDriver, _username);
			} 
		} catch (Exception e) {

		}

		return toReturn;
	}

	private static String getAHEMVerificationCode(String _username, String _server, String wordToSearchFor) {
		String toReturn = "";
		try {
				AHEM_EmailProvider.init(_username, _server, TestBase.getStartTime());
				// if (AHEM_EmailProvider.EmptyInbox()) {
					Awaitility.with().pollDelay(1000, TimeUnit.MILLISECONDS).and().pollInterval(3000, TimeUnit.MILLISECONDS).await().atMost(60, TimeUnit.SECONDS).until(() -> 
						AHEM_EmailProvider.isNotEmptyInbox(true) == true
					);

				    if (AHEM_EmailProvider.isNotEmptyInbox(false)) {
				    	if (wordToSearchFor == null) {
					    	toReturn = AHEM_EmailProvider.getVerificationCode();
				    	} else {
				    		toReturn = AHEM_EmailProvider.getValidationURL(wordToSearchFor);
				    	}
				    	AHEM_EmailProvider.EmptyInbox();
				    }
				// }
		  	} catch (Exception ex) {
		  		ex.printStackTrace();
		  		toReturn = "";
		  	}
		return toReturn;
	}

	private static String getYopmailValidationCode(WebDriver mailDriver, String _username) {
		// legacy code, TODO: transform to PageObject...
		
		String destiny = "http://www.yopmail.com/en/";
		mailDriver.get(destiny);
		
		// System.out.println("getYopmailValidationCode - Flag 1");
		// mailDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestService.fluentWait(mailDriver, By.id("login"));

		mailDriver.findElement(By.id("login")).click();
		mailDriver.findElement(By.id("login")).clear();
		mailDriver.findElement(By.id("login")).sendKeys(_username);
		TestService.fluentWait(mailDriver, By.id("f"));
		mailDriver.findElement(By.id("f")).submit();
		// mailDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// System.out.println("getYopmailValidationCode - Flag 2");
		TestService.fluentWait(mailDriver, By.id("ifmail"));
		WebElement mail_iframeSwitch = mailDriver.findElement(By.id("ifmail"));
		// System.out.println("getYopmailValidationCode - Flag 3");
		mailDriver.switchTo().frame(mail_iframeSwitch);
		// System.out.println("getYopmailValidationCode - Flag 4");
		
		TestService.fluentWait(mailDriver, By.className("verificationCode"));
		String toReturn = mailDriver.findElement(By.className("verificationCode")).getText();
		
		/*
		// System.out.println("getYopmailValidationCode - Flag 5");
		mailDriver.switchTo().parentFrame();
		// System.out.println("getYopmailValidationCode - Flag 6");

		TestService.fluentWait(mailDriver, By.id("ifinbox"));
		WebElement inbox_iframeSwitch = mailDriver.findElement(By.id("ifinbox"));
		// System.out.println("getYopmailValidationCode - Flag 7");
		mailDriver.switchTo().frame(inbox_iframeSwitch);
		
		// System.out.println("getYopmailValidationCode - Flag 8");
		WebElement deleteButton = mailDriver.findElement(By.className("lmenudelall"));
		// System.out.println("getYopmailValidationCode - Flag 9");
		WebElement emptyButton = mailDriver.findElement(By.className("lmen_all"));
		// System.out.println("getYopmailValidationCode - Flag 10");
		deleteButton.click();
		// System.out.println("getYopmailValidationCode - Flag 11");
		emptyButton.click();
		// System.out.println("getYopmailValidationCode - Flag 12");
		*/
		return toReturn; 
	}

	public static String getLinkFromEmail(String _username, String wordToSearchFor) {
		String toReturn = null;

		try {
			String server = _username.substring(_username.indexOf("@")+1).toLowerCase().trim();
			
			switch (server) {
				case "yopmail.com":
					WebDriver mailDriver = TestBase.getDriver();			
					toReturn =  getYopmailValidationURL(mailDriver, _username, wordToSearchFor);
					// mailDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

					TestBase.clearDriver(mailDriver);
					break;
				case "ahem.email":
				case "spol.ddns.net":
					_username = _username.substring(0, _username.indexOf("@")).toLowerCase().trim();
					toReturn =  getAHEMVerificationCode(_username, server, wordToSearchFor);
					break;
			} 
		} catch (Exception e) {
			
		}
		
		return toReturn;
	}

	private static String getYopmailValidationURL(WebDriver mailDriver, String _username, String wordToSearchFor) {
		String toReturn = null;

		String destiny = "http://www.yopmail.com/en/";
		mailDriver.get(destiny);

		mailDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		mailDriver.findElement(By.id("login")).click();
		mailDriver.findElement(By.id("login")).clear();
		mailDriver.findElement(By.id("login")).sendKeys(_username);
		mailDriver.findElement(By.id("f")).submit();
		mailDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement mail_iframeSwitch = mailDriver.findElement(By.id("ifmail"));
		mailDriver.switchTo().frame(mail_iframeSwitch);

		toReturn = mailDriver.findElement(By.linkText(wordToSearchFor)).getAttribute("href");
		
		mailDriver.switchTo().parentFrame();
		WebElement inbox_iframeSwitch = mailDriver.findElement(By.id("ifinbox"));
		mailDriver.switchTo().frame(inbox_iframeSwitch);
		
		WebElement deleteButton = mailDriver.findElement(By.className("lmenudelall"));
		WebElement emptyButton = mailDriver.findElement(By.className("lmen_all"));
		deleteButton.click();
		emptyButton.click();
		
		return toReturn;
	}	

	public static String getPasswordResetConfirmationSubject(String _username) {
		String toReturn = null;

		try {
			String server = _username.substring(_username.indexOf("@")+1).toLowerCase().trim();
			
			switch (server) {
				case "yopmail.com":
					break;
				case "ahem.email":
				case "spol.ddns.net":
					Awaitility.with().pollDelay(1000, TimeUnit.MILLISECONDS).and().pollInterval(3000, TimeUnit.MILLISECONDS).await().atMost(60, TimeUnit.SECONDS).until(() -> 
						AHEM_EmailProvider.isNotEmptyInbox(true) == true
					);

				    if (AHEM_EmailProvider.isNotEmptyInbox(false)) {
				    	toReturn = AHEM_EmailProvider.getSubject();
				    }
					break;
			} 
		} catch (Exception e) {
			
		}
		
		return toReturn;
	}
	
	public static void Logout(BasePage currentPage, LoginPageObject loginPage) {
		currentPage.buttonLogOff().click(); 

		loginPage.waitUntilLoadComplete();
	}
}

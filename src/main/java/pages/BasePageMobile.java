package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
// import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class BasePageMobile {
	public static AppiumDriver<MobileElement> driver;
	private final String expectedTitle;
	private String defaultNonTitleConfiguredMessage = "Title needs to be configured...";
	// private WebDriver driver;
  
	public BasePageMobile() {
		this.expectedTitle = defaultNonTitleConfiguredMessage;
		// this.driver = _driver;
	}

	public BasePageMobile(String defaultTitle, AppiumDriver<MobileElement> _driver) {
		// this.driver = _driver;
		expectedTitle = defaultTitle;
		driver = _driver;
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public String getExpectedPageTitle() {
		return expectedTitle;
	}	
	
	public MobileElement getElement(By locator) {
		MobileElement element = null;
		try {
			element = driver.findElement(locator);
	    } catch (org.openqa.selenium.NoSuchElementException e) {

	    }
		return element; 
	}

	public List<MobileElement> getElements(By locator) {
		List<MobileElement> elements = null;
		try {
			elements = driver.findElements(locator);
	    } catch (org.openqa.selenium.NoSuchElementException e) {

	    }
		return elements; 
	}

	public List<MobileElement> getElementsbyClassName(String locator) {
		List<MobileElement> elements = null;
		try {
			elements = driver.findElementsByClassName(locator);
	    } catch (org.openqa.selenium.NoSuchElementException e) {

	    }
		return elements; 
	}
	
	public List<MobileElement> getElements(MobileElement elem, By locator) {
		List<MobileElement> elements = null;
		try {
			elements = elem.findElements(locator);
	    } catch (org.openqa.selenium.NoSuchElementException e) {

	    }
		return elements; 
	}

	// Must be override in the child who needs it
	public WebElement buttonLogOff() {
		return null;
	};

    public static boolean waitForPresence(int timeLimitInSeconds, MobileElement e){
    	Boolean isElementPresent = false;
	    try{
	    	WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
	    	wait.until(ExpectedConditions.visibilityOf(e));
	    	isElementPresent = e.isDisplayed();
	    }catch(Exception ex){
	    	isElementPresent = false;
	    	System.out.println(ex.getMessage());
	    }
    	return isElementPresent;	
    }

	@SuppressWarnings("deprecation")
	protected WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(500, TimeUnit.MILLISECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};

	protected void fluentWaitDisappear(final By locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitUntilElementAppears(MobileElement t) {
		waitForPresence(30, t);
	}
}

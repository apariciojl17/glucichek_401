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

import base.TestService;

public class BasePage {
	public WebDriver driver;
	private final String expectedTitle;
	private String defaultNonTitleConfiguredMessage = "Title needs to be configured...";
	
	public BasePage() {
		this.expectedTitle = defaultNonTitleConfiguredMessage;
	}

	public BasePage(String defaultTitle) {
		expectedTitle = defaultTitle;
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public String getExpectedPageTitle() {
		return expectedTitle;
	}	
	
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
	    } catch (org.openqa.selenium.NoSuchElementException e) {

	    }
		return element; 
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(locator);
	    } catch (org.openqa.selenium.NoSuchElementException e) {

	    }
		return elements; 
	}

	public List<WebElement> getElements(WebElement elem, By locator) {
		List<WebElement> elements = null;
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

	@SuppressWarnings("deprecation")
	protected WebElement fluentWait(final By locator) {
	    return TestService.fluentWait(driver, locator);
		/* Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(500, TimeUnit.MILLISECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo; */
	};

	protected void fluentWaitDisappear(final By locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	};
}

package base;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import pages.BasePage;

public class TestService {
	public static boolean shouldVerifyJavascriptConsole = false;
	private static Set<String> errorStrings = new HashSet<>();    
	private static Set<Level> errorLevels = new HashSet<>();

	public static String rect_id = "qa_rect_around";
	
	public TestService() {
		
	}

	public static void setJavascriptConsoleParams(String verifyConsole, String javascriptErrorStrings, String javascriptErrorLevels) {
		if (verifyConsole != null) {
			shouldVerifyJavascriptConsole = !(verifyConsole.trim().contains("0") || verifyConsole.toLowerCase().trim().contains("f") || verifyConsole.toLowerCase().trim().contains("no") || verifyConsole.toLowerCase().trim().contains("off"));
	
			for (String errorString : javascriptErrorStrings.split(",")) {
				errorStrings.add(errorString);
			}
	
			for (String errorLevel : javascriptErrorLevels.split(",")) {
				switch(errorLevel) {
					case "SEVERE":
						errorLevels.add(Level.SEVERE);	
						break;
					case "WARNING":
						errorLevels.add(Level.WARNING);	
						break;
					case "INFO":
						errorLevels.add(Level.INFO);	
						break;
					case "CONFIG":
						errorLevels.add(Level.CONFIG);	
						break;
					case "FINE":
						errorLevels.add(Level.FINE);	
						break;
					case "FINER":
						errorLevels.add(Level.FINER);	
						break;
					case "FINEST":
						errorLevels.add(Level.FINEST);	
						break;
				}
			}
		}
	}

	public static void checkPresenceAndVisibilityAndValue(WebElement elem, String expectedValue, String message) { 
		checkPresenceAndVisibility(elem, message);
		checkExactMatch(elem, expectedValue, message);
	}

	public static void checkPresenceAndVisibilityAndValueByAttribute(WebElement elem, String expectedValue, String message) { 
		checkPresenceAndVisibility(elem, message);
		checkExactMatch(elem.getAttribute("value"), expectedValue, message);
	}

	public static void checkPresenceAndVisibility(WebElement elem, String message) {		
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the presence and visibility of '" + message + "'");
		if (elem != null) {
			if (!isPresent(elem) || !elem.isDisplayed()) {
				Assert.fail("<b>Element</b>: '" + message + "' is not present or is not visible");
			}
		} else {
				Assert.fail("<b>Element</b>: '" + message + "' is not present or is not visible");
		  }		
	}

	public static void checkExactMatch(WebElement elem, String expectedValue, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the '" + message + "', expected to be: '" + expectedValue + "'");
		if (elem != null) {
			try {
				expectedValue = expectedValue.trim();
				String temp = elem.getText().trim();
				if (! temp.equalsIgnoreCase(expectedValue)) {
					Assert.fail("<b>Element</b>: '" + message + "' doesn't match<br><b>Current:</b> '" + temp + "'<br><b>Expected:</b> '" + expectedValue + "'");
				}
			} catch ( Exception e ) {
				Assert.fail("<b>Element</b>: '" + message + "' generates an error at the time of the assert.");
			}
		} else {
			Assert.fail("<b>Element</b>: '" + message + "' is not present on the page");
		}
	}

	public static void checkIsChecked(WebElement elem, String expectedValue, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the '" + message + "', expected to be: '" + expectedValue + "'");
		if (elem != null) {
			try {
				expectedValue = expectedValue.trim();
				String temp = elem.getAttribute("checked");
				if (! temp.equalsIgnoreCase(expectedValue)) {
					Assert.fail("<b>Element</b>: '" + message + "' doesn't match<br><b>Current:</b> '" + temp + "'<br><b>Expected:</b> '" + expectedValue + "'");
				}
			} catch ( Exception e ) {
				Assert.fail("<b>Element</b>: '" + message + "' generates an error at the time of the assert.");
			}
		} else {
			Assert.fail("<b>Element</b>: '" + message + "' is not present on the page");
		}
	}

	public static void checkExactMatch(Integer elem, Integer expectedValue, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the '" + message + "', expected to be: '" + expectedValue + "'");
		if (elem != null) {
			try {
				if ( elem.intValue() != expectedValue.intValue() ) {
					Assert.fail("<b>Element</b>: '" + message + "' doesn't match<br><b>Current:</b> '" + elem + "'<br><b>Expected:</b> '" + expectedValue + "'");
				}
			} catch ( Exception e ) {
				Assert.fail("<b>Element</b>: '" + message + "' generates an error at the time of the assert.");
			}
		} else {
			Assert.fail("<b>Element</b>: '" + message + "' is not present on the page");
		}
	}

	public static void checkExactMatch(Boolean elem, Boolean expectedValue, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the '" + message + "', expected to be: '" + expectedValue + "'");
		if (elem != null) {
			try {
				if ( elem.booleanValue() != expectedValue.booleanValue() ) {
					Assert.fail("<b>Element</b>: '" + message + "' doesn't match<br><b>Current:</b> '" + elem + "'<br><b>Expected:</b> '" + expectedValue + "'");
				}
			} catch ( Exception e ) {
				Assert.fail("<b>Element</b>: '" + message + "' generates an error at the time of the assert.");
			}
		} else {
			Assert.fail("<b>Element</b>: '" + message + "' is not present on the page");
		}
	}

	public static void checkExactMatch(String[] elems, String[] expectedValues, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the '" + message + "', expected to be: [" + String.join(",'", expectedValues) + "]");
		if (elems != null) {
			try {
				if ( ! Arrays.equals(elems, expectedValues) ) {
					Assert.fail("<b>Element</b>: '" + message + "' doesn't match<br><b>Current:</b> [" + String.join(",'", elems) + "]<br><b>Expected:</b> [" + String.join(",'", expectedValues) + "]");
				}
			} catch ( Exception e ) {
				Assert.fail("<b>Element</b>: '" + message + "' generates an error at the time of the assert.");
			}
		} else {
			Assert.fail("<b>Element</b>: '" + message + "' is not present on the page");
		}
	}
	
	public static void checkContains(List<WebElement> elem, String expectedValues[], String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the contents of '" + message + "', expected to contains: ['" + String.join("'", expectedValues) + "']");
		if (elem != null) {
			try {
				int externalCount = 0;
				boolean externalExit = false;
				boolean internalFound;
				while ((externalCount<expectedValues.length) && !externalExit) {
					internalFound = false;
					int j=0;
					while ((j<elem.size()) && !internalFound) {
						internalFound = (expectedValues[externalCount].trim().toLowerCase().contains(elem.get(j).getText().trim().toLowerCase()));
						j++;
					}
					if (internalFound) {
						externalCount++;
					} else {
						externalExit = true;
					}
				}
				if (externalExit) {
					Assert.fail("<b>Element</b>: '" + message + "' doesn't contain all the elements in [" +  String.join(", ", expectedValues) + "]");
				}
			} catch ( Exception e ) {
				Assert.fail("<b>Element</b>: '" + message + "' generates an error at the time on the assert.");
			}
		} else {
			Assert.fail("<b>Element</b>: '" + message + "' is not present on the page");
		}
	}

	public static void checkContains(String value, String expectedValue, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating that '" + message + "' contains: '" + expectedValue + "'");
		if (! value.trim().toLowerCase().contains(expectedValue.trim())) {
			Assert.fail("<b>Element</b>: '" + message + "' with the value '" + value + "' doesn't contains '<b>" + expectedValue + "</b>'");
		}
	}

	public static void checkTrue(Boolean value, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating that '" + message + "'");
		if (value != true ) {
			Assert.fail("<b>Element</b>: '" + message + "' is not TRUE.");
		}
	}

	public static void checkExactMatch(String value, String expectedValue, String message) {	
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the '" + message + "', expected to be: '" + expectedValue + "'");
		if (! value.trim().equalsIgnoreCase(expectedValue.trim())) {
			Assert.fail("<b>Element</b>: '" + message + "' doesn't match<br><b>Current:</b> '" + value.trim() + "'<br><b>Expected:</b> '" + expectedValue + "'");
		}
	}

	public static void checkExactTitle(BasePage me) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the window title, expected exact match: '" + me.getExpectedPageTitle() + "'");
		Assert.assertEquals(me.getPageTitle(), me.getExpectedPageTitle(), "Page title doesn't match");		
	}

	public static void checkDisabled(WebElement elem, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating that '" + message + "' is disabled");
		Assert.assertFalse(elem.isEnabled(), "Element is not disabled");
	}

	public static void checkEnabled(WebElement elem, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating that '" + message + "' is enabled");
		Assert.assertTrue(elem.isEnabled(), "Element is not enabled");
	}

	public static void setAndCheckInput(WebElement elem, String data, String message, boolean clearAndSendCR) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating that field '" + message + "' is writeable and retains data");
		if (clearAndSendCR) {
			elem.clear();
		}

		elem.sendKeys(data);

		if (clearAndSendCR) {
			elem.sendKeys(Keys.RETURN);
		}

		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating field '" + message + "', expected to be: '" + data + "'");
		Assert.assertEquals(elem.getAttribute("value"), data, "Written value and readed value doesn't match");
	}

	public static void setInput(WebElement elem, String data, String message, boolean clearAndSendCR) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Sending this data '" + data + "' to the input field '" + message + "'");
		if (clearAndSendCR) {
			elem.clear();
		}
		
		elem.sendKeys(data);

		if (clearAndSendCR) {
			elem.sendKeys(Keys.RETURN);
		}
	}

	public static void checkContainsTitle(BasePage me) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating the window title, expected to contain: '" + me.getExpectedPageTitle() + "'");
		String currentTitle = me.getPageTitle();
		String expectedTitle = me.getExpectedPageTitle();
		if (! currentTitle.contains(expectedTitle)) {
			Assert.fail("Title doesn't match, <br>current: '" + currentTitle + "', <br>expected: '" + expectedTitle + "'");
		}
	}

	public static boolean isPresent(WebElement elem) {
	    return elem != null;
	}

	public static void setCheckedValue(WebElement elem, Boolean value, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Set the value of '" + message +  "' at '" + value + "'");
		elem.click();
		if (value != elem.isSelected()) {
			elem.click();
		}
	}

	public static void clickUsingJavascript(WebDriver wd, WebElement elem, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Set the value of '" + message +  "' as 'Selected'");
		JavascriptExecutor js = (JavascriptExecutor) wd;	
		js.executeScript("arguments[0].click();", elem);		
	}

	public static By whichAppearsFirst(BasePage bo, By elem1, By elem2, int maxMsToWait, int pollingEveryms) throws InterruptedException {
		// this will wait a maximum of maxSecondsToWait seconds for elem1 or elem2 to appear polling every pollingEvery ms
		By toReturn = null; 
		int iterations = maxMsToWait / (pollingEveryms);
		
		boolean found = false;
		while (!found && iterations>0) {
			found = isPresent(bo.getElement(elem1)) || isPresent(bo.getElement(elem2));
			if (!found) {
				Thread.sleep(pollingEveryms);
				iterations--;
			}
		}
		if (isPresent(bo.getElement(elem1))) {
			toReturn = elem1;
		} else {
			if (isPresent(bo.getElement(elem2))) {
				toReturn = elem2;
			}
		}
		return toReturn;
	}
	
	public static void drawRectangle(WebDriver wd, WebElement label, WebElement input, String message) {
		drawRectangle(wd, label, input, 0, 0, message);
	}
	
	public static void drawRectangle(WebDriver wd, WebElement label, WebElement input, double extraHeight, double extraWidth, String message) {
		try {
			String jsScript = "function draw_rectangle(label, email, style, color){ " + 
							  "    var email_rect = email.getBoundingClientRect(); " +
							  "    console.log(email_rect.top, email_rect.left, email_rect.right, email_rect.bottom); " +
							  "     " +
							  "    var label_rect = label.getBoundingClientRect(); " +
							  "    console.log(label_rect.top, label_rect.left, label_rect.right, label_rect.bottom); " +
							  "     " +
							  "    var height = email_rect.bottom - label_rect.top + 40 + " + extraHeight + "; " +
							  "    var width = email_rect.right - email_rect.left + 40 + " + extraWidth + "; " +
							  "    console.log(height, width); " +
							  "     " +
							  "    var div = document.createElement('div'); " +
							  "    div.id = '" + rect_id + "'; " +
							  "    div.style.cssText = 'display: block; position: fixed; z-index: 1; padding-top: 0px; overflow: auto; background-color: transparent; margin: auto; padding: 20px; border: 6px solid ' + color + '; border-style:' + style + '; width: ' + width + 'px; height: ' + height + 'px; top: ' " + 
							  " + (label_rect.top - 20) + 'px; left: ' + (label_rect.left - 20) + 'px;}'; " +
							  "    document.body.appendChild(div); " +
							  "}" +
							  "     " +
							  "var label  = arguments[0]; " +
							  "var input  = arguments[1]; " +
							  "draw_rectangle(label, input, 'dashed', '#517F7D');";
	
			JavascriptExecutor js = (JavascriptExecutor) wd;	
			js.executeScript(jsScript, label, input);
		} catch (Exception e ) {
			
		}
	}

	public static void unDrawRectangle(WebDriver wd) {
		try {
			// TimeUnit.MILLISECONDS.sleep(250);
			
			String jsScript = "var div = document.getElementById('" + rect_id + "'); " + 
							  "document.body.removeChild(div); ";
	
			JavascriptExecutor js = (JavascriptExecutor) wd;	
			js.executeScript(jsScript);
		} catch (Exception e ) {
			
		}
	}

	public static void clearUsingJavascript(WebElement elem, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Clear the value of '" + message +  "'");
		JavascriptExecutor js = (JavascriptExecutor) TestBase.getDriver();	
		js.executeScript("arguments[0].value= '';", elem);		
	}

	public static void setValueUsingJavascript(WebDriver driver, WebElement elem, String field, String value) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Setting the value to '" + value +  "' for the field '" + field + "' using Javascript");
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("arguments[0].value = '" + value + "';", elem);		
	}

	public static void ensureItsVisibleUsingJavascript(WebDriver driver, WebElement elem, String message) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Ensuring the object '" + message +  "' is in the current viewport");
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("arguments[0].scrollIntoView();", elem);		
	}

	public static void checkRadioButtonOrCheckBox(WebElement elem, String message, boolean value) {
		ExtentTestManager.getTest().log(Status.INFO, "Step: Validating that '" + message + "' is " + (value ? "":"not") + " selected/checked");
		Assert.assertEquals(elem.isSelected(), value, "Element is not enabled");
	}

	public static void WriteAWarning(String message) {
		ExtentTestManager.getTest().log(Status.WARNING, "Step: '" + message);
	}

	public static String getJSNetworkActivity(WebDriver driver, String search) {	 
		String toReturn = ""; 
		
		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();

		// System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
	    for (LogEntry entry : entries) {
	        // System.out.println(entry.getMessage());
	        if ((entry.toJson().toString().indexOf(search) > 0) ) {
	        	toReturn += entry.toJson() + "\n";
	        }
	    }
	    
	    return toReturn; 
    }
	
	public static String getJSErrorsOnThePage() {	 
		String jsConsoleErrors = null;
		if (shouldVerifyJavascriptConsole) {
			LogEntries logEntries = ((WebDriver) TestBase.getLastDriver()).manage().logs().get(LogType.BROWSER);

		    Set<LogEntry> filteredtemp =  errorLevels.stream().flatMap(el -> logEntries.getAll().stream().filter(logent -> el.equals(logent.getLevel()))).collect(Collectors.toCollection(LinkedHashSet::new));
		    Set<LogEntry> filteredLogEntries =  errorStrings.stream().flatMap(el -> filteredtemp.stream().filter(logent -> logent.getMessage().contains(el))).collect(Collectors.toCollection(LinkedHashSet::new));

		    if (filteredLogEntries.size() >0) {
		    	jsConsoleErrors = "";
			    for (LogEntry logEntry : filteredLogEntries) {
	                // System.out.println("Java Script error has been detected:");
	                jsConsoleErrors += " - " + (new Date(logEntry.getTimestamp()) + " [<b>" + logEntry.getLevel() + "</b>] " + logEntry.getMessage());
			    }

			    if (jsConsoleErrors.trim().length() > 0) {
			    	jsConsoleErrors = "<b>Java Script errors has been detected:</b><BR>" + jsConsoleErrors;
			    }
	    	}		    
		}
		return jsConsoleErrors;
	}

	public static void fail(String sMessage) {
		fail(sMessage);
	}

	public static WebElement fluentWait(Object driver, final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>((WebDriver) driver)
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
	
	public static void clearJSConsole() {
		/*if (!TestBase.isNullDriver(TestBase.WEBDRIVER_MAIN_APP)) {
			JavascriptExecutor js = (JavascriptExecutor) TestBase.getDriver();	
			js.executeScript("console.clear();");
		}*/
	}
}

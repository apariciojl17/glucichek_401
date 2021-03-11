package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {
	public static final int APPTYPE_WEB = 0;
	public static final int APP_TYPE_MOBILE_NATIVE = 1;
	public static final int APP_TYPE_MOBILE_WEB = 2;

	public static final int OS_ANDROID = 0;
	public static final int OS_IOS = 1;
	public static final int OS_WINDOWS = 2;

	public static final String WEBDRIVER_CHROME = "chrome";
	public static final String WEBDRIVER_FIREFOX = "firefox";
	public static final String WEBDRIVER_OPERA = "opera";
	public static final String WEBDRIVER_IE = "ie";
	public static final String WEBDRIVER_EDGE = "edge";
	public static final String WEBDRIVER_SAFARI = "safari";
	
	private static Long startedTime;
	
	private static List<Object> driver;
	private static String driverPath = "C:\\roche\\Selenium\\";
	
	private static String _newRecordEmailTemplate = null;
	private static String _newRecordEmailTemplatePrefix = null;
	private static String _newRecordPasswordTemplate = null;
	
	private static String _browserType = null;
	private static String _propertiesFile = null;
	private static String _LookForJavascriptConsoleError = null;
	private static String _JavascriptConsoleErrorStrings = null;
	private static String _JavascriptConsoleErrorLevels = null;

	private static String _environment = null;
	private static String _language = null;
	public static String professionalAppURL = null;
	public static String patientAppURL = null;
	private static String seleniumURL = null;
	private static String seleniumMode = null;
	private static String seleniumWindowSize = null;
	private static String appiumURL = null;

	private static String androidDeviceName = null;
	private static String androidDeviceUDID = null;
	private static String androidAppPath = null;

	private static String iosPlatformVersion = null;
	private static String iosDeviceName = null;
	private static String iosAppPath = null;

	public static String  patientLoginUsername = null;
	public static String  patientLoginPassword = null;
	public static String  patientLoginName = null;	
	public static String  patientFirstName = null;
	public static String  patientLastName = null;
	public static String  patientHealthCareID = null;
	public static Integer patientDateOfBirthDay = null;
	public static Integer patientDateOfBirthMonth = null;
	public static Integer patientDateOfBirthYear = null;
	public static String  patientDiabetesType = null;

	public static String professionalLoginUsername = null;
	public static String professionalLoginPassword = null;
	public static String professionalLoginName = null;	
	
	private static String reportDirLocation = null;

	private static Boolean abortTestSuite = false;
	private static Boolean abortTestSuiteScreenshotTaken = false;

	public static int WEBDRIVER_MAIN_APP = 0;
	public static int WEBDRIVER_EMAIL_VALIDATION_APP = 1;

	public static Object currentDriver = null; 

	public static boolean getScreenshots = true; 	
	
	public static String getEnvironment() {
		return _environment;
	};

	public static String getLanguage() {
		return _language;
	};

	public static Object getDriver(int appType, int os, String browser) {
		Object wd = null;
		switch (appType) {
			case APP_TYPE_MOBILE_NATIVE:
			case APP_TYPE_MOBILE_WEB:		
				wd = createDriverAppium(appType, os, browser);
				break;
			case APPTYPE_WEB:
				wd = createDriverSelenium(browser);
				break;
		}
		driver.add(wd);
		TestBase.currentDriver = wd;
		return wd;
	}

	public static Object getDriver(int appType, String browser) {
		Object wd = null;
		switch (appType) {
			case APPTYPE_WEB:
				wd = createDriverSelenium(browser);
				break;
		}
		driver.add(wd);
		TestBase.currentDriver = wd;
		return wd;
	}

	public static WebDriver getDriver() {
		WebDriver wd = createDriverSelenium(TestBase.WEBDRIVER_CHROME);
		
		driver.add(wd);
		return wd;
	}

	public static Object getLastDriver() {
		Object toReturn = null;
		
		if (driver.size() > 0) {
			toReturn = driver.get(driver.size() - 1); 
		}
		return toReturn;
	}
	
	public static boolean isNullDriver(int app) {
		return (driver.get(app) == null);
	}

	public static void clearDriver(Object wd) {		
		driver.remove(wd);
	}

	public static String getAndroidDeviceName() {
		return androidDeviceName;
	}	

	public static String getAndroidDeviceUDID() {
		return androidDeviceUDID;
	}	

	public static String getAndroidAppPath() {
		return androidAppPath;
	}	
	
	public static String getIOsPlatformVersion() {
		return iosPlatformVersion;
	}	

	public static String getIOsDeviceName() {
		return iosDeviceName;
	}	
	
	public static String getIOsAppPath() {
		return iosAppPath;
	}

	public static String getBrowserType() {
		return _browserType;
	}

	public static String getEmailTemplate() {
		return _newRecordEmailTemplate;
	}	

	
	public static String getEmailTemplatePrefix() {
		return _newRecordEmailTemplatePrefix;
	}	

	public static String getPasswordTemplate() {
		return _newRecordPasswordTemplate;
	}	

    public static Boolean getabortTestSuiteScreenshotTaken() {
        return abortTestSuiteScreenshotTaken;
    }

    public static void setabortTestSuiteScreenshotTaken(Boolean _abortTestSuiteScreenshotTaken) {
    	abortTestSuiteScreenshotTaken = _abortTestSuiteScreenshotTaken;
    }

    public static Boolean getAbortTestSuite() {
        return abortTestSuite;
    }

    public static void setAbortTestSuite(Boolean _abortTestSuite) {
        abortTestSuite = _abortTestSuite;
    }

    public static String getReportDirLocation() {
    	return reportDirLocation;
    }
    
	private static WebDriver createDriverSelenium(String browser) {
		WebDriver _driver = null;
		switch (browser.toLowerCase()) {
			case TestBase.WEBDRIVER_CHROME:
				_driver = initChromeDriver(seleniumURL);
				break;
			case TestBase.WEBDRIVER_FIREFOX:
				_driver = initFirefoxDriver();
				break;
			case TestBase.WEBDRIVER_OPERA:
				_driver = initOperaDriver();
				break;
			default:
				_driver = initChromeDriver(seleniumURL);
		}
		return _driver;
	}

	private static AppiumDriver<MobileElement> createDriverAppium(int appType, Integer os, String browser) {
		AppiumDriver<MobileElement> _driver = null;
		switch (appType) {
			case TestBase.APP_TYPE_MOBILE_NATIVE:
			case TestBase.APP_TYPE_MOBILE_WEB:
				_driver = initDriverMobile(os, appType, browser);
				break;
		}
		return _driver;
	}

	private static WebDriver initChromeDriver(String seleniumURL) {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		caps.setCapability( "goog:loggingPrefs", logPrefs);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments( "--ignore-certificate-errors", "--ignore-ssl-errors");
		if ((seleniumMode != null) && (seleniumMode.trim().length() > 0)) { 
			options.addArguments(seleniumMode);
		}
		if ((seleniumWindowSize != null) && (seleniumWindowSize.trim().length() > 0)) { 
			options.addArguments("window-size=" + seleniumWindowSize);
		}
		
		try {
			options.merge(caps);
			// WebDriver driver = new RemoteWebDriver(new URL(seleniumURL),  caps);   // ChromeDriver();
			WebDriver driver = new RemoteWebDriver(new URL(seleniumURL),  options);   // ChromeDriver();
			
			if ((seleniumWindowSize == null) || (seleniumWindowSize.trim().length() == 0)) { 
				driver.manage().window().maximize();
			}		
			return driver;
		} 
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			System.out.println("Exception creating Webdriver\nStack trace:\n" + sw.toString());
			 
			System.exit(1);
			return null;
		}
	}

	private static AppiumDriver<MobileElement> initDriverMobile(int os, int appType, String browser) {
		DesiredCapabilities caps = new DesiredCapabilities();
		AppiumDriver<MobileElement> driver = null;
		
		try {
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "120");
			switch (appType) {
				case APP_TYPE_MOBILE_WEB:
					caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
					// caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0.1");
					caps.setCapability(MobileCapabilityType.DEVICE_NAME, TestBase.getAndroidDeviceName());
					caps.setCapability(MobileCapabilityType.UDID, TestBase.getAndroidDeviceUDID());
					switch (browser.toLowerCase()) {
						case WEBDRIVER_CHROME:
							caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
							break;
						case WEBDRIVER_FIREFOX:   
							caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Firefox");  // not tested yet 
							break;
						case WEBDRIVER_OPERA:   
							caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Opera");  // not tested yet 
							break;
					}
					driver = new AppiumDriver<MobileElement>(new URL(appiumURL), caps);
					break;
				case APP_TYPE_MOBILE_NATIVE:
					caps.setCapability(MobileCapabilityType.FULL_RESET, false);
					caps.setCapability(MobileCapabilityType.NO_RESET, true);
					switch (os) {
						case OS_ANDROID:
							caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
							caps.setCapability(MobileCapabilityType.DEVICE_NAME, TestBase.getAndroidDeviceName());

							// caps.setCapability(MobileCapabilityType.APP, TestBase.getAndroidAppPath());
							String apkLocation = TestBase.getAndroidAppPath();
							File app=new File(apkLocation);
							apkLocation = app.getAbsolutePath();

							caps.setCapability(MobileCapabilityType.APP, apkLocation);

							caps.setCapability(MobileCapabilityType.UDID, TestBase.getAndroidDeviceUDID());
							// caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "120");
							// caps.setCapability("appActivity", "org.appcelerator.titanium.TiActivity");
							// caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.roche.glucichek.*");
							caps.setCapability("adbExecTimeout", "120000");
							caps.setCapability("appPackage", "com.roche.glucichek.rc");
							// caps.setCapability("appActivity", "org.appcelerator.titanium.TiActivity");
							// caps.setCapability("appWaitActivity", "org.appcelerator.titanium.TiActivity");
							caps.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);   // appium re-signs the .apk file and change the content on the META-INF directory, making the app uninstallable
							// caps.setCapability(MobileCapabilityType.FULL_RESET, false);
							// caps.setCapability(MobileCapabilityType.NO_RESET, true);
							caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
							driver = new AndroidDriver<MobileElement>(new URL(appiumURL), caps);

					        break;
						case OS_IOS:
					        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
					        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);

					        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, TestBase.getIOsPlatformVersion());
					        caps.setCapability(MobileCapabilityType.DEVICE_NAME, TestBase.getIOsDeviceName());
					        caps.setCapability(MobileCapabilityType.APP, TestBase.getIOsAppPath());
					        // caps.setCapability(MobileCapabilityType.FULL_RESET, false);
					        // caps.setCapability(MobileCapabilityType.NO_RESET, true);
					        driver = new IOSDriver<>(new URL(appiumURL), caps);
					        break;
					}
			}					
			return driver;
		} catch(Exception e) {
			System.out.println("Exception creating Mobile driver\nCause: " + e.getCause() + "\nMessage: " + e.getMessage());
			e.printStackTrace();

			System.exit(1);
			return null;
		}
	}

	public static String getCookies(WebDriver wd) {
		String toReturn = "";
		try {
            for(org.openqa.selenium.Cookie ck : wd.manage().getCookies())							
            	toReturn +=	ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure() + "\n";
		} catch (Exception e) {
			
		}
		return toReturn;
	}
	
	public static List<String> extractTokens(String inCookies, String separator, String begin, String end) {
		List<String> toReturn = new ArrayList<String>();
		int needle = 0;
		int endIndex;

		int beginIndex = inCookies.indexOf(begin, needle);
		while (beginIndex != -1 ) {
			endIndex = inCookies.indexOf(end, needle);
			
			toReturn.add(inCookies.substring(beginIndex, endIndex + end.length()));
			needle = endIndex + end.length();
			beginIndex = inCookies.indexOf(begin, needle);
		}
		
		return toReturn;
	}	
	
	public static void getNetworkEvents() {
		try {
			WebDriver instance = null;
			instance = (WebDriver) driver.get(0);
			List<org.openqa.selenium.logging.LogEntry> entries = instance.manage().logs().get(LogType.PERFORMANCE).getAll();
			System.out.println(entries.size() + " log entries found");
			PrintWriter writer = new PrintWriter("chrome_events.txt", "UTF-8");
			for (org.openqa.selenium.logging.LogEntry entry : entries) {
              writer.println(new java.sql.Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
			writer.close();
		} catch (Exception e) {
			
		}
	}

	private static WebDriver initFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		return driver;
	}

	private static WebDriver initOperaDriver() {
		System.setProperty("webdriver.gecko.driver", driverPath + "opera.exe");
		WebDriver driver = new OperaDriver();

		return driver;	
	}

	public static void setStartTimestamp() {
		startedTime = System.currentTimeMillis(); 
	}

	public static Long getStartTime() {
		return startedTime;
	}

    private static String getSystemVariable(String varName, String varValue, boolean optional) throws Exception {
    	String toReturn = null;
    	
    	if (((System.getProperty(varName) == null) || (System.getProperty(varName).toString().trim() == ""))) {
			System.out.println("The variable '" + varName + "' hasn't been set. This parameter can be assigned like this example: -D" + varName + "=\"" + varValue + "\"");
			if (!optional) {
				throw new Exception("The variable '" + varName + "' is not optional.");
				// System.exit(1);
			}
		} else {
			toReturn = System.getProperty(varName).toString().trim();
		}
		return toReturn;
	}

	public static String getSingleProperty(Properties _prop, String propertyName, String varValue, boolean optional) throws Exception {
		String propertyValue = null;
		
		if (_prop != null) {
			propertyValue = _prop.getProperty(propertyName);			
		} else {
			propertyValue = getSystemVariable(propertyName, varValue, optional);			
		}
		return propertyValue;
	}	
	
	public static Boolean getProperties() {
		Boolean toReturn = false;
		Properties prop = null;
		InputStream input = null;
		String temp;

		try {
			_propertiesFile = getSystemVariable("propertiesFile", "../resources/config.properties", true);

			if (_propertiesFile != null) {
				input = new FileInputStream(_propertiesFile);

				if (input != null) {
					prop = new Properties();
		            prop.load(input);
				}
			} else {
				System.out.println("propertiesFile was not specified, will resume trying to read the variables through command line parameters...");
			}

			_browserType                   = getSingleProperty(prop, "browserType", "chrome", true);
			_LookForJavascriptConsoleError = getSingleProperty(prop, "LookForJavascriptConsoleError", "true", true);
			_JavascriptConsoleErrorStrings = getSingleProperty(prop, "JavascriptConsoleErrorStrings", "SyntaxError,EvalError,ReferenceError,RangeError,TypeError,URIError,Error", true);
			_JavascriptConsoleErrorLevels  = getSingleProperty(prop, "JavascriptConsoleErrorLevels", "SEVERE,WARNING,INFO,CONFIG,FINE,FINER,FINEST", true);
			
			_environment                   = getSingleProperty(prop, "executionEnv", "test", true);
			_language                      = getSingleProperty(prop, "executionLang", "usernamehere", true);
			seleniumURL                    = getSingleProperty(prop, "seleniumURL", "http://127.0.0.1/hub/wd", true);
			seleniumMode                   = getSingleProperty(prop, "seleniumMode", "headless", true);
			seleniumWindowSize             = getSingleProperty(prop, "seleniumWindowSize", "1920x1080", true);
			appiumURL				   	   = getSingleProperty(prop, "appiumURL", "http://127.0.0.1:4723/hub/wd", true);

			androidDeviceName     	       = getSingleProperty(prop, "android_deviceName", "device", true);
			androidDeviceUDID     	       = getSingleProperty(prop, "android_deviceUDID", "emulator-5556", true);
			androidAppPath    	           = getSingleProperty(prop, "android_appPath", "/resources/apps/app-dev-debug.apk", true);

			iosPlatformVersion   	       = getSingleProperty(prop, "ios_platformVersion", "13.3", true);
			iosDeviceName                  = getSingleProperty(prop, "ios_deviceName", "iPhone 11 Pro Max", true);
			iosAppPath                     = getSingleProperty(prop, "ios_appPath", "/resources/Gluci-Chek-delta.app", true);

			_newRecordEmailTemplate	       = getSingleProperty(prop, _environment + "_emailTemplate", "_auto@yopmail.com", true);
			_newRecordEmailTemplatePrefix  = getSingleProperty(prop, _environment + "_emailTemplatePrefix", "hcp_spoc_", true);
			_newRecordPasswordTemplate	   = getSingleProperty(prop, _environment + "_passwordTemplate", "Roche123.-", true);

			patientAppURL                  = getSingleProperty(prop, _environment + "_patientAppURL", "https://domain.com/any", true);
			patientLoginUsername           = getSingleProperty(prop, _environment + "_patientLoginUsername", "usernamehere", true);
			patientLoginPassword           = getSingleProperty(prop, _environment + "_patientLoginPassword", "passwordhere", true);
			patientLoginName               = getSingleProperty(prop, _environment + "_patientLoginName", "\"patient full name\"", true);
			patientFirstName               = getSingleProperty(prop, _environment + "_patientFirstName", "\"patient first name\"", true);
			patientLastName                = getSingleProperty(prop, _environment + "_patientLastName", "\"patient last name\"", true);
			patientHealthCareID            = getSingleProperty(prop, _environment + "_patientHealthCareID", "\"patient health care id\"", true);
			temp = getSingleProperty(prop, _environment + "_patientDateOfBirthDay", "patient date of birth - day", true);
			if (temp != null) {
				patientDateOfBirthDay = Integer.valueOf(temp);
			}
			temp = getSingleProperty(prop, _environment + "_patientDateOfBirthMonth", "patient date of birth - month", true); 
			if (temp != null) {
				patientDateOfBirthMonth          = Integer.valueOf(temp);
			}
			temp = getSingleProperty(prop, _environment + "_patientDateOfBirthYear", "patient date of birth - year", true); 
			if (temp != null) {
				patientDateOfBirthYear          = Integer.valueOf(temp);
			}
			patientDiabetesType            = getSingleProperty(prop, _environment + "_patientDiabetesType", "patient diabetes type", true);

			professionalAppURL             = getSingleProperty(prop, _environment + "_doctorAppURL", "https://domain.com/any", true);
			professionalLoginUsername      = getSingleProperty(prop, _environment + "_doctorLoginUsername", "usernamehere", true);
			professionalLoginPassword      = getSingleProperty(prop, _environment + "_doctorLoginPassword", "passwordhere", true);
			professionalLoginName          = getSingleProperty(prop, _environment + "_doctorLoginName", "doctor full name", true);

			reportDirLocation              = getSingleProperty(prop, "reportDirLocation", "/usr/data/reports", true);

			toReturn = true;
		} 
		catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			System.out.println("Error trying to read the input properties variables needed for this test.\nStack trace:\n" + sw.toString());
		} 

		return toReturn;
	}
	
	@BeforeClass
	public void initializeTestBaseSetup() {
		setStartTimestamp();
		setAbortTestSuite(false);

		if (!getProperties()) {
			// System.out.println("An error ocurred trying to read the variables needed for this test.");		
			System.exit(1);			
		}

		TestService.setJavascriptConsoleParams(_LookForJavascriptConsoleError, _JavascriptConsoleErrorStrings, _JavascriptConsoleErrorLevels);

		try {
			driver = new ArrayList<>();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			System.out.println("Error trying to connect to Selenium Server at '" + seleniumURL + "'\nStack trace:\n" + sw.toString());
		}
	}
	
	@AfterClass
	public void tearDown() {
		for (int i=0; i<driver.size(); i++) {
			driver.remove(i);
		}
	}
}

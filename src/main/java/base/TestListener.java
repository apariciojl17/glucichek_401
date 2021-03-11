package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import java.sql.Timestamp;

public class TestListener implements ITestListener {

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		return reqTestClassname[i];
	}
	
	private String getScreenshotFilename(ITestResult result) {
		String relativePath = "";
		String screenshotsRootPath = "screenshots";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Object driver = TestBase.currentDriver;

		String testClassName = getTestClassName(result.getInstanceName()).trim();
		String timeStamp = Long.toString(timestamp.getTime());  // ).toString().trim();
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + timeStamp + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = ExtentManager.getReportRootPath();
		
		try {
			relativePath = screenshotsRootPath + fileSeperator + testClassName;
			reportsPath += fileSeperator + relativePath; 
			File file = new File(reportsPath); // Set screenshots folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					// System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					System.out.println("Failed to create directory: " + file.getAbsolutePath());
				}
			}
			relativePath += fileSeperator + screenShotName;
			reportsPath += fileSeperator + screenShotName;

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			File targetFile = new File(reportsPath);
			FileHandler.copy(screenshotFile, targetFile);
		} catch (FileNotFoundException e) {
			// System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			// System.out.println("An exception occurred while taking screenshot " + e.getCause());
		}
		return relativePath;
	}

	public void onStart(ITestContext context) {
		// System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		// System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		// System.out.println(("*** Running test method " + result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getInstance().getClass().getPackage().getName() + "<br> - " + result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName());
		if (TestBase.getAbortTestSuite()) {
            throw new SkipException ("Skipping Test: " + result.getName());
        } else {
			TestService.clearJSConsole();
        }
	}

	public void onTestSuccess(ITestResult result) {
		// System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");

		// ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		try {
			// ExtentTestManager.getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshotFilename(result)).build());
			if (TestBase.getScreenshots) {
				ExtentTestManager.getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshotFilename(result)).build());
			}
		} catch (IOException e) {	
			// log.info("An exception occurred while taking screenshot " + e.getCause());
		}

		String jsErrorsOnPage = TestService.getJSErrorsOnThePage();
		if (jsErrorsOnPage != null) {
				ExtentTestManager.getTest().log(Status.WARNING, jsErrorsOnPage);
		} else {  
			ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		}
	}

	public void onTestFailure(ITestResult result) {
		// System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		// System.out.println("flag 4");

		try {
			// ExtentTestManager.getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshotFilename(result)).build());
			if (TestBase.getScreenshots) {
				ExtentTestManager.getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshotFilename(result)).build());
			}
		} catch (IOException e) {	
			// log.info("An exception occurred while taking screenshot " + e.getCause());
		}

		String jsErrorsOnPage = TestService.getJSErrorsOnThePage();
		if (jsErrorsOnPage != null) {
				ExtentTestManager.getTest().log(Status.WARNING, jsErrorsOnPage);
		} else {  
			ExtentTestManager.getTest().log(Status.FAIL, "Test failed");
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		// only one screenshot in the first test skipped
		if (!TestBase.getabortTestSuiteScreenshotTaken()) {
			try {
				// ExtentTestManager.getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshotFilename(result)).build());
				ExtentTestManager.getTest().info("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(this.getScreenshotFilename(result)).build());
				TestBase.setabortTestSuiteScreenshotTaken(true);
			} catch (IOException e) {	
				// log.info("An exception occurred while taking screenshot " + e.getCause());
			}
		}
		
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}

package base;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.sql.Timestamp;

public class ExtentManager {  
    private static ExtentReports extent;
    private static String reportFileName = "Test-Automation-Report.html";
    private static String fileSeperator = System.getProperty("file.separator");

	private static Timestamp timestamp;
	private static String timeStamp;    
    private static String reportRootFilepath;
    private static String reportFilepath;
    private static String reportFileLocation;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = getReportPath();
       
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
 
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
		extent.setSystemInfo("Developer", "aparicioJL@gmail.com");
		extent.setSystemInfo("Test", "TC144_XXX_YYY...");
 
        return extent;
    }

    //Create the report path
    private static String getReportPath () {
        File testRootDirectory = null;

        timestamp = new Timestamp(System.currentTimeMillis());
    	timeStamp = Long.toString(timestamp.getTime());  // ).toString().trim();    
 
		reportRootFilepath = TestBase.getReportDirLocation();
    	if (reportRootFilepath != null) {
    		testRootDirectory = new File(TestBase.getReportDirLocation());
            reportFilepath = reportRootFilepath + fileSeperator + "TestReport-" + timeStamp;
            reportFileLocation =  reportFilepath + fileSeperator + reportFileName;
    	} else {
            reportRootFilepath = System.getProperty("user.dir") + fileSeperator + "TestReports";
            reportFilepath = reportRootFilepath + fileSeperator + "TestReport-" + timeStamp;
            reportFileLocation =  reportFilepath + fileSeperator + reportFileName;

            testRootDirectory = new File(reportRootFilepath);
    	}
    	
        if (!testRootDirectory.exists()) {
        	if (testRootDirectory.mkdir()) {
                return reportFileLocation;
            } else {
                return System.getProperty("user.dir");
            }
        }

    	File testDirectory = new File(reportFilepath);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                return reportFileLocation;
            } else {
                return System.getProperty("user.dir");
            }
        }
		return reportFileLocation;
    }
 
 	public static String getReportRootPath() {
 		return reportFilepath;
 	} 

}

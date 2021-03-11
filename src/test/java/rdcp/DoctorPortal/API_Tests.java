package rdcp.DoctorPortal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import base.ExtentTestManager;
import base.TestBase;
import base.TestService;

import base.APIService;

import io.restassured.response.Response;

public class API_Tests {
    private String sBaseURI = "https://muleqaeu-r1.rochedc.accentureanalytics.com/customers/api/v1";
	private String sAPIAddress = "patients";
    private String token = "Bearer st2.s.AcbHApa8Sw.ywwPSxDuB0vMlxxw_7kNVlcY0ZKeMJ-62WkV-rMIY49UrYZKBv9ZZeCxmWnQFvW2iWUvOM-aR8QS1rU3LJpwgA.KhWq15alF4JdTz0I8BzY-_CKd-alpJNOCpIt6rTaOX3yTLwW3RTstfDoHSiCz0mNGDtZn059gKJ1w0fPXy9fEw.sc3";
    private String apikey = "3_cs0Oq7TtY_9qDVnsFT-upPj_JCFGyx5xmElzIYzWGWSaboR7a8xQjOVrzv1i6-Vb";
    private Integer expectedCount = 15; 
	String toSearchForApikey = "apikey is missing";
	String toSearchForAccessToken = "access token is missing";
	List<List<String>> listOfHeaders = new ArrayList<List<String>>();
	private final int APIKEY = 0;
	private final int AUTHORIZATION = 1;

	@BeforeClass
	public void setUp(ITestContext ctx) {
		TestBase.getScreenshots = false;
		
    	listOfHeaders.add(Arrays.asList("ApiKey", apikey));    	
    	listOfHeaders.add(Arrays.asList("Authorization", token));    	
	}

    @Test
	public void TC01_ApiKeyMissing() {
    	List<List<String>> alistOfHeaders = new ArrayList<List<String>>(listOfHeaders);
    	alistOfHeaders.remove(APIKEY);  // ApiKey will be missing from the headers...
    	io.restassured.internal.http.Status expectedStatus = io.restassured.internal.http.Status.FAILURE;

    	ExtentTestManager.getTest().log(Status.INFO, "Step: Opening API at '" + sBaseURI + "/" + sAPIAddress + "', expecting status '<b>" + expectedStatus + "</b>'");

    	Response response = APIService.doGetRequest(sBaseURI,  sAPIAddress, alistOfHeaders);
    	APIService.logStandardMessages(response, APIService.ALL_JSON);
		
		TestService.checkTrue(expectedStatus.matches(response.getStatusCode()), "Returned status code is <b>" + expectedStatus + "</b>");
		
		TestService.checkContains(response.jsonPath().get("description"), toSearchForApikey, "Lowercased response body");
    }
    
    @Test
	public void TC02_AuthorizationMissing() {
    	int expectedStatus = 400;
    	List<List<String>> alistOfHeaders = new ArrayList<List<String>>(listOfHeaders);
    	alistOfHeaders.remove(AUTHORIZATION);  // Authorization Bearer will be missing from the headers...
    	
    	ExtentTestManager.getTest().log(Status.INFO, "Step: Opening API at '" + sBaseURI + "/" + sAPIAddress + "', expecting status '<b>" + expectedStatus + "</b>'");

    	Response response = APIService.doGetRequest(sBaseURI,  sAPIAddress, alistOfHeaders);
    	APIService.logStandardMessages(response, APIService.ALL_JSON);

		TestService.checkExactMatch(Integer.valueOf(response.getStatusCode()), expectedStatus, "Returned status code");

		TestService.checkContains(response.jsonPath().get("description"), toSearchForAccessToken, "Lowercased response body");
    }

    @Test
	public void TC03_ReturnStatusOK() {
    	int expectedStatus = 200;
    	ExtentTestManager.getTest().log(Status.INFO, "Step: Opening API at '" + sBaseURI + "/" + sAPIAddress + "', expecting status '<b>" + expectedStatus + "</b>'");

    	Response response = APIService.doGetRequest(sBaseURI,  sAPIAddress, listOfHeaders);
    	APIService.logStandardMessages(response, APIService.ONE_PERCENT_JSON);
    	
		TestService.checkExactMatch(Integer.valueOf(response.getStatusCode()), expectedStatus, "Returned status code");
    }

    @Test
	public void TC04_NumberOfPatientsWithoutCountingTheList() {
    	int expectedStatus = 200;
    	ExtentTestManager.getTest().log(Status.INFO, "Step: Opening API at '" + sBaseURI + "/" + sAPIAddress + "', expecting status '<b>" + expectedStatus + "</b>'");

    	Response response = APIService.doGetRequest(sBaseURI,  sAPIAddress, listOfHeaders);
    	APIService.logStandardMessages(response, APIService.ONE_PERCENT_JSON);

    	int getStatusCode = response.getStatusCode();
    	TestService.checkExactMatch(getStatusCode, expectedStatus, "Returned status code");

    	// If getStatusCode != 200 this code will never be reached...


		Integer model_patientNumber = response.jsonPath().get("model.patientNumber"); 
		ExtentTestManager.getTest().log(Status.INFO, "Step: Showing 'model.patientNumber' property value from returned JSON: '" + model_patientNumber + "'");

		TestService.checkExactMatch(Integer.valueOf(model_patientNumber), expectedCount, "Patient Number");
    }

    @Test
	public void TC05_NumberOfPatientsCountingTheList() {
    	int expectedStatus = 200;
    	ExtentTestManager.getTest().log(Status.INFO, "Step: Opening API at '" + sBaseURI + "/" + sAPIAddress + "', expecting status '<b>" + expectedStatus + "</b>'");

    	Response response = APIService.doGetRequest(sBaseURI,  sAPIAddress, listOfHeaders);
    	APIService.logStandardMessages(response, APIService.ONE_PERCENT_JSON);

		int getStatusCode = response.getStatusCode();
    	TestService.checkExactMatch(getStatusCode, expectedStatus, "Returned status code");

    	// If getStatusCode != 200 this code will never be reached...
		List<String> jsonResponse = response.jsonPath().getList("model.patientList");
		int model_patientListSize = jsonResponse.size(); 
		ExtentTestManager.getTest().log(Status.INFO, "Step: Showing list count from returned JSON patients list: '" + model_patientListSize + "'");
		TestService.checkExactMatch(model_patientListSize, expectedCount, "Patient list count");
    }

}

package base;

import java.util.List;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIService {
	public final static int ALL_JSON = 0;
	public final static int ONE_PERCENT_JSON = 1;
	
	public static Response doGetRequest(String sBaseURI, String sAPIAddress, List<List<String>> listOfHeaders) {
    	RequestSpecification requestSpecifications = RestAssured.given();

    	if (listOfHeaders != null) {
    		listOfHeaders.forEach(name -> requestSpecifications.header(name.get(0), name.get(1)));
    	}

		Response response = requestSpecifications.
	            accept(ContentType.JSON)
				.when()
	            .get(sBaseURI + "/" + sAPIAddress)
	            .then()
	            .contentType(ContentType.JSON)
	            .extract()
	            .response();
		
		return response;
	}
	
	public static void logStandardMessages(Response response, int howManyCharsFromJSON) {
		String toShow = "";
		String responseBody = response.getBody().asString();
		int responseLength = responseBody.length();
		
		if ((howManyCharsFromJSON == ALL_JSON) || (responseLength < 1000)) {
			toShow = responseBody.toString();
		} else {
			if (howManyCharsFromJSON == ONE_PERCENT_JSON) {
				int howManyChars = (int) (responseBody.length() * 0.01);
				toShow = responseBody.substring(1, howManyChars) + " ... " + responseBody.substring(responseBody.length() - howManyChars);
			}
		}

		ExtentTestManager.getTest().log(Status.INFO, "Step: Showing response status text: '" + response.getStatusLine() + "'");
		ExtentTestManager.getTest().log(Status.INFO, "Step: Showing response body ('" + responseBody.length() + "' chars): '" + toShow + "'");		
	}
}

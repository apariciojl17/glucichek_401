package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class AHEM_EmailProvider {
	private static String token = "";
	private static URL URI_token;
	private static URL URI_mailbox;
	private static String username = "";
	private static HttpURLConnection conn;
	private static JSONArray arrJSONInbox; 
	private static Long seedTimestamp;
	
	public AHEM_EmailProvider() {

	}
	
	public static void init(String _username, String _server, Long afterThisTimestamp) {
		try {
			username = _username;
			URI_token = new URL("http://" + _server + "/api/auth/token");
			URI_mailbox = new URL("http://" + _server + "/api/mailbox/" + username + "/email");
			token = "";
			seedTimestamp = afterThisTimestamp;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	};
	
	private static Boolean getToken() {
		Boolean	toReturn = false;
		
		try {
			conn = null;
	
			conn = (HttpURLConnection) URI_token.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setDoOutput(true);
			
			String input = "{}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			String wholeOutput = "";
			while ((output = br.readLine()) != null) {
				wholeOutput += output;   
			}
			conn.disconnect();
			
			JSONObject obj = new JSONObject(wholeOutput);
			Boolean success = (Boolean) obj.get("success");
			
			if (success) {
				token = obj.getString("token");
				
				if (token.trim() != "") {
					toReturn = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return toReturn;
	}
	
	private static void getInbox() {
		// JSONObject temp = null;
		// Long emailTimestamp;

		try {
			if ((token != "") || (getToken())) {
				conn = null;
	
				conn = (HttpURLConnection) URI_mailbox.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Bearer " + token);
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Content-Type", "text/plain");
				conn.setRequestProperty("Accept", "application/json");
				// TODO: Wait max 30 seconds for the mail to arrive... 
				
				BufferedReader br = null;
				String output = "";
				if (conn.getResponseCode() ==  HttpURLConnection.HTTP_NOT_FOUND) {
					arrJSONInbox = null;
				} else {
					if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}

					br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					String wholeOutput = "";
					while ((output = br.readLine()) != null) {
						wholeOutput += output;   
					}

					arrJSONInbox = new JSONArray(wholeOutput.trim());					
					// remove the items with  emailTimestamp < seedTimestamp
					/* int max = arrJSONInbox.length();
					int pivot = 0;
					while (pivot < max) {
						temp = (JSONObject) arrJSONInbox.get(pivot);
						emailTimestamp = (Long) temp.get("timestamp");
						
						if (emailTimestamp < seedTimestamp) {
							arrJSONInbox.remove(pivot);
							max--;
						} else {
							pivot++;
						}
					}*/
					// System.out.println("flag 9");
					// System.out.println(arrJSONInbox.length());
					// System.out.println(arrJSONInbox != null);
					// System.out.println("Most recent email: " + response_json.get(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
  		    conn.disconnect();	
		}
	}
	
	public static Boolean isNotEmptyInbox(Boolean _getInbox) {
		Boolean toReturn = false;

		if (_getInbox) {
			getInbox();
		}
		if (arrJSONInbox != null) {
			toReturn = arrJSONInbox.length() > 0;
		}
		return toReturn;
	}

	public static String getEmailID() {
		String toReturn = null;
		JSONObject temp = null;

		if (arrJSONInbox.length() > 0) {
			// get the first email whose timestamp is greater than the TestBase.getStartTime()
			int max = arrJSONInbox.length();
			Long startedTime = TestBase.getStartTime();
			Long emailTimestamp = null;
			boolean found = false;
			while ((!found) && (max>0)) {
				temp = (JSONObject) arrJSONInbox.get(max-1);
				emailTimestamp = (Long) temp.get("timestamp");
				found = (emailTimestamp >= startedTime);
				if (!found) { 
					max--;
				}
			}
			if (found) {
				toReturn = (String) temp.get("emailId");
			}
			// Example:  {"emailId":"5d8a14696df0f12360af7ce8","sender":{"address":"global-ids.portals@rochedc.com","name":"Smart Pix Online"},"subject":"Email Code Verification","timestamp":1569330281545,"isRead":false}
			// TODO: get the "truly" last email 
		} 
		
		return toReturn;
	}

	public static String getVerificationCode() {
		String toReturn = null;
		String bodyText = null;
		String emailId = null;

		try {
			emailId = getEmailID();
			
			if (emailId != null) {
				URL URI_mail = new URL(URI_mailbox.toURI() + "/" + emailId);

				conn = null;

				conn = (HttpURLConnection) URI_mail.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Bearer " + token);
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Content-Type", "text/plain");
				conn.setRequestProperty("Accept", "application/json");
				
				BufferedReader br = null;
				if (conn.getResponseCode() ==  HttpURLConnection.HTTP_OK) {
					br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
					bodyText = br.readLine();

					int init = bodyText.indexOf("<p class=\\\"verificationCode");
					int end = bodyText.indexOf("</p>", init);
					String line = bodyText.substring(init, end + 4 );
					
					Pattern MY_PATTERN = Pattern.compile("\\>(.*?)\\<");
					Matcher m = MY_PATTERN.matcher(line);
					m.find();
					toReturn = m.group(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toReturn;		
	}

	public static String getValidationURL(String wordToSearchFor) {
		String toReturn = null;
		String bodyText = null;
		String emailId = null;
   
		try {
			emailId = getEmailID();
			
			if (emailId != null) {
				URL URI_mail = new URL(URI_mailbox.toURI() + "/" + emailId);

				conn = null;

				conn = (HttpURLConnection) URI_mail.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Bearer " + token);
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Content-Type", "text/plain");
				conn.setRequestProperty("Accept", "application/json");
				
				BufferedReader br = null;
				if (conn.getResponseCode() ==  HttpURLConnection.HTTP_OK) {
					// this server responses with error 404 when the inbox is empty, just validate the response and look for "MAILBOX IS EMPTY!"
					br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					bodyText = br.readLine();

					String toSearch = "";
					switch (wordToSearchFor) {
						case "click here":
							toSearch = "account. Simply\\n                                                            <a href";
							break;
						case "here":
							toSearch = "set your password <a href=";
							break;		
						case "professional process":
							toSearch = "registration process! Please";
							break;		
						case "sign in":
							toSearch = "Platform account  <a href=";
							break;		
					}

					if (toSearch != "") {
						int init = bodyText.indexOf(toSearch);
						
						if (init != -1) {
							int end = bodyText.indexOf("</a>", init);
							String line = bodyText.substring(init, end + 4);
		
							init = line.indexOf("href=");
							end = line.indexOf("target", init);
							toReturn = line.substring(init + 7, end -3);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toReturn;		
	}

	public static String getSubject() {
		String toReturn = null;
		String emailId = null;
		JSONObject temp;

		try {
			emailId = getEmailID();
			
			if (emailId != null) {
				temp = (JSONObject) arrJSONInbox.get(0);
				toReturn = (String) temp.get("subject");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toReturn;		
	}	

	public static Boolean EmptyInbox() {
		return EmptyInbox(null);
	}
	
	public static Boolean EmptyInbox(Long since) {
		String emailId = "";
		Long timestamp = null;
		Boolean	toReturn = true;
		JSONObject temp = null;
		HttpURLConnection conn3;

		if (isNotEmptyInbox(true)) {
			try {
				int max = arrJSONInbox.length();
				
				int i=0;
				while ((i<max) && toReturn) {
					temp = (JSONObject) arrJSONInbox.get(i);
					timestamp = (Long) temp.get("timestamp");

					if ((since == null) || ((timestamp != null) && (timestamp <= since))) {
						emailId = (String) temp.get("emailId");
						URL URI_Delete = new URL(URI_mailbox.toURI() + "/" + emailId);

						conn3 = (HttpURLConnection) URI_Delete.openConnection();
						conn3.setRequestMethod("DELETE");
						conn3.setRequestProperty("Authorization", "Bearer " + token);
						conn3.setRequestProperty("Content-Type", "application/json");
						conn3.setRequestProperty("Content-Type", "text/plain");
					
						if (conn3.getResponseCode() != HttpURLConnection.HTTP_OK) {
							throw new RuntimeException("Failed : HTTP error code : " + conn3.getResponseCode());
						}
					
						BufferedReader br = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
						
						String output;
						String wholeOutput = "";
						while ((output = br.readLine()) != null) {
							wholeOutput += output;   
						}
						conn3.disconnect();
					
						JSONObject obj = new JSONObject(wholeOutput);
						Boolean success = (Boolean) obj.get("success");
						
						if (!success) {
							toReturn = false;
						}
					}
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 			
		}

		return toReturn;
	}
}

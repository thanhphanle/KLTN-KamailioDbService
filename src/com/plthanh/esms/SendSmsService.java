package com.plthanh.esms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.plthanh.util.Config;

public class SendSmsService {
	final String APIKey = Config.ESMS_API_KEY;
	final String SecretKey = Config.ESMS_SECRET_KEY;
	private String message;
	private String phone;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String execute() {
		return "SUCCESS";
	}

	public String send(String message, String phone) throws IOException {
		this.message = message;
		this.phone = phone;
		
		String url = "http://api.esms.vn/MainService.svc/xml/SendMultipleMessage_V2/";

		URL obj;
		try {
			obj = new URL(url);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// you need to encode ONLY the values of the parameters
			String customers = "";

			String[] lstPhone = phone.split(",");

			for (int i = 0; i < lstPhone.length; i++) {
				customers = customers + "<CUSTOMER>" + "<PHONE>"
						+ lstPhone[i].replace(" ", "") + "</PHONE>"
						+ "</CUSTOMER>";
			}
			
			// Send ASCII message
			String SampleXml = "<RQST>" + "<APIKEY>" + APIKey + "</APIKEY>"
					+ "<SECRETKEY>" + SecretKey + "</SECRETKEY>"
					+ "<ISFLASH>0</ISFLASH>" + "<UNICODE>0</UNICODE>"
					+ "<SMSTYPE>3</SMSTYPE>" + "<CONTENT>" + message
					+ "</CONTENT>" + "<CONTACTS>" + customers + "</CONTACTS>"
					+ "</RQST>";

			String postData = SampleXml.trim();

			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setFixedLengthStreamingMode(postData.getBytes().length);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// send the POST out
			PrintWriter out = new PrintWriter(con.getOutputStream());
			out.print(postData);
			out.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			if (responseCode == 200)
			{
				// Check CodeResult from response
			}
			// Read Response
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "SUCCESS";
	}
}

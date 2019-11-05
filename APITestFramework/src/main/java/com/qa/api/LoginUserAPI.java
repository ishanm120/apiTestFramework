package com.qa.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import com.qa.Base.BaseAPI;

public class LoginUserAPI extends BaseAPI{
	CloseableHttpResponse closeableHttpResponse;
	
	public LoginUserAPI() {
		super();
		setUrl(BASE_URL+"/api/login" );
	}
	
	
	public void loginViaAPI(String email, String password) throws ClientProtocolException, IOException {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("email", email);
		jsonObject.put("password", password);
		System.out.println("Entity sent for Login : "+ jsonObject.toString());
		StringEntity stringEntity= new StringEntity(jsonObject.toString(), "UTF-8");
		closeableHttpResponse=  POST(getUrl(), stringEntity);
		setStatusCode(closeableHttpResponse.getStatusLine().getStatusCode());
		System.out.println("Response code of Login API is "+ getStatusCode());
	}

}

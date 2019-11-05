package com.qa.api;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import com.qa.Base.BaseAPI;

public class CreateUserAPI extends BaseAPI {
	CloseableHttpResponse closeableResponse;

	public CreateUserAPI() {
		super();
		setUrl(BASE_URL + "/api/users");
	}

	public void creatNewUserViaApi(String name, String job) throws ClientProtocolException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("job", job);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
		System.out.println("Data of new user is : " + jsonObject.toString());
		System.out.println("Enitiy sent for Pot is : " + stringEntity);
		closeableResponse = POST(getUrl(), stringEntity);
		System.out.println("Response of POST Request is : " + closeableResponse.toString());
		setStatusCode(closeableResponse.getStatusLine().getStatusCode());
		System.out.println("Status code of POST Request is : " + getStatusCode());
	}

	public void creatNewUserViaApi(org.json.simple.JSONObject jsonObject) throws ClientProtocolException, IOException {
		jsonObject.put("name", "Tom");
		jsonObject.put("job", "QA Manager");
		HashMap<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", "application/json");
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
		System.out.println("Data of new user is : " + jsonObject.toString());
		System.out.println("Enitiy sent for Pot is : " + stringEntity);
		closeableResponse = POST(getUrl(), stringEntity, headerMap);
		System.out.println("Response of POST Request is : " + closeableResponse.toString());
		setStatusCode(closeableResponse.getStatusLine().getStatusCode());
		System.out.println("Status code of POST Request is : " + getStatusCode());
	}

}

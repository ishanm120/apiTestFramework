package com.qa.test;

import java.io.FileReader;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.qa.api.CreateUserAPI;
import com.qa.objects.User;

public class CreateUserTest {
	User user = new User();
	CreateUserAPI createUserApi = new CreateUserAPI();

	@Test
	public void createUserViaApi() throws ClientProtocolException, IOException, ParseException {
		Object obj = new JSONParser()
				.parse(new FileReader(System.getProperty("user.dir") + "\\JSONFiles\\JSONExample.json"));

		JSONObject jObject = (JSONObject) obj;
		user.setUserName((String) jObject.get("Name"));
		System.out.println(user.getUserName());
		user.setJob("QA Lead");
		createUserApi.creatNewUserViaApi(user.getUserName(), user.getJob());
	}

	@Test
	public void createUserViaAPI() throws ClientProtocolException, IOException {
		createUserApi.creatNewUserViaApi(new JSONObject());
	}

}

package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.qa.api.LoginUserAPI;
import com.qa.objects.User;

public class LoginTest {
	User user= new User();

	@Test
	public void loginUser() throws ClientProtocolException, IOException {
		LoginUserAPI loginUserApi = new LoginUserAPI();
		user.setEmail("tom@thomsanpress.com");
		user.setPassword("123456");
		loginUserApi.loginViaAPI(user.getEmail(), user.getPassword());
	}

}

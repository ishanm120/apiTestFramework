package com.qa.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.Base.BaseAPI;
import com.qa.config.CommonConstants;

public class GETAPITest extends BaseAPI {
	CloseableHttpResponse closeableResponse;

	public GETAPITest() {
		super();
		setUrl(BASE_URL + "/api/users");
	}

	@BeforeTest
	public void setUp() throws ClientProtocolException, IOException {
		System.out.println("API URL is :" + getUrl());
	}

	@Test
	public void verifyStatusCode() throws ClientProtocolException, IOException {
		closeableResponse = GET(getUrl());
		Assert.assertEquals(getStatusCode(closeableResponse), CommonConstants.STATUS_CODE_200);
	}

/*	@Test
	public void verifyResponseParameterValue() throws ClientProtocolException, IOException {
		closeableResponse = GET(getUrl());
		getResponseParemeterValue(closeableResponse, "pages");

	}*/

}

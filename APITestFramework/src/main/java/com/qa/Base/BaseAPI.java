package com.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class BaseAPI {

	public Properties prop;
	protected static String BASE_URL;
	private String url;
	private int statusCode;

	public BaseAPI() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BASE_URL = prop.getProperty("BaseUrl");
	}

	// GET call without headers
	public CloseableHttpResponse GET(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closeableResponse = httpclient.execute(httpget);
		String stringResponse = EntityUtils.toString(closeableResponse.getEntity(), "UTF-8");
		System.out.println("String Response of Request is  : " + stringResponse);
		return closeableResponse;
	}

	// GET Call with headers
	public CloseableHttpResponse GET(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableResponse = httpclient.execute(httpget);
		return closeableResponse;
	}

	// POST call without headers
	public CloseableHttpResponse POST(String url, StringEntity stringEntity)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(stringEntity);
		CloseableHttpResponse closeableResponse = httpclient.execute(httpPost);
		return closeableResponse;
	}

	// POST Call with Headers
	public CloseableHttpResponse POST(String url, StringEntity stringEntity, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		for (Map.Entry<String, String> map : headerMap.entrySet()) {
			httpPost.addHeader(map.getKey(), map.getValue());
		}
		httpPost.setEntity(stringEntity);
		CloseableHttpResponse closeableResponse = httpclient.execute(httpPost);
		return closeableResponse;
	}

	
	/**
	 * Get status code 
	 * @param closeableResponse
	 * @return
	 */
	public int getStatusCode(CloseableHttpResponse closeableResponse) {
		return closeableResponse.getStatusLine().getStatusCode();
	}

	/**
	 * get Response parameter value
	 * @param closeableResponse
	 * @param paramterName
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String getResponseParemeterValue(CloseableHttpResponse closeableResponse, String paramterName)
			throws ParseException, IOException {
		String stringResponse = EntityUtils.toString(closeableResponse.getEntity(), "UTF-8");
		System.out.println("Response Entity is : " + stringResponse);
		JSONObject jsonResponse = new JSONObject(stringResponse);
		String perameterValue = jsonResponse.getString(paramterName);
		System.out.println("Value of Paramter '" + paramterName + "' is '" + perameterValue + "'");
		return perameterValue;
	}

	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}

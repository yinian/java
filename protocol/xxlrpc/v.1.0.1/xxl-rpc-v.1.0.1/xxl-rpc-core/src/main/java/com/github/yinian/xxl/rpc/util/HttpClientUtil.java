package com.github.yinian.xxl.rpc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * httpclient util
 * @author yinian 2015-10-31 19:50:41
 */
public class HttpClientUtil {

	/**
	 * post request
	 */
	public static byte[] postRequest(String reqURL, byte[] date) {

		byte[] responseBytes = null;

		HttpPost httpPost = new HttpPost(reqURL);
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			if (date != null){
				httpPost.setEntity(new ByteArrayEntity(date, ContentType.DEFAULT_BINARY));

			}
			// do post
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity){
				responseBytes = EntityUtils.toByteArray(entity);
				EntityUtils.consume(entity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpPost.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseBytes;


	}
	
	/**
	 * read bytes from http request
	 * @param is
	 * @param contentLen
	 * @return
	 * @throws IOException 
	 */
	public static final byte[] readBytes(HttpServletRequest request) throws IOException {

		request.setCharacterEncoding("UTF-8");
		int contentLen = request.getContentLength();
		InputStream is = request.getInputStream();
		if (contentLen > 0){
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];

			try {
				while (readLen != contentLen){
					readLengthThisTime = is.read(message,readLen, contentLen - readLen);
					if (readLengthThisTime == -1){
						break;
					}
					readLen += readLengthThisTime;
				}

				return message;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new byte[] {};
	}
	
}

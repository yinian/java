package com.yinian.rcp.remoting.net.impl.jetty.server;

import com.yinian.rcp.remoting.net.params.XxlRpcRequest;
import com.yinian.rcp.remoting.net.params.XxlRpcResponse;
import com.yinian.rcp.remoting.provider.XxlRpcProviderFactory;
import com.yinian.rcp.util.XxlRpcException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * jetty handler
 * @author yinian 2015-11-19 22:32:36
 */
public class JettyServerHandler extends AbstractHandler {
	private static Logger logger = LoggerFactory.getLogger(JettyServerHandler.class);
	private XxlRpcProviderFactory xxlRpcProviderFactory;
	public JettyServerHandler(XxlRpcProviderFactory xxlRpcProviderFactory) {
		this.xxlRpcProviderFactory = xxlRpcProviderFactory;
	}
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// request
		XxlRpcRequest xxlRpcRequest = parseRequest(request);

		// invoke + response
		XxlRpcResponse xxlRpcResponse =
				xxlRpcProviderFactory.invokeService(xxlRpcRequest);

		writeResponse(baseRequest, response, xxlRpcResponse);
	}


	/**
	 * write response
	 */
	private void writeResponse(Request baseRequest, HttpServletResponse response, XxlRpcResponse xxlRpcResponse) throws IOException {

		// serialize response
		byte[] responseBytes = xxlRpcProviderFactory.getSerializer().serialize(xxlRpcResponse);

		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		OutputStream out = response.getOutputStream();
		out.write(responseBytes);
		out.flush();
	}


	/**
	 * parse request
	 */
	private XxlRpcRequest parseRequest(HttpServletRequest request) throws IOException {
		// deserialize request
		byte[] requestBytes = readBytes(request);
		if (requestBytes == null || requestBytes.length == 0){
			throw new XxlRpcException("XxlRpcRequest byte[] is null");
		}

		XxlRpcRequest rpcRequest =
				(XxlRpcRequest) xxlRpcProviderFactory.getSerializer().deserialize(requestBytes,XxlRpcRequest.class);
		return rpcRequest;
	}

	/**
	 * read bytes from http request
	 *
	 * @param request
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
					readLengthThisTime = is.read(message,readLen,contentLen - readLen);
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
		return new byte[]{};
	}
}

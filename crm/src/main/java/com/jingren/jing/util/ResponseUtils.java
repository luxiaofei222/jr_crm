package com.jingren.jing.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONException;



/**
 * 异步返回各种格式 json xml text
 * 
 * @author lx
 *
 */
public class ResponseUtils {

	// 发送内容
	public static void render(HttpServletResponse response, String contentType,
			String text) {
		response.setContentType(contentType);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.flush();
		pw.close();
	}

	// 发送的是JSON
	public static void renderJson(HttpServletResponse response, String text) {
		render(response, "application/json;charset=UTF-8", text);
	}

	// 单个发送json
	public static void renderJson(HttpServletResponse response, String name,
			String text) throws JSONException {
		JSONObject jo = new JSONObject();
		jo.put(name, text);
		render(response, "application/json;charset=UTF-8", jo.toString());
	}

	// 单个发送json
		public static void renderuserJson(HttpServletResponse response, String name,
				String text, String username,
				String value) throws JSONException {
			JSONObject jo = new JSONObject();
			jo.put(name, text);
			jo.put(username, value);
			render(response, "application/json;charset=UTF-8", jo.toString());
		}
	// 发送xml
	public static void renderXml(HttpServletResponse response, String text) {
		render(response, "text/xml;charset=UTF-8", text);
	}

	// 发送text
	public static void renderText(HttpServletResponse response, String text) {
		render(response, "text/plain;charset=UTF-8", text);
	}

}

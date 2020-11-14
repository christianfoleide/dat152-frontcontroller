package no.hvl.dat152.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import no.hvl.dat152.model.Item;

public class JsonUtil {

	public static void sendJson(String json, HttpServletResponse resp) throws IOException {
		
		PrintWriter writer = resp.getWriter();
		resp.addHeader("Content-Type", "application/json");
		writer.println(json);
		writer.flush();
		
	}
	
	public static Item Serialize(final HttpServletRequest req) {
		
		Gson gson = new Gson();
		
		StringBuffer jBuffer = new StringBuffer();
		String line = null;
		try {
			
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jBuffer.append(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return gson.fromJson(jBuffer.toString(), Item.class);
	}
	
}

package no.hvl.dat152.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import no.hvl.dat152.action.Action;
import no.hvl.dat152.action.GetItemAction;
import no.hvl.dat152.model.ErrorMessage;

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actions;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		actions = new HashMap<String, Action>();
		actions.put("/allItems", new GetItemAction());
		gson = new Gson();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Action act = getCommand(req);
		int result = act.doAction(req, resp);
		if (result != Action.SUCCESS) {
			
			ErrorMessage em = new ErrorMessage()
										.withDescription("Bad Request")
										.withStatusCode(400);
			
			String errorJson = gson.toJson(em);
			resp.setStatus(400);
			sendJsonError(errorJson, resp);
			
		}
	}
	
	private Action getCommand(final HttpServletRequest req) {
		String uri = req.getPathInfo();
		return actions.get(uri);
	}
	
	private void sendJsonError(String json, HttpServletResponse resp) throws IOException {	
		PrintWriter writer = resp.getWriter();
		resp.addHeader("Content-Type", "application/json");
		writer.println(json);
		writer.flush();
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

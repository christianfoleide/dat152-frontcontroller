package no.hvl.dat152.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import no.hvl.dat152.action.Action;
import no.hvl.dat152.action.CreateItemAction;
import no.hvl.dat152.action.GetAllItemsAction;
import no.hvl.dat152.action.GetSingleItemAction;
import no.hvl.dat152.action.UpdateItemAction;
import no.hvl.dat152.model.ErrorMessage;
import no.hvl.dat152.repository.JsonUtil;

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actions;
	private Gson gson;
	
	@Override
	public void init() throws ServletException {
		actions = new HashMap<String, Action>();
		actions.put("/allitems", new GetAllItemsAction());
		actions.put("/singleitem", new GetSingleItemAction());
		actions.put("/createitem", new CreateItemAction());
		actions.put("/updateitem", new UpdateItemAction());
		
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
			JsonUtil.sendJson(errorJson, resp);
			
		}
	}
	
	private Action getCommand(final HttpServletRequest req) {
		String uri = req.getRequestURI();
		String[] prefixes = uri.split("/");
		return actions.get("/" + prefixes[2]);
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

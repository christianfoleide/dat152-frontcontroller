package no.hvl.dat152.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.repository.ItemRepository;

public class GetItemAction implements Action {
	
	@Override
	public int doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		ItemRepository repo = ItemRepository.getInstance();
		String json = repo.allItemsAsJson();
		if (json.equals("")) {
			return -1;
		}
		sendJson(json, resp);
		return Action.SUCCESS;
		
	}
	
	private void sendJson(String json, HttpServletResponse resp) throws IOException {
		
		PrintWriter writer = resp.getWriter();
		resp.addHeader("Content-Type", "application/json");
		writer.println(json);
		writer.flush();
		
	}
}

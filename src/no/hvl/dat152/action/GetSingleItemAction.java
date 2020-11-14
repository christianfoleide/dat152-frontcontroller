package no.hvl.dat152.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.repository.ItemRepository;
import no.hvl.dat152.repository.JsonUtil;

public class GetSingleItemAction implements Action {

	@Override
	public int doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Integer id = Integer.parseInt((String) req.getAttribute("updateid"));
		
		ItemRepository repo = ItemRepository.getInstance();
		
		String json = repo.singleItemAsJson(id);
		if (json == null) {
			return -1;
		}
		
		JsonUtil.sendJson(json, resp);
		return Action.SUCCESS;
	}
}

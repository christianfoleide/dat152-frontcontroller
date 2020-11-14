package no.hvl.dat152.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.repository.ItemRepository;
import no.hvl.dat152.repository.JsonUtil;

public class DeleteItemAction implements Action {

	@Override
	public int doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		ItemRepository repo = ItemRepository.getInstance();
		
		Integer id = Integer.parseInt((String) req.getAttribute("updateid"));
		String json = repo.deleteItem(id);
		if (json == null) {
			return -1;
		}
		JsonUtil.sendJson(json, resp);
		return Action.SUCCESS;
	}
}

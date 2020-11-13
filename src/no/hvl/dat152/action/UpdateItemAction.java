package no.hvl.dat152.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.model.Item;
import no.hvl.dat152.repository.ItemRepository;
import no.hvl.dat152.repository.JsonUtil;

public class UpdateItemAction implements Action {
	
	@Override
	public int doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Item newItem = JsonUtil.Serialize(req);
		if (newItem == null) {
			return -1;
		}
		
		Integer id = Integer.parseInt((String) req.getAttribute("updateid"));
		
		ItemRepository repo = ItemRepository.getInstance();
		String updatedItemJson = repo.updateItem(newItem, id);
		if (updatedItemJson == null) {
			return -1;
		}
		JsonUtil.sendJson(updatedItemJson, resp);
		return Action.SUCCESS;
	}

}

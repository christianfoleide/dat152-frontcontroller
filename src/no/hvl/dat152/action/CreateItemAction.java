package no.hvl.dat152.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat152.model.Item;
import no.hvl.dat152.repository.ItemRepository;
import no.hvl.dat152.repository.JsonUtil;

public class CreateItemAction implements Action {
	
	@Override
	public int doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
		Item item = JsonUtil.Serialize(req);
		if (item == null) {
			return -1;
		}
		
		ItemRepository repo = ItemRepository.getInstance();
		repo.createItem(item);
		
		String json = repo.allItemsAsJson();
		JsonUtil.sendJson(json, resp);
		
		return Action.SUCCESS;
		
	}

}

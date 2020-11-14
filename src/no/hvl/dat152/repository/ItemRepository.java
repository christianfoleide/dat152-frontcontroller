package no.hvl.dat152.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

import no.hvl.dat152.model.Item;

public class ItemRepository {

	private AtomicInteger nextId = new AtomicInteger(4);
	private ConcurrentHashMap<Integer, Item> items;
	private Gson gson;
	
	private static ItemRepository instance;
	
	private ItemRepository() {
		
		items = new ConcurrentHashMap<Integer, Item>();
		items.put(1, new Item(1, "Te", 20.0));
		items.put(2, new Item(2, "Melk", 25.0));
		items.put(3, new Item(3, "Sjokolade", 40.0));
		
		gson = new Gson();
		
	}
	
	public static synchronized ItemRepository getInstance() {
		if (instance == null) {
			instance = new ItemRepository();
		}
		return instance;
	}
	
	public String deleteItem(int id) {
		Item item = items.remove(id); //no-op if doesn't exist
		if (item == null) {
			return null;
		}
		
		return gson.toJson(items.values());
	}
	
	private Item getItem(int id) {
		return items.get(id);
	}
	
	private Collection<Item> getAllItems() {
		return items.values();
	}
	
	public void createItem(Item item) {
		int id = nextId.getAndIncrement();
		item.setId(id);
		items.put(id, item);
	}
	
	public String updateItem(Item newItem, int id) {
		
		Item item = items.get(id);
		if (item == null) {
			return null;
		}
		item.setName(newItem.getName());
		item.setPrice(newItem.getPrice());
		
		return gson.toJson(item);
	}
	
	public String singleItemAsJson(int id) {
		Item item = getItem(id);
		if (item == null) {
			return null;
		}
		return gson.toJson(item);
	}
	
	public String allItemsAsJson() {
		Collection<Item> allItems = getAllItems();
		return gson.toJson(allItems);
	}

}

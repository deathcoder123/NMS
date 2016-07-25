package BackEnd;

import java.util.*;

public class Requirement {
	private Map<Item,Integer> itemFreqMap;
	private int amount;
	
	public Requirement(Map<Item,Integer> itemFreqMap, int amount) {
		this.itemFreqMap = itemFreqMap;
		this.amount = amount;
	}

	public Map<Item, Integer> getItemFreqMap() {
		return itemFreqMap;
	}

	public int getAmount() {
		return amount;
	}
}

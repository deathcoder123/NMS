package BackEnd;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
	Map<Item,Integer> freqMap;
	
	Inventory(){
		freqMap = new HashMap<Item,Integer>();
	}

	public void useItem(Object key, Object value) {
		int old = freqMap.get(key);
		freqMap.put((Item) key, new Integer(old-(Integer)value));
	}
	
}

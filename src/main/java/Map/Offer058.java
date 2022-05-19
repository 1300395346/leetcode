package Map;

import java.util.Map;
import java.util.TreeMap;

public class Offer058 {
    TreeMap<Integer, Integer> map;

    public Offer058() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entryFloor = map.floorEntry(start);
        Map.Entry<Integer, Integer> entryCeiling = map.ceilingEntry(start);
        if (entryFloor != null && entryFloor.getValue() > start) {
            return false;
        }
        if (entryCeiling != null && entryCeiling.getKey() < end) {
            return false;
        }
        map.put(start, end);
        return true;
    }
}

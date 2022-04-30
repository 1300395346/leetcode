package struct;

import java.util.*;

public class RandomizedSet {
    public final Map<Integer, Integer> map;
    public final List<Integer> list;

    public final Random random;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        int valIndex = map.getOrDefault(val, -1);
        if (valIndex == -1) {
            return false;
        }
        int listEndIndex = list.size() - 1;
        if (valIndex != listEndIndex) {
            int temp = list.get(listEndIndex);
            list.set(valIndex, temp);
            map.put(temp, valIndex);
        }
        map.remove(val);
        list.remove(listEndIndex);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

}

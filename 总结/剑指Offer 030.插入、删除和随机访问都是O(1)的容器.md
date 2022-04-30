# 剑指Offer 030.插入、删除和随机访问都是O(1)的容器

设计一个支持在*平均*时间复杂度O(1)下，执行以下操作的数据结构：

- `insert(val)`：当元素`val`不存在时返回`true`，并向集合中插入该项，否则返回`false`。

- `remove(val)`：当元素`val`存在时返回`true`，并从集合中移除该项，否则返回`false`。

- `getRandom`：随机返回现有集合中的一项。每个元素应该有**相同的概率**被返回。

2022/4/30 第一次刷

初见思路：用哈希表可以实现插入和删除操作时间复杂度为O(1)，但是不能实现随机去一个数字的时间复杂度，因此需要考虑同时利用list集合来存储数据，然后使用random.nextInt(list.size())的方式获取随机数。但这样的话删除操作时除非删除的数据正好是list集合的最后一个数字，否则不能保证时间复杂度为O(1)，因此我们需要在删除时将要删除的数据和最后一个数据调换。因此需要利用map集合存储数据，其中key为val，value为该val在list中的位置。

代码如下：

```java
class RandomizedSet {
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
```

结果：通过！！ 时间复杂度O(1)，空间复杂度O(n)



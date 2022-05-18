# 剑指Offer 057.值和下标之差都在给定的范围内

给你一个整数数组`nums`和两个整数`k`和`t`。请你判断是否存在**两个不同的下标**`i`和`j`，使得`abs(nums[i] - nums[j]) <= t`，同时又满足`abs(i - j) <= k`。

如果存在则返回`true`，不存在返回`false`。

2022/5/18 第一次见

初见思路：暴力枚举

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            for (int j = i+1; j - i <= k && j < len; ++j) {
                if (Math.abs((long)nums[i] - (long)nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

结果： 超出时间限制 ！！ 时间复杂度O(n*k)，空间复杂度O(1)

（查看了官方题解）滑动窗口+有序集合

对于序列中每一个元素x左侧至多k个元素，如果这k个元素中存在一个元素落在区间[x-t,x+t]中，我们就找到了一对符合条件的元素。注意到这对于两个相邻元素，它们各自的左侧的k个元素中有k-1个重合的。于是我们可以使用滑动窗口的思路，维护一个大小为k的滑动窗口，每次遍历到元素x时，滑动窗口中包含元素x前面的最多k个元素，我们检查窗口中是否存在元素落在区间[x-t,x+t]中即可。

如果使用队列维护滑动窗口内的元素，由于元素是无序的，我们只能对于每个元素都遍历一次队列来检查是否有元素符合条件。如果数组的长度为n，则使用队列的时间复杂度为O(nk)，会超出时间限制。

因此我们希望能够找到一个数据结构维护滑动窗口内的元素，该数据结构需要满足以下操作：

- 支持添加和删除指定元素的操作，否则我们无法维护滑动窗口；

- 内部元素有序，支持二分查找的操作，这样我们可以快速判断滑动窗口中是否存在元素满足条件，具体而言，对于元素x，当我们希望判断滑动窗口中是否存在某个数y落在区间[x-t,x+t]中，只需要判断滑动窗口中所有大于x-t的元素中的最小元素是否小于等于x+t即可。

我们可以使用有序集合来支持这些操作。

实现方面，我们在有序集合中查找大于等于x-t的最小元素y，如果y存在，且y≤x+t，我们就找到了一对符合条件的元素。完成检查后，我们将x插入到有序集合中，如果有序集合中元素超过了k，我们就将有序集合汇中最早插入的元素删除即可。

**注意**

如果当前有序集合中存在相同的u安苏，那么此时程序将直接返回true。因此本题中的有序集合无需处理相同元素的情况。

为了防止整型int溢出，我们既可以使用长整型龙，也可以对查找区间[x-t,x+t]进行限制，使其落在int范围内。

代码如下：

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < len; ++i) {
            Long temp = set.ceiling((long) nums[i] - (long) t);
            if (temp != null && temp <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
```

结果：通过！ 时间复杂度O(nlog(min(n,k)))，其中 n 是给定数组的长度。每个元素至多被插入有序集合和从有序集合中删除一次，每次操作时间复杂度均为O(log(min(n,k))。

空间复杂度O(min(n,k))，其中 n 是给定数组的长度。有序集合中至多包含 min(n,k+1) 个元素。

桶

我们也可以使用利用桶排序的思想解决本题。我们按照元素的大小进行分桶，维护一个滑动窗口内的元素对应的元素。

对于元素x，其影响的区间为[x-t,x+t]。对于我们可以设定桶的大小为t+1。如果两个元素同属于一个桶，那么这两个元素必然符合条件。如果两个元素属于相邻桶，那么我们需要校验两个元素是否差值不超过t。如果两个元素既不属于一个桶，也不属于相邻桶，那么这两个元素必然不符合条件。

具体地，我们遍历该序列，假设当前遍历到元素x，那么我们首先检查x所属于的痛是否已经存在元素，如果存在，那么我们就找到了一对符合条件的元素，否则我们继续检查两个相邻的桶是否存在符合条件的元素。

实现方面，我们将int范围内的每一个整数x表示为x=(t+1)×a+b(0≤b≤t)的形式，这样x即归属于编号为a的痛。因为一个桶内至多只会有一个元素，所以我们使用哈希表实现即可。

**注意**：求桶的ID时，非负数直接用值除以桶的大小即可，但是当值为负数的时候，需要让值+1再除以桶的大小然后再-1。这是因为当值小于桶的大小且为负数时，直接除以桶的大小得出来的值是0，而非我们需求的-1，同时只用前（桶的大小-1）个元素计算的ID为0，因此需要给元素的值+1。

代码如下：

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < len; ++i) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id,(long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public Long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间负担



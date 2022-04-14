# 

# 剑指Offer 006.排序数组中两个数字之和

给定一个已按照 **升序排列**  的整数数组 `numbers` ，请你从数组中找出两个数满足相加之和等于目标数 `target` 。

函数应该以长度为 `2` 的整数数组的形式返回这两个数的下标值。`numbers` 的下标 **从 0 开始计数** ，所以答案数组应当满足 `0 <= answer[0] < answer[1] < numbers.length` 。

假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。

2022/4/14 第一次刷

初见思路：将升序排列的整数数组存入Hash表当中（数组值为key，数组下标为value），然后遍历整个数组，根据target值和数组当前值的差，判断哈希表中该key值内是否存在数字，最后返回value。

代码如下：

```java
Class Solution{    
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++){
            map.put(numbers[i],i);
        }
        for (int i = 0; i < numbers.length; i++){
            int temp = numbers[i];
            int flag = target - temp;
            if (map.containsKey(flag)){
                result[0] = i;
                result[1] = map.get(flag);
                break;
            }
        }
        return result;
    }
}
```

结果：通过。时间复杂度O(n)，只遍历了两次数组，一次存储一次查询。空间复杂度为O(n)，存储数组的哈希表长度与数组等长。

#### 这个方法主要是牺牲空间换取时间的，那是否有其他方法既不需要牺牲空间，时间复杂度也不上升呢？

答案是有的。（思路来源于官方题解中的双指针法）

具体思路如下：分别创建两个指针，一个指向数组头部，一个指向数组尾部，将两数字相加，若小于目标数字，则头部指针向尾部移动，若大于目标数字，则尾部指针向头部移动，知道得到目标下标为止。

代码如下：

```java
Class Solution{
    public int[] twoSum(int[] numbers, int target){
        int i = 0;//头部指针
        int j = numbers.length-1;//尾部指针
        int[] result = new int[2];
        while (i < j){//当头部指针等于尾部指针时，已经将数组遍历一遍了，答案应该已经得到
            int temp = numbers[i] + numbers[j];
            if (temp < target){
                i++;
            } else if (temp > target){
                j--;
            } else {
                result[0] = i;
                result[1] = j;
                break;
            }
        }
        return result;
    }
}
```

结果：通过！！

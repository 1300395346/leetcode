# 剑指Offer 058.日程表

请实现一个`MyCalendar`类来存放你的日程安排。如果需要添加的时间内没有其他安排，则可以存储这个新的日程安排。

`MyCalendar`有一个`book(int start, int end)`方法。它意味着在start到end时间内增加一个日程安排，注意，这里的时间是半开区间，即`[start, end)`，实数`x`的范围为：`start <= x < end`。

当两个日程安排有一些时间上的交叉时（例如两个日程都安排在同一时间内），就会产生重复预定。

每次调用`MyCalendar.book`方法时，如果可以将日程安排成功添加到日历中而不会导致重复预定，返回`true`。否则，返回`false`并且不要将该日程安排添加到日历中。

请按照以下步骤调用`MyCalendar`类：`MyCalendar cal = new MyCalendar();``MyCalendar.book(start,end)`

2022/5/19 第一次见

初见思路：遇到区间问题，可以尝试使用TreeMap来解决：

floorEntry：获取集合中第一个key小于等于指定值的集合

ceilingEntry：获取集合中第一个key大于等于指定值的集合

代码如下：

```java
class MyCalendar {
    TreeMap<Integer, Integer> map;

    public MyCalendar() {
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
```

结果：通过！

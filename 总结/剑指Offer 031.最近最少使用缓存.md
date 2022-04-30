# 剑指Offer 031.最近最少使用缓存

运用所掌握的数据结构，设计和实现一个 LRU（Least Recently Used，最近最少使用）缓存机制。

实现`LRUCache`类：

- `LRUCache(int capachity)`以正整数作为容量`capacity`初始化LRU缓存

- `int get(int key)`如果关键字`key`存在于缓存中，则返回关键字的值，否则返回`-1`

- `void put(int key, int value)`如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组[关键字-值]。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值流出空间。

2022/4/30 第一次刷

初见思路：该题的关键在于最近最少使用这一策略，这一个策略需要维护最近使用的值和最久使用的值，同时还得保证存储的值不超过总缓存空间。因此一开始想到了队列，但是队列不能将队列已有的数据重新放入队列头部，因此考虑双向链表。让最近使用的节点插入链表头部，新增节点插入链表尾部，但还有一个问题，获取链表中节点的位置需要遍历整个链表。因此采用HashMap的方式存储，其中key为链表中的key，value为该节点。

代码如下：

```java
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

}
```

结果：通过！！！！ 时间复杂度O(1)，空间复杂度O(capacity)

思路二：利用java自带的API——LinkedHashMap（参考了题解）

代码如下：

```java
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
```

# LinkedHashMap详解：

## 1 LinkedHashMap概述

    HashMap是Java  Collection Framework的重要成员，也就是Map族中我们最为常用的一种。不过，HashMap是无序的，也就是说，迭代HashMap所得到的元素顺序并不是它们最初防止的HashMap的顺序。

<img title="" src="https://img-blog.csdn.net/20170317181610752?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanVzdGxvdmV5b3Vf/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast" alt="" data-align="inline">

    HashMap的这一缺点往往会造成诸多不便，因为在有些场景中，我们需要用到一个可以保持插入顺序的Map。庆幸的是，JDK为我们解决了这个问题，它为HashMap提供了一个子类——LinkedHashMap。虽然LinkedHashMap增加了时间和空间上的开销，但是它通过维护一个二外的双向链表保证了迭代顺序。

    特别地，该迭代顺序可以是插入顺序，也可以是访问顺序。因此，根据链表中元素的顺序可以将LinkedHashMap分为：保持插入顺序的LinkedHashMap和保持访问顺序的LinkedHashMap，其中LinkedHashMap的默认实现是按插入顺序排序的。

    本质上，HashMap和双向链表合二为一即是LinkedHashMap。所谓LinkedHashMap，其落脚点在HashMap，因此更准确地说，它是一个将所有Entry节点链入一个双向链表的HashMap。

    在LinkedHashMap中，所有put进来的Entry都保存在如下所示的哈希表中，但由于它又额外定义了一个以head为头结点的双向链表，因此对于每次put进来的Entry，除了将其保存到哈希表中对应的位置上之外，还会将其插入到双向链表的尾部。

![](https://img-blog.csdn.net/20170317181650025?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanVzdGxvdmV5b3Vf/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

    更直观地，下图很好的还原了LinkedHashMap的原貌：HashMap和双向链表的密切配合和分工合作造就了LinkedHashMap。特别需要注意的是，next用于维护HashMap各个桶中的Entry链，before、after用于维护LinkedLHashMap的双向链表，虽然它们的作用对象都是Entry，但是各自分离，是两码事。

![](https://img-blog.csdn.net/20170512160734275?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanVzdGxvdmV5b3Vf/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

      其中，HashMap与LinkedHashMap的Entry结构示意图如下所示：

![](https://img-blog.csdn.net/20170512155609530?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanVzdGxvdmV5b3Vf/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

    特别地，由于LinkedHashMap是HashMap的子类，所以LinkedHashMap自然会拥有HashMap的所有特性。比如，LinkedHashMap也最多只允许一条Entry的键位Null（多条会覆盖），但允许多条Entry的值为Null。此外，LinkedHashMap也是Map的一个非同步实现。

## 2 LinkedHashMap源码定义

### 2.1 类结构定义

    LinkedHashMap继承与HashMap，其在JDK中的定义为：

```java
public class LinkedHashMap<K,V> extends HashMay<K,V>
                                implements Map<K,V>{
    ...
}
```

### 2.2 成员变量定义

    与HashMap相比，LinkedHashMap增加了两个属性用于保证迭代顺序，分别是双向链表头结点header和标志位AccessOrder（值为true时，表示按照访问顺序迭代；值为false时，表示按照插入顺序迭代）。

```java
/**
 * The head of the doubly linked list.
 */
private transient Entry<K,V> header; // 双向链表的表头元素


/**
 * The iteration ordering method for this linked hash map:  true for
 * access-order, false for insertion-order.
 *
 * @serial
 */
private final boolean accessOrder; //true表示按照访问顺序迭代，false表示按照插入顺序迭代
```

### 2.3 成员方法定义

    从下图我们可以看出，LinkedHashMap中并没有增加额外方法。也就是说，LinkedHashMap与HashMap在操作上大致相同，只是在实现细节上略有不同罢了。

![](https://imgconvert.csdnimg.cn/aHR0cDovL3N0YXRpYy56eWJ1bHVvLmNvbS9SaWNvMTIzL252b2pndjRzMG8wY2lpZWliejF0YmFrYy9MaW5rZWRIYXNoTWFwX091dGxpbmUucG5n?x-oss-process=image/format,png) 

### 2.4 基本元素 Entry

    LinkedHashMap采用的hash算法和HashMap相同，但是它重新定义了Entry。LinkedHashMap中的Entry增加了两个指针before和after，它们分别用于维护双向链表列表。特别需要注意的是，next用于维护HashMap各个桶中 Entry的连接顺序，before、after用于维护Entry插入的先后顺序，源代码如下：

```java
private static class Entry<K,V> extends HashMap.Entry<K,V> {
    
    // These fields comprise the doubly linked list used for iteration.
    Entry<K,V> before, after;

    Entry<int hash, K key, V value, HashMap.Entry<K,V> next) {
        super(hash,key,value,next);
    }
    ...
}
```

    形象地，HashMap与LinkedHashMap的Entry结构示意图如下所示：

![](https://img-blog.csdn.net/20170512155609530?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanVzdGxvdmV5b3Vf/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 3 LinkedHashMap的方法

## 3.1 LinkedHashMap的构造方法

    LinkedHashMap一共提供了五个构造函数，它们都是在HashMap的构造函数的基础上实现的，除了默认空参数构造方法，下面这个构造函数包含了大部分其他构造方法使用的参数，就不一一列举了。

```java
LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
```

    该构造函数意在构造一个指定初始容量和指定负载因子的具有指定迭代顺序的LinkedHashMap，其源码如下：

```java
/**
 * Constructs an empty LinkedHashMap instance with the
 * specified initial capacity, load factor and ordering mode.
 *
 * @param initialCapcity the initial capacity
 * @param loadFactor     the load factor
 * @param accessOrder    the ordering mode - true for
 *        access-order, false for insertion-order
 * @throws IllegalArgumentException if the initial capacity is negative
 *         or the load factor is nonpositive
 */
public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder){
    super(initialCapacity,loadFactor);  // 调用HashMap对应的构造函数
    this.accessOrder = accessOrder;     // 迭代顺序的默认值
}
```

    初始容量和负载因子是影响HashMap性能的两个重要参数。同样的，他们也是影响LinkedHashMap性能的两个重要参数。此外，LinkedHashMap增加了双向链表头结点header和标志accessOrder两个属性用于保证迭代顺序。

```java
LinkedHashMap(<Map<? extends K, ? extends V> m)
```

    该构造函数意在构造一个与指定Map具有相同映射的LinkedHashMap，其初始容量不小于16（具体依赖于指定Map的大小），负载因子是0.75，是Java Collection Framework 规范推荐提供的，其源码如下：

```java
/**
 * Constructs an insertion-ordered LinkedHashMap instance with
 * the same mappings the specified map. The LinkedHashMap
 * instance is created whith a default load factor (0.75) and an 
 * initial capacity sufficient to hold the mappings in specified map.
 *
 * @param m the map whose mappings are to be placed in this map
 * @throws NullPointerException if the specified map is null
 */
public LinkedHashMap(Map<? extends K, ? extends V> m){
    super(m);     //调用HashMap对应的构造函数
    accessOrder = false;  //迭代顺序的默认值
}
```

### 3.2 初始化方法——init方法

    从上面的五种构造函数我们可以看出，无论采用何种方式创建LinkedHashMap，其都会调用HashMap相应的构造函数。事实上，不管调用HahsMap的哪种构造函数，HashMap的构造函数都会在最后调用一个init()方法进行初始化，只不过这个方法在HashMap中是一个空实现，而在LinkedHashMap中重写了它用于初始化它所维护的双向链表。例如，HashMap的参数为空的构造函数以及init()的源码如下：

```java
/**
 * Constructs an empty HashMap with the default initial capacity
 * (16) and the default load factor (0.75).
 */
public HashMap(){
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    table = new Entry[DEFAULT_INITIAL_CAPACITY];
    init();
}

/**
 * Initialization hook for subclasses. This method is called in all
 * constructors and pseudo-constructors (clonem readObject) after
 * HashMap has been initialized but before any entries have been
 * inserted. (In the absence of this method, readObject would require
 * explicit knowledge of subclasses.)
 */
void init(){
}
```

    因此，我们在创建LinkedHashMap的同时就会不知不觉的对双向链表进行初始化。

### 3.3 LinkedHashMap的快速存取

    我们知道，在HashMap中最常用的两个操作是：put(Key,Value)和get(Key)。同样的，在LinkedHashMap中最常用的也是这两个操作。

    对于put(Key,Value)方法而言，LinkedHashMap完全继承了HashMap的put(Key,Value)方法，只是对put(Key,Value)方法所调用的recordAccess方法和addEntry方法进行了重写；对于get(Key)方法而言，LinkedHashMap则直接对它进行了重写。

#### 3.3.1 LinkedHashMap的存储实现：put(key,value)

    LinkedHashMap没有对put(Key,Value)方法进行任何直接的修改，完全继承了HashMap的put(Key,Value)方法，其源码如下：

```java
publilc V put(K key, V value){
    
    //当Key为null时，调用putForNullKey方法，并将该键值对保存到table的第一个位置
    if (key == null)
        return putForNullKey(value);

    //根据key的hashCode计算hash值
    int hash = hash(key.hashCode());

    //在table的第i个桶上进行迭代，寻找key保存的位置
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;

        //判断该条链上是否存在hash值相同且key值相等的映射，若存在，则直接覆盖value，并返回并返回旧value
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this); // LinkedHashMap重写了Entry中的recordAccess方法
            return oldValue; // 返回旧值
        }
    }
    
    modCount++; //修改次数增加1，快速失败机制
    
    //原Map中无该映射，将该添加至该链的链头
    addEntry(hash,key,value,i); //LinkedHashMap重写了HashMap中的createEntry方法
    return null;
}
```

    上述源码反映了LinkedHashMap与HashMap保存数据的过程。特别地，在LinkedHashMap中，它对addEntry方法和Entry的recordAccess方法进行了重写。下面我们对比的看一下LinkedHashMap和HashMap的addEntry方法的具体实现：

```java
/**
 * This override alters behavior of superclass put method. It causes
 * newly allocated entry to get inserted at the end of linked list
 * and removes the eldest entry if appropriate.
 *
 * LinkedHashMap中addEntry方法
 */
void addEntry(int hash, K key, V value, int bucketIndex){
    //创建新的Entry，并插入到LinkedHashMap中
    createEntry(hash,key,value,bucketIndex); //重写了HashMap中的createEntry方法
    
    //双向链表的第一个有效节点（header后的那个节点）为最近最少使用的节点，这是用来支持LRU算法的
    Entry<K,V> eldest = header.after;
    //如果有必要，则删除掉该最近最少使用的节点
    //这要看对removeEldestEntry的覆写，由于默认为false，因此默认是不做任何处理的。
    if (removeEldestEntry(eldest)) {
        removeEntryForKey(eldest.key);
    } else{
        //扩容到原来的2倍
        if (size >= threshold)
            resize(2 * table.length);
    }
}



/**
 * Adds a new entry with the specified key, value and hash code
 * to the specified bucket. It is the responsibility of this 
 * method to resize the table if appropriate.
 *
 * Subclass overrides this to alter the behavior of put method.
 *
 * HashMap中的addEntry方法
 */
void addEntry(int hash, K key, V value, int bucketIndex) {
    //获取bucketIndex处的Entry
    Entry<K,V> e = table[bucketIndex];

    //将新创建的Entry放入bucketIndex索引处，并让新的Entry指向原来的Entry
    table[bucketIndex] = new Entry<K,V>(hash,key,value,e);

    //若HashMap中元素的个数超过了极限，则容量扩大两倍
    if (size++ >= threshold){
        resize(2 * table.length);
    }
}
```

    由于LinkedHashMap本身维护了插入的先后顺序，因此其可以用来做缓存，14~19行的操作就是用来支持LRU算法的。此外，在LinkedHashMap的addEntry方法中，它重写了HashMap中的createEntry方法，我们接着看看createEntry方法：

```java
void createEntry(int hash, K key, V value, int bucketIndex){
    //向哈希表中插入Entry，这点与HashMap相同
    //创建新的Entry并将其链入到数组对应桶的链表的头结点处
    HashMap.Entry<K,V> old = table[bucketIndex];
    Entry<K,V> e = new Entry<K,V>(hash,key,value,old);
    table[bucketIndex] = e;

    //在每次向哈希表插入Entry的同时，都会将其插入到双向链表的尾部，
    //这样就按照Entry插入LinkedHashMap的先后顺序来迭代元素（LinkedHashMap根据双向链表重写了迭代器）
    //同时，新put进来的Entry是最近访问的Entry，把其放在链表末尾，也符合LRU算法的实现
    e.addBefore(header);
    size++;
}
```

    由以上源码我们可以知道，在LinkedHashMap中向哈希表中插入新Entry的同时，还会通过Entry的addBefore方法将其链入到双链表中。其中，addBefore方法本质上是一个双向链表的插入操作，其源码如下：

```java
//在双向链表中，将当前的Entry插入到existingEntry(header)的前面
private void addBefore(Entry<K,V> existingEntry) {
    after = existingEntry;
    before = existingEntry.before;
    before.after = this;
    after.before = this;
}
```

    总的来说，相比HashMap而言，LinkedHashMap在向哈希表添加一个键值对的同时，也会将其链入到它所维护的双向链表中，以便设定迭代顺序。

#### 3.3.2 LinkedHashMap的扩容操作——resize()

    在HashMap中，我们知道随着HashMap中元素的数量越来越多，发生碰撞的概率将越来越大，所产生的的子链长度就会越来越长，这样势必会影响HashMap的存取速度。为了保证HashMap的效率，系统必须要在某个临界点进行扩容处理，该临界点就是HashMap中元素的数量在数值上等于threshold（table数组长度*加载因子）。

    但是，扩容是一个非常耗时的过程，因为它需要重新计算这些元素在新table数组中的位置并进行复制处理。所以，如果我们能够提前与指导HashMap中元素的个数，那么在构造HashMap时预设元素的个数能够有效的提高HashMap的性能。同样的问题也存在于LinkedHashMap中，因为LinkedHashMap本来就是一个HashMap，只是它还将所有Entry节点链入到了一个双向链表中。LinkedHashMap完全继承了HashMap的resize()方法，只是对它所调用的transfer方法进行了重写。我们先看resize()方法的源码：

```java
void resize(int newCapacity){
    Entry[] oldTable = table;
    int oldCapacity = oldTable.length;

    //若oldCapacity已达到最大值，直接将threshold设为Integer.MAX_VALUE
    if (oldCapacity == MAXIMUM_CAPACITY) {
        threshold = Integer.MAX_VALUE;
        return;  //直接返回
    }

    //否则，创建一个更大的数组
    Entry[] newTable = new Entry[newCapacity];
    
    //将每条Entry重新哈希到新的数组中
    transfer(newTable);  //LinkedHashMap对它所调用的transfer方法进行了重写

    table = newTable;
    threshold = (int) (newCapacity * loadFactor); // 重新设定threshold
}
```

    从上面代码中我们可以看出，Map扩容操作的核心在于重哈希。所谓重哈希是指重新计算原HashMap中的元素在新table数组中的位置并进行赋值处理的过程。鉴于性能和LinkedHashMap自身特点的考量，LinkedHashMap对重哈希过程（transfer方法）进行了重写，源码如下：

```java
/**
 * Transfers all entries to new table array. This method is called
 * by superclass resize. It is overridden for performance, as it is
 * faster to iterate using our linked list.
 */
void transfer(HashMap.Entry[] newTable){
    int newCapacity = newTable.length;
    //与HashMap相比，借助于双向链表的特点进行重哈希使得代码更加简洁
    for (Entry<K,V> e = header.after; e != header; e = e.after) {
        int index = indexFor(e.hash, newCapacity);  //计算每隔Entry所在的桶
        //将其链入桶中的链表
        e.next = newTable[index];
        newTable[index] = e;
    }
}
```

    如上述源码所示，LinkedHashMap借助于自身维护的双向链表轻松的实现了重哈希操作。

#### 3.3.3 LinkedHashMap的读取实现——get(Object key)

    相对于LinkedHashMap的存储而言，读取就显得比较简单了。LinkedHashMap中重写了HashMap中的get方法，源码如下：

```java
public V get(Object key){
    //根据key获取对应的Entry，若没有这样的Entry，则返回null
    Entry<K,V> e = (Entry<K,V>)getEntry(key);
    if (e == null)  //若不存在这样的Entry，直接返回
        return;
    e.recordAccess(this);
    retrun e.value;
}


/**
 * Returns the entry associated with the specified key in the
 * HashMap. Return null if the HashMap contains no mapping 
 * for the key.
 *
 * HashMap中的方法
 *
 */
final Entry<K,V> getEntry(Object key){
    if (size == 0){
        return null;
    }

    int hash = (key == null) ? 0 : hash(key);
    for (Entry<K,V> e = table[IndexFor(hasn,table.length)]; e != null; e = e.next){
        Object k;
        if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
            return e;        
    }
    return null;
}
```

    在LinkedHashMap的get方法中，通过HashMap中的getEntry方法获取Entry对象。注意这里的recordAccess方法，如果链表中元素的排序对象是按照插入的先后顺序的话，该方法什么也不做；如果链表中元素的排序是按照访问的先后顺序的话，则将e移到链表的末尾处。

    另外，同样的，调用LinkedHashMap的get(Object key)方法后，若返回值是NULL，则也存在如下两种可能：

1.    该key对应的值就是null；

2.    HashMap中不存在 该key。

#### 3.3.4 LinkedHashMap存取小结

1.    LinkedHashMap的存取过程与HashMap基本类似，只是在细节实现上稍有不同，这是由LinkedHashMap本身的特性所决定的，因为它要额外维护一个双向链表用于保持迭代顺序。

2.    在put操作上，虽然LinkedHashMap完全继承了HashMap的put操作，但是在细节上还是做了一定的调整，比如：在LinkedHashMap中向哈希表中插入新的Entry的同时，还会通过Entry的addBefore方法将其链入到双向链表中。

3.    在扩容操作上，虽然LinkedHashMap完全继承了HashMap的resize操作，但是鉴于性能和LinkedHashMap自身特点的考量，LinkedHashMap对其中的重哈希过程（transfer方法）进行了重写。

4.    在读取操作上，LinkedHashMap中重写了HashMap中的get方法，通过HashMap中的getEntry方法获取Entry对象。在此基础上，进一步获取指定键对应的值。

## 4 LinkedHashMap与LRU算法

    LRU算法即Least recently used（最近最少使用）算法。

### 4.1 put操作与标志位accessOrder

```java
public V put(K key, V value){
    //若key为null，则将该键值对添加到table[0]中。
    if (key == null)
        return putForNullKey(value);
    //若key不为null，则计算该key的哈希值，然后将其添加到该哈希值对应的链表中。
    int hash = hash(key.hashCode());
    int i = indexFor(hash,table.length);
    for(Entry<K,V> e = table[i]; e != null; e = e.next){
        Object k;
        //若key对应的值已经存在，则用新的value取代旧的valude
        if (e.hash == hash &&((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }

    //若key不存在，则将键值对添加到table中
    modCount++;
    //将键值对添加到table[i]处
    addEntry(hash,key,value,i);
    return null;
}
```

    从上述源码我们可以看到，当要put进来的Entry的key在哈希表中已经存在时，会调用Entry的recordAccess方法；当key不存在时，则会调用addEntry方法将行的Entry插入到对应桶的单链表的头部。我们先来看recordAccess方法：

```java
/**
 * This method is invoked by the superclass whenever the value
 * of a pre-existing entry is read by Map.get or modified by 
 * Map.set. If the enclosing Map is access-ordered, it moves the
 * entry to the end of the list; otherwise, it does nothing.
 */
void recordAccess(HashMap<K,V> m){
    LinkedHashMap<K,V> lm = (LinkedHashMap<K,V>)m;
    //如果链表中元素按照访问顺序排序，则将当前访问的Entry移到双向循环链表的尾部，
    //如果是按照插入的先后顺序排序的，则不作任何事情。
    if (lm.accessOrder) {
        lm.modCount++;
        //移除当前访问的Entry
        remove();
        //将当前访问的Entry插入到链表的尾部
        addBefore(lm.header);
    }
}
```

    LinkedHashMap重写HashMap中的recordAccess方法（HashMap中该方法为空），当调用父类的put方法时，在发现key已经存在时，会调用该方法；当调用自己的get方法是，也会调用该方法。

    该方法提供了LRU算法的实现，它将最近使用的Entry放到双向链表的尾部。也就是说，当accessOrder为true时，get方法和put方法都会调用recordAccess方法使得最近使用的Entry移到双向链表的末尾；当accessOrder为默认值false时，从源码可以看出recordAccess方法什么也不会做。我们反过头来，再看一下addEntry方法：

```java
/**
 * This override alters behavior of superclass put method. It causes
 * newly allocated entry to get inserted at the end of the linked
 * list and removes teh eldest entry if approproate.
 * 
 */
void addEntry(int hash, K key, V value, int bucketIndex){
    //创建爱你新的Entry，并插入到LinkedHashMap中
    createEntry(hash,key,value,bucketIndex); //重写了HashMap中的createEntry方法
    //双向链表的第一个有效节点（header后的那个节点）为最近最少使用节点，这是用来支持LRU算法的
    Entry<K,V> eldest = header.after;
    //如果有必要，则删除掉该最近最少使用的节点
    //这要看对removeEldestEntry的覆写，由于默认为false，因此默认是不做任何处理的。
    if (removeEldestEntry(eldest)) {
        removeEntryForKey(eldest.key);
    } else {
        if (size >= threshold)
            resize(2 * table.length);
    }
}


void createEntry(int hash, K key, V value, int bucketIndex){
    //向哈希表中插入Entry，这点与HashMap中相同
    //创建新的Entry并将其链入到数组对应桶的链表的头结点处
    HashMap.Entry<K,V> old = table[bucketIndex];
    Entry<K,V> e = new Entry<K,V>(hash,key,value,old);
    table[bucketIndex] = e;
    
    //在每次向哈希表插入Entry的同时，都会将其插入到双向链表的尾部
    //这样就按照Entry插入LinkedHashMap的先后顺序来迭代元素（LinkedHashMap根据双向链表重写了迭代器）
    //同时，新put进来的Entry是最近访问的Entry，把其放在链表末尾，也符合LRU算法的实现
    e.addBefore(header);
    size++;
}
```

    同样是将新的Entry链入到table中对应桶的单链表中，但可以在createEntry方法中看出，同时也会把新put进来的Entry插入到双向链表的尾部。从插入顺序的层面来说，新的Entry插入到双向链表的尾部可以实现按照插入的先后顺序来迭代Entry，而从访问顺序的层面来说，新put进来的Entry又是最近访问的Entry，也应该将其放在双向链表的尾部。在上面的addEntry方法中还调用了removeEldestEntry方法，该方法源码如下：

```java
/**
 * This implementation merely return false (so that this map
 * acts like a normal map - the eldest element is never removed).
 * 
 * @param  eldest The least recently inserted entry in the map, or
 *         if this is an access-ordered map, the least recently 
 *         accessed entry. This is the entry that will be removed
 *         it this method return true. If the map was empty prior
 *         to the put or putAll invocation resulting in this 
 *         invocation, this will be the entry that was just
 *         inserted; in other words, if the map contains a single
 *         entry, the eldest entry is also the newest.
 * @return true if the eldest entry should be removed from the map;
 *         false if it should be retained.
 */
 protected boolent removeEldestEntry(Map.Entry<K,V> eldest){
    return false;
}
```

    该方法是用来被重写的，一般地，如果用LinkedHashMap实现LRU算法，就要重写该方法。比如，可以将该方法覆写为如果设定的内存已满，则返回true，这样当再次向LinkedHashMap中putEntry时，在调用的addEntry方法中便会将最近最少未使用的节点删除掉（header后的那个节点）。

### 4.2 get操作与标志位accessOrder

    在LinkedHashMap中进行读取操作是，一样会调用recordAccess方法。

```java
public V get(Object key){
    //根据key获取对应的Entry，若没有这样的Entry，则返回null
    Entry<K,V> e = (Entry<K,V>)getEntry(key);
    if (e == null)  //若不存在这样的Entry，直接返回
        return null;
    e.recordAccess(this);
    return e.value;
}
```

### 4.3 LinkedHashMap与LRU小结

    使用LinkedHashMap实现LRU的必要前提是将accessOrder标志位设为true以便开启按访问顺序排序内模式。我们可以看到，无论是put方法还是get方法，都会导致目标Entry成为最近访问的Entry，因此就把该Entry加入到双向链表的末尾：get方法通过调用recordAccess方法来实现；

    put方法在覆写已有key的情况下，也是调用recordAccess方法来实现，在插入新的Entry时，则是通过createEntry方法中的addBefore方法来实现。这样，我们便把最近使用的ENtry放入到了双向链表的后面。多次操作后，双向链表前面的Entry便是最近最久未使用的，这样当节点个数满的时候，删除最前面的Entry（header后面的那个Entry）即可，因为他就是最近最少未使用的Entry。

## 5 使用LinkedHashMap实现LRU算法

### 5.1 使用LinkedHashMap实现LRU 算法示例

```java
public class LRU<K,V> extends LinkedHashMap<K,V> implements Map<K,V>{
    private static final long serialVersionUID = 1L;
    
    public LRU(int initialCapacity, float loadFactor, boolent accessOrder){
        super(initialCapacity,loadFactor,accessOrder);
    }

    /**
     * @description 重写LinkedHashMap中removeEldestEntry方法，当LRU元素多于6个时
     *              删除最近最久不适用的元素
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K,V> eldest){
        //TODO Auto-generated method stub
        if (size() > 6){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        
        LRU<Character, Integer> lru = new LRU<Character, Integer>(16,0.75f,true);
        
        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); ++i){
            lru.put(s.charAt(i),i);
        }
        System.out.println("LRU中key为h的Entry的值为："+lru.get('h'));
        System.out.println("LRU的大小："+lru.size());
        System.out.println("LRU："+lru);
    }
}
```

    下图是程序运行结果示例：

![](C:\Users\86189\AppData\Roaming\marktext\images\2022-04-30-22-55-05-image.png)

### 5.2 LinkedHashMap有序性原理分析

    如前文所述，LinkedHashMap增加了双向链表头结点header和标志位accessOrder两个属性用于保证迭代顺利。但是要真正实现其有序性，还差临门一脚，那就是重写HashMap的迭代器，其源码实现如下：

```java
private abstract class LinkedHashIterator<T> implements Iterator<T>{
    Entry<K,V> nextEntry = header.after;
    Entry<K,V> lastReturned = null;

    /**
     * The modCount value that the iterator believes that the backing
     * List should have. If this expectation is violated, the iterator
     * has detected concurrent modification.
     */
    int expectedModCount = modCount;
    
    public boolean hasNext(){   //根据双向链表判断
        return nextEntry != header;
    }

    public void remove() {
        if (lastReturned == null)
        throw new IllegalStateException();
        if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
            LinkedHashMap.this.remove(lastReturned.key);
            lastReturned = null;
            expectedModCount = modCount;
    }
    
    Entry<K,V> nextEntry(){   //迭代输出双向链表各节点
        if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
            if (nextEntry == header)
                throw new NoSuchElementException();

            Entry<K,V> e = lastReturned = nextEntry;
            nextEntry = e.after;
            return e;
    }
}

//Key迭代器， KeySet
private class KeyIterator extends LinkedHashIterator<K> {
    public K next(){
        return nextEntry().getKey();
    }
}

//Value迭代器，Value(Collection)
private class ValueIterator extends LinkedHashIterator<V> {
    public V next(){
        retrun nextEntry().value;
    }
}

//Entry迭代器，EntrySet
private class EntryIterator extends LinkedHashIterator<Map.Entry<K,V>>{
    public Map.Entry<K,V> next(){
        return nextEntry();
    }
}
```

    从上述代码中我们可以知道，LinkedHashMap重写了HashMap的迭代器，它使用其维护的双向链表进行迭代输出。

## 6 JDK1.8的修改

    原文是基于JDK1.6的实现，实际上JDK1.8对其进行了改动。首先它删除了addEntry，createEntry等方法（事实上是HashMap的改动影响了它）。LinkedHashMap同样使用了大部分HashMPA的增删查改。新版本的LinkedHashMap主要是通过内置几个方法重写来实现LRU的。

    HashMap不提供实现：

```java
void afterNodeAccess(Node<K,V> p){}
void afterNodeInsertion(boolean evict){}
void afterNodeRemoval(Node<K,V> p){}
```

    LinkedHashMap的实现：

        （1）处理元素被访问后的情况

```java
void afterNodeAccess(Node<K,V> e){ // move node to last
    LinkedHashMap.Entry<K,V> last;
    if (accessOrder && (last = tail) != e) {
        LinkedHashMap.Entry<K,V> p = (LinkedHashMap.Entry<K,V>) e;
        LinkedHashMap.Entry<K,V> b = p.before,a = p.after;
        p.after = null;
        if (b == null)
            head = a;
        else
            b.after = a;
        if (a != null)
            a.before = b;
        else
            last = b;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
        tail = p;
        ++modCount;
    }
}
```

        （2）处理元素插入后的情况

```java
void afterNodeInsertion(boolean evict) { // possibly remove eldest
    LinkedHashMap.Entry<K,V> first;
    if (evict && (first == head) != null && removeEldestEntry(first)) {
        K key = first.key;
        removeNode(hash(key),key,null,false,true);
    }
}
```

        （3）处理元素被删除后的情况

```java
void afterNodeRemoval(Node<K,V> e){  //unlink
    LinkedHashMap.Entry<K,V> p = (LinkedHashMap.Entry<K,V>) e;
    LinkedHashMap.Entry<K,V> b = p.before, a = p.after;
    p.before = p.after = null;
    if (b == null)
        head = a;
    else
        b.after = a;
    if (a == null)
        tail = b;
    else
        a.before = b;
}
```

    另外JDK1.8的HashMap在链表超过8时自动转为红黑树，会按照顺序插入链表中的元素，可以自定义比较器来定义节点的插入顺序。

    JDK1.8的LinkedHashMap同样会使用这一特性，当变为红黑树以后，节点的先后顺序同样是插入红黑树的顺序，其双向链表的性质没有改变，只是原来的HashMap的链表变成了红黑树而已。

## 7 LinkedHashMap小结

1. LinkedHashMap在HashMap的数组加链表结构的基础上，将所有节点连成一个双向链表。

2. 当主动传入的accessOrder参数为false时，使用put方法时，新加入的元素不会被加入双向链表，get方法使用时也不会把元素放入双向链表尾部。

3. 当主动传入的accessOrder参数为true时，使用put方法新加入的元素，如果遇到了哈希冲突，并且key值相同的元素进行了替换，就会被放在双向链表的尾部，当元素超过上限且removeEldestEntry方法返回true时，直接删除最近最久未使用元素以便新元素插入。如果没有冲突则直接放入，同样会将该元素加入到链表尾部。使用get方法时会把get到的元素放到双向链表尾部。

4. LinkedHashMap的扩容比HashMap来的方便，因为HashMap需要将原来的每个链表的元素分别在新数组进行反向插入链化，而LinkedHashMap的元素都连在同一个链表上，可以直接迭代然后插入。

5. LinkedHashMap的removeEldestEntry方法默认返回false，要实现LRU很重要的一点就是集合满的时候要讲最近最久未使用的元素删除，在LinkedHashMap中这个元素就是头指针指向的元素。实现LRU可以直接实现继承LinkedHashMap并重现removeEldestEntry方法来设置缓存大小。JDK中实现了LRUCache也可以直接使用。





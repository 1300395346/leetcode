# 剑指Offer 003 前n个数字二进制中1的个数

2022/4/11 第一次刷

给定一个非负整数 `n` ，请计算 `0` 到 `n` 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。

**说明 :**

- `0 <= n <= 105`

初见思路：从0开始遍历到n，将其转换成二进制数，然后遍历二进制数计算1的个数

代码如下：

```java
class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n+1];
        result[0] = 0;
        for (int i = 1; i <= n; i++){
            int temp = 0;
            String binary = Integer.toBinaryString(i);
            int j = binary.length()-1;
            while (j >= 0){
                if (binary.charAt(j--)-'0'==1){
                    temp++;
                }
            }
            result[i] = temp;
        }

        return result;
    }
}
```

进阶:

- 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？

- 要求算法的空间复杂度为 O(n) 。

- 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。

进阶1：利用java内置函数Integer.bitCount(n)实现时间复杂度为O(n)，空间复杂度为O(1)

    代码如下：

```java
class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n+1];
        for (int i = 0; i<=n; i++){
            result[i] = Integer.bitCount(i);
        }
        return result;
    }
}
```

进阶2：要求空间复杂度为O(n)：将从0到n的所有二进制数的汉明重量（含1的个数）存储起来，运算时直接查表即可。

进阶3：不使用内置函数完成时间复杂度为O(n)，即代码实现Java的Integer.bitCount()

   没有思路！

查询了java源码Integer.bitCount()的算法原理

    每两位bit为一组，分别统计有几个1，然后把结果存到这两个bit位上，如：11有2个1，结果为10，10替代11的存储到原位置。然后进行加法计算，把所有的结果加起来。加的过程中呢又可以两两相加，减少计算流程。

算法实现如下：  

- 首先整数i抹除左一位：`i & 0x55555555`，然后错位相加。`(i >>> 1) & 0x55555555`表示：左位移到右边，再把左位抹除，这样就可以计算两个bit位上1的个数了：`0b1011=>0b0001 + 0b0101 = 0b0110`左两位有1个1，右两位有2个1。  

- 这时i中存储了每两位的统计结果，可以进行两两相加，最后求和。

- 第一步：两个bit计算1的数量：`0b11: 0b01 + 0b01 = 0b10 = 2`, `0b10: 0b00 + 0b01 = 0b01 = 1`。研究发现：`2=0b11-0b1`，`1=0b10-0b1`,可以减少一次位于计算：`i = i - ((i >>> 1) & 0x55555555)`

- 第二步：暂时没有好的优化方法

- 第三步：实际是计算每个byte中的1的数量，最多8（0b1000）个，占4bit，可以最后进行位与运算消位，减少一次`&`运算：`i = (i + (i >>> 4)) & 0x0f0f0f0f`

- 第四,五步：同上理由，可以最后消位。但是由于int最多32（0b100000）个1，所以这两步可以不消位，最后一步把不需要的bit位抹除就可以了：`i & 0x3f`

算法原型：

```java
public static int bitCount(int i) {
    i = (i & 0x55555555) + ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i & 0x0f0f0f0f) + ((i >>> 4) & 0x0f0f0f0f);
    i = (i & 0x00ff00ff) + ((i >>> 8) & 0x00ff00ff);
    i = (i & 0x0000ffff) + ((i >>> 16) & 0x0000ffff);
    return i;
}
```

代码实现如下：

```java
class Solution {
    public int[] countBits(int n){
        int[] result = new int[n+1];
        for(int j=0; j <=n; j++){
            int i = j;
            i = (i & 0x55555555) + ((i >>> 1) & 0x55555555);
            i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
            i = (i & 0x0f0f0f0f) + ((i >>> 4) & 0x0f0f0f0f);
            i = (i & 0x00ff00ff) + ((i >>> 8) & 0x00ff00ff);
            i = (i & 0x0000ffff) + ((i >>> 16) & 0x0000ffff);
            result[j] = i;
        }
        return result;
    }
}
```

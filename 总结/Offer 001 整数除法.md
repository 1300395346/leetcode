# Offer 001 整数除法

2022/4/10 第一次刷到这道题

给定两个整数 `a` 和 `b` ，求它们的除法的商 `a/b` ，要求不得使用乘号 `'*'`、除号 `'/'` 以及求余符号 `'%'` 。

注意：

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31−1]。本题中，如果除法结果溢出，则返回 2^31 − 1


初见思路：计算除数b连续加自身，加到第几次时大于被除数a，此时的次数即为商。（可以预见一定是超时的。）

```
class Solution {
    public int divide(int a, int b) {
        if(a==0) return 0;
        if(b==1) return a;
        if(a==-2147483648&&b==-1) return 2147483647;
        boolean flag = false;
        if((a<0&&b>0)||(a>0&&b<0)) flag = true;
        a = Math.abs(a);
        b = Math.abs(b);

        int i = 1;
        int c = b;
        while (c<a){
            c+=b;
            i++;
            if (c>a){
                i--;
            }
        }
        return flag?-i:i;
    }
}
```

结果：解答错误；最后执行的输入为a=1,b=2。出错原因：未曾考虑当被除数小于除数时的状况。

修改后代码如下：

```
class Solution {
    public int divide(int a, int b) {
        if(a==0) return 0;
        if(b==1) return a;
        if(a==-2147483648&&b==-1) return 2147483647;
        boolean flag = false;
        if((a<0&&b>0)||(a>0&&b<0)) flag = true;
        a = Math.abs(a);
        b = Math.abs(b);
        if(a<b)return 0;
        int i = 1;
        int c = b;
        while (c<a){
            c+=b;
            i++;
            if (c>a){
                i--;
            }
        }
        return flag?-i:i;
    }
}
```

结果：超出时间限制；最后执行输入：a=2147483647，b=2；



未曾想到其他思路，因此参考题解

![整数除法.png](https://pic.leetcode-cn.com/1643716802-McpMhm-%E6%95%B4%E6%95%B0%E9%99%A4%E6%B3%95.png)

了解到：当某个数向右移动一位，意味着这个数乘以2，而当1向右移动n位，意味着求2的n次方。

根据上图，写出的代码如下：

```
class Solution {
    public int divide(int a, int b){
        if (a == 0 || b==-2147483648) return 0;
        if (b == 1) return a;
        if (a == -2147483648 && b == -1) return 2147483647;
        int flag = 2;
        //将负数转换为正数，因为前面考虑了最小值的情况，因此这里不会存在溢出
        if (a < 0) {
            a = -a;
            flag--;
        }
        if (b < 0){
            b = -b;
            flag--;
        }
        if (a < b) return 0;
        if (a == b) return flag==1?-1:1;
        //获取除数右移的最大位数
        int i = getMaxShift(a,b);
        int result = (1 << i);
        int j = a - (b << i);
        //求被除数与除数右移后的差，当差小于除数时，说明求得了商
        while (j > b){
            if (j - (b << i) < 0){
                i--;
            }else {
                result += (1 << i);
                j -= (b << i);
                i--;
            }
        }
        return flag==1?-result:result;
    }

    private int getMaxShift(int a, int b) {
        int i = 0;
        while ((b << i) < a){
            i++;
        }
        return i-1;
    }
}
```

结果：超出时间限制；最后执行输入：a=2147483647，b=2；

超时原因：当a=2147483647，b=2时，b<<29=1073741824<a，而b<<30溢出变成负数，因此调补出循环

修改策略：在while循环中多增加一个判断(b<<i)!=Integer.MIN_VALUE

```
    private int getMaxShift(int a, int b) {
        int i = 0;
        while ((b << i) < a &&(b << i) != Integer.MIN_VALUE){
            i++;
        }
        return i-1;
    }
```

结果：解答错误；最后执行输入：a = -2147483648 b = 2

错误原因：负数转换成正数产生溢出，最后未能转换成正数

修改策略：调整核心代码，需要从负数角度开始考虑

修改后代码如下：

```
class Solution {
    public int divide(int a,int b){
        if (a == Integer.MAX_VALUE && b == -1) return Integer.MIN_VALUE;
        if (a == 0 || b == 1){
            return a;
        }else if (b == -1){
            return -a;
        }

        int flag = 2;
        if (a > 0){
            a = - a;
            flag--;
        }
        if (b > 0){
            b = -b;
            flag--;
        }
        if(a>b) return 0;
        int result = divideCore(a,b);
        return flag==1?-result:result;
    }

    public int divideCore(int a, int b){
        if (a == b) return 1;
        int shift = getMaxShift(a,b);
        int result = (1 << shift);
        int temp = a - (b << shift);
        while (temp < b){
            while (temp > (b << shift)){
                shift--;
            }
            result +=(1 << shift);
            temp -= (b << shift);
        }
        return result;
    }

    public int getMaxShift(int a,int b){
        int shift = 0;
        int temp = b;
        int BOUND = (Integer.MIN_VALUE) >> 2;
        while (temp > a && temp >= BOUND){
            temp <<= 1;
            shift++;
        }
        return shift-1;
    }
}
```

结果：解答错误；最后执行输入：a=-2147483648，b=-1

错误原因：边界值考虑出错，负数转为正数才会溢出

修改后代码：

```
  if (a == Integer.MAX_VALUE && b == -1) return Integer.MIN_VALU
```

结果：解答错误；最后执行输入：a=-231；b=3

错误原因：未曾考虑能够被整除的状况

修改后代码：

```
while (temp <= b){
            while (temp > (b << shift)){
                shift--;
            }
            result +=(1 << shift);
            temp -= (b << shift);
        }
```

结果：解答错误；最后执行输入：a=1026117192，b=-874002063

错误原因：求最大右移位数出错，这次的出发中，最大右移位数为0，可是求出来的是-1

修改策略：在计算最大右移位数时得出的结果不-1，而是在核心计算的过程中，将结果的计算放入while循环当中，即得出最大右移位数后不计算右移后与被除数的差，而是比较本身的被除数和除数之间的大小。若被除数小于除数，则得出的右移位数一定为0，同时不进入while循环；若被除数大于除数，则比较被除数与右移后数字的大小，因为之前得出的右移后的数是大于被除数的，因此一定会执行一次while循环，得到真正的右移位数。

综上所述，如此操作之后，最大右移位数能够不出错的得到，同时使得代码结构更加的巧妙。

修改后代码如下：

```
class Solution {
    public int divide(int a,int b){
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        if (a == 0 || b == 1){
            return a;
        }else if (b == -1){
            return -a;
        }

        int flag = 2;
        if (a > 0){
            a = - a;
            flag--;
        }
        if (b > 0){
            b = -b;
            flag--;
        }
        if (a > b) return 0;
        int result = divideCore(a,b);
        return flag==1?-result:result;
    }

    public int divideCore(int a, int b){
        if (a == b) return 1;
        int shift = getMaxShift(a,b);
        int result = 0;
        while (a <= b){
            while (a > (b << shift)){
                shift--;
            }
            result +=(1 << shift);
            a -= (b << shift);
        }
        return result;
    }

    public int getMaxShift(int a,int b){
        int shift = 0;
        int temp = b;
        int BOUND = (Integer.MIN_VALUE) >> 2;
        while (temp > a && temp >= BOUND){
            temp <<= 1;
            shift++;
        }
        return shift;
    }
}
```

结果：通过！！！



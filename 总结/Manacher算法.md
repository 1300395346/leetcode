# Manacher算法

Manacher算法，又叫“马拉车”算法，可以在时间复杂度为O(n)的情况下求解一个字符串的最长回文子串长度的问题。

## 一、回文子串的一般解法

将字符串的每一个字符作为回文子串的中心对称点，每次保存前面求得的回文子串的最大值，最后得到的就是最长的回文子串的长度，这种方法的时间复杂度是O(n^2)。在求解过程中，需要考虑回文子串是奇数和是偶数的情况。为了解决这个问题有两种思路，一种是在每个字母两边加上任意一个符号，便将字符串的长度转化成奇数了，将求得的答案除以2就得到最后的结果；另一种是将奇数和偶数的情况同时考虑，一个长度为n的字符串会生成2n-1组回文中心，回文中心的左边界为i/2，右边界为i/2+（imod2），只需要从0开始遍历到2n-2即可找到所有回文中心。

## 二、Manacher算法中的基础概念

在进行Manacher算法时，字符串都会进行预处理：在每个字符左右两边加入一个字符，如：输入acbbcbds，预处理过后的新字符串为：#a#c#b#b#c#b#d#s#。

### 1、回文半径数组radius

回文半径数组radius是用来记录以每一个位置的字符为回文中心求出的回文半径长度，故上文预处理过后的新字符串的回文半径数组radius为[1,2,1,2,1,2,5,2,1,4,1,2,1,2,1,2,1]

### 2、最右回文右边界R

一个位置最右回文右边界指的是这个位置及之前的位置的回文子串，所到达的最右边的地方。R的初始值为-1，当指针p从新字符串的第一个字符开始向右遍历时，R的值依次变更为：0,2,2,6,6，...

### 3、最右回文右边界的对称中心C

即当R=0时，C=0，R=2，C=1，R=2，C=1，R=6，C=3

## 三、Manacher算法的流程

首先分为两种情况：

1. 下一个要移动的位置在最右回文右边界R的右边
   
   如：R=-1时，p的下一个移动位置为p=0，p=0在R=-1右边；p=0时，此时的R=0，p的下一个移动位置p=1，也在R=0右边。

2. 下一个要移动的位置就是最右回文右边界或者在最右回文右边界R的左边
   
   1. 下一个要移动的位置p1**不在**最右回文右边界R的右边，且cL<pL。
      
      p2是p1以C为对称中心的对称点；
      
      pL是以p2为对称中心的回文子串的左边界；
      
      cL是以C为对称中心的回文子串的最边界。
      
      这种情况下p1的回文半径就是p2的回文半径radius[p2]。<img src="https://upload-images.jianshu.io/upload_images/12738509-ecbf2b8587300ac1" title="" alt="" data-align="center">
   
   2. 下一个要移动的位置p1**不在**最右回文右边界R的右边，且cL>pL。
      
      p2是p1以C为对称中心的对称点；
      
      pL是以p2为对称中心的回文子串的左边界；
      
      cL是以C为对称中心的回文子串的最边界。
      
      这种情况下p1的回文半径就是p1到R的距离R-p1+1。
      
      <img src="https://upload-images.jianshu.io/upload_images/12738509-1631cdae7127f45f?imageMogr2/auto-orient/strip|imageView2/2/w/426/format/webp" title="" alt="" data-align="center">
   
   3. 下一个要移动的位置p1**不在**最右回文右边界R的右边，且cL=pL。
      
      p2是p1以C为对称中心的对称点；
      
      pL是以p2为对称中心的回文子串的左边界；
      
      cL是以C为对称中心的回文子串的最边界。
      
      这种情况下p1的回文半径的就还要继续往外扩，但是只需要从R开始往外扩就可以了，扩了之后更新R和C。
      
      <img src="https://upload-images.jianshu.io/upload_images/12738509-b3694bc666896313?imageMogr2/auto-orient/strip|imageView2/2/w/296/format/webp" title="" alt="" data-align="center">

## 四、Manacher时间复杂度分析

从上面的分析中，可以看出，第二种情况的1、2求某个位置的回文半径的时间复杂度是O(1)，对于第一种情况和第二种情况的3，R是不断的向外扩的，不会往回退，而且寻找回文半径时，R之内的位置是不进行个判断的，所以对于整个字符串而言，R的移动是从字符串的起点开始到终点的，时间复杂度为O(n)，所以整个算法的时间复杂度是O(n)。

## 五、代码实现

```java
class Manacher{
    public static void main(String[] args){
        String str = "abcdcbafabcdck";
        System.out.println(manacher(str));
    }

    public static char[] manacherString(String str){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); ++i){
            stringBuilder.append("#");
            stringBuilder.append("#");
        }
        stringBuilder.append("#");
        return stringBuilder.toString().toCharArray();
    }

    public static int manacher(String str){
        if (str == null || str.length() < 1){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] radius = new int[charArr.length];
        int R = -1;
        int C = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < radius.length; ++i){
            raius[i] = R > i ? Math.min(radius[2*C-i],R-i+1):1;
            while (i+radius[i] < charArr.length && i - radius[i] > -1){
                if (charArr[i-radius[i]] == charArr[i+radius[i]]){
                    radius[i]++;
                } else {
                    break;
                }
            }
            if (i + radius[i] > R){
                R = i + radius[i] - 1;
                C = i;
            }
            max = Math.max(max,radius[i]);
        }
        return max-1;
    }
}
```

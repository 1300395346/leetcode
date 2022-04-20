package 位运算;

import java.util.Arrays;

public class Offer003 {
    public static void main(String[] args) {
        Offer003 offer003 = new Offer003();
        int[] ans = offer003.countBits(5);
        System.out.println(Arrays.toString(ans));
    }
/**
 * 利用了java内置函数
 */
//    public int[] countBits(int n) {
//        int[] result = new int[n+1];
//        for (int i = 0; i<=n; i++){
//            result[i] = Integer.bitCount(i);
//        }
//        return result;
//    }

    /**
     * 时间复杂度O(n*sizeof(n))
     */
//    public int[] countBits(int n) {
//        int[] result = new int[n+1];
//        result[0] = 0;
//        for (int i = 1; i <= n; i++){
//            int temp = 0;
//            String binary = Integer.toBinaryString(i);
//            int j = binary.length()-1;
//            while (j >= 0){
//                if (binary.charAt(j--)-'0'==1){
//                    temp++;
//                }
//            }
//            result[i] = temp;
//        }
//
//        return result;
//    }
/**
 * Integer.bitCount()的java源码实现
 */
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

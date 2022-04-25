package 字符串;

import java.util.Arrays;

public class Offer014 {
    public static void main(String[] args) {
        Offer014 offer014 = new Offer014();
        boolean flag = offer014.checkInclusion("abc","cccbbbaaaaaaa");
        System.out.println(flag);
    }
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[] n1 = new int[26];
        int[] n2 = new int[26];
        if (len1 > len2){
            return false;
        }
        for (int i = 0; i < len1; ++i){
            ++n1[s1.charAt(i)-'a'];
            ++n2[s2.charAt(i)-'a'];
        }
        if (Arrays.equals(n1,n2)){
            return true;
        }
        for (int i = len1; i < len2; ++i){
            ++n2[s2.charAt(i)-'a'];
            --n2[s2.charAt(i-len1)-'a'];
            if (Arrays.equals(n1,n2)){
                return true;
            }
        }
        return false;
    }
}

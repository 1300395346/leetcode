package 字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer015 {
    public static void main(String[] args) {
        Offer015 offer015 = new Offer015();
        List<Integer> ans = offer015.findAnagrams("abab","ab");
        System.out.println(ans);
    }

    public List<Integer> findAnagrams(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        int[] n1 = new int[26];
        int[] n2 = new int[26];
        List<Integer> ans = new ArrayList<>();
        if (len2 > len1){
            return ans;
        }
        for (int i = 0; i < len2; ++i){
            ++n1[s.charAt(i) - 'a'];
            ++n2[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(n1,n2)){
            ans.add(0);
        }
        for (int i = len2; i < len1; ++i){
            ++n1[s.charAt(i) - 'a'];
            --n1[s.charAt(i-len2) - 'a'];
            if (Arrays.equals(n1,n2)){
                ans.add(i-len2+1);
            }
        }
        return ans;
    }
}

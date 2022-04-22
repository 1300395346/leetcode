package 数组;

import java.util.HashSet;
import java.util.Set;

public class Offer016 {
    public static void main(String[] args) {
        Offer016 offer016 = new Offer016();
        int ans = offer016.lengthOfLongestSubstring("bbbbb");
        System.out.println(ans);
    }

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int right = -1;
        int n = s.length();
        for (int left = 0; left < n; ++left){
            if (left !=0){
                set.remove(s.charAt(left-1));
            }
            while (n-left >= ans && right+1 < n && !set.contains(s.charAt(right+1))){
                set.add(s.charAt(right+1));
                ++right;
                ans = Math.max(ans,right-left+1);
            }
        }
        return ans;
    }
}

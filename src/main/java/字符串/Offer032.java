package 字符串;

import java.util.Arrays;

public class Offer032 {
    public static void main(String[] args) {
        Offer032 offer032 = new Offer032();
        System.out.println(offer032.isAnagram("rat", "car"));
    }

    public boolean isAnagram(String s, String t) {
        int[] sNum = new int[26];
        int[] tNum = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            sNum[s.charAt(i) - 'a']++;
            tNum[t.charAt(i) - 'a']++;
        }
        return Arrays.equals(sNum, tNum) && !s.equals(t);
    }
}

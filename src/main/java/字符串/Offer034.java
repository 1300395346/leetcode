package 字符串;

import java.util.HashMap;

public class Offer034 {
    public static void main(String[] args) {
        Offer034 offer034 = new Offer034();
        System.out.println(offer034.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    }

    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; ++i) {
            map.put(order.charAt(i), i + 1);
        }
        int wordLen = words.length;
        for (int i = 1; i < wordLen; ++i) {
            if (compare(words[i - 1], words[i], map) > 0) {
                return false;
            }
        }
        return true;
    }

    public int compare(String word1, String word2, HashMap<Character, Integer> map) {
        int len1 = word1.length();
        int len2 = word2.length();
        int len = Math.min(len1, len2);
        for (int i = 0; i < len; ++i) {
            if (!map.get(word1.charAt(i)).equals(map.get(word2.charAt(i)))) {
                return map.get(word1.charAt(i)) - map.get(word2.charAt(i));
            }
        }
        return len1 - len2;
    }
}

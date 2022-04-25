package 字符串;

import java.util.HashMap;
import java.util.Map;

public class Offer017 {
    public static void main(String[] args) {
        Offer017 offer017 = new Offer017();
        String ans = offer017.minWindows("a","a");
        System.out.println(ans);
    }

    public String minWindows(String s, String t){
        int n = s.length();
        int m = t.length();
        if (n < m){
            return "";
        }
        int left = 0;
        int right = -1;
        int ansLeft = -1;
        int ansRight = -1;
        HashMap<Character,Integer> map = new HashMap<>();
        HashMap<Character,Integer> check = new HashMap<>();
        for (int i = 0; i < m; ++i){
            int value = map.getOrDefault(t.charAt(i),0);
            map.put(t.charAt(i),value+1);
        }
        int len = Integer.MAX_VALUE;
        while (right < n){
            ++right;
            if (right<n && map.containsKey(s.charAt(right))){
                check.put(s.charAt(right),check.getOrDefault(s.charAt(right),0)+1);
            }
            System.out.println(left+" "+right);
            while (check(map,check)&&left<=right){
                if (right - left + 1 < len){
                    len = right - left + 1;
                    ansLeft = left;
                    ansRight = len+left;
                }
                if (map.containsKey(s.charAt(left))){
                    check.put(s.charAt(left),check.get(s.charAt(left))-1);
                }
                ++left;
            }
        }
        System.out.println(ansLeft+"  "+ansRight);
        return ansRight == -1?"":s.substring(ansLeft,ansRight);
    }
    public boolean check(HashMap<Character,Integer> map, HashMap<Character,Integer> map1){
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (map1.getOrDefault(key, 0) < value) {
                return false;
            }
        }
        return true;
    }
}

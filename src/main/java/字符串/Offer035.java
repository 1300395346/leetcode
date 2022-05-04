package 字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer035 {
    public static void main(String[] args) {
        Offer035 offer035 = new Offer035();
        List<String> list = new ArrayList<>();
        list.add("23:59");
        list.add("00:00");
        list.add("00:00");
        int ans = offer035.findMinDifference(list);
        System.out.println(ans);
    }

    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        int[] time = new int[len];
        for (int i = 0; i < len; ++i) {
            String s = timePoints.get(i);
            String[] temp = s.split(":");
            time[i] = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
        }
        Arrays.sort(time);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < len; ++i) {
            min = Math.min(min, time[i] - time[i - 1]);
        }
        return Math.min(min, time[0] + 1440 - time[len - 1]);
    }
}

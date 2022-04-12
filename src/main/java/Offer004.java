import java.util.HashMap;
import java.util.Map;

public class Offer004 {
    public static void main(String[] args) {
        Offer004 offer004 = new Offer004();
        int[] test = {1,1,1,2,2,2,3,3,3,5,6,6,6,8,8,8,0,0,0};
        int ans = offer004.singleNumber(test);
        System.out.println(ans);
    }

    public int singleNumber(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int ans = 0;
        for (Map.Entry<Integer,Integer> maps:map.entrySet()){
            int num = maps.getKey(), index = maps.getValue();
            if (index == 1){
                ans = num;
                break;
            }
        }
        return ans;
    }
}

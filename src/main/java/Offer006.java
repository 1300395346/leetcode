import java.util.Arrays;
import java.util.HashMap;

public class Offer006 {
    public static void main(String[] args) {
        Offer006 offer006 = new Offer006();
        int[] numbers = {-1000,-501,-754,2,4,6,8,10,12,16,18,100,754,1000};
        int target = 756;
        int[] ans = offer006.twoSum(numbers,target);
        System.out.println(Arrays.toString(ans));
    }
    //双指针法，时间复杂度O(n)，空间复杂度O(1)
    public int[] twoSum(int[] numbers, int target){
        int i = 0;//头部指针
        int j = numbers.length-1;//尾部指针
        int[] result = new int[2];
        while (i < j){//当头部指针等于尾部指针时，已经将数组遍历一遍了，答案应该已经得到
            int temp = numbers[i] + numbers[j];
            if (temp < target){
                i++;
            } else if (temp > target){
                j--;
            } else {
                result[0] = i;
                result[1] = j;
                break;
            }
        }
        return result;
    }


    //牺牲空间换取时间，时间复杂度O(n)，空间复杂度O(n)
//    public int[] twoSum(int[] numbers, int target) {
//        HashMap<Integer,Integer> map = new HashMap<>();
//        int[] result = new int[2];
//        for (int i = 0; i < numbers.length; i++){
//            map.put(numbers[i],i);
//        }
//        for (int i = 0; i < numbers.length; i++){
//            int temp = numbers[i];
//            int flag = target - temp;
//            if (map.containsKey(flag)){
//                result[0] = i;
//                result[1] = map.get(flag);
//                break;
//            }
//        }
//        return result;
//    }
}

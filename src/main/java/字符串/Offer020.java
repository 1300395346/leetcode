package 字符串;

public class Offer020 {
    public static void main(String[] args) {
        Offer020 offer020 = new Offer020();
        int ans = offer020.countSubstrings("aaa");
        System.out.println(ans);
    }

    public int countSubstrings(String s){
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < 2*len-1; ++i){
            int left = i/2;
            int right = i/2 + (i%2);
            while (left >= 0 && right < len && s.charAt(left)==s.charAt(right)){
                --left;
                ++right;
                ++ans;
            }
        }
        return ans;
    }

//    public int countSubstrings(String s) {
//        int len = s.length();
//        int ans = 0;
//        for (int i = 0; i < len; ++i){
//            int left = i;
//            int right = i;
//            while (left<=right&&left>=0&&right<len&&s.charAt(left)==s.charAt(right)){
//                --left;
//                ++right;
//                ++ans;
//            }
//        }
//        for (int i = 0; i < len-1; ++i){
//            int left = i;
//            int right = i+1;
//            while (left<=right&&left>=0&&right<len&&s.charAt(left)==s.charAt(right)){
//                --left;
//                ++right;
//                ++ans;
//            }
//        }
//        return ans;
//    }
}

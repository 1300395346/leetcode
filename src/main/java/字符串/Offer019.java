package 字符串;

public class Offer019 {
    public static void main(String[] args) {
        Offer019 offer019 = new Offer019();
        boolean flag = offer019.validPalindrome("cupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupucu");
        System.out.println(flag);
    }

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while (left < right){
            char c1 = s.charAt(left);
            char c2 = s.charAt(right);
            if (c1 == c2){
                ++left;
                --right;
            } else {
                return isPalindrome(s,left,right-1)||isPalindrome(s,left+1,right);
            }
        }
        return true;
    }
    public boolean isPalindrome(String s, int start, int end){
        for (int i = start, j = end; i < j; ++i,--j){
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            if(c1 != c2){
                return false;
            }
        }
        return true;
    }
}

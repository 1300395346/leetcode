package 字符串;

public class Offer018 {
    public static void main(String[] args) {
        Offer018 offer018 = new Offer018();
        boolean flag= offer018.isPalindrome("race a car");
        System.out.println(flag);
    }

    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); ++i){
            if(Character.isLetterOrDigit(s.charAt(i))){
                stringBuilder.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }
}

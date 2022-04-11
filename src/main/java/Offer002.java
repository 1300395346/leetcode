public class Offer002 {
    public static void main(String[] args) {
        Offer002 offer002 = new Offer002();
        String ans = offer002.addBinary("1010","1011");
        System.out.println(ans);
    }

    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        while (i >= 0 || j >= 0){
            int A = i >= 0 ? a.charAt(i--) - '0' : 0;
            int B = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = A + B + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum>=2 ? sum - 2 : sum;
            result.append(sum);
        }
        if(carry == 1){
            result.append(carry);
        }
        return result.reverse().toString();
    }
}

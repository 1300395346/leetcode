public class Offer005 {
    public static void main(String[] args) {
        Offer005 offer005 = new Offer005();
        String[] words = {"a","ab","abc","d","cd","bcd","abcd"};
        int ans = offer005.maxProduct(words);
        System.out.println(ans);
    }

    public int maxProduct(String[] words){
        int len = words.length;
        int[] binary = new int[len];
        for (int i =0; i < len; i++){
            for (int j = 0; j < words[i].length(); j++){
                binary[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int ans = 0;

        for (int i = 0; i < len - 1; i++){
            for (int j = i + 1; j < len; j++){
                if ((binary[i] & binary[j]) == 0){
                    ans = Math.max(ans,words[i].length()*words[j].length());
                }
            }
        }
        return ans;
    }

    /**
     * 超出时间限制
     */
//    public int maxProduct(String[] words) {
//        int len = words.length;
//        int ans = 0;
//        for (int i = 0; i < len-1; i++){
//            for (int j = i+1; j < len; j++){
//                int temp = judge(words[i],words[j]);
//                if (ans<temp){
//                    ans = temp;
//                }
//            }
//        }
//        return ans;
//    }
//
//    public int judge(String a, String b){
//        int ans = 0;
//        int i = a.length();
//        int j = b.length();
//        boolean flag = true;
//        for (int m = 0; m < i; m++){
//            for (int n = 0; n < j; n++){
//                Character x = a.charAt(m);
//                Character y = b.charAt(n);
//                if (x.equals(y)){
//                    flag = false;
//                }
//            }
//        }
//        if (flag){
//            ans = i * j;
//        }
//        return ans;
//    }
}

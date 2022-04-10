public class Offer001 {
    public static void main(String[] args) {
        Offer001 offer001 = new Offer001();
        int a = offer001.divide(1, 3);
        System.out.println("64/3="+a);
    }


//超时
//    public int divide(int a, int b) {
//        if(a==0) return 0;
//        if(b==1) return a;
//        if(a==-2147483648&&b==-1) return 2147483647;
//        boolean flag = false;
//        if((a<0&&b>0)||(a>0&&b<0)) flag = true;
//        a = Math.abs(a);
//        b = Math.abs(b);
//        if(a<b)return 0;
//        int i = 1;
//        int c = b;
//        while (c<a){
//            c+=b;
//            i++;
//            if (c>a){
//                i--;
//            }
//        }
//        return flag?-i:i;
//    }


//答案出错
//    public int divide(int a, int b){
//        if (a == 0 || b==-2147483648) return 0;
//        if (b == 1) return a;
//        if (a == -2147483648 && b == -1) return 2147483647;
//        int flag = 2;
//        //将正数转换成负数，防止溢出
//        if (a > 0) {
//            a = -a;
//            flag--;
//        }
//        if (b > 0){
//            b = -b;
//            flag--;
//        }
//        if (a < b) return 0;
//        if (a == b) return flag==1?1:-1;
//        //获取除数右移的最大位数
//        int i = getMaxShift(a,b);
//        int result = (1 << i);
//        int j = a - (b << i);
//        //求被除数与除数右移后的差，当差小于除数时，说明求得了商
//        while (j < b){
//            if (j - (b << i) > 0){
//                i--;
//            }else {
//                result += (1 << i);
//                j -= (b << i);
//                i--;
//            }
//        }
//        return flag==1?result:-result;
//    }

//    private int getMaxShift(int a, int b) {
//        int i = 0;
//        while ((b << i) > a &&(b << i) != Integer.MAX_VALUE){
//            i++;
//        }
//        return i-1;
//    }

    public int divide(int a,int b){
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        if (a == 0 || b == 1){
            return a;
        }else if (b == -1){
            return -a;
        }

        int flag = 2;
        if (a > 0){
            a = - a;
            flag--;
        }
        if (b > 0){
            b = -b;
            flag--;
        }
        int result = divideCore(a,b);
        return flag==1?-result:result;
    }

    public int divideCore(int a, int b){
        if (a == b) return 1;
        int shift = getMaxShift(a,b);
        int result = 0;
        while (a <= b){
            while (a > (b << shift)){
                shift--;
            }
            result +=(1 << shift);
            a -= (b << shift);
        }
        return result;
    }

    public int getMaxShift(int a,int b){
        int shift = 0;
        int temp = b;
        int BOUND = (Integer.MIN_VALUE) >> 2;
        while (temp > a && temp >= BOUND){
            temp <<= 1;
            shift++;
        }
        System.out.println("shift:"+shift);
        return shift;
    }
}

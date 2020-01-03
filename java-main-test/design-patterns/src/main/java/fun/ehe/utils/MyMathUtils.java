package fun.ehe.utils;

import java.util.UUID;

public class MyMathUtils {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            T.t(null, getRandomNum(6));
        }
    }

    public static Integer getPrimeNum(Integer min) {
        Integer primeNum = null;
        for (int divisor = min + 1;; divisor++) {
            boolean flag = true;
            for (int factor = 2; factor < divisor; factor++) {
                if (divisor % factor == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                primeNum = divisor;
                break;
            }
        }
        return primeNum;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getRandomNum(int length) {
        String temp = String.valueOf(Math.random());
        return temp.substring(2, length + 2);
    }

}

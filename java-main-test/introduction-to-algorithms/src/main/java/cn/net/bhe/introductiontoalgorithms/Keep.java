package cn.net.bhe.introductiontoalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Keep {
    
    public static void main(String[] args) {
        System.out.println(trailingZeroes(10));
    }
    
    public static int trailingZeroes(int n) {
        int cnt5 = 0;
        int cnt2 = 0;
        while (n > 1) {
            cnt5 += n / 5;
            cnt2 += n / 2;
            n--;
        }
        return Math.min(cnt2, cnt5);
    }
    
}


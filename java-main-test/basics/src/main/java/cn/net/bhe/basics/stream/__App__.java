package cn.net.bhe.basics.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class __App__ {
    
    /*
     * 查找
     * 
     * int array是否存在某个value
     */
    @Test
    public void anyMatch() {
        int[] arr = {1, 2, 8};
        boolean result = Arrays.stream(arr).anyMatch(i -> i == 8);
        System.out.println(result);
    }
    
    /*
     * 转换
     * 
     * 嵌套List展开为一个List
     */
    @Test
    public void nestedList() {
        List<String> in1 = Arrays.asList(new String[] { "f", "z", "k" });
        List<String> in2 = Arrays.asList(new String[] { "a", "r", "f" });
        List<List<String>> outer = new ArrayList<>();
        outer.add(in1);
        outer.add(in2);
        System.out.println(outer.stream().flatMap(List::stream).collect(Collectors.toList()));
    }
    
    /*
     * 转换
     * 
     * 对象List转字段List
     */
    @Test
    public void convertToListField() {
        class Keep {
            Integer id;
            Keep(int i) { id = i;}
            int getId() { return id; }
        }
        List<Keep> listobj = Arrays.asList(new Keep[] { new Keep(0), new Keep(2), new Keep(2) });
        List<Integer> listint = listobj.stream().map(e -> e.getId()).collect(Collectors.toList());
        System.out.println(listint);
    }
    
    /*
     * 查找
     * 
     * 搜索(list)
     */
    @Test
    public void filter() {
        class Keep {
            Integer id;
            Keep(int i) { id = i;}
            int getId() { return id; }
        }
        List<Keep> listobj = Arrays.asList(new Keep[] { new Keep(0), new Keep(2), new Keep(2) });
        Keep search = listobj.stream()
                .filter(e -> e.getId() == 2)
                .findAny().orElse(null); // 只取第一个
        System.out.println(search);
    }
    
}
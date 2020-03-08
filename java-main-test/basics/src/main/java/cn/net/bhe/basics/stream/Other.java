package cn.net.bhe.basics.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Other {

    /**
     * 嵌套List展开为一个List
     */
    public static void nestedList
    (String[] args) {
        List<String> in1 = Arrays.asList(new String[] { "f", "z", "k" });
        List<String> in2 = Arrays.asList(new String[] { "a", "r", "f" });
        List<List<String>> outer = new ArrayList<>();
        outer.add(in1);
        outer.add(in2);
        System.out.println(
                outer.stream().flatMap(List::stream).collect(Collectors.toList()));
    }
    
    /**
     * 对象List转字段List
     */
    public static void convertToListField
    (String[] args) {
        List<Keep> listobj = Arrays.asList(new Keep[] { new Keep(0), new Keep(2), new Keep(2) });
        List<Integer> listint = listobj.stream().map(e -> e.getId()).collect(Collectors.toList());
        System.out.println(listint);
    }
    
    /**
     * 搜索
     */
    public static void main
    (String[] args) {
        List<Keep> listobj = Arrays.asList(new Keep[] { new Keep(0), new Keep(2), new Keep(2) });
        Keep search = listobj.stream()
                .filter(e -> e.getId() == 2)
                .findAny().orElse(null); // 只取第一个
        System.out.println(search);
    }
    
}

class Keep {
    Integer id;
   
    Keep(int i) { id = i;}
    int getId() { return id; }
}

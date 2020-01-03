package cn.net.bhe.basics.base.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

    public static void main
    (String[] args) {
        List<String> list = Arrays.asList(new String[] {"a", "c", "b", "a"});
        System.out.println(
                list.stream()
                .sorted((ele1, ele2) -> ele1.compareTo(ele2))
                .collect(Collectors.toList()));
    }

}

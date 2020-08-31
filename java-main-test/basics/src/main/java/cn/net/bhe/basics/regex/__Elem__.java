package cn.net.bhe.basics.regex;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class __Elem__ {

    @Test
    public void group() {
        Pattern p = Pattern.compile("(\\d+,)(\\d+)"); // 一对括号一个组
        String s = "123,456-34,345";
        Matcher m = p.matcher(s);
        while (m.find()) {
            pln(m.group()); // 所有组
            pln(m.group(1)); // 第一组
            pln(m.group(2)); // 第二组
            pln();
        }
        pln(m.groupCount()); // 组数
    }

}

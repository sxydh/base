package cn.net.bhe.basics.nativ;
import static cn.net.bhe.utils.main.PrintUtils.*;

/**
 * ▪ 必须实现Cloneable接口
 * ▪ Object的protected native Object clone()只支持浅克隆，即只支持值克隆，不支持引用对象的克隆
 */
public class CloneTest {
    
    public static void main(String[] args) {
        Source source = new Source();
        pln(source == source.clone());
    }
    
    public static class Source implements Cloneable {
        @Override
        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return clone;
        }
    }
    
}

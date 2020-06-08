package cn.net.bhe.basics.syntax.enu;

/**
 * ▪ 因为所有的枚举已经继承了java.lang.Enum，所以不能再继承其它类，但是可以实现其它接口
 * ▪ 枚举是单例的，这意味着使用操作符"=="来替换equals()成为了可能
 * ▪ 不能使用关键字"new"来构建一个枚举
 * 
 * @see <a href=
 *      "https://howtodoinjava.com/java/enum/guide-for-understanding-enum-in-java/">reference</a>
 * @author sxydh
 */
public class App {

    public static void main(String[] args) {
        System.out.println(Direction.EAST.ordinal()); // 0
    }

}

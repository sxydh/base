package cn.net.bhe.basics.keep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.util.StringUtils;

public class Test {

    static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        StringUtils.equals("", "");
    }

    public static Node reverse(Node x) {
        if (x.next == null) {
            return x;
        }

        Node last = reverse(x.next);
        last.next = x;
        x.next = null;
        return x;
    }

}

class Node {
    String str;
    Node next;

    public Node(String str) {
        this.str = str;
    }
    
}

package cn.net.bhe.basics.datastructures;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * 双向链表
 * 
 * 特点：插入、删除、顺序访问效率高，但是随机访问效率低
 */
public class LinkedListTest {

    /**
     * 几种访问效率的测试
     */
    @Test
    public void accessPerformance() {
        LinkedList<Integer> llist = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            llist.addLast(i);
        }
        long time = System.currentTimeMillis();
        for (Iterator<Integer> iter = llist.iterator(); iter.hasNext();) {
            iter.next();
        }
        System.out.printf("%-30s%10s\r\n", "linkedList.iterator.next()", time - (time = System.currentTimeMillis()));
        
        for (int i = 0; i < llist.size(); i++) {
            llist.get(i);
        }
        System.out.printf("%-30s%10s\r\n", "linkedList.get(i)",  time - (time = System.currentTimeMillis()));
        
        llist.forEach((i) -> {});
        System.out.printf("%-30s%10s\r\n", "linkedList.forEach", time - (time = System.currentTimeMillis()));
        
        while (!llist.isEmpty()) {
            llist.poll();
        }
        System.out.printf("%-30s%10s\r\n", "linkedList.poll", time - (time = System.currentTimeMillis()));
    }

}

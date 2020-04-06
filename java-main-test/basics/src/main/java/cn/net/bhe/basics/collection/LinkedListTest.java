package cn.net.bhe.basics.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {

    /**
     * 随机访问效率最低
     */
    public static void 
//    accessPerformance() 
    main(String[] args)
    {
        LinkedList<Integer> llist = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            llist.addLast(i);
        }
        long time = System.currentTimeMillis();
        // iterator遍历
        for (Iterator<Integer> iter = llist.iterator(); iter.hasNext();) {
            iter.next();
        }
        System.out.printf("%10s\r\n", time - (time = System.currentTimeMillis()));
        
        // 随机访问遍历
        for (int i = 0; i < llist.size(); i++) {
            llist.get(i);
        }
        System.out.printf("%10s\r\n", time - (time = System.currentTimeMillis()));
        
        // forEach遍历
        llist.forEach((i) -> {});
        System.out.printf("%10s\r\n", time - (time = System.currentTimeMillis()));
        
        // poll遍历
        while (!llist.isEmpty()) {
            llist.poll();
        }
        System.out.printf("%10s\r\n", time - (time = System.currentTimeMillis()));
    }

}

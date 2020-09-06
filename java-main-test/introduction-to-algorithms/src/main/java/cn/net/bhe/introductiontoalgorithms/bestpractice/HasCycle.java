package cn.net.bhe.introductiontoalgorithms.bestpractice;

import org.junit.jupiter.api.Test;

/**
 * 判断链表是否包含环
 */
public class HasCycle {
    
    /*
     * 思路：利用快慢指针(快指针只能快1)
     * 
     * 证明：相对速度为1，若有环一定会重合。
     */
    @Test
    public void hasCycle() {
        
    }
    
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (true) {
            if (slow == fast) return true;
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

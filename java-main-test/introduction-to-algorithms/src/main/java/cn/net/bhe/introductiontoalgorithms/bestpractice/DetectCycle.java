package cn.net.bhe.introductiontoalgorithms.bestpractice;

import org.junit.jupiter.api.Test;

/**
 * 找到链表中包含的环的入口点
 */
public class DetectCycle {
    
    /*
     * 思路：链表起点到环入口点的距离a，慢指针slow和快指针fast相遇点到入口点的正向距离为b，环长度为c，
     * 则有a = (n - 1) * c + b，
     * 此时若链表起点和相遇点按相同速度推进，则一定在入口点相遇
     * 
     * 证明a = (n - 1) * c + b：
     * 首先慢指针slow在第二次到达入口点前一定和快指针fast相遇，
     * 令慢指针行驶距离为s = a + c - b，
     * 令快指针行驶距离为f = a + c - b + n * c，
     * 又f = 2 * s，
     * 所以a = (n - 1) * c + b
     */
    @Test
    public void detectCycle() {
        
    }
    
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (true) {
            if (slow == fast) break;
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next; // 注意相遇点到入口点的距离不包括相遇点
        while (true) {
            if (head == slow) break;
            head = head.next;
            slow = slow.next;
        }
        return head;
    }

}

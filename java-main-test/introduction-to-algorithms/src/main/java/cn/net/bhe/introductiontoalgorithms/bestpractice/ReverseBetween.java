package cn.net.bhe.introductiontoalgorithms.bestpractice;

import static cn.net.bhe.utils.main.PrintUtils.pln;

import org.junit.jupiter.api.Test;

import cn.net.bhe.utils.main.ListNode;

/**
 * 反转链表的指定区间
 */
public class ReverseBetween {

    @Test
    public void reverseBetween() {
        ListNode head = ListNode.toLNode(4, 1, 2, 10, 8, 9, 7);
        pln(head);
        ListNode ret = reverseBetween(head, 1, 2);
        pln(ret);
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n || head.next == null)
            return head;
        int i = 1;
        ListNode pre = null;
        ListNode c = head;
        while (i < m) {
            pre = c;
            c = c.next;
            i++;
        }
        ListNode left = pre;
        ListNode mNode = c;

        pre = null;
        while (i <= n) {
            ListNode temp = c.next;
            c.next = pre;
            pre = c;
            c = temp;
            i++;
        }
        ListNode nNode = pre;
        ListNode right = c;

        if (left != null) {
            left.next = nNode;
        } else {
            head = nNode;
        }
        mNode.next = right;
        return head;
    }

}

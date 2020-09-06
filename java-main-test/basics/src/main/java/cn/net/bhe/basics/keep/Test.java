package cn.net.bhe.basics.keep;

import static cn.net.bhe.utils.main.PrintUtils.*;

import cn.net.bhe.utils.main.ListNode;

public class Test {

    public static void main(String[] args) {
    }

    @org.junit.jupiter.api.Test
    public void test() {
        pln();
        
        pln(addInList(ListNode.toLNode(1), ListNode.toLNode(1,0,2)));
    }
    
    public ListNode addInList (ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        ListNode r1 = reverse(head1);
        ListNode r2 = reverse(head2);
        ListNode ret = null;
        int carry = 0;
        while (r1 != null || r2 != null || carry > 0) {
            if (r1 != null) {
                carry += r1.val;
                r1 = r1.next;
            }
            if (r2 != null) {
                carry += r2.val;
                r2 = r2.next;
            }
            ListNode n = new ListNode(carry % 10);
            n.next = ret;
            ret = n;
            carry /= 10;
        }
        return ret;
    }
    
    ListNode reverse(ListNode head) {
        if (head == null) return head;
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

}
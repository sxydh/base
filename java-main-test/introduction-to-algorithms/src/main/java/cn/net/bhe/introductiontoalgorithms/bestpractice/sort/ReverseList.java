package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

/**
 * 反转链表
 */
public class ReverseList {

    public static void main(String[] args) {
        
    }
    
    /*
     * 递归方法
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next); // p是反转后的头部
        head.next.next = head;
        head.next = null;
        return p;
    }
    
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    
}

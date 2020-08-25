package cn.net.bhe.introductiontoalgorithms.bestpractice;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 
 * @author Administrator
 *
 */
public class AddTwoNumbers {
    
    public static void 
    main (String[] args)
    {
        ListNode l1 = new ListNode(9).setNext(new ListNode(9).setNext(new ListNode(1)));
        ListNode l2 = new ListNode(1);
        
        ListNode result = new ListNode(0);
        ListNode cursor = result;
        int carry = 0;
        int curr;
        while (true) {
            if (l1 == null && l2 == null) break;
            curr = ((l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0)) + cursor.val;
            carry = curr / 10;
            curr = curr % 10;
            cursor.val = curr;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            if (l1 != null || l2 != null || carry > 0) {
                cursor.next = new ListNode(carry);
                cursor = cursor.next;
            }
        }
        System.out.println(result);
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
    ListNode setNext(ListNode next) {
        this.next = next;
        return this;
    }
    
    @Override
    public String toString() {
        String str = "";
        ListNode curr = this;
        while(true) {
            str += new Integer(curr.val);
            curr = curr.next;
            if (curr == null) return str;
        }
    }
}

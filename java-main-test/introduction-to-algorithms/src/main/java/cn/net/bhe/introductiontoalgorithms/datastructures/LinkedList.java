package cn.net.bhe.introductiontoalgorithms.datastructures;

public class LinkedList {
    
    public static void 
    main (String[] args)
//    addTwoNumbers() 
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

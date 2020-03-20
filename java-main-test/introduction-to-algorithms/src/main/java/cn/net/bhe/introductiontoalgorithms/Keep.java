package cn.net.bhe.introductiontoalgorithms;

public class Keep {
    
    public static void main(String[] args) {
        
    }
    
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode result = null;
        if (headA.val != headB.val) {
            result = getIntersectionNode(headA, headB.next);
            if (result == null) {
                result = getIntersectionNode(headB, headA.next);
            }
        }
        return result;
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

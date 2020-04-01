package cn.net.bhe.introductiontoalgorithms.common;
import java.util.HashSet;
import java.util.Set;

/**
 * 相交链表的交点查找
 */
public class IntersectionNode {

    public static void main(String[] args) {
        
    }
    
    /**
     * 哈斯表查找
     */
    public static ListNode hashFind(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
    
    /**
     * 指针相遇法：指针A从链表A头遍历到链表B尾，指针B从链表B头遍历到链表A尾，如果有交点，两者肯定相遇
     */
    public static ListNode cursorFind(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int cntA = 0;
        int cntB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        while (cntA < 2 && cntB < 2) {
            if (curA == curB) return curA;
            if (curA.next == null) {
                cntA++;
                curA = headB;
            } else {
                curA = curA.next;
            }
            if (curB.next == null) {
                cntB++;
                curB = headA;
            } else {
                curB = curB.next;
            }
        }
        return null;
    }
    
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

package cn.net.bhe.introductiontoalgorithms.bestpractice.sort;

/**
 * 链表排序
 */
public class SortListNode {

    public static void main(String[] args) {
        ListNode l = new ListNode(2).setNext(1).setNext(1).setNext(5).setNext(0).head;
        l = sort(l);
        System.out.println(l);
    }
    
    /*
     * 归并排序（分治算法）
     */
    static ListNode sort(ListNode node) {
        ListNode middle = getMiddle(node);
        if (node == middle) {
            if (node.next == null) return node;
            if (node.val > node.next.val) {
                ListNode newHead = node.next;
                newHead.next = node;
                node.next = null;
                return newHead;
            }
            return node;
        }
        ListNode right = middle.next;
        middle.next = null;
        ListNode left = node;
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    static ListNode getMiddle(ListNode node) {
        ListNode slow, fast;
        slow = node;
        fast = node;
        while (slow != null && slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode result = null, resultCur = null, node1Cur = node1, node2Cur = node2;
        while (true) {
            if (node1Cur.val < node2Cur.val) {
                if (result == null) {
                    result = node1Cur;
                    resultCur = result;
                } else {
                    resultCur.next = node1Cur;
                    resultCur = resultCur.next;
                }
                if (node1Cur.next != null) {
                    node1Cur = node1Cur.next;
                } else {
                    resultCur.next = node2Cur;
                    break;
                }
            } else {
                if (result == null) {
                    result = node2Cur;
                    resultCur = result;
                } else {
                    resultCur.next = node2Cur;
                    resultCur = resultCur.next;
                }
                if (node2Cur.next != null) {
                    node2Cur = node2Cur.next;
                } else {
                    resultCur.next = node1Cur;
                    break;
                }
            }
        }
        return result;
    }
    
    static class ListNode {
        ListNode head;
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode setNext(int x) {
            if (this.head == null) { this.head = this; }
            this.next = new ListNode(x);
            this.next.head = this.head;
            return this.next;
        }

    }

}

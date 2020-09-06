package cn.net.bhe.utils.main;

import java.util.ArrayList;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
    
    public ListNode next(ListNode next) {
        this.next = next;
        return next;
    }

    public ListNode next(int val) {
        this.next = new ListNode(val);
        return this.next;
    }
    
    public int size() {
        return toList(this).size();
    }
    
    public ArrayList<ListNode> toList(ListNode lNode) {
        ArrayList<ListNode> list = new ArrayList<ListNode>();
        while (lNode != null) {
            if (list.contains(lNode)) break;
            list.add(lNode);
            lNode = lNode.next;
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (ListNode lNode : toList(this)) {
            ret.append(",").append(lNode.val);
        }
        if (ret.length() > 0) {
            ret.deleteCharAt(0);
        }
        return ret.toString();
    }
    
    public static ListNode toLNode(int... args) {
        ListNode head = null;
        ListNode cur = null;
        for (int i : args) {
            if (head == null) {
                head = new ListNode(i);
                cur = head;
            } else {
                cur = cur.next(i);
            }
        }
        return head;
    }

}

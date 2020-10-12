package com.company;

/**
 * @author MaJin_Buu
 */
public class NodeListAlgorithm {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(4);
        System.out.println(listNode);
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

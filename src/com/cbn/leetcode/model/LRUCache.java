package com.cbn.leetcode.model;

/**
 * 大小为Capacity的链表，两个引用，分别指向第一个和最后一个。
 * 
 * @author boning
 *
 */
public class LRUCache {

	private final int capacity;
	private ListNode head;// 指向队头
	// private ListNode tail;// 指向队尾
	private int count=0;// 当前个数

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		ListNode temp = head;
		if(temp==null)return -1;
		if(temp!=null && temp.key==key)return temp.value;
		while (temp.next != null) {
			if (temp.next.key == key){
				int val=temp.next.value;
				moveToFirst(temp);
				return val;
			}
			temp = temp.next;
		}
		return -1;
	}

	private void moveToFirst(ListNode temp) {
		ListNode cur=temp.next;
		temp.next=cur.next;
		cur.next=head;
		head=cur;
	}

	public void set(int key, int value) {
		if (head == null) {
			head = new ListNode(key, value);
			count++;// =1
			return;
		}
		// 如果链表里面存在,并修改结点
		ListNode temp = head;
		if (head.key == key) {
			head.value = value;
			return;
		}
		while (temp.next != null) {
			if (temp.next.key == key) {
				// 找到为Key的结点，与头结点交换位置
				ListNode curNode = temp.next;
				temp.next = temp.next.next;
				curNode.value = value;
				curNode.next = head;
				head = curNode;
				return;
			}
			temp=temp.next;
		}
		// 先删除第16个节点。
		if (count >= capacity) {
			temp = head;
			int length = count-1;
			for (int i = 1; i < length; i++) {
				temp = temp.next;
			}
			temp.next = null;
			count--;
		}
		ListNode newNode = new ListNode(key, value);
		newNode.next = head;
		head = newNode;
		count++;

		// key在链表中没照到，则插入新结点

	}

	private static class ListNode {
		final int key;
		int value;
		ListNode next;

		public ListNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}
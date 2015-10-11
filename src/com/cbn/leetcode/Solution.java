package com.cbn.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.cbn.leetcode.model.ListNode;
import com.cbn.leetcode.model.TreeNode;

/**
 * Leetcode解答
 * 
 * @author boning
 *
 */
public class Solution {
	/**
	 * #290 Given a pattern and a string str, find if str follows the same
	 * pattern.
	 * 
	 * @param pattern
	 * @param str
	 * @return
	 */
	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		int length = pattern.length();
		if (length != strs.length)
			return false;
		HashMap<Character, String> map = new HashMap<Character, String>();
		HashSet<String> map2 = new HashSet<String>();
		for (int i = 0; i < length; i++) {
			char temp = pattern.charAt(i);
			String tempstr = strs[i];
			if (map.containsKey(temp)) {
				if (!map.get(temp).equals(tempstr)) {
					return false;
				}
			} else {
				map.put(temp, tempstr);
				if (map2.contains(tempstr))
					return false;
				else
					map2.add(tempstr);
			}
		}
		return true;
	}

	/**
	 * #283 Given an array nums, write a function to move all 0's to the end of
	 * it while maintaining the relative order of the non-zero elements. For
	 * example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
	 * should be [1, 3, 12, 0, 0].
	 * 
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {
		int numsOfZero = 0;
		int length = nums.length;
		if (length == 1)
			return;
		for (int i = 0; i < length; i++) {
			if (nums[i] == 0)
				numsOfZero++;
			else {
				if (numsOfZero != 0) {
					nums[i - numsOfZero] = nums[i];
					nums[i] = 0;
				}
			}
		}
		// for(int i=length-numsOfZero;i<length;i++){
		// nums[i]=0;
		// }
	}

	/**
	 * #278 Suppose you have n versions [1, 2, ..., n] and you want to find out
	 * the first bad one, which causes all the following ones to be bad.
	 * 
	 * @param n
	 * @return
	 */
	public int firstBadVersion(int n) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isBadVersion(mid)) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		if (isBadVersion(lo))
			return lo;
		else
			return lo + 1;
	}

	private boolean isBadVersion(int mid) {
		return false;
	}

	/**
	 * #258 Given a non-negative integer num, repeatedly add all its digits
	 * until the result has only one digit.
	 * 
	 * @param num
	 * @return
	 */
	public int addDigits(int num) {
		return (num < 10) ? num : ((num - 10) % 9) + 1;
	}

	/**
	 * #104 Given a binary tree, find its maximum depth.
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		return (root == null) ? 0 : Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
	}

	/**
	 * #237 Write a function to delete a node (except the tail) in a singly
	 * linked list, given only access to that node. Supposed the linked list is
	 * 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the
	 * linked list should become 1 -> 2 -> 4 after calling your function.
	 * 
	 * @param node
	 */
	public void deleteNode(ListNode node) {
		if (node == null)
			return;
		if (node.next != null) {
			node.val = node.next.val;
			node.next = node.next.next;
		}
	}

	/**
	 * #100 Given two binary trees, write a function to check if they are equal
	 * or not. Two binary trees are considered equal if they are structurally
	 * identical and the nodes have the same value.
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val == q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		} else
			return false;
	}

	/**
	 * #191 Write a function that takes an unsigned integer and returns the
	 * number of '1' bits it has (also known as the Hamming weight). For
	 * example, the 32-bit integer ’11' has binary representation
	 * 00000000000000000000000000001011, so the function should return 3.
	 * 
	 * 
	 * @param n
	 * @return
	 */
	public int hammingWeight(int n) {
		int res = 0;
		while (n != 0) {
			res = (n ^ (n - 1)) == 1 ? res + 1 : res;
			// res = (n & 1) == 1 ? res + 1 : res;
			n = n >>> 1;
		}
		return res;
	}

	/**
	 * #217 Given an array of integers, find if the array contains any
	 * duplicates. Your function should return true if any value appears at
	 * least twice in the array, and it should return false if every element is
	 * distinct.
	 * 
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		int length = nums.length;
		if (length <= 1)
			return false;
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < length; i++) {
			set.add(nums[i]);
			if (set.size() != (i + 1))
				return true;
		}
		return false;
	}

	/**
	 * Given a non-negative number represented as an array of digits, plus one
	 * to the number. The digits are stored such that the most significant digit
	 * is at the head of the list.
	 * 
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}

			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;

		return newNumber;
	}

	/**
	 * #226 Invert a binary tree.
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if (null != root)
			invertTree0(root);
		return root;

	}

	private void invertTree0(TreeNode root) {
		if (null != root) {
			invertTree0(root.left);
			invertTree0(root.right);
			TreeNode temp = root.left;
			root.left = root.right;
			root.right = temp;
		} else
			return;
	}

	/**
	 * #169 Majority Element Given an array of size n, find the majority
	 * element. The majority element is the element that appears more than ⌊ n/2
	 * ⌋ times.
	 * 
	 * You may assume that the array is non-empty and the majority element
	 * always exist in the array.
	 * 
	 * @param nums
	 * @return
	 */
	public int majorityElement(int[] nums) {
		int length = nums.length;
		int num = 1, temp = nums[0];
		for (int i = 1; i < length; i++) {
			if (temp == nums[i])
				num++;
			else {
				if (num == 0) {
					num = 1;
					temp = nums[i];
				} else {
					num--;
					if (num == 0)
						temp = nums[i];
				}
			}
		}
		return temp;
	}

	/**
	 * Valid Anagram Given two strings s and t, write a function to determine if
	 * t is an anagram of s.
	 * 
	 * For example, s = "anagram", t = "nagaram", return true. s = "rat", t =
	 * "car", return false.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {
		int lengthS = s.length();
		int lengthT = t.length();
		if (lengthS != lengthT)
			return false;
		int[] a = new int[26];
		for (int i = 0; i < lengthS; i++) {
			int tempa = s.charAt(i) - 97;
			a[tempa]++;
			int tempb = t.charAt(i) - 97;
			a[tempb]--;
		}
		for (int i = 0; i < 26; i++) {
			if (a[i] != 0)
				return false;
		}
		return true;
	}

	/**
	 * #83 Remove Duplicates from Sorted List Given a sorted linked list, delete
	 * all duplicates such that each element appear only once.
	 * 
	 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return
	 * 1->2->3.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			ListNode temp2 = temp;
			while (temp.next != null && temp.val == temp.next.val) {
				temp = temp.next;
			}
			temp2.next = temp.next;
			temp = temp.next;
		}
		return head;
	}

	/**
	 * #206 Reverse Linked List
	 * 
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if (head == null)
			return null;
		ListNode L1 = head, L2, L3;
		L2 = L1.next;
		L1.next = null;
		while (L2 != null) {
			L3 = L2.next;
			L2.next = L1;
			L1 = L2;
			L2 = L3;
		}
		return L1;
	}

	/**
	 * #70 Climbing Stairs
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		int a = 1, b = 2, i = 3;

		while (i <= n) {
			int c = a + b;
			a = b;
			b = c;
			i++;
		}
		return b;
	}

	/**
	 * #202 Happy Number
	 * 
	 * @param n
	 * @return
	 */
	public boolean isHappy(int n) {
		Set<Integer> inLoop = new HashSet<Integer>();
		int squareSum, remain;
		// 出现重复就停止循环
		while (inLoop.add(n)) {
			squareSum = 0;
			// do HappNumber
			while (n > 0) {
				remain = n % 10;
				squareSum += remain * remain;
				n /= 10;
			}
			if (squareSum == 1)
				return true;
			else
				n = squareSum;

		}
		return false;
	}

	/**
	 * #204 Count Primes(质数）
	 * 
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true;
		}
		// Loop's ending condition is i * i < n instead of i < sqrt(n)
		// to avoid repeatedly calling an expensive function sqrt().
		for (int i = 2; i * i < n; i++) {// 循环次数为根号N
			if (!isPrime[i])
				continue;
			for (int j = i * i; j < n; j += i) {// 从i^2开始，i^2+i..i^2+2*i
				isPrime[j] = false;
			}
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime[i])
				count++;
		}
		return count;
	}

	/**
	 * #21 Merge Two Sorted Lists
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		// 递归！！
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	/**
	 * #136 Single Number 通过异或操作
	 * 
	 * @param nums
	 * @return
	 */
	public int singleNumber(int[] nums) {
		int length = nums.length;
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum ^= nums[i];
		}
		return sum;
	}

	/**
	 * #136 Single Number III Given an array of numbers nums, in which exactly
	 * two elements appear only once and all the other elements appear exactly
	 * twice. Find the two elements that appear only once.
	 * 
	 * For example:
	 * 
	 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
	 * 根据a^b的值找出最后一位为1的，即区别a和b的地方，将该数组分为两组
	 * 
	 * @param nums
	 * @return
	 */
	public int[] singleNumber2(int[] nums) {
		int aOXRb = 0;
		int length = nums.length;
		for (int i = 0; i < length; i++) {
			aOXRb ^= nums[i];
		}
		int lastBit = aOXRb & (-aOXRb);// 找到最后一位是1的数字
		int[] res = new int[2];
		for (int j = 0; j < length; j++) {
			if ((nums[j] & lastBit) == 0)
				res[0] ^= nums[j];
			else
				res[1] ^= nums[j];
		}
		return res;
	}
}
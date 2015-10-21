package com.cbn.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cbn.leetcode.model.ListNode;
import com.cbn.leetcode.model.Point;
import com.cbn.leetcode.model.TreeNode;

/**
 * Leetcode解答
 * 
 * @author boning
 *
 */
public class Solution {
	/**
	 * Max Points on a Line 对每个i，初始化一个哈希表，key 为斜率，value
	 * 为该直线上的点数。遍历结束后得到和当前i点共线的点的最大值，再和全局最大值比较，最后就是结果。
	 * 时间复杂度O(n2)，空间复杂度O(n)。
	 * 
	 * 其中有几点要注意的是： 存在坐标一样的点；存在斜率不存在的点（与x轴平行的直线）。
	 * 
	 * @param points
	 * @return
	 */
	public int maxPoints(Point[] points) {
		if (points.length <= 2) {
			return points.length;
		}
		// 斜率
		double k = 0.0;
		int maxPointNum = 0;
		int tempMaxPointNum = 0;
		// 坐标完全相同点的个数
		int samePointNum = 0;
		// 与y轴平行
		int parallelPointNum = 0;
		HashMap<Double, Integer> slopeMap = new HashMap<Double, Integer>();
		for (int i = 0; i < points.length - 1; i++) {
			// 代表起始点，会被累加上
			samePointNum = 1;
			parallelPointNum = 0;
			tempMaxPointNum = 0;
			slopeMap.clear();
			for (int j = i + 1; j < points.length; j++) {
				// 坐标完全相同
				if ((points[i].x == points[j].x) && ((points[i].y == points[j].y))) {
					samePointNum++;
					continue;
				}
				// 与y轴平行
				if (points[i].x == points[j].x) {
					parallelPointNum++;
				} else {
					if (points[i].y == points[j].y) {
						k = 0;
					} else {
						k = ((double) (points[i].y - points[j].y)) / (points[i].x - points[j].x);
					}
					// 斜率不存在
					if (slopeMap.get(k) == null) {
						slopeMap.put(k, new Integer(1));
						if (1 > tempMaxPointNum) {
							tempMaxPointNum = 1;
						}
					} else {
						// 斜率已存在
						int number = slopeMap.get(k);
						number++;
						slopeMap.put(k, new Integer(number));
						if (number > tempMaxPointNum) {
							tempMaxPointNum = number;
						}
					}
				}
			} // end of for

			if (parallelPointNum > 1) {
				if (parallelPointNum > tempMaxPointNum) {
					tempMaxPointNum = parallelPointNum;
				}
			}
			// 加上起始点和具有相同坐标的点
			tempMaxPointNum += samePointNum;
			if (tempMaxPointNum > maxPointNum) {
				maxPointNum = tempMaxPointNum;
			}
		}
		return maxPointNum;
	}

	/**
	 *#190	Reverse Bits
	 * Reverse bits of a given 32 bits unsigned integer.
	 * 
	 * For example, given input 43261596 (represented in binary as
	 * 00000010100101000001111010011100), return 964176192 (represented in
	 * binary as 00111001011110000010100101000000).
	 * 
	 * 
	 * @param n
	 * @return
	 */
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		int res = 0;
		for (int i = 31; i >= 0; i--) {
			// 判断末尾一位是1or0
			if ((n & 1) == 1) {
				res += (1 << (i));
			}
			n >>>= 1;
		}
		return res;
	}

	/**
	 * #137 Single Number II ！！！根据int32位，出现三次，则相加必然是3的倍数。
	 * 
	 * @param nums
	 * @return
	 */
	public int singleNumber3(int[] nums) {
		int res = 0;
		int k = 0;
		while (k < 32) {
			int temp = 0;
			for (int i = 0; i < nums.length; i++) {
				temp += ((nums[i] >> k) & 1);// 每一位所有数加起来，取余数
			}
			if (temp % 3 != 0) {
				res = res | (1 << k);// 每一位或操作
			}
			k++;
		}
		return res;
	}

	/**
	 * 在一个有序的数组查找插入的位置
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (target > nums[mid]) {
				lo = mid + 1;
			} else if (target < nums[mid]) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	/**
	 * 缺失的数字
	 * 
	 * @param nums
	 * @return
	 */
	public int missingNumber(int[] nums) {
		int length = nums.length;
		if (length <= 0)
			return 0;
		long sum = (1 + length) * length / 2;
		for (int i = 0; i < length; i++) {
			sum -= nums[i];
		}
		return (int) sum;
	}

	/**
	 * #141 Linked List Cycle 检测链表是否有循环
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		// 标准解答
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			head = head.next;
			fast = fast.next.next;
			if (head == fast)
				return true;
		}
		return false;

		// 我的解答
		// ListNode pre = new ListNode(0);
		// pre.next = head;
		// ListNode h1, h2;
		// h1 = h2 = pre;
		// while (h2 != null) {
		// h1 = h1.next;
		// h2 = h2.next;
		// if (h2 != null) {
		// h2 = h2.next;
		// if (h2!=null && h2 == h1)
		// return true;
		// }
		// }
		// return false;

	}

	/**
	 * #94 Binary Tree Inorder Traversal 中序遍历
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		inorderTraversal0(root, list);
		return list;
	}

	private void inorderTraversal0(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		inorderTraversal0(root.left, list);
		list.add(root.val);
		inorderTraversal0(root.right, list);

	}

	/**
	 * #144 Binary Tree Preorder Traversal 先序遍历二叉树
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		preorderTraversal0(root, list);
		return list;

		// List<Integer> list = new ArrayList<Integer>();
		// if (root!=null) {
		// list.add(root.val);
		// list.addAll(preorderTraversal(root.left));
		// list.addAll(preorderTraversal(root.right));
		// }
		// return list;
	}

	private void preorderTraversal0(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		list.add(root.val);
		if (root.left != null)
			preorderTraversal0(root.left, list);
		if (root.right != null)
			preorderTraversal0(root.right, list);
	}

	/**
	 * #67 Add Binary
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int ai = a.length() - 1;
		int bi = b.length() - 1;
		int carry = 0;
		int sum = 0;

		while (ai >= 0 || bi >= 0) {
			int x = ai >= 0 ? a.charAt(ai) - '0' : 0;
			int y = bi >= 0 ? b.charAt(bi) - '0' : 0;
			sum = x ^ y ^ carry;// 1+1再加进位1
			carry = x + y + carry > 1 ? 1 : 0;
			sb.insert(0, sum);
			ai--;
			bi--;
		}

		if (carry == 1)
			sb.insert(0, 1);
		return sb.toString();

	}

	/**
	 * #203 Remove Linked List Elements
	 * 
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElements(ListNode head, int val) {
		ListNode preHead = new ListNode(0);
		preHead.next = head;
		ListNode runner = preHead;
		while (runner.next != null) {
			if (runner.next.val == val) {
				runner.next = runner.next.next;
			} else {
				runner = runner.next;
			}
		}
		return preHead.next;
	}

	/**
	 * #257 Binary Tree Paths Given a binary tree, return all root-to-leaf
	 * paths.
	 * 
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> list = new ArrayList<String>();
		if (root != null) {
			if (root.left == null && root.right == null) {
				list.add(root.val + "");
			} else {
				if (root.left != null) {
					list.addAll(binaryTreePaths(root.left));
				}
				if (root.right != null) {
					list.addAll(binaryTreePaths(root.right));
				}
				for (int i = 0; i < list.size(); i++) {
					list.set(i, root.val + "->" + list.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * #223 Rectangle Area
	 * 
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @param E
	 * @param F
	 * @param G
	 * @param H
	 * @return
	 */
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		if (A >= G || E >= C || B >= H || F >= D)
			return (C - A) * (D - B) + (G - E) * (H - F);
		int x1 = Math.max(A, E);
		int y1 = Math.max(B, F);
		int x2 = Math.min(C, G);
		int y2 = Math.min(D, H);
		return (C - A) * (D - B) + (G - E) * (H - F) - (x2 - x1) * (y2 - y1);
	}

	/**
	 * #112 Path Sum 递归调用
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root != null) {
			int temp = sum - root.val;
			if (temp == 0 && root.left == null && root.right == null)
				return true;
			else
				return hasPathSum(root.left, temp) || hasPathSum(root.right, temp);

		} else {
			return false;
		}
	}

	/**
	 * @26 Remove Duplicates from Sorted Array 两个index指向数组元素，分别移位
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		int length = nums.length;
		int count = length;
		if (count <= 1)
			return count;
		for (int i = 1, j = 0; i < length; i++) {
			if (nums[j] == nums[i]) {
				count--;
			} else {
				j++;
				if (j < i)
					nums[j] = nums[i];
			}
		}
		return count;
	}

	/**
	 * #231 Power of Two 判断是否为2的N次方
	 * 
	 * @param n
	 * @return
	 */
	public boolean isPowerOfTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

	/**
	 * #110 Balanced Binary Tree 判断是否是一颗平衡二叉树
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if (depthOfTreenode(root) == -1)
			return false;
		else {
			return true;
		}
	}

	private int depthOfTreenode(TreeNode root) {
		if (root == null)
			return 0;
		int left_depth = depthOfTreenode(root.left);
		if (left_depth == -1)
			return -1;
		int right_depth = depthOfTreenode(root.right);
		if (right_depth == -1)
			return -1;
		if (Math.abs(right_depth - left_depth) > 1)
			return -1;
		return 1 + Math.max(right_depth, left_depth);

	}

	/**
	 * #27 Remove Element
	 * 
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {

		int length = nums.length;
		int count = 0;
		for (int i = 0, j = 0; i < length; i++) {
			if (nums[i] == val)
				count++;
			else {
				nums[j++] = nums[i];
			}
		}
		return length - count;
	}

	/**
	 * #263 Ugly Number
	 * 
	 * @param num
	 * @return
	 */
	public boolean isUgly(int num) {
		if (num == 0)
			return false;
		while (num % 2 == 0)
			num = num / 2;
		while (num % 3 == 0)
			num = num / 3;
		while (num % 5 == 0)
			num = num / 5;
		if (num == 1)
			return true;
		else
			return false;
	}

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
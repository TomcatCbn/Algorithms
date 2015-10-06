package com.cbn.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
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
}

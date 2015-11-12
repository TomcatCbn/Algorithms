package com.cbn.algorithm.stringsort;

import com.cbn.algorithm.sort.impl.InsertionSort;

/**
 * 高位优先的字符串排序
 * @author boning.cbn
 *
 */
public class MSD {
private static int R=256;//基数
private static final int M=15;//小数组的切换阈值
private static String[] aux ;//数据分类的辅助数组
private static InsertionSort insertSort=new InsertionSort();
private static int chatAt(String s, int d){
	if(d<s.length())return s.charAt(d);
	else
		return -1;
}

private static void sort(String[] a){
	int N=a.length;
	aux = new String[N];
	sort(a,0,N-1,0);
}

private static void sort(String[] a, int lo, int hi, int d) {
	//以d个字符为键将a[i]至a[j]排序
	if(hi<=lo+M){
		//调用插入排序解决小数组的排序
	}
	int[] count = new int[R+2];//计算频率
	for(int i=lo;i<=hi;i++)
	count[chatAt(a[i],d)+2]++;
	
	for(int r=0;r<R+1;r++)
		count[r+1]+=count[r];
	
	for(int i=lo;i<=hi;i++){
		aux[count[chatAt(a[i],d)+1]++]=a[i];
	}
	
	for(int i=lo;i<=hi;i++){
		a[i]=aux[i];
	}
	
	//递归的以每个字符为键进行排序
	for(int r=0;r<R;r++){
		sort(a,lo+count[r],lo+count[r+1]-1,d+1);
	}
}
}

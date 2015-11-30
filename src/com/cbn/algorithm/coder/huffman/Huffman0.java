package com.cbn.algorithm.coder.huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * 单词查找树的结点表示
 * @author boning.cbn
 *
 */
/**
 * @author boning
 *
 */
public class Huffman0 {
	private final  String srcFilename;
	private final String desFilename;
	private static final int R= 256;//ASCII字母表
	
	public Huffman0(String srcFilename, String desFilename) {
		this.srcFilename=srcFilename;
		this.desFilename=desFilename;
	}
	
	public void compress() throws Exception{
		//读取输入
		int[] freq= new int[R];
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(new File(srcFilename)));
		char[] c= new char[1];
		int len =-1;
		int length=0;
		while((len=br.read(c))!=-1){
			//统计频率
			freq[c[0]]++;
			length++;
			sb.append(c[0]);
		}
		br.close();
		
		//构造霍夫曼编码
		Node root = buildTrie(freq);
		//递归构造编译表
		String[] st= new String[R];
		buildCode(st, root, "");
		//递归打印解码用的单词查找树
		FileOutputStream out= new FileOutputStream(new File(desFilename));
		writeTrie(root,out);
		//打印字符总数
		out.write(length);
		
		
		//使用霍夫曼编码处理输入
		char[] inputChars = sb.toString().toCharArray();
		TrieWriter tw = new TrieWriter();
		for(int i=0;i<length;i++){
			String code = st[inputChars[i]];
			for(int j=0;j<code.length();j++){
				if(code.charAt(j)=='1'){
					tw.writeBoolean(true);
				}else{
					tw.writeBoolean(false);
				}
			}
		}
		BinaryStdout.write(tw.get(), out);
		out.close();
	}
	/**
	 *前缀码的展开，解码 
	 * @throws IOException 
	 */
	private FileInputStream input=null;
	public  void expand() throws IOException{
		input = new FileInputStream(new File(desFilename));
		//读取单词查找树
		Node root = readTrie();
		
		//读取文件,为二进制字符串
		String src = BinaryStdIn.read(input);
		TrieReader tr = new TrieReader(src);
		//第一个为int值，为编码的个数
		int N = tr.readInt(); 
		//解码
		for(int i=0;i<N;i++){
			//展开第i个编码所对应的字母
			Node x = root;
			while(!x.isLeaf()){
				if(tr.readBoolean()){
					x=x.right;
				}else{
					x=x.left;
				}
			}
			System.out.println(x.ch);
		}
	}
	private String[] buildCode(Node root){
		//使用单词查找树构造编译表
		String[] st = new String[R];
		buildCode(st,root,"");
		return st;
	}
	private void buildCode(String[] st , Node x, String s){
		//使用单词查找树构造编译表
		if(x.isLeaf()){
			st[x.ch]=s;return;
		}
		buildCode(st,x.left,s+'0');
		buildCode(st, x.right, s+'1');
	}
	/**
	 * 从流中读取单词查找树比特字符串
	 * @return
	 * @throws IOException 
	 */
	private Node readTrie() throws IOException {
		String s = BinaryStdIn.read(input);
		TrieReader tr = new TrieReader(s);
		int length = s.length();
		if (tr.readBoolean()) {
			return new Node(tr.readChar(), 0, null, null);
		}
		return new Node('\0', 0, readTrie(),readTrie());
	}
	 class TrieReader{
		private final String s;
		private int index=0;
		public TrieReader(String s) {
			// TODO Auto-generated constructor stub
			this.s =s;
		}
		public int readInt() {
			String temp = s.substring(index,index+32);
			index+=32;
			return Integer.valueOf(temp, 2);
		}
		public boolean readBoolean(){
			return '1'==s.charAt(index++);
		}
		
		public char readChar(){
			char b =(char)(int)Integer.valueOf(s.substring(index,index+8));
			index+=8;
			return (char)b;
		}
	}
	 
	 class TrieWriter{
		 private StringBuilder sb ;
		 public TrieWriter(){
			 sb = new StringBuilder();
		 }
		public void writeBoolean(boolean b) {
			// TODO Auto-generated method stub
			if(b){
				sb.append('1');
			}else{
				sb.append('0');
			}
		}
		public String get(){
			return sb.toString();
		}
	 }
	
	
	/**
	 * 写入单词查找树的比特字符串
	 * @param x
	 * @throws Exception
	 */
	private void writeTrie(Node x,FileOutputStream output) throws Exception{
		StringBuilder sb  = new StringBuilder();
		writeTrie0(x,sb);
		String temp =sb.toString();
		System.out.println(temp);
		
		BinaryStdout.write(temp,output);
	}
	/**
	 * 将单词查找树写为比特字符串
	 * 如果访问的是内部节点，则写入一个比特0，
	 * 否则访问一个叶子节点，则写入一个比特1，紧接写入叶子节点的8位ASCII码
	 * @param x
	 */
	private void writeTrie0(Node x,StringBuilder sb){
		if(x.isLeaf()){
			sb.append("1");
			sb.append(Integer.toBinaryString(x.ch));
			return;
		}
		sb.append("0");
		writeTrie0(x.left,sb);
		writeTrie0(x.right,sb);
	}
	
	/**
	 * 构造一颗霍夫曼编码单词查找树
	 * @param freq
	 * @return
	 */
	private Node buildTrie(int[] freq){
		//使用多颗单节点树初始化优先队列
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(char c=0;c<R;c++){
			if(freq[c]>0){
				pq.add(new Node(c,freq[c],null,null));
			}
		}
		while(pq.size()>1){
			//合并两颗频率最小的树
			Node x = pq.poll();
			Node y = pq.poll();
			Node parent = new Node('\0',x.freq+y.freq,x,y);
			pq.add(parent);
		}
		return pq.poll();
	}

 	private static class Node implements Comparable<Node> {
		// 霍夫曼单词查找树中的结点
		private char ch;// 内部结点不会使用该变量
		private int freq;// 展开过程不会使用该变量
		private final Node left, right;

		public Node(char ch, int freq, Node left, Node right) {

			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}

	}
 	public static void main(String[] args) {
		Huffman0 h0 = new Huffman0("wordscount.txt", "test.txt");
		try {
			h0.compress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			h0.expand();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.cbn.algorithm.coder.huffman;  
  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.PriorityQueue;  
import java.util.Set;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JButton;  
import java.awt.EventQueue;  
import java.awt.event.ActionListener;  
import java.awt.event.ActionEvent;  
  
import javax.swing.JTextArea;  
  
public class huffmanInterface {  
  
    private JFrame frame;  
  
    /** 
     * Launch the application. 
     */  
    public static void main(String[] args) {  
        EventQueue.invokeLater(new Runnable() {  
            public void run() {  
                try {  
                    huffmanInterface window = new huffmanInterface();  
                    window.frame.setVisible(true);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
    }  
  
    /** 
     * Create the application. 
     *  
     * @throws IOException 
     */  
    public huffmanInterface() throws IOException {  
        initialize();  
    }  
  
    /** 
     * Initialize the contents of the frame. 
     *  
     * @throws IOException 
     */  
    private void initialize() throws IOException {  
  
        frame = new JFrame();  
        frame.setBounds(100, 100, 665, 588);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().setLayout(null);  
        final JTextArea jt = new JTextArea();  
        jt.setBounds(103, 44, 321, 24);  
        frame.getContentPane().add(jt);  
        JButton btnNewButton = new JButton("open file");  
        btnNewButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent arg0) {  
                JFileChooser jChooser = new JFileChooser();  
                // 设置打开文件类型,此处设置成只能选择文件夹，不能选择文件  
                jChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能打开文件夹  
                // 打开一个对话框  
                int index = jChooser.showDialog(null, "打开文件");  
                if (index == JFileChooser.APPROVE_OPTION) {  
                    // 把获取到的文件的绝对路径显示在文本编辑框中  
                    jt.setText(jChooser.getSelectedFile().getAbsolutePath());  
                }  
  
            }  
        });  
        btnNewButton.setBounds(103, 11, 93, 23);  
        frame.getContentPane().add(btnNewButton);  
        JLabel lblFileLocation = new JLabel("file location");  
        lblFileLocation.setBounds(10, 48, 83, 15);  
        frame.getContentPane().add(lblFileLocation);  
  
        JLabel lblNewLabel = new JLabel("rate");  
        lblNewLabel.setBounds(10, 193, 66, 15);  
        frame.getContentPane().add(lblNewLabel);  
  
        final JTextArea textArea_1 = new JTextArea();  
        textArea_1.setBounds(103, 189, 321, 24);  
        frame.getContentPane().add(textArea_1);  
  
        final JTextArea textArea = new JTextArea();  
        textArea.setBounds(42, 289, 560, 251);  
        frame.getContentPane().add(textArea);  
        textArea.setLineWrap(true);  
  
        JButton btnCompress = new JButton("Compress");  
        btnCompress.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent arg0) {  
                Huffman h = new Huffman();  
                try {  
                    h.huffmanCompress(h, jt.getText(), "");  
                    textArea_1.setText(String.valueOf(h.rate));  
                    textArea.setText(h.encodeSheet.toString());  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        });  
        btnCompress.setBounds(103, 121, 93, 23);  
        frame.getContentPane().add(btnCompress);  
  
        JLabel label = new JLabel("huffman \nencode sheet");  
        label.setBounds(10, 231, 140, 48);  
        frame.getContentPane().add(label);  
  
    }  
}  
  
/** 
 * 本类是将输入的文本文件生成霍夫曼编码，并将根据此编码的压缩的文件存放成源文件+。compress,然后根据此文件和霍夫曼编码将压缩的文件解压缩， 
 * 并存储为源文件+。copy 
 *  
 * @author zhuwei 
 * 
 */  
class Huffman {  
    // 存储霍夫曼编码  
    Map<String, String> encodeSheet = new HashMap<String, String>();  
    // 存储压缩率  
    float rate = -1;  
  
    // 读文件，并返回文件内容  
    String textInput(String filename, int[] size) {  
        try {  
            FileInputStream fis = new FileInputStream(new File(filename));  
            byte[] buff = new byte[fis.available()];  
            // 存储两次读文件的该文件的大小  
            if (size[0] == -1) {  
                size[0] = fis.available();  
            } else if (size[1] == -1) {  
                size[1] = fis.available();  
            }  
            fis.read(buff);  
            fis.close();  
            System.out.println(new String(buff));  
            return new String(buff);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    // 分析文件，并返回每个字符的频率  
    ArrayList<Node> analyzeString(String str) {  
        // 保存每个字符频率的列表  
        ArrayList<Node> list = new ArrayList<Node>();  
        // 暂存字符频率的map  
        Map<Character, Integer> map = new HashMap<Character, Integer>();  
        for (int i = 0; i < str.length(); i++) {  
            if (!map.containsKey(str.charAt(i))) {  
                map.put(str.charAt(i), 1);  
            } else {  
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);  
            }  
        }  
        Set<Entry<Character, Integer>> entryset = map.entrySet();  
        Iterator<Entry<Character, Integer>> iter = entryset.iterator();  
        // 由map转化成list<Node>  
        while (iter.hasNext()) {  
            Entry<Character, Integer> entry = iter.next();  
            Node node = new Node(null, null, String.valueOf(entry.getKey()),  
                    entry.getValue());  
            list.add(node);  
        }  
        return list;  
  
    }  
  
    // 将输入的文本根据encodesheet编码，并存放至文件中,文件名为源文件+。compress  
    void encode(String text, String storefile) throws IOException {  
        char[] chars = text.toCharArray();  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < chars.length; i++) {  
            Character tempChar = chars[i];  
            sb.append(encodeSheet.get(tempChar.toString()));  
        }  
        String str = sb.toString();  
        sb = new StringBuilder();  
        String sub;  
        int len = 0;  
        for (int i = 0; i < str.length(); i = i + 7) {  
            sub = new String();  
            // 最后一组比特位，要保存它的长度，在最末尾  
            if ((i + 7) >= str.length()) {  
                len = str.length() - i;  
                sub = str.substring(i, str.length());  
            } else {  
                sub = str.substring(i, i + 7);  
            }  
            sb.append((char) (int) Integer.valueOf(sub, 2));  
  
        }  
        FileOutputStream fos = new FileOutputStream(new File(storefile));  
        fos.write(sb.toString().getBytes());  
        // 将最后一组比特位长度存在文件末尾  
        fos.write(len + 48);  
        fos.close();  
    }  
  
    // 将读取的文件内容解析成二进制串  
    String decode(String str) {  
        char[] tempchars = str.toCharArray();  
        StringBuilder sb = new StringBuilder();  
        int len = tempchars[tempchars.length - 1] - 48;  
        System.out.println(len);  
        for (int i = 0; i < tempchars.length - 1; i++) {  
            String temp = Integer.toBinaryString(tempchars[i]);  
            if (i == tempchars.length - 2) {  
                while (temp.length() < len) {  
                    temp = "0" + temp;  
                }  
                sb.append(temp);  
            } else {  
                while (temp.length() < 7) {  
                    temp = "0" + temp;  
                }  
                sb.append(temp);  
            }  
        }  
  
        return sb.toString();  
    }  
  
    // 根据encodesheet将解码后的二进制串解码成原文件,文件名为源文件名+。copy  
    void decodeErgodic(String str, Node node, String filename)  
            throws IOException {  
        Node root = node;  
        Node temp = node;  
        StringBuilder sb = new StringBuilder();  
        char[] tempchars2 = str.toCharArray();  
        int i = 0;  
        for (; i < tempchars2.length; i++) {  
            char c = tempchars2[i];  
            if (c == '1') {  
                temp = temp.r;  
            } else if (c == '0') {  
                temp = temp.l;  
            }  
            if (temp.l == null && temp.r == null) {  
                sb.append(temp.keyOrType);  
                temp = root;  
            }  
  
        }  
        File file = new File(filename + ".copy");  
        FileOutputStream fos = new FileOutputStream(file);  
        fos.write(sb.toString().getBytes());  
        fos.close();  
    }  
  
    // 利用贪心算法的霍夫曼编码程序  
    ArrayList<Node> huffmanCode(List<Node> nodes) {  
        ArrayList<Node> temp = new ArrayList<Node>(nodes);  
        PriorityQueue<Node> queue = new PriorityQueue<Node>(nodes);  
        for (int i = 0; i < nodes.size() - 1; i++) {  
            Node l = queue.poll();  
            Node r = queue.poll();  
            Node tempNode = new Node(l, r, "@special", l.freq + r.freq);  
            temp.add(tempNode);  
            queue.add(tempNode);  
        }  
        return temp;  
    }  
  
    // 将每个字符的编码存至encodesheet中  
    void ergodic(Node node, String str) {  
        if (node.l != null)  
            ergodic(node.l, str + "0");  
        if (node.r != null)  
            ergodic(node.r, str + "1");  
        if (node.l == null && node.r == null) {  
            encodeSheet.put(node.keyOrType, str);  
        }  
    }  
  
    void huffmanCompress(Huffman h, String filename, String storefile)  
            throws IOException {  
        int[] sizes = { -1, -1 };  
        String str = h.textInput(filename, sizes);  
        ArrayList<Node> list = h.analyzeString(str);  
        list = h.huffmanCode(list);  
        Node node = list.get(list.size() - 1);  
        h.ergodic(node, "");  
        h.encode(str, filename + ".compress");  
        String encodeString = h.textInput(filename + ".compress", sizes);  
        System.out.println(sizes[1] + " " + sizes[0] + " "  
                + ((float) sizes[1] / (float) sizes[0]));  
        rate = ((float) sizes[1] / (float) sizes[0]);  
        String decodeString = h.decode(encodeString);  
        h.decodeErgodic(decodeString, node, filename);  
    }  
  
}  
  
class Node implements Comparable<Object> {  
    Node l;  
    Node r;  
    String keyOrType;  
    int freq;  
  
    public Node(Node l, Node r, String keyOrType, int freq) {  
        this.l = l;  
        this.r = r;  
        this.keyOrType = keyOrType;  
        this.freq = freq;  
    }  
  
    public Node() {  
    }  
  
    @Override  
    public int hashCode() {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result  
                + ((keyOrType == null) ? 0 : keyOrType.hashCode());  
        return result;  
    }  
  
    // 重写equal方法，只用keyOrType  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        Node other = (Node) obj;  
        if (keyOrType == null) {  
            if (other.keyOrType != null)  
                return false;  
        } else if (!keyOrType.equals(other.keyOrType))  
            return false;  
        return true;  
    }  
  
    @Override  
    public int compareTo(Object o) {  
        Node node = (Node) o;  
        return this.freq - node.freq;  
    }  
  
    @Override  
    public String toString() {  
        return "Node [ keyOrType=" + keyOrType + " freq " + freq + "]";  
    }  
} 
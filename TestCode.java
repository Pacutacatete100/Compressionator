import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import trees.SimpleTreeSet.Node;

public class TestCode {
	
	
    //Main copy from https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
	private static Map<Character, Integer> charMap;
	private static Map<Character, String> binaryMap;
	private static int maxLen;
	private static List<Character> chars;
	private static List<Integer> frequency;
	private static String compressedData = "C:/Users/sadda/Compressionator/compressedData.txt/";
	private static String inputData = "C:/Users/sadda/Compressionator/inputData.txt/";
	private static String afterCompressedData = "C:/Users/sadda/Compressionator/uncompressedInputData.txt/";
	
	
	
	private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

	
    public static void main(String[] args) throws IOException {

    	charMap = new HashMap<>();
    	
    	try {
			createMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for (Map.Entry<Character, Integer> test : charMap.entrySet()) {
    		Node add = new Node('z', 0, null, null);
    	}
    	compress();
    }
    
    

    public static void createMap() throws IOException{
    	Map<Character,Integer> testMap = new HashMap<>();
    	BufferedReader in = new BufferedReader(new FileReader(inputData));
		String line = in.readLine();
		while (line != null) {
			System.out.println(line);
			for (char x:line.toCharArray()) {
				if (x != ' ') {
					if (!testMap.containsKey(x)) {
						testMap.put(x, 1);
					} else {
						testMap.replace(x, testMap.get(x) +1);
					}
				}
			}
			line = in.readLine();
		}
		//System.out.println(testMap);
		
		Map<Character, Integer> newMap = new TreeMap<>();
		List<Character> charList = new ArrayList<>();
		List<Integer> freq = new ArrayList<>();
		maxLen = Integer.toBinaryString(testMap.size()).length();
		Map<Character, String> binMap = new HashMap<>();
		
		for (int i = 0; i < testMap.size(); i ++) {
			char max = ' ';
			int maxNum = 0;
			
			for (Map.Entry<Character,Integer> part :testMap.entrySet()) {
				
				if (part.getValue() != 0) {
					if (part.getValue() > maxNum) {
						maxNum = part.getValue();
						max = part.getKey();
					}
				}
			}
			String bin = Integer.toBinaryString(i);
			while (bin.length() < maxLen) {
				bin = "0" + bin;
			}
			System.out.println(max + " -- " + maxNum + " -- " + i + "(" + bin + ")");
			binMap.put(max, bin);
			charList.add(max);
			freq.add(maxNum);
			newMap.put(max, maxNum);
			testMap.replace(max,0);
			
		}
		
		System.out.println(binMap);
//		System.out.println(freq);
//		System.out.println(charList);
//		System.out.println(newMap);
		binaryMap = binMap;
		chars = charList;
		frequency = freq;
		System.out.println(binMap);
    }
    
    public static void compress() {
    	new File(compressedData).delete();
    	try {
			FileWriter writer = new FileWriter(compressedData);
			BufferedWriter bwr = new BufferedWriter(writer);
			BufferedReader in = new BufferedReader(new FileReader(inputData));
			String line = in.readLine();
			while (line != null) {
				for (char x:line.toCharArray()) {
					if (x == ' ') {
						bwr.write(" ");
					} else {
						bwr.write(binaryMap.get(x));
					}
				}
				bwr.write("\n");
				line = in.readLine();
				
			}	
			
			bwr.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
    }
    
    
    public void decompress() {
    	
    }
    
    
}
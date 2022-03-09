import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TestCode {
	
	
    //Main copy from https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
	private static Map<Character, Integer> charMap;
	private static String compressedData = "Compressionator/compressedData.txt/";
	private static String inputData = "Compressionator/inputData.txt/";
	
    public static void main(String[] args) throws IOException {

    	charMap = new HashMap<>();
    	
    	try {
			createMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

    public static void createMap() throws IOException{
    	BufferedReader in = new BufferedReader(new FileReader(inputData));
		String line = in.readLine();
		while (line != null) {
			System.out.println(line);
			for (char x:line.toCharArray()) {
				if (!charMap.containsKey(x)) {
					charMap.put(x, 1);
				} else {
					charMap.replace(x, charMap.get(x) +1);
				}
			
			}
			line = in.readLine();
		}
    	System.out.println(charMap.toString());
    	//charMap.toString();
    }
    
    public void compress() {
    	try {
			FileWriter writer = new FileWriter(inputData);
			BufferedWriter bwr = new BufferedWriter(writer);
			
			bwr.write("");
			bwr.write("\n");
			
			bwr.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
    }
    
    
    public void decompress() {
    	
    }
    
    
}
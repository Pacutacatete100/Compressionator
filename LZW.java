import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class LZW {
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 32;
    

    private LZW() { }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses
     * them using LZW compression with 12-bit codewords; and writes the results
     * to standard output.
     */
    public static void compress() { 
    	BufferedReader input = new BufferedReader(new FileReader("Compressionator/inputData.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("Compressionator/compressedData.txt"));
        String line = input.readLine();
        TST<Integer> st = new TST<Integer>();

        // since TST is not balanced, it would be better to insert in a different order
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);

        int code = R+1;  // R is codeword for EOF

        while (line.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            input.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < line.length() && code < L)    // Add s to symbol table.
                st.put(line.substring(0, t + 1), code++);
            input = line.substring(t);            // Scan past s in input.
        }
        writer.write(R, W);
        input.close();
    } 

    /**
     * Reads a sequence of bit encoded using LZW compression with
     * 12-bit codewords from standard input; expands them; and writes
     * the results to standard output.
     */
    public static void expand() {
        BufferedReader input = new BufferedReader(new FileReader(""));
        BufferedWriter writer = new BufferedWriter(new FileWriter(""));
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int size = Integer.parseInt(input.readLine());
        if (size == R) return;           // expanded message is empty string
        String val = st[size];

        while (true) {
            writer.write(val);
            size = input.readLine(W);
            if (size == R) break;
            String s = st[size];
            if (i == size) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        input.close();
    }
}
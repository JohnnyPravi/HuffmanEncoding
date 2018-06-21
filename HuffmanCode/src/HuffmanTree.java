
  // Μια κλάση abstract  που χρησιμεύει ως πρότυπο για τις κλάσεις NODE και LEAF
 
abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public int syxnothta; 
    //
    public HuffmanTree(int freq) { 
    	syxnothta = freq; 
    }
    
    public int compareTo(HuffmanTree tree) {
        return syxnothta - tree.syxnothta;
    }
}
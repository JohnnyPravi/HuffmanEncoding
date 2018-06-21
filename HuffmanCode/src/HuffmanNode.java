
class HuffmanNode extends HuffmanTree {
    public HuffmanTree left, right; 
 
    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.syxnothta + r.syxnothta); // pernoume ths suxnothtes apo thn huffmantree klash
        
        left = l;
        right = r;
    }
}
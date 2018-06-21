//Huffman Encoder // Authors Ioannis Pravinos 738 - Nikos Moukas 719
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class HuffmanCoding {
    
  
public static void main(String[] args) throws Exception {  ////////////////////////Main Synarthsh
		
        String InputDataFromTxt =readFileAsString("C:\\Users\\John Pravinos\\Documents\\NetBeansProjects\\HuffmanCode\\src\\Input.txt"); //Diavasma Txt Arxeiou apo synarthsh readfileasstring
          
        int[] charfreqsarray = new int[256]; //Dhmiourgia pinaka 256 thesewn - 256 ascii dynatoi xarakthres.
        
        for (char x : InputDataFromTxt.toCharArray()) // elegxos gia kathe gramma kai auxhsh ths syxnothtas tou / kai metatroph se char array = spasimo string
            charfreqsarray[x]++; //auxhsh gia na metakinoumaste sto epomeno gramma kathe fora (tou string mas = input apo txt )
       
        HuffmanTree Tree = TreeBuilderFunction(charfreqsarray); // kalesma buildtree , stelnontas to pinaka me t syxnothta kathe grammatos
         
        HCodePerChar(Tree, new StringBuffer()); //kalesma gia thn paragwgh tou huffman code per char kai dhmiourgia tou arxeiou
        
        String EncodedText = Encoding(Tree,InputDataFromTxt); // topothetisi apotelesmatos Synarthshs encode sto encode
        
        PrintWriter writer1 = new PrintWriter("output-erwthma2B.huf", "UTF-8"); // Synarthsh gia dhmiourgia .huf arxeiou poy ektyponoume to                                                                                    
        writer1.println(InputDataFromTxt); //Ektypwsh arxikou keimenou                                    //arxiko keimeno txt kai to encoded keimeno
        writer1.println("\n"); 
        writer1.println(EncodedText);  //  keimeno se huffman 
        writer1.close();        //termatismos kai oloklhrwsh dhmiourgias tou neou arxeiou .huf
           
        System.out.println("Success - Check Files"); 

    }

////////////////////////Functions/////////////////////////////////////
        
         public static String readFileAsString(String fileName)throws Exception  //Synarthsh gia na diavazoume apo to arxeio mas.
  {
    String data = ""; //arxikopoihsh
    data = new String(Files.readAllBytes(Paths.get(fileName))); //Diavasma Keimenou apo to txt kai epistrofh "timhs" sto data
    return data;
  }

        
    public static HuffmanTree TreeBuilderFunction (int[] charfreqsarray) { //Dexomaste ws parametro ton pinaka me tis suxnothtes kathe xarakthra
                                                           //Dhmiourgia tou dentrou kodikopoihshs apo ton arithmo twn syxnothtwn kathe grammtos
                                                           //epistrefei thn timh trees.poll() gia thn kwdikopoihsh
    	// Dhmiourgia mia adeias seiras proteraiothtas trees
        PriorityQueue<HuffmanTree> PrioQueue = new PriorityQueue<HuffmanTree>();
        
        for (int i = 0; i < charfreqsarray.length; i++){
            if (charfreqsarray[i] > 0)// elegxos oti h syxnothta ths thesis [i] einai panw apo 0
                PrioQueue.add(new HuffmanLeaf(charfreqsarray[i], (char)i)); // // topothetisi ths syxnothtas kai tou xarakthra sto pqueue.
        }
        
        while (PrioQueue.size() > 1) { //h leitourgia epanalambanete mexri na meinei 1 dentro apo tous xarakthres mas
           //oi xarakthres me tis mikroteres times gia dentro
            HuffmanTree PQelement1 = PrioQueue.poll(); // auth h leitourgia topothtei stis metablhtes to head ths seiras proteraiothtas pou exoume thn dedomenh stigmh *
            HuffmanTree PQelement2 = PrioQueue.poll(); 
 
            //
            PrioQueue.add(new HuffmanNode(PQelement1, PQelement2)); //psosthesh twn suxnothtwn twn duo metablhtwn PQelement1-2 
        }
        // 
        return PrioQueue.poll();//epistrofh ths rizas
    }
 
  
    
     
    public static void  HCodePerChar(HuffmanTree Tree, StringBuffer HCcharvalue) throws IOException   { 
        if (Tree != null){ //elegxos ean yparxoun akoma dentra *
        
         char symvolo ='a'; // tyxaio char xaraktira gia thn arxikopoihsh ths metablhths symbol kathe fora p trexei h syanrthsh
       
         if (Tree instanceof HuffmanLeaf) { // elegxos an eisai se leaf 
            
             HuffmanLeaf leaf = (HuffmanLeaf)Tree; // kaloume thn huffmanleaf gia na paroume to xarakthra mas (value)
             symvolo = leaf.gramma;  //topothetisi timis sto symvolo
              System.out.println("Gramma:" + leaf.gramma + "\t / Syxnothta:" + leaf.syxnothta);

             FileWriter writer = new FileWriter("output_erwthma_2a.cm", true);{ // dhmiourgia arxeiou .cm , h file writer exei thn dunatotha na prosthetei nea dedomena sto arxeio kratwntas kai ta prohgoumena
             writer.write(symvolo+ ":" + HCcharvalue); // ektupwsh tou xarakthra kai ths kwdikopoihshs tou
             writer.write("\n");//allagh grammhs
             writer.close();}
          
        } else if (Tree instanceof HuffmanNode) { // elegxos an eimaste se node // xrhsh instanceof gia na checkaroume to tupo tou obj(aristera- tree)
            HuffmanNode node = (HuffmanNode)Tree; // kaloume thn huffmannode
 
            // bazoume thn timh 0 ston aristero komvo
            HCcharvalue.append('0'); //topothethsh timhs 0 sto telos tou huffmancode tou kathe Symvolou 
            HCodePerChar(node.left, HCcharvalue); //kaloume thn sunarthsh hcodeperchar kai stelnopume thn suxnothta toy aristerou kombou kai thn kwdikopoihsh tou
            HCcharvalue.deleteCharAt(HCcharvalue.length()-1);
 
            //  bazoume thn timh 1 ston deksio komvo
            HCcharvalue.append('1');//topothethsh timhs 1 sto telos tou huffmancode tou kathe Symvolou 
            HCodePerChar(node.right, HCcharvalue); //kaloume thn sunarthsh  kai stelnopume thn suxnothta toy deksiou kombou kai thn kwdikopoihsh tou
            HCcharvalue.deleteCharAt(HCcharvalue.length()-1); 
        }
}
    }
    
    public static String Encoding(HuffmanTree Tree, String InputDataFromTxt){
       
    	String EncodedText = ""; //arxikopoihsh tou encoded text
        for (char currentletter : InputDataFromTxt.toCharArray()){ //spame to inout mas se xarakthres   kai to anathetoume sto currentletter gia elegxo parakatw
        	EncodedText+=( HFcodeQueueExportFunction(Tree, new StringBuffer(),currentletter)); // kaloume synarthsh  HFcodeQueueExportFunction 
        }
    	return EncodedText; // return 
    }
 

  
    public static String  HFcodeQueueExportFunction(HuffmanTree Tree, StringBuffer HCcharvalue, char currentletter) { //ginontai oi elegxoi kai paragoume to oliko enwmeno kwdika huffman
        if (Tree != null){ //elegxos ean yparxoun akoma dentra                                                                //
        
        if (Tree instanceof HuffmanLeaf) { // elegxos an eisai se leaf 
            HuffmanLeaf leaf = (HuffmanLeaf)Tree; // kaloume thn huffmanleaf gia na paroume to xarakthra mas kai thn suxnothata
            
            // elegxos an einai o idios xarakthras apo to keimeno mas
            if (leaf.gramma == currentletter ){ //antistoixoume thn timh ths currentletter/leafvalue me ton idio xarakthra mesa ston pinaka mas 
            	return HCcharvalue.toString(); // epistrefoume to huffman kwdika tou xarakthra tou sygkekrimenou grammatos
            }
           
        } else if (Tree instanceof HuffmanNode) { // elegxos an eimaste se node
            HuffmanNode node = (HuffmanNode)Tree; // kaloume thn huffmannode
 
            // metakinoumaste aristera
            HCcharvalue.append('0'); //prosthetoume thn timh 0 sto telos tou hf code gia tou xarakthres pou einai sthn metablhth left
            String left =  HFcodeQueueExportFunction(node.left, HCcharvalue, currentletter);//kaloume  ksana HFcodeQueueExportFunctiongia gia na elegksoume an eimaste se leaf h se node afou metakinithikame mia thesh
            HCcharvalue.deleteCharAt(HCcharvalue.length()-1); //**
 
              // metakinoumaste deksia
            HCcharvalue.append('1'); //prosthetoume thn timh 1 sto telos tou hf code gia tou xarakthres pou einai sthn metablhth right
            String right =  HFcodeQueueExportFunction(node.right, HCcharvalue,currentletter); //kaloume thn  HFcodeQueueExportFunctiongia gia na elegksoume an eimaste se leaf h se node afou metakinithikame mia thesh
            HCcharvalue.deleteCharAt(HCcharvalue.length()-1); //**
            
            if (left==null) return right; else return left;
        }
		return null;
    }
        	return null;
    }
    


}
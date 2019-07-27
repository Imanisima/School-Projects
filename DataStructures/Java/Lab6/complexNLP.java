import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 
 * Purpose: To read a file with a list of words and create clusters based off of similarity
 *
 * Used with: Heap.java, HeapNode.java, DSF.java, hashTableStrings.java, sNode.java
 *
 */
public class complexNLP {

    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);

        /** HASH **/
        hashTableStrings H = new hashTableStrings(15001);
        H = readFileForHash(H);  //("glove.6B.50d.txt");
        //wordComp("wordcomp.txt", H);

        /** HEAP **/
        /* Reading the file and adding words into the array */
        ArrayList<String> lab6words = new ArrayList<>();
        lab6words = readFileForArrStrings(lab6words); //"Lab6Words"

        /* Search and Compare */
        Heap list = wordCompInHeap(H, lab6words);

        /** Extracting Elements **/
        System.out.println("How many min extracts would you like?");
        int numOfExtractions = scnr.nextInt();

        System.out.println("RESULTS Of Extraction:");
        HeapNode extractedElem;
        for (int i = 0; i < numOfExtractions; i++) {
            extractedElem = list.extractMin();
            extractedElem.printResults();
        }


        /** DSF **/
        System.out.println("\nCORRESPONDING Dsf for Clusters: ");
        DSF dsf = unionizingAll(lab6words, list, numOfExtractions);
        dsf.print();

        System.out.println("\nHow many clusters would you like to print?");
        int numOfClusters = scnr.nextInt();
        printLargestCluster(dsf, lab6words, numOfClusters);

    }

    /** DSF **/
    /* Unionizing words a certain amount of times, creating a set */
    public static DSF unionizingAll(ArrayList<String> list, Heap wordList, int numOfExtractions){
        DSF dsf = new DSF(list.size());
        HeapNode temp;

        for(int i = 0; i < numOfExtractions; i++){
            temp = wordList.extractMin();

            /* Searching for index of word */
            int indexOfA = indexPosition(list, temp.word0);
            int indexOfB = indexPosition(list, temp.word1);
            /* Create a union between A and B */
            dsf.unionBySize(indexOfA, indexOfB);
        }

        return dsf;
    }

    /* Locating Position of the word */
    public static int indexPosition(ArrayList<String> list, String s){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(s))
                return i;
        }

        return -1;
    }


    /** HEAPS **/
    /* Reads a list of words and inserts them into an array of strings */
    public static ArrayList<String> readFileForArrStrings(ArrayList<String> strWords){
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader("Lab6Words.txt"));
            String line;
            while((line = br.readLine()) != null){
                strWords.add(line);
            }

            br.close();

        }catch (Exception e){
            System.out.println("File Not Found!");
        }

        return strWords;
    }

    /* Comparing words in the heap; Same concept as hash table */
    public static Heap wordCompInHeap(hashTableStrings H, ArrayList<String> list) throws IOException{
        Heap heap = new Heap(15001);

        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                sNode columnA = H.search(list.get(i));
                sNode columnB = H.search(list.get(j));

                /* Cosine Distance */
                float distance = cosDistance(columnA.embedding, columnB.embedding);
                float compareA = (float)Math.sqrt(cosDistance(columnA.embedding, columnA.embedding));
                float compareB = (float)Math.sqrt(cosDistance(columnB.embedding, columnB.embedding));

                HeapNode completeList = new HeapNode(list.get(i), list.get(j), ((double)(distance/(compareA*compareB))));
                heap.insert(completeList);
            }
        }
        return heap;
    }

    public static float cosDistance(float[] columnA, float[] columnB){
        float total = 0;

        for (int i = 0; i < 50; i++){
            total += columnA[i] * columnB[i];
        }

        return total;
    }

    /* Prints the largest clusters from the disjoint set forests */
    public static void printLargestCluster(DSF dsf, ArrayList<String> list, int numOfClusters) {
        System.out.println("Printing largest set from greatest to least:");

        int root;
        int[] resetIndex = new int[numOfClusters];

        for (int i = 0; i < numOfClusters; i++) {
            resetIndex[i] = -1;
            /* Now find the root of the cluster */
            root = dsf.findRoot(resetIndex[i], resetIndex);

            /* Print the elements within the set */
            dsf.printSet(root, list);
            resetIndex[i] = root;
        }
    }

    /** HASHTABLE **/
    /* Reads a list of words and inserts them into a hash */
    public static hashTableStrings readFileForHash(hashTableStrings H) throws IOException{
        BufferedReader br = null;

        try{
            br = new BufferedReader(new FileReader("glove.6B.50d.txt"));
            String line;
            while((line = br.readLine()) != null){
            	/* Breaking into pairs */
                String[] list = line.split(" ");
                String word = list[0];
                /* Adds associated floats from the file to an array of floats */
                float[] embedding = new float[50];
                for(int i = 0; i < list.length - 1; i++){
                    embedding[i] = Float.parseFloat(list[i+1]);
                }

                H.insert(word, embedding);
            }

            br.close();

        }catch(FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        return H;
    }

    /* Comparing Word Similarity */
    public static void wordComp(String file, hashTableStrings H) throws IOException{
        System.out.println("Word Similarity: ");
        BufferedReader br = null;
        int counter = 0;

        String line;
        try{
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null){
                String[] word = line.split(" ");
                /* Search for the node that the word is in */
                sNode columnA = H.search(word[0]);
                sNode columnB = H.search(word[1]);

                /* Calculate similarity percentage */
                float similarityPerc = columnA.findSimilarity(columnB);
                System.out.println(columnA.word + " " + columnB.word + ": " + Float.toString(similarityPerc));

                counter++;
            }

            br.close();

        }catch (FileNotFoundException e){
            System.out.println("Error Found");
        }
    }


}

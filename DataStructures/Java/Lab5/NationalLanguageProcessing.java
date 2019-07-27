import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * Purpose: To read a file with a list of 50+ words, and store each word within the hashtable
 * A second file is read (2 words per line) and the similarity between the two is compared
 * Verify that the hashtable distributes the words as evenly as possible
 *
 * Used with: sNode.java, hashTableStrings.java
 */
public class NationalLanguageProcessing {
    /* FIX: REHASH when load factor ' */

    public static void main(String[] args) throws Exception{

        hashTableStrings H = new hashTableStrings(101);
        H = readFile(H);  //("glove.6B.50d.txt");
        wordComp("wordcomp.txt", H);

        System.out.println("Load Factor: " +  H.loadFactor());
        System.out.println("Percentage of Empty List: " + H.emptyPer());
        System.out.println("Standard Deviation: " + H.standardDev());
    }

    public static hashTableStrings readFile(hashTableStrings H) throws IOException{
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

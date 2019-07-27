import java.io.*;
import java.util.*;

/**
 * 
 * Purpose: The purpose of this lab is to take the similarities of the a list of words
 *  from lab 6, and implement them into a graph using Kruskal's Minimum Spanning Tree. For input,
 *  an edge list representation is used.
 *
 *  Second, unrelated to this an unweighted undirected graph is created from an adjacency list.
 *
 */


public class graphs {

    public static void main(String[] args) throws IOException {
        int xMax = 100;
        int yMax = 100;

        /** Adjacency List **/
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter your desired number of vertices for the graph: ");
        int numOfVertices = scnr.nextInt();

        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);
        StdDraw.setPenColor(StdDraw.BLACK);

        graphNode[] G = createAdjList(numOfVertices);
        System.out.println("Now showing the graph:");

        /* Creating space around the imaginary circle for the vertices */
        double degree = (2 * Math.PI) / numOfVertices;
        calculations(G, degree, 30); //from here, the draw method is called
        System.out.println("Please press [enter] to continue...");
        pause();

        /** Kruskal's Minimum Spanning Tree **/
        System.out.println("Now the Minimum Spanning Tree");

        /** HASH **/
        hashTableStrings H = new hashTableStrings(15001);
        H = readFileForHash(H);  //("glove.6B.50d.txt");

        /* Reading the file and adding words into the array */
        ArrayList<String> lab6words = new ArrayList<>();
        lab6words = readFileForArrStrings(lab6words); //"Lab6Words"

        /* Search and Compare */
        edgeNode list = wordCompInEL(H, lab6words);

        edgeNode MST = KruskalMst(list);
        printGraph(MST); //print results

    }

                /** Kruskal's Minimum Spanning Tree **
                 ***********************************/
   /** Creates the Minimum Spanning Tree **/
    public static edgeNode KruskalMst(edgeNode G, graphNode[] g){
        edgeNode MST = null;
        DSF S = new DSF(g.length);

        for(edgeNode t = G; t != null; t = t.next){
            if(S.union(t.source, t.dest) == 1) //if "t" does not create a cycle
                MST = new edgeNode(t.source, t.dest, t.cost, MST); //add "t" to MST
        }

        return MST;
    }

    /** Read File **/
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

    /** Word Comparison **/
    /* Comparing words in the edgeNode */
    public static edgeNode wordCompInEL(hashTableStrings H, ArrayList<String> list) throws IOException{
        edgeNode G = null;

        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                sNode columnA = H.search(list.get(i));
                sNode columnB = H.search(list.get(j));

                /* Cosine Distance */
                float distance = cosDistance(columnA.embedding, columnB.embedding);
                float compareA = (float)Math.sqrt(cosDistance(columnA.embedding, columnA.embedding));
                float compareB = (float)Math.sqrt(cosDistance(columnB.embedding, columnB.embedding));

                edgeNode completeList = new edgeNode(list.get(i), list.get(j),((double)(distance/(compareA*compareB))),G);
                for(edgeNode t = completeList; t != null; t = t.next){
                    G = new edgeNode(t.source, t.dest, t.cost, G); //add edge to G
                }

            }
        }

        return G;
    }

    public static float cosDistance(float[] columnA, float[] columnB){
        float total = 0;

        for (int i = 0; i < 50; i++){
            total += columnA[i] * columnB[i];
        }

        return total;
    }

    /** Printing results **/
    public static void printGraph(edgeNode G){
        for(edgeNode t = G; t != null; t = t.next){
            System.out.println(t.source + " > " + t.dest + " = " + t.cost);
        }
    }


                /** Adjacency List **
                 * *******************/
    /* Creating the adjacency list */
    public static graphNode[] createAdjList(int numOfVertices){
        graphNode[] G = new graphNode[numOfVertices];

        for(int i = 0; i < numOfVertices; i++){
            for(int j = 0; j < numOfVertices; j++){
                G[i] = new graphNode((i+1)%numOfVertices, j);
            }
        }

        return G;
    }

    /* Calculates the equal spacing degrees for drawing and creates an array of values to be used for drawing */
    public static void calculations(graphNode[] G, double degree, int size){
        double[] xCoordinate = new double[G.length];
        double[] yCoordinate = new double[G.length];

        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G.length; j++) {
                /* Keep the points within the imaginary circle */
                xCoordinate[i] = 62.5 + (size * Math.cos(i * degree));
                yCoordinate[i] = 62.5 + (size * Math.sin(i * degree));
            }
        }

        draw_adj(xCoordinate, yCoordinate, G, G.length);

    }

    /** Drawing **/
    /* From an adjacency list, an unweighted/undirected graph is drawn */
    public static void draw_adj(double[] xCoor, double[] yCoor, graphNode[] G, int numofVertices){

        /* Necessary for connecting all vertices to each other */
        for (int i = 0; i < numofVertices; i++) {
            for (graphNode t = G[i]; t != null; t = t.next) {
                StdDraw.line(xCoor[i], yCoor[i], xCoor[t.dest], yCoor[t.dest]);
                StdDraw.line(xCoor[i%2], yCoor[i%2], xCoor[t.dest], yCoor[t.dest]);
                StdDraw.line(xCoor[(i+1)%2], yCoor[(i+1)%2], xCoor[t.dest], yCoor[t.dest]);
                StdDraw.line(xCoor[(i+2)%3], yCoor[(i+2)%3], xCoor[t.dest], yCoor[t.dest]);

            }
        }

        for (int i = 0; i < numofVertices; i++){
            /* Misc */
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(xCoor[i], yCoor[i], 5);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.circle(xCoor[i], yCoor[i], 5);
            StdDraw.text(xCoor[i], yCoor[i], Integer.toString(i));

        }

    }

    public static void pause(){
        Scanner scnr = new Scanner(System.in);
        scnr.nextLine();
    }
}

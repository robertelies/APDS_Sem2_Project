package Graph_Folder;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile_G {

    //helping funtion
    private String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    //main varibales
    private String filename;
    private Graph graph;
    private Node_G mostfollowing;
    public int numusers;

    public int[] counters;

    //constructor + read the file
    public ReadFile_G(String Filename) throws IOException {
        this.filename = Filename;

        //read values from the text file

        BufferedReader in = new BufferedReader( new java.io.FileReader("C:\\Users\\marce\\Desktop\\PAED_2ndSem\\TextFiles\\graphs\\"+ filename +".txt"));


        //we define a variable to store the info of a user all in the same String
        String str;
        int numUsers = 0;

        //read the number of users and pass to "int"
        String index_loop = in.readLine();

        numUsers = Integer.parseInt(index_loop);

        graph = new Graph(numUsers);

        this.numusers = numUsers;

        this.counters = new int[numUsers];

        //we define an array of strings to store the all the user info provided
        //uis = User Info Separated
        String[] uis;

        //we define an array of string to store the interests of a single user
        String[] interests;

        //COUNTER TIME
        int start = 0;


        for (int i = 0; i < numUsers; i++) {



            //we scan a user and all his info separated by ";"
            //we add a "space" of each line to avoid errors when reading the file
            str = in.readLine() + " ";
            //uis = User Info Separated
            uis = str.split(";");

            interests = uis[3].split(",");

            //we remove the extra space we added before to avoid errors
            if(interests[interests.length - 1].charAt(interests[interests.length - 1].length()-1) == ' '){
                interests[interests.length - 1] = removeLastCharacter(interests[interests.length - 1]);
            }


            //we introduce the info created into a new node inside the list of nodes

            graph.putNode(Integer.parseInt(uis[0]), uis[1], uis[2], interests);

            counters[i]++;




        }

        //lets read the interations now -------------------------------------------------------------------------------------------

        index_loop = in.readLine();

        numUsers = Integer.parseInt(index_loop);
        //System.out.println(numUsers);

        int prev = 0;
        int count = 0;
        int most_count = 0;


        //stores the info that will be stored in the edges
        int[] interactions = new int[4];
        for (int i = 0; i < numUsers; i++) {

            //we scan a user and all his info separated by ";"
            //we add a "space" of each line to avoid errors when reading the file
            str = in.readLine() + " ";
            //uis = User Info Separated
            uis = str.split(";");

            //we remove the extra space we added before to avoid errors
            if (uis[uis.length - 1].charAt(uis[uis.length - 1].length() - 1) == ' ') {
                uis[uis.length - 1] = removeLastCharacter(uis[uis.length - 1]);
            }

            //transform the string to ints as they are all numbers
            for (int o = 0; o < uis.length; o++) {
                interactions[o] = Integer.parseInt(uis[o]);
            }

            //store the interaction into Edges
            graph.putEdge(interactions[0], graph.getOneNode(interactions[1]), interactions[2], interactions[3]);
            //listNodes.get(uis[0]).setNewEdge(new Graph_Folder.Edge_M( listNodes.get(uis[1]) , interactions[2], interactions[3]) );

            // Get the most folling user- -  - --  -- - - - - - - -- - - -- - -- - -- - - -
            if(prev == interactions[0]){
                count++;
                if(count > most_count) {
                    this.mostfollowing = graph.getOneNode(interactions[0]);
                    most_count = count;
                }
            }else{
                count = 1;
            }
            prev = interactions[0];
            //finish the most following user - - -  -  - - - - -- - -  - - - - -  -- - - - -
        }

        in.close();


    }

    public int[] getcoords(){
        int counter = 0;
        int counterV2 = 0;
        int[] returnity = new int[numusers];
        for(int one: this.counters ){



            returnity[counterV2] = (int) (one);
            counterV2++;
        }
        return returnity;
    }



    //return the graph
    public Graph getGraph() {
        return graph;
    }

    public Node_G getMostfollowing() {
        return mostfollowing;
    }
}

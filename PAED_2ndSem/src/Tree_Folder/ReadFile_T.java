package Tree_Folder;

import Graph_Folder.Graph;
import Graph_Folder.Node_G;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile_T {
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
    private Tree tree;
    private Node_T mostfollowing;
    public int numusers;

    public inte[] counters;

    //constructor + read the file
    public ReadFile_T(String Filename) throws IOException {


        this.filename = Filename;

        //read values from the text file

        BufferedReader in = new BufferedReader( new java.io.FileReader("C:\\Users\\marce\\Desktop\\PAED_2ndSem\\TextFiles\\trees\\"+ filename +".txt"));


        //we define a variable to store the info of a user all in the same String
        String str;
        int numUsers = 0;

        //read the number of users and pass to "int"
        String index_loop = in.readLine();

        numUsers = Integer.parseInt(index_loop);



        //we define an array of strings to store the all the user info provided
        //uis = User Info Separated
        String[] uis;

        //lets read the ROOT node
        str = in.readLine();
        uis = str.split(";");

        this.numusers = numUsers;
        this.counters= new inte[numUsers];
        for(int yu = 0; yu < numUsers; yu++){
            this.counters[yu] = new inte();
        }


        tree = new Tree(new Node_T(Integer.parseInt(uis[0]), uis[1], uis[2], uis[3], Integer.parseInt(uis[4])));
        tree.totalNodes = numUsers;
        for (int i = 0; i < numUsers - 1; i++) {


            //we scan a user and all his info separated by ";"
            //we add a "space" of each line to avoid errors when reading the file
            str = in.readLine();
            //uis = User Info Separated
            uis = str.split(";");

            //we introduce the info created into a new node inside the list of nodes
            Node_T newNode = new Node_T(Integer.parseInt(uis[0]), uis[1], uis[2], uis[3], Integer.parseInt(uis[4]));
            tree.addNode(tree.superiorNode, newNode, counters[i] );


            //System.out.println(end - start);
        }
        in.close();
    }
    public int[] getcoords(){
        int counter = 0;
        int counterV2 = 0;
        int[] returnity = new int[tree.totalNodes];
        for(inte one: this.counters ){


            returnity[counterV2] = one.x;


            counterV2++;

        }
        return returnity;
    }

    public Tree getTree() {
        return tree;
    }
}


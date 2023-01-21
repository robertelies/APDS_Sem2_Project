package RTree_Folder;

import Table_Folder.Table;
import Tree_Folder.inte;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile_R {
    private RTree rTree;

    private int numusers;

    public inteR[] counters;

    //constructor + read the file
    public ReadFile_R(String Filename) throws IOException {

        //read values from the text file
        BufferedReader in = new BufferedReader(new java.io.FileReader("C:\\Users\\marce\\Desktop\\PAED_2ndSem\\TextFiles\\r_trees\\" + Filename + ".txt"));

        //we define a variable to store the info of a user all in the same String
        String str;
        int numColors = 0;

        //read the number of users and pass to "int"
        String index_loop = in.readLine();
        numColors = Integer.parseInt(index_loop);
        this.numusers = numColors;

        //timecounter
        this.counters= new inteR[numusers];
        for(int yu = 0; yu < numusers; yu++){
            this.counters[yu] = new inteR();
        }

        //we define an array of strings to store the all the user info provided
        //uis = User Info Separated
        String[] uis;

        //read the first point of the file
        str = in.readLine();
        uis = str.split(";");
        Point_R firstPoint = new Point_R(new Point_R(Float.parseFloat(uis[0]), Float.parseFloat(uis[1]), Float.parseFloat(uis[2]), uis[3]));

        //Now we can create the Rtree using the previus node
        rTree = new RTree(numColors, new rectangle_R(firstPoint));

        for (int i = 0; i < numColors - 1; i++) {

            //we scan a user and all his info separated by ";"
            str = in.readLine();
            //uis = User Info Separated
            uis = str.split(";");


            //insert
            rTree.superiorRectangle.insertPoint_R(new Point_R(Float.parseFloat(uis[0]), Float.parseFloat(uis[1]), Float.parseFloat(uis[2]), uis[3]),  counters[i] );



        }
        in.close();
    }


    public int[] getcoords(){
        int counter = 0;
        int counterV2 = 0;
        int[] returnity = new int[numusers];
        for(inteR one: this.counters ){


            returnity[counterV2] = one.x;


            counterV2++;

        }
        return returnity;
    }
    public RTree getRTree() {
        return rTree;
    }
}

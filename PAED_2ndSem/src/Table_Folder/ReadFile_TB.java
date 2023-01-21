package Table_Folder;

import Graph_Folder.Graph;
import Graph_Folder.Node_G;
import RTree_Folder.inteR;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile_TB {

    private int counter;

    private Table table;

    private int numBusi;

    public inteT[] counters;

    //constructor + read the file
    public ReadFile_TB(String Filename) throws IOException {

        counter = 0;
        //read values from the text file
        BufferedReader in = new BufferedReader( new java.io.FileReader("C:\\Users\\marce\\Desktop\\PAED_2ndSem\\TextFiles\\tables\\"+ Filename +".txt"));

        //we define a variable to store the info of a user all in the same String
        String str;
        int numBusiness = 0;

        //read the number of users and pass to "int"
        String index_loop = in.readLine();
        numBusiness = Integer.parseInt(index_loop);

        this.numBusi = numBusiness;

        //timecounter
        this.counters= new inteT[numBusiness];
        for(int yu = 0; yu < numBusiness; yu++){
            this.counters[yu] = new inteT();
        }

        //we define an array of strings to store the all the user info provided
        //uis = User Info Separated
        String[] uis;

        table = new Table(numBusiness);

        for (int i = 0; i < numBusiness - 1; i++) {

            //we scan a user and all his info separated by ";"
            str = in.readLine();
            //uis = User Info Separated
            uis = str.split(";");

            //insert
            table.addBusiness(uis[0], uis[1], Integer.parseInt(uis[2]), counters[i] );
            counter++;
        }

        in.close();
    }

    public int[] getcoords(){
        int counter = 0;
        int counterV2 = 0;
        int[] returnity = new int[numBusi];
        for(inteT one: this.counters ){


            returnity[counterV2] = one.x;


            counterV2++;

        }
        return returnity;
    }
    public Table getTable() {
        return table;
    }
}


package Table_Folder;

import RTree_Folder.RTree;

import javax.swing.*;

public class Visialization_Table {

    private static final int SIZE = 750;
    private static JFrame f = new JFrame("Draw a line");

    RTree rTree;

    public static void draw(int[] days){

        f.getContentPane().add(new graphic_Table(days, SIZE));
        f.setSize(SIZE, SIZE);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


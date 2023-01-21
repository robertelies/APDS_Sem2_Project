package RTree_Folder;

import javax.swing.*;

public class Visialization_RTree {

    private static final int SIZE = 750;
    private static JFrame f = new JFrame("Draw a line");

    RTree rTree;

    public static void draw(RTree rtree){

        f.getContentPane().add(new graphic_RTree(rtree.superiorRectangle, SIZE));
        f.setSize(SIZE, SIZE);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }





}


package Tree_Folder;

import Graph_Folder.Edge_G;

import java.util.ArrayList;
import java.util.List;

public class Node_T implements TreePrinter.PrintableNode{

    public int id;
    public String name;
    public String language;
    public String cost;
    public int timestamp;
    public Node_T left;
    public Node_T right;

    //AVL NODE
    private int height;

    public boolean visited;


    public Node_T(int id, String name, String language, String cost, int timestamp) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.cost = cost;
        this.timestamp = timestamp;

        this.left = null;
        this.right = null;

        this.visited = false;

        this.height = 1;
    }


    @Override
    public TreePrinter.PrintableNode getLeft() {
        if(left == null){
            return null;
        }else{
            return left;
        }
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        if(right == null){
            return null;
        }else{
            return right;
        }
    }

    @Override
    public String getText() {
        return Integer.toString(timestamp);
    }

    public int getHeight(){
        return this.height;
    }

    public void setHeight(int height){
        this.height = height;
    }


}

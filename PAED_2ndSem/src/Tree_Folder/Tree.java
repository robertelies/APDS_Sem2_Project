package Tree_Folder;

import Graph_Folder.DIY_printer_graph;
import Graph_Folder.Edge_G;
import Graph_Folder.Node_G;

import java.time.Instant;
import java.util.LinkedList;

public class Tree  {


    public Node_T superiorNode;
    public int totalNodes;
    public int counterfeed;

    //---------------------------------------------------------------------CONSTRUCTOR
    public Tree(Node_T superiorNode) {
        this.superiorNode = superiorNode;

        this.totalNodes = 0;

        this.counterfeed = 0;

    }

    //---------------------------------------------------------------------AVL
    int max(int a, int b) {
        return Math.max(a, b);
    }



    //---------------------------------------------------------------------ADDNODE
    public void addNode(Node_T sourceNode, Node_T NodeToIntroduce, inte counter){
        //timecounter
        counter.x++;
        //we first choose the direction (left, right) depending of the ID of the Node
        if(NodeToIntroduce.timestamp < sourceNode.timestamp){
            //then, depending on if it has a value we introduce the new node or not
            if(sourceNode.left == null){
                sourceNode.left = NodeToIntroduce;
            }else {
                addNode(sourceNode.left, NodeToIntroduce, counter);
            }
        }else{
            if(sourceNode.right == null) {
                sourceNode.right = NodeToIntroduce;
            }else{
                addNode(sourceNode.right, NodeToIntroduce, counter);
            }
        }
    }

    //---------------------------------------------------------------------ERASE NODE
    public Node_T eraseNode_T(Node_T node , int id, Node_T parent, int left_or_right){  //1 = left, 2 = right

        if(node.id != id){
            if(node.left != null) eraseNode_T(node.left, id, node, 1);
            if(node.right != null) eraseNode_T(node.right,  id, node, 2);
        }else {
            //if the id is equal we have to remove it
            if(node.left == null && node.right == null){

                if(left_or_right == 1){
                    parent.left = null;
                }else if(left_or_right == 2){
                    parent.right = null;
                }

                node = null;
            }else if(node.right != null){
                changeNodes(successor(node), node);
                node.right = eraseNode_T(node.right, node.id, node, 2);
            }else{
                changeNodes(predecessor(node), node);
                node.left = eraseNode_T(node.left, node.id, node, 1);
            }
        }
        return node;
    }

    private Node_T successor(Node_T Succesor){
        Succesor = Succesor.right;
        while(Succesor.left != null) { Succesor = Succesor.left; }
        return Succesor;
    }

    private Node_T predecessor(Node_T Predecessor){
        Predecessor = Predecessor.left;
        while(Predecessor.right != null){ Predecessor = Predecessor.right; }
        return Predecessor;
    }


    private void changeNodes(Node_T final_node, Node_T erased_node){

        erased_node.id = final_node.id;
        erased_node.name = final_node.name;
        erased_node.timestamp = final_node.timestamp;
        erased_node.cost = final_node.cost;
        erased_node.language = final_node.language;
    }

    //---------------------------------------------------------------------SHOW SORTED TREE
    public void printTreeNodes(Node_T node){
        if(node.left != null){
            printTreeNodes(node.left);
        }
        DIY_printer_tree.printNodeTime(node);
        if(node.right != null){
            printTreeNodes(node.right);
        }
    }

    //---------------------------------------------------------------------SEARCH FOR A NODE
    public void searchForNode(Node_T node, int time){
        if(node.left == null && node.right== null){
            System.out.println("NO ALGORITHM FOUND\n");
        }else {
            if (node.timestamp > time) {
                if(node.left != null) {
                    searchForNode(node.left, time);
                }

            } else if (node.timestamp < time) {
                if(node.right != null) {
                    searchForNode(node.right, time);
                }
            } else {
                System.out.println("An algorithm was found... " + node.id + ") " + node.name +"\n");

            }
        }
    }

    //----------------------------------------------------------------------SEARCH A RANGE

    public void searchARange(int min, int max){
        searchARange(superiorNode, min, max);
    }

    private void searchARange(Node_T node, int min, int max){
        if(node != null && node.timestamp > min && node.timestamp > max){
            searchARange(node.left, min, max);

        }else if(node != null && node.timestamp < max && node.timestamp < min){
            searchARange(node.right, min, max);

        }else if(node != null){
            counterfeed++;
            System.out.println( node.id + ") " + node.name +" -> " +node.timestamp);

            if(node.left != null) searchARange(node.left, min, node.timestamp);

            if(node.right != null) searchARange(node.right, node.timestamp, max);
        }
    }

}

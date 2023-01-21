package Tree_Folder;

public class AVLTree {


    public Node_T superiorNode;


    //---------------------------------------------------------------------CONSTRUCTOR
    public AVLTree() {
    }

    //---------------------------------------------------------------------AUXILIAR FUNTIONS
    private int getMaxInt(int a, int b) {
        return Math.max(a, b);
    }

    private int getHeight(Node_T nodee){
        if( nodee == null){
            return -1;
        }else {
            return nodee.getHeight();
        }
    }

    private void updateHeight(Node_T node) {
        int rightHei = getHeight(node.right);
        int leftHei = getHeight(node.left);

        node.setHeight(getMaxInt(leftHei, rightHei) + 1);
    }

    private int getBalanceFactor(Node_T N) {

        if(N == null){
            return 0;
        }
            return getHeight(N.right) - getHeight(N.left);

    }


    private Node_T rotateRight(Node_T node) {
        //copy the left node to modify it after
        Node_T leftChild = node.left;
        node.left = leftChild.right;
        //bring back node
        leftChild.right = node;
        //update the height in the following order
        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }



    private Node_T rotateLeft(Node_T node) {
        //copy the right node to modify it after
        Node_T rightChild = node.right;
        node.right = rightChild.left;
        //bring back node
        rightChild.left = node;
        //update the height in the following order
        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }




    private Node_T rebalance(Node_T node) {
        int balanceFactor = 0;
        if(node != null){
            balanceFactor = getBalanceFactor(node);
        }

        //if the balance factor is 0 there is nothing to to do here
        if(balanceFactor == 0){
            return node;
        }

        // if we have more height on the left:
        if (balanceFactor < -1) {
            if (node.left == null || getBalanceFactor(node.left) <= 0) {
                // if the node.left.left is less than the node.left.right we only rotate right
                node = rotateRight(node);
            } else {
                // if the node.left.left is bigger than the node.left.right we rotate left the node.left and then the node to the right
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }else{
            // if we have more height on the right:
            if (node.left == null || getBalanceFactor(node.right) >= 0) {
                // if the node.right.right is less than the node.right.left we only rotate left
                node = rotateLeft(node);
            } else {
                // if the node.right.right is bigger than the node.left.right we rotate right the node.right and then the node to the left
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }

        return node;
    }

    public void insertNode(int id, String name, String language, String cost, int timestamp) {
        superiorNode = insertNode(id, name, language, cost, timestamp, superiorNode);
    }

    Node_T insertNode(int id, String name, String language, String cost, int timestamp, Node_T node) {

        if (node == null) {
            node = new Node_T(id, name, language, cost, timestamp);

        } else if (timestamp < node.timestamp) {
            node.left = insertNode(id, name, language, cost, timestamp, node.left);

        } else if (timestamp > node.timestamp) {
            node.right = insertNode(id, name, language, cost, timestamp, node.right);
        }

        return rebalance(node);
    }


    //---------------------------------------------------------------------SHOW SORTED TREE
    public void printTreeNodes(){
        printTreeNodes(this.superiorNode);
    }
    private void printTreeNodes(Node_T node){
        if(node.left != null){
            printTreeNodes(node.left);
        }
        DIY_printer_tree.printNodeTime(node);
        if(node.right != null){
            printTreeNodes(node.right);
        }
    }

    //---------------------------------------------------------------------SEARCH FOR A NODE

    public void searchForNode(int time){
        searchForNode(this.superiorNode, time);
    }
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

            System.out.println( node.id + ") " + node.name +" -> " +node.timestamp);

            if(node.left != null) searchARange(node.left, min, node.timestamp);

            if(node.right != null) searchARange(node.right, node.timestamp, max);
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
        while(Succesor.left != null){
            Succesor = Succesor.left;
        }
        return Succesor;
    }

    private Node_T predecessor(Node_T Predecessor){
        Predecessor = Predecessor.left;

        while(Predecessor.right != null){
            Predecessor = Predecessor.right;
        }
        return Predecessor;
    }

    private void changeNodes(Node_T final_node, Node_T erased_node){

        erased_node.id = final_node.id;
        erased_node.name = final_node.name;
        erased_node.timestamp = final_node.timestamp;
        erased_node.cost = final_node.cost;
        erased_node.language = final_node.language;
    }


}

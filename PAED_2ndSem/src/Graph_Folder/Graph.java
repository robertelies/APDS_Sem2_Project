package Graph_Folder;


import java.util.*;

public class Graph {

    //declaration of the array visites and the list of nodes
    private boolean[] visited;
    private Node_G[] listNodes;

    private int maximun;

    //declaration of the stack that will be used in the drama option
    Stack<Node_G> stack = new Stack<Node_G>();


    long time1;
    long time2;

    //constructor of the graph
    public Graph(int total_users) {
        this.maximun = (total_users * 4) ;

        //inside the graph set the visited to FALSE
        this.visited = new boolean[maximun];
        Arrays.fill(visited, Boolean.FALSE);


        //initialize the list of nodes
        this.listNodes =new Node_G[maximun];
        this.time1 = 0;
        this.time2 = 0;

    }

    //insert a node function
    public void putNode(int id, String name, String alias, String[] interest){
        Node_G node = new Node_G(id, name, alias, interest);
        this.listNodes[id] = node;
    }

    //insert an edge function
    public void putEdge(int id, Node_G following, int timestamp, int interactions){
        this.listNodes[id].setNewEdge(new Edge_G(following, timestamp, interactions));
    }

    //get a node
    public Node_G[] getListNodes() {
        return listNodes;
    }

    public Node_G getOneNode(int id){
        return listNodes[id];
    }

    //DFS algorithms---------------------------------------------------------------------------------------------------------

    public void DFS(Node_G node) {
        //set the Node to visited using the id of itself in the array Visited

        if(!visited[node.getId()] ){

            //set the node to visited
            visited[node.getId()] = true;
            //print the node
            System.out.print(node.getId() + ") " + node.getName() + " Follows: ");
            //print the connections nodes
            for(Edge_G edge_print: node.getEdges()) System.out.print(edge_print.getFollowing().getName() + " ");
            System.out.print("\n");
            //for each relation we check if it has been visited, and we call recursion
            for(Edge_G edge :node.getEdges() ){
                if(!visited[edge.getFollowing().getId()]){
                    DFS(edge.getFollowing() );
                }
            }
        }
    }

    public void DFS_notConnected(){
        for (Node_G node : listNodes){
            if(node != null) {
                if (!visited[node.getId()]) {
                    System.out.println("\nNew Circle found:");
                    DFS(node);
                }
            }
        }
        //set to false the visited list
        Arrays.fill(visited, Boolean.FALSE);
    }

    //BFS algorithm------------------------------------------------------------------------------------------------------
    void BFS(Node_G node) {

        //create a linkedlist in order to store the nodes that will be analyzed in the future
        LinkedList<Node_G> queue = new LinkedList();
        queue.add(node);
        visited[node.getId()] = true;

        //while there are nodes inside the linkedlist..
        while(queue.size() != 0){
            //print the first element in the queue
            if(visited[queue.getFirst().getId()] ) {
                System.out.println("Here are their followed accounts:\n");
            }
            //for each edge that the first node in the queue has, we will store the connections in the queue
            for(Edge_G edge: queue.poll().getEdges()){

                if(!visited[edge.getFollowing().getId()]){

                    DIY_printer_graph.printNode(edge.getFollowing());
                    System.out.print("\n");
                    visited[edge.getFollowing().getId()] = true;
                    queue.add(edge.getFollowing());
                }

            }
        }
    }

    public void BFS_notConnected(Node_G firstNode){
        BFS(firstNode);

        for (Node_G node : listNodes){
            if(node != null) {
                if (!visited[node.getId()]) {
                    //System.out.println("\n - - - - - New Circle found: - - - - - ");
                    BFS(node);
                }
            }
        }
        //set to false the visited list
        Arrays.fill(visited, Boolean.FALSE);
    }

    //Recommend users algorithm -------------------------------------------------------------------------------------------
    public void recommendUsersFiller(int id, int num_of_recommends){

        //define the variables and arrays
        recommendUsers[] recommedList;
        //initialize the rec0mend list
        recommedList = new recommendUsers[maximun];
        for(int e = 1; e < maximun; e++) {
            recommedList[e] = new recommendUsers();
        }

        System.out.println("id: " +id + "\n");
        Node_G mainNode = listNodes[id];

        int counter_final = 0;
        int bigPunt = 0;
        int []savePunt = new int[num_of_recommends];
        Node_G[]finalNodes = new Node_G[num_of_recommends];

        //we set the node that the ask for recommends with a negative punctuation
        recommedList[id].addPunctuation(-10000000);


        //1rst part - - - We get all the info needed, and we set up a punctuation
        for(Node_G node : listNodes){
            if(node != null) {

                // 1) check if the user is being followed by actual user
                for(Edge_G single_edge : mainNode.getEdges() ){
                    if(single_edge.getFollowing() == node){
                        recommedList[node.getId()].setFollowed();
                        //add to the punctuation
                        recommedList[node.getId()].addPunctuation(-10000000);
                    }
                }

                // 2) check if the users that the main User follows him
                for (Edge_G edge : mainNode.getEdges()) {
                    for (Edge_G edge_2 : edge.getFollowing().getEdges()) {
                        if (edge_2.getFollowing() == node) {
                            recommedList[node.getId()].newFollowedByFollowing(edge.getFollowing());
                            //add to the punctuation
                            recommedList[node.getId()].addPunctuation(1);
                        }
                    }
                }

                // 3) check if the user has same interests as the mainUser
                for (String interest : mainNode.getInterests()) {
                    for (String interest_2 : node.getInterests()) {
                        if (Objects.equals(interest, interest_2)) {
                            recommedList[node.getId()].addSimilarInterest(interest_2);
                            //add to the punctuation
                            recommedList[node.getId()].addPunctuation(4);
                        }
                    }
                }

                // 4) check if the user is following the main user
                for(Edge_G following: node.getEdges()){
                    if(following.getFollowing() == mainNode){
                        recommedList[node.getId()].setFollowback();
                        //add to the punctuation
                        recommedList[node.getId()].addPunctuation(7);
                    }
                }

            }
        }



        //2nd part - - - We find the N most similar to the user

        for(int e = 0; e < num_of_recommends; e++){
            bigPunt = 0;

            for(int q = 0; q < maximun; q++){
                if (listNodes[q] != null) {
                    if(recommedList[q].getPunctuation() > bigPunt) {
                        bigPunt = recommedList[q].getPunctuation();
                        finalNodes[e] = listNodes[q];
                    }
                }
            }
            savePunt[e] = recommedList[finalNodes[e].getId()].getPunctuation();
            recommedList[finalNodes[e].getId()].addPunctuation(-1000000);
        }

        //3rd part - - - print the recommended users
        for(int i = 0; i < num_of_recommends; i++){
            //assign the recommended user that we will work with to make it easier
            recommendUsers final_rec = recommedList[finalNodes[i].getId()];
            //return their real punt
            recommedList[finalNodes[i].getId()].setPunctuation(savePunt[i]);
            DIY_printer_graph.printNode(finalNodes[i]);
            //print the last phrase
            counter_final = 0;
            System.out.print("\tCriteria: ");

            if(final_rec.getFolloback() ) {
                System.out.print("They follow you");
                counter_final++;
            }
            if (final_rec.getNum_similarInterests() > 0) {
                if(counter_final > 0){
                    System.out.print(", you share " + final_rec.getNum_similarInterests() + " interests");
                }else{
                    System.out.print("You share " + final_rec.getNum_similarInterests() + " interests");
                }
                counter_final++;
            }

            if (final_rec.getNum_followedByFollowing() > 0) {
                if (counter_final == 0) {
                    System.out.print("Is followed by ");
                } else {
                    System.out.print(" and is followed by ");
                }

                for (int t = 0; t < final_rec.getNum_followedByFollowing(); t++) {
                    System.out.print(final_rec.getFollowedByFollowing()[t].getId() + " - " + final_rec.getFollowedByFollowing()[t].getName() + " (" + final_rec.getFollowedByFollowing()[t].getAlias() + ")");
                    if (t + 1 < final_rec.getNum_followedByFollowing()) {
                        System.out.print(", ");
                    }
                }
            }

            System.out.println("");
            System.out.println("\tPunctuation: " + recommedList[finalNodes[i].getId()].getPunctuation()  + "\n");
        }
    }

    //contextualizing drama---------------------------------------------------------------------------------------------

    public void contDrama(Node_G MostFollowedNode){

        //if there are nodes without visiting
        for (Node_G node : listNodes){
            if(node != null) {
                if (!visited[node.getId()]) {
                    System.out.println("\n - - - - - New Circle found: - - - - - " + node.getId());
                    drama_visiter(node);
                }
            }
        }

        //print the stack
        while(!stack.empty()){
            Node_G aux = stack.pop();
            System.out.print(aux.getId() + " - " + aux.getName() + " (" + aux.getAlias() + ")");
            aux.printSuccessors();
            if(stack.size() != 0){
                System.out.println("↓");
            }
        }

        //set to false the visited list
        Arrays.fill(visited, Boolean.FALSE);
    }

    public void drama_visiter(Node_G node){
        for(Node_G successor : node.getSuccessors()){
            if(!visited[successor.getId()]){
                drama_visiter(successor);
            }
        }

        visited[node.getId()] = true;
        stack.add(node);

    }

    //Helping with Networking--------------------------------------------------------------------------------------------

    //declaration of the variables used in the option D
    ArrayList<Node_G>[] walks;
    int []d ;

    public void setUpWalksAndD(){
        d = new int[maximun];
        walks = new ArrayList[maximun];
        for(int i = 0; i < maximun; i++){
            walks[i] = new ArrayList<Node_G>();
            d[i] = 2147483647;//max int value
        }
    }

    public void HelpingWithNetwork(int start, int end){
        setUpWalksAndD();
        ArrayList<Node_G> final_list = dijkstra(listNodes[start], listNodes[end]);
        for (Node_G name : final_list) {
            System.out.println(name.getId());
        }
    }

    public ArrayList<Node_G> dijkstra(Node_G start, Node_G end){

        Node_G current = start;
        for(int i = 0; i < maximun; i++){

            if(listNodes[i] != null && !visited[i]){

                for(Edge_G adj: current.getEdges()){
                    if(!visited[adj.getFollowing().getId()]){
                        int possible = d[current.getId()] + adj.getInteractions();
                        if(d[adj.getFollowing().getId()] > possible){
                            d[adj.getFollowing().getId()] = possible;
                            walks[current.getId()].add(adj.getFollowing());
                        }
                    }
                }
                visited[current.getId()] = true;
                current = getMinimunNode();

            }
        }
        int o = 0;
        for(ArrayList<Node_G> listnode: walks) {
            if (listNodes[o] != null) {
                System.out.println("START: " + start.getId() + " -> END: " + listNodes[o].getId());
                for (Node_G nodee : listnode) System.out.println(" \t\t" + nodee.getId());

            }
            o++;
        }
        return walks[end.getId()];
    }

    public Node_G getMinimunNode(){
        int j=0;
        int mindis = 0;
        for (int i = 0; i < d.length; i++) {
            if (!visited[i]){
                if(d[i]<mindis){
                    mindis=d[i];
                    j=i;
                }
            }
        }
        return listNodes[j];
    }



}

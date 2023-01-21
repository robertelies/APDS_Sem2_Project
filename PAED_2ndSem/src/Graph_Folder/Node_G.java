package Graph_Folder;

import java.util.ArrayList;
import java.util.List;

public class Node_G {
    private int id;
    private String name;
    private String alias;
    private String[] interest;
    private List<Edge_G> list_edges;

    public Node_G() {
    }

    public Node_G(int id, String name, String alias, String[] interest) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.interest = interest;
        this.list_edges = new ArrayList<>();

    }

    //setter
    public void setNewEdge(Edge_G connection)
    {
        this.list_edges.add(connection);
    }

    public void setInterest(String aux, int index){
        this.interest[index] = aux;
    }

    //getter
    public int getId() { return id; }
    public String getName() { return name; }
    public String getAlias() { return alias; }
    public String[] getInterests() { return interest; }
    public String getOneInterest(int id_new) { return interest[id_new]; }
    public Edge_G getOneEdge(int id_new) { return list_edges.get(id_new); }
    public List<Edge_G> getEdges(){return list_edges;}
    public List<Node_G> getSuccessors(){
        List<Node_G> solution = new ArrayList<Node_G>();
        for(Edge_G edge : this.list_edges){
            solution.add(edge.getFollowing());
        }
        return solution;
    }
    public void printSuccessors(){

        for(Edge_G edge : this.list_edges){
            System.out.print(" " + edge.getFollowing().getId());
        }
        System.out.println();
    }

    //getter of the edges
    public String getNameEdge(int id_new){
        return list_edges.get(id_new).getFollowing().getName();
    }



}

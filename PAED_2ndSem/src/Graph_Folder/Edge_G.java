package Graph_Folder;

public class Edge_G {

    private Node_G following;
    private int timestamp;
    private int interactions;

    public Edge_G(Node_G following, int timestamp, int interactions) {
        this.following = following;
        this.timestamp = timestamp;
        this.interactions = interactions;
    }

    //setter
    public void setFollowing(Node_G aux){
        this.following = aux;
    }

    public void setInteractions(int interactions) {
        this.interactions = interactions;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    //getter

    public int getInteractions() {
        return interactions;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public Node_G getFollowing() {
        return following;
    }
}

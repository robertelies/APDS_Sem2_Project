package Graph_Folder;

public class recommendUsers {

    private boolean isFollowed;
    private int num_followedByFollowing;
    private Node_G[] followedByFollowing;

    private String similarInterests;
    private int num_similarInterests;

    private boolean followback;

    private int punctuation;

    public recommendUsers(){
        this.isFollowed = false;
        this.num_followedByFollowing = 0;
        this.num_similarInterests = 0;
        this.similarInterests = "p";
        this.followedByFollowing = new Node_G[50];
        this.punctuation = 0;
        this.followback = false;

    }

    //if the user is being followed by the mother User we will not take into account in the future
    public void setFollowed() {
        isFollowed = true;
    }
    //getter if the user is being followed
    public boolean getIsFollowed(){
        return isFollowed;
    }


    //add if the recommended user is followed by a followed user of the mother user
    public void newFollowedByFollowing(Node_G node){
        followedByFollowing[num_followedByFollowing] = node;
        num_followedByFollowing++;
    }
    //get number of people that follows the recommended user by a following of the mother user
    public int getNum_followedByFollowing(){
        return num_followedByFollowing;
    }
    //get the list of users that follows the recommended user
    public Node_G[] getFollowedByFollowing() {
        return followedByFollowing;
    }


    //we add a similar interest
    public void addSimilarInterest( String interest){
        num_similarInterests++;
        if(similarInterests == "p"){
            similarInterests = interest;
        }else {
            similarInterests = similarInterests + ", " + interest;
        }
    }

    //we return the number of similar interest and the string of interests
    public int getNum_similarInterests(){
        return num_similarInterests;
    }

    public String getSimilarInterests() {
        return similarInterests;
    }

    //punctuation setter and getter
    public void addPunctuation(int punctuationn) {
        this.punctuation = this.punctuation + punctuationn;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public int getPunctuation() {
        return punctuation;
    }

    //followback setter and getter
    public void setFollowback() {
        this.followback = true;
    }
    public boolean getFolloback(){
        return followback;
    }

}

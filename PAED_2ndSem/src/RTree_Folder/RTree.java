package RTree_Folder;

public class RTree {

    public rectangle_R superiorRectangle;

    private int numColors;

    //auxiliary attributes
    public int searchNumber;
    public int searchNumberMAX;



    public RTree( int numColors, rectangle_R superiorRectangle) {
        this.superiorRectangle = superiorRectangle;
        this.numColors = numColors;

    }
}

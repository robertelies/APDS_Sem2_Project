package RTree_Folder;

import java.util.ArrayList;

public abstract class figure {
    public rectangle_R parent;
    public int modify;


    protected abstract Point_R getMinPoint();
    protected abstract Point_R getMaxPoint();
    protected abstract Point_R getCenter();

    protected abstract void printFigure();
    protected abstract ArrayList<figure> getDescendants();

    protected abstract void searchByArea(rectangle_R area, RTree rtree);

    protected abstract void erasePoint(float xcoor, float ycoor);


}

package RTree_Folder;

import java.util.ArrayList;

public class Point_R extends figure {

    public float x_coor;
    public float y_coor;
    public float radius;
    public String color_RGB;

    public Point_R(float x_coor, float y_coor, float radius, String color_RGB) {
        this.x_coor = x_coor;
        this.y_coor = y_coor;
        this.radius = radius;
        this.color_RGB = color_RGB;
        this.modify = 1;
    }

    public Point_R(Point_R other) {
        this.x_coor = other.x_coor;
        this.y_coor = other.y_coor;
        this.radius = other.radius;
        this.color_RGB = other.color_RGB;
    }

    @Override
    protected Point_R getMinPoint() {
        return this;
    }

    @Override
    protected Point_R getMaxPoint() {
        return this;
    }

    @Override
    protected Point_R getCenter() {
        return this;
    }

    @Override
    protected void printFigure() {
        System.out.println("\t\t" + this.color_RGB + " (" + this.x_coor + ", " + this.y_coor + ")" + " r= " + this.radius);
    }

    @Override
    protected ArrayList<figure> getDescendants() {
        return null;
    }

    @Override
    protected void searchByArea(rectangle_R area, RTree rtree) {

    }

    @Override
    protected void erasePoint(float xcoor, float ycoor) {
        System.out.println("ERROR ERASE POINT WRONG CALLED");
    }
}

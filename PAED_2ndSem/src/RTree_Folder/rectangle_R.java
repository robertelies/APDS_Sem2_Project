package RTree_Folder;

import java.util.ArrayList;

import static java.lang.Math.*;

public class rectangle_R extends figure{
    private final int MAX_NUMBER_OF_ELEMENTS = 3;


    public Point_R min_point;
    public Point_R max_point;

    public ArrayList<figure> descendants;


    public rectangle_R(Point_R same_point){
        min_point = new Point_R(same_point);
        max_point = new Point_R(same_point);

        descendants = new ArrayList<>();

        addDescendant(same_point);
    }
    public rectangle_R(rectangle_R rectangle){
        min_point = rectangle.min_point;
        max_point = rectangle.max_point;

        descendants = new ArrayList<>();
        addDescendant(rectangle);

    }

    //------------------------------------------------------------------------------------ADD POINT

    //esta funcion escoge entre los dos constructors de arriba
    public static rectangle_R newRectFromFigure(figure figure) {
        if (figure instanceof Point_R) {
            return new rectangle_R((Point_R) figure);
        }
        return new rectangle_R((rectangle_R) figure);
    }

    public void addDescendant(figure figure) {
        descendants.add(figure);
        increaseArea(figure);
        figure.parent = this;
    }


    public void insertPoint_R(Point_R newPoint_r, inteR countertime) {

        //count time
        countertime.x++;

        if(this.isleaf()){
            addDescendant(newPoint_r);

        }else{
            rectangle_R best = findBestDescendant(newPoint_r);
            best.insertPoint_R(newPoint_r, countertime);

        }

        increaseArea(newPoint_r);
        if(checkOverflow()){
            split();
        }

    }


    private void increaseArea(figure f) {
        this.min_point.x_coor = Math.min(f.getMinPoint().x_coor, min_point.x_coor);
        this.min_point.y_coor = Math.min(f.getMinPoint().y_coor, min_point.y_coor);
        this.max_point.x_coor = Math.max(f.getMaxPoint().x_coor, max_point.x_coor);
        this.max_point.y_coor = Math.max(f.getMaxPoint().y_coor, max_point.y_coor);

    }


    private boolean checkOverflow() {
        return descendants.size() > MAX_NUMBER_OF_ELEMENTS;
    }

    private void split() {

        rectangle_R parent1;
        rectangle_R parent2;
        figure[] figures = findTheFurthestFigures();
        parent1 = newRectFromFigure(figures[0]);
        parent2 = newRectFromFigure(figures[1]);

        ArrayList<figure> restOfFigures = getRestOfFigures(figures);

        for(figure single_figure : restOfFigures){
            if(parent1.checkIncrease(single_figure) < parent2.checkIncrease(single_figure)){
                parent1.addDescendant(single_figure);
            }else{
                parent2.addDescendant(single_figure);
            }
        }

        //if you are in the root we'll do something else
        if( this.parent != null) {
            //splits a rectangle in 2 and removes the previous rectangle with overflow
            this.parent.descendants.remove(this);
            this.parent.addDescendant(parent1);
            this.parent.addDescendant(parent2);
            this.parent = null;
        }else{
            //creates a new level between you the parents
            this.descendants.clear();
            this.addDescendant(parent1);
            this.addDescendant(parent2);
        }

    }

    private ArrayList<figure> getRestOfFigures(figure[] points) {

        ArrayList<figure> restOfPoints = new ArrayList<>();
        for(figure singleFig : this.descendants){
            if(singleFig != points[0] && singleFig != points[1]){
                restOfPoints.add(singleFig);
            }
        }
        return restOfPoints;
    }

    private figure[] findTheFurthestFigures() {
        figure [] Furthestfigures = new figure[2];
        float biggestDistance = 0;
        for(figure point1 : descendants){
            for(figure point2: descendants){
                if(getDistance(point1.getCenter(), point2.getCenter()) >= biggestDistance){
                    biggestDistance = getDistance(point1.getCenter(), point2.getCenter());
                    Furthestfigures[0] = point1;
                    Furthestfigures[1] = point2;
                }
            }
        }

        return Furthestfigures;
    }

    private float getDistance(Point_R point1, Point_R point2) {

        return (float) sqrt(pow((point2.x_coor - point1.x_coor), 2) + pow((point2.y_coor - point1.y_coor), 2));
    }

    private rectangle_R findBestDescendant(Point_R point) {
        double minArea = Double.MAX_VALUE;
        rectangle_R minRec = null;
        for(figure fig : descendants){
            rectangle_R r = (rectangle_R) fig;
            double area = r.checkIncrease(point);
            if(area < minArea){
                minRec = r;
                minArea = area;
            }

        }
        return minRec;
    }

    private double checkIncrease(figure point) {

        double[] aux_min = new double[2];
        double[] aux_max = new double[2];

        aux_min[0] = Math.min(this.min_point.x_coor, point.getMinPoint().x_coor);
        aux_min[1] = Math.min(this.min_point.y_coor,  point.getMinPoint().y_coor);
        aux_max[0] = Math.max(this.max_point.x_coor, point.getMaxPoint().x_coor);
        aux_max[1] = Math.max(this.max_point.y_coor, point.getMaxPoint().y_coor);

        //returns base * height
        return (aux_max[0] - aux_min[0]) * (aux_max[1] - aux_min[1]);
    }


    private boolean isleaf() {
        return descendants.get(0) instanceof Point_R;

    }

    private boolean isleafV2(figure rec) {
        return rec.getDescendants().get(0) instanceof Point_R;

    }

    //------------------------------------------------------------------------------------SEARCH BY AREA
    public void searchByArea( rectangle_R area, RTree rtree){



        if(isleafV2(this)){
            for(figure fig : this.getDescendants()) {
                rtree.searchNumberMAX++;
                if(fig.getMaxPoint().x_coor < area.max_point.x_coor && fig.getMaxPoint().y_coor < area.max_point.y_coor &&
                        fig.getMinPoint().x_coor > area.min_point.x_coor && fig.getMinPoint().y_coor > area.min_point.y_coor) {
                    fig.printFigure();
                    rtree.searchNumber++;
                }
            }
        }else{
            ArrayList<figure> possible_descendants = findPossibleRectangles(area);
            for(figure single_figure : possible_descendants) {
                single_figure.searchByArea( area, rtree);
            }
        }
    }

    //no need
    private ArrayList<figure> findPossibleRectangles(rectangle_R area) {
        ArrayList<figure> possible_descendants = new ArrayList<>();


        for(figure des : this.getDescendants()){
            if(overlap(des, area)){
                possible_descendants.add(des);
            }
        }
        return possible_descendants;
    }

    private boolean overlap(figure one, figure two) {
        if (one.getMaxPoint().y_coor < two.getMinPoint().y_coor
                || one.getMinPoint().y_coor > two.getMaxPoint().y_coor) {
            return false;
        }
        if (one.getMaxPoint().x_coor < two.getMinPoint().x_coor
                || one.getMinPoint().x_coor > two.getMaxPoint().x_coor) {
            return false;
        }
        return true;
    }

    //------------------------------------------------------------------------------------SEARCH SIMILAR TO A POINT

    public void searchSimilar(Point_R point, float percentage, RTree rtree){
        //create a rectangle using the criteria we got from the user and apply searchByArea
        Point_R minPoint = new Point_R(point.x_coor - point.x_coor * percentage, point.y_coor - point.y_coor * percentage, point.radius, point.color_RGB);
        Point_R maxPoint = new Point_R(point.x_coor + point.x_coor * percentage, point.y_coor + point.y_coor * percentage,  point.radius, point.color_RGB);
        rectangle_R aproximationRec = new rectangle_R(point);
        aproximationRec.addDescendant(minPoint);
        aproximationRec.addDescendant(maxPoint);


        searchByArea(aproximationRec, rtree);
    }


    //------------------------------------------------------------------------------------ERASE A POINT

    //do a search
    figure point_to_erase = null;
    public void erasePoint(float xcoor, float ycoor){
        int index = 0;
        if(this.isleaf()){
            for(figure fig : this.getDescendants()) {
                if(fig.getMaxPoint().x_coor == xcoor && fig.getMaxPoint().y_coor == ycoor) {
                    fig.printFigure();

                    this.descendants.remove(index);
                    index++;

                }
            }
        }else{

            for(figure single_figure : this.descendants) {
                if(overlap(single_figure, new Point_R(xcoor,ycoor,0.0f, "color")))
                single_figure.erasePoint( xcoor, ycoor);
            }
        }


        //on the way up check check if its empty


    }



    @Override
    protected Point_R getMinPoint() {
        return this.min_point;
    }

    @Override
    protected Point_R getMaxPoint() {
        return this.max_point;
    }

    @Override
    protected Point_R getCenter() {
        Point_R newPoint = new Point_R((max_point.x_coor + min_point.x_coor)/2, (max_point.y_coor + min_point.y_coor)/2, 0.0f, " ");
        return newPoint;
    }

    @Override
    protected void printFigure() {
        System.out.println("PRINTING RECTANGLE");
    }

    @Override
    protected ArrayList<figure> getDescendants() {
        return this.descendants;
    }
}

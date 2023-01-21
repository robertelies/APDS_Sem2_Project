package RTree_Folder;

import javax.swing.*;
import java.awt.*;

public class graphic_RTree extends JPanel {

    private rectangle_R root;
    private int SIZE;
    private float X_mult;
    private float Y_mult;

    public graphic_RTree(rectangle_R root, int SIZE) {

        this.SIZE = SIZE;
        this.root = root;
        //X_mult =;
        //Y_mult = 20;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        X_mult = ((float)getWidth()) / (root.max_point.x_coor - root.min_point.x_coor) * 0.95f;
        Y_mult = ((float)getHeight()) / (root.max_point.y_coor - root.min_point.y_coor) * 0.95f;
        drawRTree(root, g);
    }

    private float getmax_X(){
        return this.root.max_point.x_coor;
    }

    private float getmax_Y(){
        return this.root.max_point.y_coor;
    }



    private void drawRTree(rectangle_R root, Graphics g) {
        g.drawRect(
                Math.round(root.min_point.x_coor * X_mult),
                Math.round(root.min_point.y_coor * Y_mult),
                Math.round((root.max_point.x_coor - root.min_point.x_coor) * X_mult),
                Math.round((root.max_point.y_coor - root.min_point.y_coor)* Y_mult)
        );

        for(figure single_fig : root.descendants){
            if(single_fig instanceof rectangle_R){
                drawRTree((rectangle_R) single_fig, g);
            }else{
                Point_R point = (Point_R) single_fig;
                g.drawOval(
                        Math.round(point.x_coor * X_mult)  - 3,
                        Math.round(point.y_coor * Y_mult) - 3,
                        6, 6
                );
            }
        }
    }
}


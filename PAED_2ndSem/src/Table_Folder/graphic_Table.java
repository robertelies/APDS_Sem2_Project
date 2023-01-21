package Table_Folder;

import RTree_Folder.Point_R;
import RTree_Folder.figure;
import RTree_Folder.rectangle_R;

import javax.swing.*;
import java.awt.*;

public class graphic_Table extends JPanel {

    private int[] days;
    private int SIZE;

    private float[] Y_mult;

    private int maxday;
    private int maxdayIndex;

    public graphic_Table(int[] days, int SIZE) {

        this.SIZE = SIZE;
        this.days = days;
        this.Y_mult = new float[7];

        //get the max day
        maxday = days[0];
        for(int i = 0; i < 7; i++){
            if(days[i] > maxday){
                maxday = days[i];
                maxdayIndex = i;
            }
        }
        for(int i = 0; i < 7; i++){
            Y_mult[i] = (float) days[i]/maxday;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //X_mult = ((float)getWidth()) / (root.max_point.x_coor - root.min_point.x_coor) * 0.95f;
        //Y_mult = ((float)getHeight()) / (root.max_point.y_coor - root.min_point.y_coor) * 0.95f;


        drawTable(days, g );
    }


    private void drawTable(int [] rects, Graphics g) {

        for(int i = 0; i < 7; i++){
            g.drawRect(100 * i, SIZE - ((int)  Math.round(SIZE * 0.75 * Y_mult[i])),50, ((int)  Math.round(SIZE * 0.75 * Y_mult[i])));

        }

    }
}


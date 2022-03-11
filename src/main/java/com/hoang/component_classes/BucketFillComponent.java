package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByBucketFillCommand;
import com.hoang.change_on_canvas.ChangeByCanvasCommand;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.DrawableOnCanvas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@AllArgsConstructor
@Getter
@Setter
@Component
@Scope("prototype")
public class BucketFillComponent implements DrawableOnCanvas {
    private PointXY startPoint;
    private String newColor;

    @Autowired
    public BucketFillComponent(@Value("1") int x,
    @Value("1") int y, @Value("x") String color) {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        startPoint = (PointXY) appContext.getBean("pointXY");
        startPoint.setXCoordinate(x);
        startPoint.setYCoordinate(y);
        newColor = color;
    }

    public String toString() {
        return "Bucket fill: ( " + startPoint.getXCoordinate() + " ; " + startPoint.getYCoordinate()
                + " ) with color " + this.newColor;
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        if(canvas.isHavePoint(startPoint)) {
            String command = "B " + startPoint.getXCoordinate() + " " + startPoint.getYCoordinate()
                    + " " + newColor;
            ApplicationContext appContext = MainApplicationContext.getApplicationContext();
            ChangeByBucketFillCommand change
                    = (ChangeByBucketFillCommand) appContext.getBean("changeByBucketFillCommand");
            change.setCommand(command);
            change.findOldContentOnCanvas();
            HistoryComponent.addHistory(change);

            fillOnCanvas(canvas);
        }
    }

    private void fillOnCanvas(CanvasComponent can) {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        String oldColor = can.getColorAtPoint(startPoint);
        final boolean[][] pointHaveBeenInList = new boolean[can.getCanvasWidth() + 2][can.getCanvasHeight() + 2];
        make2DFalseArray(pointHaveBeenInList);
        LinkedList<PointXY> pointList = new LinkedList<PointXY>() {
            // Thay vì kiểm tra list có đang chứa 1 Point tại thời điểm hiện tại không
            // thì kiểm tra list đã từng chứa 1 Point trong quá khứ chưa
            @Override
            public boolean contains(Object o) {
                PointXY po = (PointXY) o;
                return pointHaveBeenInList[po.getXCoordinate()][po.getYCoordinate()];
            }
        };
        pointList.addLast(startPoint);
        pointHaveBeenInList[startPoint.getXCoordinate()][startPoint.getYCoordinate()] = true;
        while(pointList.size() > 0) {
            PointXY firstPoint = pointList.removeFirst();
            can.setColorAtPoint(firstPoint, newColor);

            PointXY upSidePoint = (PointXY) appContext.getBean("pointXY");
            upSidePoint.setXCoordinate(firstPoint.getXCoordinate());
            upSidePoint.setYCoordinate(firstPoint.getYCoordinate() - 1);
            if (can.isHavePoint(upSidePoint) && can.getColorAtPoint(upSidePoint).equals(oldColor)
                    && !pointList.contains(upSidePoint)) {
                pointList.addLast(upSidePoint);
                pointHaveBeenInList[upSidePoint.getXCoordinate()][upSidePoint.getYCoordinate()] = true;
            }

            PointXY downSidePoint = (PointXY) appContext.getBean("pointXY");
            downSidePoint.setXCoordinate(firstPoint.getXCoordinate());
            downSidePoint.setYCoordinate(firstPoint.getYCoordinate() + 1);
            if (can.isHavePoint(downSidePoint) && can.getColorAtPoint(downSidePoint).equals(oldColor)
                    && !pointList.contains(downSidePoint)) {
                pointList.addLast(downSidePoint);
                pointHaveBeenInList[downSidePoint.getXCoordinate()][downSidePoint.getYCoordinate()] = true;
            }

            PointXY leftSidePoint = (PointXY) appContext.getBean("pointXY");
            leftSidePoint.setXCoordinate(firstPoint.getXCoordinate() - 1);
            leftSidePoint.setYCoordinate(firstPoint.getYCoordinate());
            if (can.isHavePoint(leftSidePoint) && can.getColorAtPoint(leftSidePoint).equals(oldColor)
                    && !pointList.contains(leftSidePoint)) {
                pointList.addLast(leftSidePoint);
                pointHaveBeenInList[leftSidePoint.getXCoordinate()][leftSidePoint.getYCoordinate()] = true;
            }

            PointXY rightSidePoint = (PointXY) appContext.getBean("pointXY");
            rightSidePoint.setXCoordinate(firstPoint.getXCoordinate() + 1);
            rightSidePoint.setYCoordinate(firstPoint.getYCoordinate());
            if (can.isHavePoint(rightSidePoint) && can.getColorAtPoint(rightSidePoint).equals(oldColor)
                    && !pointList.contains(rightSidePoint)) {
                pointList.addLast(rightSidePoint);
                pointHaveBeenInList[rightSidePoint.getXCoordinate()][rightSidePoint.getYCoordinate()] = true;
            }
        }
    }

    private void make2DFalseArray(boolean[][] bool2DArray) {
        for(boolean[] row : bool2DArray) {
            for(boolean col : row) {
                col = false;
            }
        }
    }
}

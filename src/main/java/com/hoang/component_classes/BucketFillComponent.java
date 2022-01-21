package com.hoang.component_classes;

import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.DrawableOnCanvas;
import java.util.LinkedList;

public class BucketFillComponent implements DrawableOnCanvas {
    private PointXY startPoint;

    private String newColor;

    public BucketFillComponent(int x, int y, String color) {
        startPoint = new PointXY(x, y);
        newColor = color;
    }

    public BucketFillComponent(PointXY po, String color) {
        startPoint = new PointXY(po);
        newColor = color;
    }

    public String toString() {
        return "Bucket fill: ( " + startPoint.getXCoordinate() + " ; " + startPoint.getYCoordinate()
                + " ) with color " + this.newColor;
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        if(canvas.isHavePoint(startPoint)) {
            fillOnCanvas(canvas);
        }
    }

    private void fillOnCanvas(CanvasComponent can) {
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
        while(pointList.size() > 0) {
            PointXY firstPoint = pointList.removeFirst();
            can.setColorAtPoint(firstPoint, newColor);

            PointXY upSidePoint = new PointXY(firstPoint.getXCoordinate(), firstPoint.getYCoordinate() - 1);
            if(can.isHavePoint(upSidePoint) && can.getColorAtPoint(upSidePoint).equals(oldColor)
            && !pointList.contains(upSidePoint)) {
                pointList.addLast(upSidePoint);
                pointHaveBeenInList[upSidePoint.getXCoordinate()][upSidePoint.getYCoordinate()] = true;
            }

            PointXY downSidePoint = new PointXY(firstPoint.getXCoordinate(), firstPoint.getYCoordinate() + 1);
            if(can.isHavePoint(downSidePoint) && can.getColorAtPoint(downSidePoint).equals(oldColor)
            && !pointList.contains(downSidePoint)) {
                pointList.addLast(downSidePoint);
                pointHaveBeenInList[downSidePoint.getXCoordinate()][downSidePoint.getYCoordinate()] = true;
            }

            PointXY leftSidePoint = new PointXY(firstPoint.getXCoordinate() - 1, firstPoint.getYCoordinate());
            if(can.isHavePoint(leftSidePoint) && can.getColorAtPoint(leftSidePoint).equals(oldColor)
            && !pointList.contains(leftSidePoint)) {
                pointList.addLast(leftSidePoint);
                pointHaveBeenInList[leftSidePoint.getXCoordinate()][leftSidePoint.getYCoordinate()] = true;
            }

            PointXY rightSidePoint = new PointXY(firstPoint.getXCoordinate() + 1, firstPoint.getYCoordinate());
            if(can.isHavePoint(rightSidePoint) && can.getColorAtPoint(rightSidePoint).equals(oldColor)
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

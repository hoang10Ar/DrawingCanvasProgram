package com.hoang.change_on_canvas;

import com.hoang.component_classes.BucketFillComponent;
import com.hoang.component_classes.CanvasComponent;
import com.hoang.util_classes.CommandParser;
import com.hoang.util_classes.DateWithFormat;
import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ValidComponent;
import java.util.LinkedList;
import java.util.UUID;

public class ChangeByBucketFillCommand extends ChangeByCommand {
    private CanvasComponent oldContent;
    private PointXY topLeftPoint;

    public ChangeByBucketFillCommand(String command) {
        super(command, UUID.randomUUID().toString(), DateWithFormat.getDateWithDayNameAndDateAndTime());
        findOldContentOnCanvas();
    }

    private void findOldContentOnCanvas() {
        CanvasComponent canvasCopy = new CanvasComponent(DrawingProgram.getMainCanvas());
        ValidComponent component = CommandParser.getComponentByParsingCommand(getCommand());
        BucketFillComponent bucketFillComponent = (BucketFillComponent) component;
        PointXY startPoint = bucketFillComponent.getStartPoint();
        String newColor = bucketFillComponent.getNewColor();
        String oldColor = canvasCopy.getColorAtPoint(startPoint);

        int minXCoordinateOfArea = canvasCopy.getCanvasWidth();
        int maxXCoordinateOfArea = 0;
        int minYCoordinateOfArea = canvasCopy.getCanvasHeight();
        int maxYCoordinateOfArea = 0;

        final boolean[][] pointHaveBeenInList = new boolean[canvasCopy.getCanvasWidth() + 2][canvasCopy.getCanvasHeight() + 2];
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
        while (pointList.size() > 0) {
            PointXY firstPoint = pointList.removeFirst();
            canvasCopy.setColorAtPoint(firstPoint, newColor);

            if(minXCoordinateOfArea >= firstPoint.getXCoordinate()) {
                minXCoordinateOfArea = firstPoint.getXCoordinate();
            }
            if(maxXCoordinateOfArea <= firstPoint.getXCoordinate()) {
                maxXCoordinateOfArea = firstPoint.getXCoordinate();
            }
            if(minYCoordinateOfArea >= firstPoint.getYCoordinate()) {
                minYCoordinateOfArea = firstPoint.getYCoordinate();
            }
            if(maxYCoordinateOfArea <= firstPoint.getYCoordinate()) {
                maxYCoordinateOfArea = firstPoint.getYCoordinate();
            }

            PointXY upSidePoint = new PointXY(firstPoint.getXCoordinate(), firstPoint.getYCoordinate() - 1);
            if (canvasCopy.isHavePoint(upSidePoint) && canvasCopy.getColorAtPoint(upSidePoint).equals(oldColor)
                    && !pointList.contains(upSidePoint)) {
                pointList.addLast(upSidePoint);
                pointHaveBeenInList[upSidePoint.getXCoordinate()][upSidePoint.getYCoordinate()] = true;
            }

            PointXY downSidePoint = new PointXY(firstPoint.getXCoordinate(), firstPoint.getYCoordinate() + 1);
            if (canvasCopy.isHavePoint(downSidePoint) && canvasCopy.getColorAtPoint(downSidePoint).equals(oldColor)
                    && !pointList.contains(downSidePoint)) {
                pointList.addLast(downSidePoint);
                pointHaveBeenInList[downSidePoint.getXCoordinate()][downSidePoint.getYCoordinate()] = true;
            }

            PointXY leftSidePoint = new PointXY(firstPoint.getXCoordinate() - 1, firstPoint.getYCoordinate());
            if (canvasCopy.isHavePoint(leftSidePoint) && canvasCopy.getColorAtPoint(leftSidePoint).equals(oldColor)
                    && !pointList.contains(leftSidePoint)) {
                pointList.addLast(leftSidePoint);
                pointHaveBeenInList[leftSidePoint.getXCoordinate()][leftSidePoint.getYCoordinate()] = true;
            }

            PointXY rightSidePoint = new PointXY(firstPoint.getXCoordinate() + 1, firstPoint.getYCoordinate());
            if (canvasCopy.isHavePoint(rightSidePoint) && canvasCopy.getColorAtPoint(rightSidePoint).equals(oldColor)
                    && !pointList.contains(rightSidePoint)) {
                pointList.addLast(rightSidePoint);
                pointHaveBeenInList[rightSidePoint.getXCoordinate()][rightSidePoint.getYCoordinate()] = true;
            }
        }

        int widthOfArea = maxXCoordinateOfArea - minXCoordinateOfArea + 1;
        int heightOfArea = maxYCoordinateOfArea - minYCoordinateOfArea + 1;
        oldContent = new CanvasComponent(widthOfArea, heightOfArea);
        for(int xCoordinate = 1, xMain = minXCoordinateOfArea; xMain <= maxXCoordinateOfArea; xCoordinate++, xMain++) {
            for(int yCoordinate = 1, yMain = minYCoordinateOfArea; yMain <= maxYCoordinateOfArea; yCoordinate++, yMain++) {
                String color = DrawingProgram.getMainCanvas().getColorAtPoint(xMain, yMain);
                oldContent.setColorAtPoint(xCoordinate, yCoordinate, color);
            }
        }
        topLeftPoint = new PointXY(minXCoordinateOfArea - 1, minYCoordinateOfArea - 1);
    }


    private void make2DFalseArray(boolean[][] bool2DArray) {
        for(boolean[] row : bool2DArray) {
            for(boolean col : row) {
                col = false;
            }
        }
    }

    @Override
    public void undoChange() {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();

        int areaWidth = oldContent.getCanvasWidth();
        int areaHeight = oldContent.getCanvasHeight();

        int minXCoordinate = topLeftPoint.getXCoordinate() + 1;
        int maxXCoordinate = topLeftPoint.getXCoordinate() + areaWidth;
        int minYCoordinate = topLeftPoint.getYCoordinate() + 1;
        int maxYCoordinate = topLeftPoint.getYCoordinate() + areaHeight;

        for(int xCoordinate = 1, xMain = minXCoordinate; xMain <= maxXCoordinate; xCoordinate++, xMain++) {
            for(int yCoordinate = 1, yMain = minYCoordinate; yMain <= maxYCoordinate; yCoordinate++, yMain++) {
                String oldColor = oldContent.getColorAtPoint(xCoordinate, yCoordinate);
                mainCanvas.setColorAtPoint(xMain, yMain, oldColor);
            }
        }
    }
}

package com.hoang.change_on_canvas;

import com.hoang.component_classes.BucketFillComponent;
import com.hoang.component_classes.CanvasComponent;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_classes.CommandParser;
import com.hoang.util_classes.DateWithFormat;
import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ValidComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.UUID;

@Component
@Scope("prototype")
public class ChangeByBucketFillCommand extends ChangeByCommand {
    private CanvasComponent oldContent;
    private PointXY topLeftPoint;

    public ChangeByBucketFillCommand(@Value("--") String command) {
        super(command, UUID.randomUUID().toString(), DateWithFormat.getDateWithDayNameAndDateAndTime());
    }

    public void findOldContentOnCanvas() {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        CanvasComponent canvasCopy = (CanvasComponent) appContext.getBean("canvasComponent");
        canvasCopy.setCanvasMatrix(DrawingProgram.getMainCanvas().getCanvasMatrix());
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

            PointXY upSidePoint = (PointXY) appContext.getBean("pointXY");
            upSidePoint.setXCoordinate(firstPoint.getXCoordinate());
            upSidePoint.setYCoordinate(firstPoint.getYCoordinate() - 1);
            if (canvasCopy.isHavePoint(upSidePoint) && canvasCopy.getColorAtPoint(upSidePoint).equals(oldColor)
                    && !pointList.contains(upSidePoint)) {
                pointList.addLast(upSidePoint);
                pointHaveBeenInList[upSidePoint.getXCoordinate()][upSidePoint.getYCoordinate()] = true;
            }

            PointXY downSidePoint = (PointXY) appContext.getBean("pointXY");
            downSidePoint.setXCoordinate(firstPoint.getXCoordinate());
            downSidePoint.setYCoordinate(firstPoint.getYCoordinate() + 1);
            if (canvasCopy.isHavePoint(downSidePoint) && canvasCopy.getColorAtPoint(downSidePoint).equals(oldColor)
                    && !pointList.contains(downSidePoint)) {
                pointList.addLast(downSidePoint);
                pointHaveBeenInList[downSidePoint.getXCoordinate()][downSidePoint.getYCoordinate()] = true;
            }

            PointXY leftSidePoint = (PointXY) appContext.getBean("pointXY");
            leftSidePoint.setXCoordinate(firstPoint.getXCoordinate() - 1);
            leftSidePoint.setYCoordinate(firstPoint.getYCoordinate());
            if (canvasCopy.isHavePoint(leftSidePoint) && canvasCopy.getColorAtPoint(leftSidePoint).equals(oldColor)
                    && !pointList.contains(leftSidePoint)) {
                pointList.addLast(leftSidePoint);
                pointHaveBeenInList[leftSidePoint.getXCoordinate()][leftSidePoint.getYCoordinate()] = true;
            }

            PointXY rightSidePoint = (PointXY) appContext.getBean("pointXY");
            rightSidePoint.setXCoordinate(firstPoint.getXCoordinate() + 1);
            rightSidePoint.setYCoordinate(firstPoint.getYCoordinate());
            if (canvasCopy.isHavePoint(rightSidePoint) && canvasCopy.getColorAtPoint(rightSidePoint).equals(oldColor)
                    && !pointList.contains(rightSidePoint)) {
                pointList.addLast(rightSidePoint);
                pointHaveBeenInList[rightSidePoint.getXCoordinate()][rightSidePoint.getYCoordinate()] = true;
            }
        }

        int widthOfArea = maxXCoordinateOfArea - minXCoordinateOfArea + 1;
        int heightOfArea = maxYCoordinateOfArea - minYCoordinateOfArea + 1;
        oldContent = (CanvasComponent) appContext.getBean("canvasComponent");
        oldContent.setWidthAndHeight(widthOfArea, heightOfArea);
        for(int xCoordinate = 1, xMain = minXCoordinateOfArea; xMain <= maxXCoordinateOfArea; xCoordinate++, xMain++) {
            for(int yCoordinate = 1, yMain = minYCoordinateOfArea; yMain <= maxYCoordinateOfArea; yCoordinate++, yMain++) {
                String color = DrawingProgram.getMainCanvas().getColorAtPoint(xMain, yMain);
                oldContent.setColorAtPoint(xCoordinate, yCoordinate, color);
            }
        }

        topLeftPoint = (PointXY) appContext.getBean("pointXY");
        topLeftPoint.setXCoordinate(minXCoordinateOfArea - 1);
        topLeftPoint.setYCoordinate(minYCoordinateOfArea - 1);
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

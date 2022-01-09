package ComponentClass;

import UtilClass.PointXY;
import UtilInterface.DrawableOnCanvas;
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
        LinkedList<PointXY> pointList = new LinkedList<>();
        pointList.addLast(startPoint);
        while(pointList.size() > 0) {
            PointXY firstPoint = pointList.removeFirst();
            can.setColorAtPoint(firstPoint, newColor);

            PointXY upSidePoint = new PointXY(firstPoint.getXCoordinate(), firstPoint.getYCoordinate() - 1);
            if(can.isHavePoint(upSidePoint) && can.getColorAtPoint(upSidePoint).equals(oldColor)) {
                pointList.addLast(upSidePoint);
            }

            PointXY downSidePoint = new PointXY(firstPoint.getXCoordinate(), firstPoint.getYCoordinate() + 1);
            if(can.isHavePoint(downSidePoint) && can.getColorAtPoint(downSidePoint).equals(oldColor)) {
                pointList.addLast(downSidePoint);
            }

            PointXY leftSidePoint = new PointXY(firstPoint.getXCoordinate() - 1, firstPoint.getYCoordinate());
            if(can.isHavePoint(leftSidePoint) && can.getColorAtPoint(leftSidePoint).equals(oldColor)) {
                pointList.addLast(leftSidePoint);
            }

            PointXY rightSidePoint = new PointXY(firstPoint.getXCoordinate() + 1, firstPoint.getYCoordinate());
            if(can.isHavePoint(rightSidePoint) && can.getColorAtPoint(rightSidePoint).equals(oldColor)) {
                pointList.addLast(rightSidePoint);
            }
        }
    }
}

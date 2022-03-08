package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByLineCommand;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ColorOfComponent;
import com.hoang.util_interfaces.DrawableOnCanvas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LineComponent implements DrawableOnCanvas {
    private PointXY firstPoint, secondPoint;

    public LineComponent(int x1, int y1, int x2, int y2) {
        firstPoint = new PointXY(x1, y1);
        secondPoint = new PointXY(x2, y2);
    }

    public String toString() {
        return "Line: ( " + firstPoint.getXCoordinate() + " ; " + firstPoint.getYCoordinate()
        + " ) --> ( " + secondPoint.getXCoordinate() + " ; " + secondPoint.getYCoordinate() + " )";
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        if(isLineValidOnCanvas(canvas)) {
            String command = "L " + firstPoint.getXCoordinate() + " " + firstPoint.getYCoordinate()
                    + " " + secondPoint.getXCoordinate() + " " + secondPoint.getYCoordinate();
            HistoryComponent.addHistory(new ChangeByLineCommand(command));

            if(isVerticalLine()) {
                drawVerticalLineOnCanvas(canvas);
            } else if(isHorizontalLine()) {
                drawHorizontalLineOnCanvas(canvas);
            }
        }
    }

    private boolean isLineValidOnCanvas(CanvasComponent canvas) {
        return (isLineInsideCanvas(canvas) && (isVerticalLine()
                || isHorizontalLine()));
    }

    private boolean isLineInsideCanvas(CanvasComponent canvas) {
        return (canvas.isHavePoint(firstPoint) && canvas.isHavePoint(secondPoint));
    }

    private boolean isVerticalLine() {
        return (firstPoint.getXCoordinate() == secondPoint.getXCoordinate());
    }

    private boolean isHorizontalLine() {
        return (firstPoint.getYCoordinate() == secondPoint.getYCoordinate());
    }

    private void drawVerticalLineOnCanvas(CanvasComponent canvas) {
        int minYCoordinate = Math.min(firstPoint.getYCoordinate(), secondPoint.getYCoordinate());
        int maxYCoordinate = Math.max(firstPoint.getYCoordinate(), secondPoint.getYCoordinate());
        int xCoordinate = firstPoint.getXCoordinate();
        for(int yCoordinate = minYCoordinate; yCoordinate <= maxYCoordinate; yCoordinate++) {
            canvas.setColorAtPoint(xCoordinate, yCoordinate, ColorOfComponent.LINE_COLOR);
        }
    }

    private void drawHorizontalLineOnCanvas(CanvasComponent canvas) {
        int minXCoordinate = Math.min(firstPoint.getXCoordinate(), secondPoint.getXCoordinate());
        int maxXCoordinate = Math.max(firstPoint.getXCoordinate(), secondPoint.getXCoordinate());
        int yCoordinate = firstPoint.getYCoordinate();
        for(int xCoordinate = minXCoordinate; xCoordinate <= maxXCoordinate; xCoordinate++) {
            canvas.setColorAtPoint(xCoordinate, yCoordinate, ColorOfComponent.LINE_COLOR);
        }
    }
}

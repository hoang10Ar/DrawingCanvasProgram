package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByRectangleCommand;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ColorOfComponent;
import com.hoang.util_interfaces.DrawableOnCanvas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RectangleComponent implements DrawableOnCanvas {
    private PointXY topLeftPoint, bottomRightPoint;

    public RectangleComponent(int x1, int y1, int x2, int y2) {
        topLeftPoint = new PointXY(x1, y1);
        bottomRightPoint = new PointXY(x2, y2);
    }

    public String toString() {
        return "Rectangle: ( " + topLeftPoint.getXCoordinate() + " ; " + topLeftPoint.getYCoordinate()
        + " ) --> ( " + bottomRightPoint.getXCoordinate() + " ; " + bottomRightPoint.getYCoordinate() + " )";
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        if(canvas.isHavePoint(topLeftPoint) && canvas.isHavePoint(bottomRightPoint)) {
            String command = "R " + topLeftPoint.getXCoordinate() + " " + topLeftPoint.getYCoordinate()
                    + " " + bottomRightPoint.getXCoordinate() + " " + bottomRightPoint.getYCoordinate();
            HistoryComponent.addHistory(new ChangeByRectangleCommand(command));

            drawLeftBorderOnCanvas(canvas);
            drawRightBorderOnCanvas(canvas);
            drawTopBorderOnCanvas(canvas);
            drawBottomBorderOnCanvas(canvas);
        }
    }

    private void drawLeftBorderOnCanvas(CanvasComponent can) {
        int xCoordinate = topLeftPoint.getXCoordinate();
        for(int yCoordinate = topLeftPoint.getYCoordinate();
        yCoordinate <= bottomRightPoint.getYCoordinate(); yCoordinate++) {
            can.setColorAtPoint(xCoordinate, yCoordinate, ColorOfComponent.LINE_COLOR);
        }
    }

    private void drawRightBorderOnCanvas(CanvasComponent can) {
        int xCoordinate = bottomRightPoint.getXCoordinate();
        for(int yCoordinate = topLeftPoint.getYCoordinate();
        yCoordinate <= bottomRightPoint.getYCoordinate(); yCoordinate++) {
            can.setColorAtPoint(xCoordinate, yCoordinate, ColorOfComponent.LINE_COLOR);
        }
    }

    private void drawTopBorderOnCanvas(CanvasComponent can) {
        int yCoordinate = topLeftPoint.getYCoordinate();
        for(int xCoordinate = topLeftPoint.getXCoordinate();
        xCoordinate <= bottomRightPoint.getXCoordinate(); xCoordinate++) {
            can.setColorAtPoint(xCoordinate, yCoordinate, ColorOfComponent.LINE_COLOR);
        }
    }

    private void drawBottomBorderOnCanvas(CanvasComponent can) {
        int yCoordinate = bottomRightPoint.getYCoordinate();
        for(int xCoordinate = topLeftPoint.getXCoordinate();
        xCoordinate <= bottomRightPoint.getXCoordinate(); xCoordinate++) {
            can.setColorAtPoint(xCoordinate, yCoordinate, ColorOfComponent.LINE_COLOR);
        }
    }
}

package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByRectangleCommand;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ColorOfComponent;
import com.hoang.util_interfaces.DrawableOnCanvas;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
public class RectangleComponent implements DrawableOnCanvas {
    private PointXY topLeftPoint, bottomRightPoint;

    @Autowired
    public RectangleComponent(@Value("1") int x1, @Value("1") int y1,
                              @Value("1") int x2, @Value("1") int y2) {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        topLeftPoint = (PointXY) appContext.getBean("pointXY");
        topLeftPoint.setXCoordinate(x1);
        topLeftPoint.setYCoordinate(y1);
        bottomRightPoint = (PointXY) appContext.getBean("pointXY");
        bottomRightPoint.setXCoordinate(x2);
        bottomRightPoint.setYCoordinate(y2);
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
            ApplicationContext appContext = MainApplicationContext.getApplicationContext();
            ChangeByRectangleCommand change
                    = (ChangeByRectangleCommand) appContext.getBean("changeByRectangleCommand");
            change.setCommand(command);
            change.findOldContentOnCanvas();
            HistoryComponent.addHistory(change);

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

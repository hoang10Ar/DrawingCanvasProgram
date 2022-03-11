package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByLineCommand;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ColorOfComponent;
import com.hoang.util_interfaces.DrawableOnCanvas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Component
@Scope("prototype")
public class LineComponent implements DrawableOnCanvas {
    private PointXY firstPoint, secondPoint;

    @Autowired
    public LineComponent(@Value("1") int x1, @Value("1") int y1,
                         @Value("1") int x2, @Value("1") int y2) {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        firstPoint = (PointXY) appContext.getBean("pointXY");
        firstPoint.setXCoordinate(x1);
        firstPoint.setYCoordinate(y1);
        secondPoint = (PointXY) appContext.getBean("pointXY");
        secondPoint.setXCoordinate(x2);
        secondPoint.setYCoordinate(y2);
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
            ApplicationContext appContext = MainApplicationContext.getApplicationContext();
            ChangeByLineCommand change = (ChangeByLineCommand) appContext.getBean("changeByLineCommand");
            change.setCommand(command);
            change.findOldContentOnCanvas();
            HistoryComponent.addHistory(change);

            if(isVerticalLine()) {
                drawVerticalLineOnCanvas(canvas);
            } else if(isHorizontalLine()) {
                drawHorizontalLineOnCanvas(canvas);
            }
        }
    }

    private boolean isLineValidOnCanvas(CanvasComponent canvas) {
        return (isLineInsideCanvas(canvas) && (isVerticalLine() || isHorizontalLine()));
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

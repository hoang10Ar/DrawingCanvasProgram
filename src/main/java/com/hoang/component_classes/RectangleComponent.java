package com.hoang.component_classes;

import com.hoang.configuration.MainApplicationContext;
import com.hoang.service.CanvasComponentService;
import com.hoang.service.CommandService;
import com.hoang.command.Command;
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
import java.util.Date;

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
            drawLeftBorderOnCanvas(canvas);
            drawRightBorderOnCanvas(canvas);
            drawTopBorderOnCanvas(canvas);
            drawBottomBorderOnCanvas(canvas);

            saveCommandToMongoDB();
            saveCurrentCanvasToMongoDB(canvas);
            ViewCanvasComponent.printCurrentCanvas();
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

    private void saveCommandToMongoDB() {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        Command command = (Command) appContext.getBean("command");
        command.setContent("R " + topLeftPoint.getXCoordinate()
                + " " + topLeftPoint.getYCoordinate()
                + " " + bottomRightPoint.getXCoordinate()
                + " " + bottomRightPoint.getYCoordinate());
        command.setDateCreated(new Date());
        CommandService service = (CommandService) appContext.getBean("commandService");
        service.saveCommand(command);
    }

    private void saveCurrentCanvasToMongoDB(CanvasComponent canvasHaveBeenDrawOn) {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();

        CommandService commandService =
                appContext.getBean("commandService", CommandService.class);
        Command rectangleCommand = commandService.getLastCommand();

        CanvasComponentService canvasService =
                appContext.getBean("canvasComponentService", CanvasComponentService.class);
        canvasHaveBeenDrawOn.setId(rectangleCommand.getId() + "_c");
        canvasHaveBeenDrawOn.setDateCreated(rectangleCommand.getDateCreated());
        canvasService.save(canvasHaveBeenDrawOn);
    }
}

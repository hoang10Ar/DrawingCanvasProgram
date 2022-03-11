package com.hoang.util_classes;

import com.hoang.component_classes.*;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_interfaces.ValidComponent;
import org.springframework.context.ApplicationContext;

public class CommandParser {
    private static ApplicationContext applicationContext
            = MainApplicationContext.getApplicationContext();

    public static ValidComponent getComponentByParsingCommand(String command) {
        ValidComponent component = null;

        if(!CommandTypeChecker.isNullCommand(command)) {
            if(CommandTypeChecker.isCanvasCommand(command)) {
                component = getCanvasComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isLineCommand(command)) {
                component = getLineComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isRectangleCommand(command)) {
                component = getRectangleComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isBucketFillCommand(command)) {
                component = getBucketFillComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isQuitCommand(command)) {
                component = getQuitComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isListCommand(command)) {
                component = getListComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isHistoryCommand(command)) {
                component = getHistoryComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isUndoCommand(command)) {
                component = getUndoComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isViewCanvasCommand(command)) {
                component = getViewCanvasComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isJumpCommand(command)) {
                component = getJumpComponentByParsingCommand(command);
            }
        }

        return component;
    }

    private static CanvasComponent getCanvasComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        int width = Integer.parseInt(arguments[1]);
        int height = Integer.parseInt(arguments[2]);

        CanvasComponent canvas = (CanvasComponent) applicationContext.getBean("canvasComponent");
        canvas.setWidthAndHeight(width, height);

        return canvas;
    }

    public static String[] getArgumentsInCommand(String command) {
        return command.trim().split(" ");
    }

    private static LineComponent getLineComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);

        PointXY point1 = (PointXY) applicationContext.getBean("pointXY");
        point1.setXCoordinate(Integer.parseInt(arguments[1]));
        point1.setYCoordinate(Integer.parseInt(arguments[2]));

        PointXY point2 = (PointXY) applicationContext.getBean("pointXY");
        point2.setXCoordinate(Integer.parseInt(arguments[3]));
        point2.setYCoordinate(Integer.parseInt(arguments[4]));

        LineComponent line = (LineComponent) applicationContext.getBean("lineComponent");
        line.setFirstPoint(point1);
        line.setSecondPoint(point2);

        return line;
    }

    private static RectangleComponent getRectangleComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);

        PointXY topLeftPoint = (PointXY) applicationContext.getBean("pointXY");
        topLeftPoint.setXCoordinate(Integer.parseInt(arguments[1]));
        topLeftPoint.setYCoordinate(Integer.parseInt(arguments[2]));

        PointXY bottomRightPoint = (PointXY) applicationContext.getBean("pointXY");
        bottomRightPoint.setXCoordinate(Integer.parseInt(arguments[3]));
        bottomRightPoint.setYCoordinate(Integer.parseInt(arguments[4]));

        RectangleComponent rect =
                (RectangleComponent) applicationContext.getBean("rectangleComponent");
        rect.setTopLeftPoint(topLeftPoint);
        rect.setBottomRightPoint(bottomRightPoint);

        return rect;
    }

    private static BucketFillComponent getBucketFillComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        String newColor = arguments[3];

        PointXY startPoint = (PointXY) applicationContext.getBean("pointXY");
        startPoint.setXCoordinate(Integer.parseInt(arguments[1]));
        startPoint.setYCoordinate(Integer.parseInt(arguments[2]));

        BucketFillComponent bucketFill =
                (BucketFillComponent) applicationContext.getBean("bucketFillComponent");
        bucketFill.setNewColor(newColor);
        bucketFill.setStartPoint(startPoint);

        return bucketFill;
    }

    private static QuitComponent getQuitComponentByParsingCommand(String command) {
        return (QuitComponent) applicationContext.getBean("quitComponent");
    }

    private static ListComponent getListComponentByParsingCommand(String command) {
        return (ListComponent) applicationContext.getBean("listComponent");
    }

    private static HistoryComponent getHistoryComponentByParsingCommand(String command) {
        return (HistoryComponent) applicationContext.getBean("historyComponent");
    }

    private static UndoComponent getUndoComponentByParsingCommand(String command) {
        return (UndoComponent) applicationContext.getBean("undoComponent");
    }

    private static ViewCanvasComponent getViewCanvasComponentByParsingCommand(String command) {
        return (ViewCanvasComponent) applicationContext.getBean("viewCanvasComponent");
    }

    private static JumpComponent getJumpComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        String idOfCommand = arguments[1];

        JumpComponent jump =  (JumpComponent) applicationContext.getBean("jumpComponent");
        jump.setIdWillBeJumped(idOfCommand);

        return jump;
    }
}

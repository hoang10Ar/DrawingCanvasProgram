package com.hoang.util_classes;

import com.hoang.component_classes.*;
import com.hoang.util_interfaces.ValidComponent;

public class CommandParser {
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
        return new CanvasComponent(width, height);
    }

    public static String[] getArgumentsInCommand(String command) {
        return command.split(" ");
    }

    private static LineComponent getLineComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        PointXY point1 = new PointXY(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));
        PointXY point2 = new PointXY(Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));
        return new LineComponent(point1, point2);
    }

    private static RectangleComponent getRectangleComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        PointXY topLeftPoint = new PointXY(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));
        PointXY bottomRightPoint = new PointXY(Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));
        return new RectangleComponent(topLeftPoint, bottomRightPoint);
    }

    private static BucketFillComponent getBucketFillComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        PointXY startPoint = new PointXY(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));
        String color = arguments[3];
        return new BucketFillComponent(startPoint, color);
    }

    private static QuitComponent getQuitComponentByParsingCommand(String command) {
        return new QuitComponent();
    }

    private static ListComponent getListComponentByParsingCommand(String command) {
        return new ListComponent();
    }

    private static HistoryComponent getHistoryComponentByParsingCommand(String command) {
        return new HistoryComponent();
    }

    private static UndoComponent getUndoComponentByParsingCommand(String command) {
        return new UndoComponent();
    }

    private static ViewCanvasComponent getViewCanvasComponentByParsingCommand(String command) {
        return new ViewCanvasComponent();
    }

    private static JumpComponent getJumpComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);
        String idOfCommand = arguments[1];
        return new JumpComponent(idOfCommand);
    }
}

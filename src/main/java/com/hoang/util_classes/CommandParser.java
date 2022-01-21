package com.hoang.util_classes;

import com.hoang.component_classes.*;
import com.hoang.util_interfaces.DrawableOnCanvas;

public class CommandParser {
    public static DrawableOnCanvas getComponentByParsingCommand(String command) {
        DrawableOnCanvas component = null;

        if(!CommandTypeChecker.isNullCommand(command)) {
            if(CommandTypeChecker.isCanvasCommand(command)) {
                component = getCanvasComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isLineCommand(command)) {
                component = getLineComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isRectangleCommand(command)) {
                component = getRectangleComponentByParsingCommand(command);
            } else if(CommandTypeChecker.isBucketFillCommand(command)) {
                component = getBucketFillComponentByParsingCommand(command);
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
}

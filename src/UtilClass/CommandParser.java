package UtilClass;

import UtilInterface.DrawableOnCanvas;
import ComponentClass.*;

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

        return new CanvasComponent(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]));
    }

    public static String[] getArgumentsInCommand(String command) {
        return command.split(" ");
    }

    private static LineComponent getLineComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);

        return new LineComponent(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]),
                Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));
    }

    private static RectangleComponent getRectangleComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);

        return new RectangleComponent(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]),
                Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]));
    }

    private static BucketFillComponent getBucketFillComponentByParsingCommand(String command) {
        String[] arguments = getArgumentsInCommand(command);

        return new BucketFillComponent(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), arguments[3]);
    }
}

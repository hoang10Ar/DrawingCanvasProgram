package com.hoang.util_classes;

public class CommandTypeChecker {
    public static boolean isNullCommand(String command) {
        return (command == null || command.length() == 0);
    }

    public static boolean isQuitCommand(String command) {
        return command.equals("Q");
    }

    public static boolean isCanvasCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 3) && arguments[0].equals("C")
                && isNonNegativeIntegerString(arguments[1])
                && isNonNegativeIntegerString(arguments[2]));
    }

    private static boolean isNonNegativeIntegerString(String aString) {
        boolean isNonNegInt = false;
        try {
            int aInt = Integer.parseInt(aString);
            if(aInt >= 0) {
                isNonNegInt = true;
            }
        } catch(NumberFormatException ex) {
            // Biến isNonNegInt giữ nguyên giá trị
        }

        return isNonNegInt;
    }

    public static boolean isLineCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 5) && arguments[0].equals("L")
                && isNonNegativeIntegerString(arguments[1])
                && isNonNegativeIntegerString(arguments[2])
                && isNonNegativeIntegerString(arguments[3])
                && isNonNegativeIntegerString(arguments[4]));
    }

    public static boolean isRectangleCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 5) && arguments[0].equals("R")
                && isNonNegativeIntegerString(arguments[1])
                && isNonNegativeIntegerString(arguments[2])
                && isNonNegativeIntegerString(arguments[3])
                && isNonNegativeIntegerString(arguments[4]));
    }

    public static boolean isBucketFillCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 4) && arguments[0].equals("B")
                && isNonNegativeIntegerString(arguments[1])
                && isNonNegativeIntegerString(arguments[2]) && (arguments[3].length() == 1));
    }
}

package com.hoang.util_classes;

public class CommandTypeChecker {
    public static boolean isNullCommand(String command) {
        return (command == null || command.length() == 0 || command.trim().length() == 0);
    }

    public static boolean isCanvasCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 3) && arguments[0].equals("C")
                && isPositiveIntegerString(arguments[1])
                && isPositiveIntegerString(arguments[2]));
    }

    private static boolean isPositiveIntegerString(String aString) {
        boolean isPosInt = false;
        try {
            int aInt = Integer.parseInt(aString);
            if(aInt > 0) {
                isPosInt = true;
            }
        } catch(NumberFormatException ex) {
            // Biến isPosInt giữ nguyên giá trị
        }

        return isPosInt;
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

        if ((arguments.length == 5) && arguments[0].equals("L")
        && isNonNegativeIntegerString(arguments[1]) && isNonNegativeIntegerString(arguments[2])
        && isNonNegativeIntegerString(arguments[3]) && isNonNegativeIntegerString(arguments[4])) {
            int x1 = Integer.parseInt(arguments[1]);
            int y1 = Integer.parseInt(arguments[2]);
            int x2 = Integer.parseInt(arguments[3]);
            int y2 = Integer.parseInt(arguments[4]);

            return (x1 == x2) || (y1 == y2);
        }

        return false;
    }

    public static boolean isRectangleCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        if ((arguments.length == 5) && arguments[0].equals("R")
                && isNonNegativeIntegerString(arguments[1]) && isNonNegativeIntegerString(arguments[2])
                && isNonNegativeIntegerString(arguments[3]) && isNonNegativeIntegerString(arguments[4])) {
            int x1 = Integer.parseInt(arguments[1]);
            int y1 = Integer.parseInt(arguments[2]);
            int x2 = Integer.parseInt(arguments[3]);
            int y2 = Integer.parseInt(arguments[4]);

            return (x1 < x2) && (y1 < y2);
        }

        return false;
    }

    public static boolean isBucketFillCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 4) && arguments[0].equals("B")
                && isNonNegativeIntegerString(arguments[1])
                && isNonNegativeIntegerString(arguments[2]) && (arguments[3].length() == 1));
    }

    public static boolean isQuitCommand(String command) {
        return command.trim().equals("Q");
    }

    public static boolean isListCommand(String command) {
        return (command.trim().equals("L"));
    }

    public static boolean isHistoryCommand(String command) {
        return (command.trim().equals("H"));
    }

    public static boolean isUndoCommand(String command) {
        return (command.trim().equals("U"));
    }

    public static boolean isViewCanvasCommand(String command) {
        return (command.trim().equals("V"));
    }

    public static boolean isJumpCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 2) && arguments[0].equals("J"));
    }
}

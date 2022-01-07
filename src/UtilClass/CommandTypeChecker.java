package UtilClass;

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

    public static boolean isLineCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 5) && arguments[0].equals("L")
                && isPositiveIntegerString(arguments[1])
                && isPositiveIntegerString(arguments[2])
                && isPositiveIntegerString(arguments[3])
                && isPositiveIntegerString(arguments[4]));
    }

    public static boolean isRectangleCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 5) && arguments[0].equals("R")
                && isPositiveIntegerString(arguments[1])
                && isPositiveIntegerString(arguments[2])
                && isPositiveIntegerString(arguments[3])
                && isPositiveIntegerString(arguments[4]));
    }

    public static boolean isBucketFillCommand(String command) {
        String[] arguments = CommandParser.getArgumentsInCommand(command);

        return ((arguments.length == 4) && arguments[0].equals("B")
                && isPositiveIntegerString(arguments[1])
                && isPositiveIntegerString(arguments[2]) && (arguments[3].length() == 1));
    }
}

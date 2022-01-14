package Main;

import UtilInterface.DrawableOnCanvas;
import UtilClass.CommandParser;
import ComponentClass.CanvasComponent;
import UtilClass.CommandTypeChecker;
import java.util.Scanner;

public class DrawingProgram {
    private static final Scanner input = new Scanner(System.in);

    public static void runProgram() {
        String command = enterCommandToCreateCanvasComponent();
        DrawableOnCanvas componentOfCommand = CommandParser.getComponentByParsingCommand(command);
        CanvasComponent mainCanvas = (CanvasComponent) componentOfCommand;
        printCanvas(mainCanvas);
        enterCommandsToDrawOnCanvas(mainCanvas);
        quitProgram();
    }

    private static String enterCommandToCreateCanvasComponent() {
        String command = null;
        boolean havingCanvas = false;

        System.out.println("Create a first canvas!");
        while(!havingCanvas) {
            command = enterNotNullCommand();

            if(CommandTypeChecker.isQuitCommand(command)) {
                quitProgram();
            } else if(CommandTypeChecker.isCanvasCommand(command)) {
                havingCanvas = true;
            } else {
                System.out.println("This is not a command to create a canvas!");
            }
        }

        return command;
    }

    private static String enterNotNullCommand() {
        String command;
        do {
            System.out.print("Enter a command: ");
            command = input.nextLine();
        } while(CommandTypeChecker.isNullCommand(command));

        return command;
    }

    private static void quitProgram() {
        input.close();
        System.out.println("Quit!!!");
        System.exit(0);
    }

    private static void printCanvas(CanvasComponent canvas) {
        System.out.println("Current canvas: ");
        canvas.printCanvas();
    }

    private static void enterCommandsToDrawOnCanvas(CanvasComponent mainCanvas) {
        String command = enterNotNullCommand();

        while(!CommandTypeChecker.isQuitCommand(command)) {
            DrawableOnCanvas component = CommandParser.getComponentByParsingCommand(command);
            if(component != null) {
                component.drawOnCanvas(mainCanvas);
                printCanvas(mainCanvas);
            } else {
                System.out.println("This is not a valid command!");
            }

            command = enterNotNullCommand();
        }
    }
}

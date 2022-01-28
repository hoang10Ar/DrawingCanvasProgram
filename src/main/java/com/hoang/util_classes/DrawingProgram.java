package com.hoang.util_classes;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.ListComponent;
import com.hoang.util_interfaces.DrawableOnCanvas;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import com.hoang.util_interfaces.ValidComponent;
import java.util.Scanner;

public class DrawingProgram {
    private static final Scanner input = new Scanner(System.in);
    private static CanvasComponent mainCanvas = new CanvasComponent(1, 1);

    public static Scanner getInput() {
        return input;
    }

    public static CanvasComponent getMainCanvas() {
        return mainCanvas;
    }

    public static void setMainCanvas(CanvasComponent newCanvas) {
        mainCanvas = new CanvasComponent(newCanvas);
    }

    public static void runProgram() {
        ListComponent.initDefinitionList();
        enterCommandToCreateCanvasComponent();
        mainCanvas.printCanvas();
        enterCommandsToDrawOnCanvas();
    }

    private static String enterCommandToCreateCanvasComponent() {
        String command = null;
        boolean havingCanvas = false;

        System.out.println("Create a first canvas!");
        while(!havingCanvas) {
            command = enterNotNullCommand();
            ValidComponent component = CommandParser.getComponentByParsingCommand(command);
            if(component instanceof CanvasComponent) {
                havingCanvas = true;
                ((CanvasComponent) component).drawOnCanvas(mainCanvas);
            } else if(component instanceof NonDrawableOnCanvas) {
                ((NonDrawableOnCanvas) component).performFunction();
            } else {
                System.out.println("This is not a command to create a canvas!");
            }
        }

        return command;
    }

    private static String enterNotNullCommand() {
        String command;
        do {
            System.out.print("Enter a command (Type 'L' for listing all commands' definition): ");
            command = input.nextLine();
        } while(CommandTypeChecker.isNullCommand(command));

        return command;
    }

    private static void enterCommandsToDrawOnCanvas() {
        String command = enterNotNullCommand();

        while(true) {
            ValidComponent component = CommandParser.getComponentByParsingCommand(command);
            if(component instanceof ValidComponent) {
                if(component instanceof DrawableOnCanvas) {
                    ((DrawableOnCanvas) component).drawOnCanvas(mainCanvas);
                    mainCanvas.printCanvas();
                } else if(component instanceof NonDrawableOnCanvas){
                    ((NonDrawableOnCanvas) component).performFunction();
                }
            } else {
                System.out.println("This is not a valid command!");
            }
            command = enterNotNullCommand();
        }
    }
}

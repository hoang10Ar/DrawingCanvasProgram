package com.hoang.util_classes;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.ListComponent;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_interfaces.DrawableOnCanvas;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import com.hoang.util_interfaces.ValidComponent;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

public class DrawingProgram {
    private static Scanner input;
    private static ApplicationContext mainAppContext;
    private static CanvasComponent mainCanvas;

    public static Scanner getInput() {
        return input;
    }

    public static CanvasComponent getMainCanvas() {
        return mainCanvas;
    }

    public static void setMainCanvas(CanvasComponent newCanvas) {
        mainCanvas = (CanvasComponent) mainAppContext.getBean("canvasComponent");
        mainCanvas.setCanvasMatrix(newCanvas.getCanvasMatrix());
    }

    public static void runProgram() {
        initProperties();
        ListComponent.initDefinitionList();
        enterCommandToCreateCanvasComponent();
        mainCanvas.printCanvas();
        enterCommandsToDrawOnCanvas();
    }

    private static void initProperties() {
        input = new Scanner(System.in);
        mainAppContext = MainApplicationContext.getApplicationContext();
        mainCanvas = (CanvasComponent) mainAppContext.getBean("canvasComponent");
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

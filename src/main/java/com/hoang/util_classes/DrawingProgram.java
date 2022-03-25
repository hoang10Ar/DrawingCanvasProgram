package com.hoang.util_classes;

import com.hoang.command.CommandParser;
import com.hoang.command.CommandTypeChecker;
import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.ListComponent;
import com.hoang.component_classes.ViewCanvasComponent;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.service.CanvasComponentService;
import com.hoang.util_interfaces.DrawableOnCanvas;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import com.hoang.util_interfaces.ValidComponent;
import org.springframework.context.ApplicationContext;
import java.util.Scanner;

public class DrawingProgram {
    private static Scanner input;
    private static ApplicationContext mainAppContext;
    private static CanvasComponentService canvasService;

    public static Scanner getInput() {
        return input;
    }

    public static ApplicationContext getMainAppContext() {
        return mainAppContext;
    }

    public static void runProgram() {
        initProperties();
        ListComponent.initDefinitionList();
        if(canvasService.isEmptyCanvasInHistory()) {
            enterCommandToCreateCanvasComponent();
        } else {
            ViewCanvasComponent.printCurrentCanvas();
        }
        enterCommandsToDrawOnCanvas();
    }

    private static void initProperties() {
        input = new Scanner(System.in);
        mainAppContext = MainApplicationContext.getApplicationContext();
        canvasService =
                mainAppContext.getBean("canvasComponentService", CanvasComponentService.class);
    }

    private static String enterCommandToCreateCanvasComponent() {
        String command = null;
        boolean havingCanvas = false;

        System.out.println("<!> Create a first canvas!");
        while(!havingCanvas) {
            command = enterNotNullCommand();
            ValidComponent component = CommandParser.getComponentByParsingCommand(command);
            if(component instanceof CanvasComponent) {
                havingCanvas = true;
                ((CanvasComponent) component).drawOnCanvas(canvasService.getLastCanvasComponent());
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
            System.out.println();
            System.out.print("__Enter a command (Type 'L' for listing all commands' definition): ");
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
                    ((DrawableOnCanvas) component).drawOnCanvas(canvasService.getLastCanvasComponent());
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

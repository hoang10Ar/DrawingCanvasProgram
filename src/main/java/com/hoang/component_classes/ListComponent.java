package com.hoang.component_classes;

import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_classes.CommandDefinition;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListComponent implements NonDrawableOnCanvas {
    private static List<CommandDefinition> definitionList = new ArrayList<>();

    public static void initDefinitionList() {
        addDefinition("BucketFill2D", "B x y c",
                "Should fill the entire area connected to (x,y) with \"colour\" c. The " +
                        "behavior of this is the same as that of the \"bucket fill\" tool in paint " +
                        "programs.");
        addDefinition("Canvas", "C w h",
                "Should create a new canvas of width w and height h.");
        addDefinition("HistoryCommand", "H",
                "Should list all commands' history.");
        addDefinition("Jump", "J idCommand",
                "Should undo back to the command which id is \"idCommand\" in history.");
        addDefinition("DefinitionList", "L", "Should list all commands' definition.");
        addDefinition("Line2D", "L x1 y1 x2 y2",
                "Should create a new line from (x1,y1) to (x2,y2). Currently only " +
                        "horizontal or vertical lines are supported. Horizontal and vertical lines " +
                        "will be drawn using the 'x' character.");
        addDefinition("Quit", "Q", "Should quit the program.");
        addDefinition("Rectangle", "R x1 y1 x2 y2",
                "Should create a new rectangle, whose upper left corner is (x1,y1) and " +
                        "lower right corner is (x2,y2). Horizontal and vertical lines will be drawn " +
                        "using the 'x' character.");
        addDefinition("Undo", "U", "Should reverse (undo) the action of an earlier action.");
        addDefinition("ViewCanvas", "V", "Should view the current canvas.");
    }

    private static void addDefinition(String id, String structure, String description) {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        CommandDefinition definition = (CommandDefinition) appContext.getBean("commandDefinition") ;
        definition.setIdOfCommand(id);
        definition.setStructureOfCommand(structure);
        definition.setDescriptionOfCommand(description);
        definitionList.add(definition);
    }

    public static void listCommandsDefinition() {
        System.out.println("----- All commands' definition: ----------");
        System.out.println();
        definitionList.forEach((definition) -> {
            definition.printDefinition();
        });
        System.out.println("------------------------------------------");
    }

    @Override
    public void performFunction() {
        listCommandsDefinition();
    }
}

package com.hoang.component_classes;

import com.hoang.service.CanvasComponentService;
import com.hoang.service.CommandService;
import com.hoang.command.Command;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UndoComponent implements NonDrawableOnCanvas {
    @Autowired
    private CommandService commandService;
    @Autowired
    private CanvasComponentService canvasService;

    @Override
    public void performFunction() {
        Command lastCommand = commandService.getLastCommand();
        if(lastCommand != null) {
            commandService.deleteLastCommand();
            canvasService.deleteById(lastCommand.getId() + "_c");
            System.out.println("Undo success!");
        } else {
            System.out.println("Nothing to undo!");
        }
    }
}

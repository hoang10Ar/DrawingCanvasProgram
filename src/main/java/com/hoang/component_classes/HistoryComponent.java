package com.hoang.component_classes;

import com.hoang.service.CommandService;
import com.hoang.command.Command;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryComponent implements NonDrawableOnCanvas {
    @Autowired
    private CommandService commandService;

    public void showHistory() {
        List<Command> commands = commandService.findAllCommands();
        System.out.println("----- History: ----------");
        for(Command command : commands) {
            System.out.println();
            System.out.println("+++++");
            System.out.println("Id: " + command.getId());
            System.out.println("Command: " + command.getContent());
            System.out.println("Date: " + command.getDateCreated());
            System.out.println("+++++");
        }
        System.out.println();
        System.out.println("-------------------------");
    }

    @Override
    public void performFunction() {
        showHistory();
    }
}

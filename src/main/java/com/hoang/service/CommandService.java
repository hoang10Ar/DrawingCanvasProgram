package com.hoang.service;

import com.hoang.repository.CommandRepository;
import com.hoang.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommandService {
    @Autowired
    private CommandRepository repository;
    private List<Command> commands;

    public void saveCommand(Command command) {
        repository.save(command);
    }

    public List<Command> findAllCommands() {
        commands = repository.findAll();
        return commands;
    }

    public Command getLastCommand() {
        commands = repository.findAll();
        Command lastCommand = null;
        if(commands.size() > 0) {
            lastCommand = commands.get(commands.size() - 1);
        }

        return lastCommand;
    }

    public void deleteLastCommand() {
        Command lastCommand = getLastCommand();
        if(lastCommand != null) {
            repository.deleteById(lastCommand.getId());
        }
    }

    public Command getLastCommandIdStartWith(String id) {
        commands = repository.findAll();
        Command commandSatisfiedId = null;
        for(int i = commands.size() - 1; i >= 0; i--) {
            Command command = commands.get(i);
            if(command.getId().matches("^" + id + ".*")) {
                commandSatisfiedId = command;
                break;
            }
        }

        return commandSatisfiedId;
    }

    public void deleteCommandAfterCommandIdStartWith(String id) {
        Command commandSatisfiedId = getLastCommandIdStartWith(id);
        if(commandSatisfiedId != null) {
            repository.deleteCommandByDateCreatedAfter(commandSatisfiedId.getDateCreated());
        }
    }
}

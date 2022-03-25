package com.hoang.component_classes;

import com.hoang.service.CanvasComponentService;
import com.hoang.service.CommandService;
import com.hoang.command.Command;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
public class JumpComponent implements NonDrawableOnCanvas {
    @Autowired
    private CommandService commandService;
    @Autowired
    private CanvasComponentService canvasService;

    private String idWillBeJumped;

    public JumpComponent(@Value("--") String id) {
        this.idWillBeJumped = id;
    }

    @Override
    public void performFunction() {
        Command commandWillBeJumped = commandService.getLastCommandIdStartWith(idWillBeJumped);
        if(commandWillBeJumped != null) {
            commandService.deleteCommandAfterCommandIdStartWith(idWillBeJumped);
            canvasService.deleteCanvasComponentByDateCreatedAfter(commandWillBeJumped.getDateCreated());
            System.out.println("Jump success!");
        } else {
            System.out.println("Not exist command with that id!");
        }
    }
}

package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByCommand;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Getter
@Setter
@Component
@Scope("prototype")
public class JumpComponent implements NonDrawableOnCanvas {
    private String idWillBeJumped;

    public JumpComponent(@Value("--") String id) {
        this.idWillBeJumped = id;
    }

    @Override
    public void performFunction() {
        if(HistoryComponent.isContainHistoryHaveId(idWillBeJumped)) {
            String idOfCurrentHistory = HistoryComponent.getLastHistory().getId();
            while(!Pattern.matches("^" + idWillBeJumped + ".*", idOfCurrentHistory)) {
                ChangeByCommand lastHistory = HistoryComponent.getAndRemoveLastHistory();
                lastHistory.undoChange();
                idOfCurrentHistory = HistoryComponent.getLastHistory().getId();
            }
            System.out.println("Jump success!");
        }
    }
}

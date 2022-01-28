package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByCommand;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
@Setter
public class JumpComponent implements NonDrawableOnCanvas {
    private String idWillBeJumped;

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

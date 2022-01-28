package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByCommand;
import com.hoang.util_interfaces.NonDrawableOnCanvas;

public class UndoComponent implements NonDrawableOnCanvas {
    @Override
    public void performFunction() {
        ChangeByCommand componentWillBeUndo = HistoryComponent.getAndRemoveLastHistory();
        if(componentWillBeUndo != null) {
            componentWillBeUndo.undoChange();
        } else {
            System.out.println("Nothing to undo!");
        }
    }
}

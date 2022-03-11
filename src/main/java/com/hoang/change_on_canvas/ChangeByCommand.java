package com.hoang.change_on_canvas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ChangeByCommand {
    private String command, id, date;

    public void setCommand(String comm) {
        this.command = comm;
    }

    public abstract void findOldContentOnCanvas();

    public abstract void undoChange();
}

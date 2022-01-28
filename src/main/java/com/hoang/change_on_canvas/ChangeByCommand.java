package com.hoang.change_on_canvas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ChangeByCommand {
    private String command, id, date;

    public abstract void undoChange();
}

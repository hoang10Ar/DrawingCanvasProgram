package com.hoang.change_on_canvas;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.util_classes.DateWithFormat;
import com.hoang.util_classes.DrawingProgram;
import java.util.UUID;

public class ChangeByCanvasCommand extends ChangeByCommand {
    private CanvasComponent oldCanvas;

    public ChangeByCanvasCommand(String command) {
        super(command, UUID.randomUUID().toString(), DateWithFormat.getDateWithDayNameAndDateAndTime());
        oldCanvas = new CanvasComponent(DrawingProgram.getMainCanvas());
    }

    @Override
    public void undoChange() {
        DrawingProgram.setMainCanvas(oldCanvas);
    }
}

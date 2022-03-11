package com.hoang.change_on_canvas;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.configuration.MainApplicationContext;
import com.hoang.util_classes.DateWithFormat;
import com.hoang.util_classes.DrawingProgram;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Scope("prototype")
public class ChangeByCanvasCommand extends ChangeByCommand {
    private CanvasComponent oldCanvas;

    public ChangeByCanvasCommand(@Value("--") String command) {
        super(command, UUID.randomUUID().toString(), DateWithFormat.getDateWithDayNameAndDateAndTime());
    }

    @Override
    public void findOldContentOnCanvas() {
        ApplicationContext appContext = MainApplicationContext.getApplicationContext();
        oldCanvas = (CanvasComponent) appContext.getBean("canvasComponent");
        oldCanvas.setCanvasMatrix(DrawingProgram.getMainCanvas().getCanvasMatrix());
    }

    @Override
    public void undoChange() {
        DrawingProgram.setMainCanvas(oldCanvas);
    }
}

package com.hoang.change_on_canvas;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.LineComponent;
import com.hoang.util_classes.CommandParser;
import com.hoang.util_classes.DateWithFormat;
import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_interfaces.ValidComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Scope("prototype")
public class ChangeByLineCommand extends ChangeByCommand {
    private String[] oldContent;

    public ChangeByLineCommand(@Value("--") String command) {
        super(command, UUID.randomUUID().toString(), DateWithFormat.getDateWithDayNameAndDateAndTime());
    }

    public void findOldContentOnCanvas() {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();
        ValidComponent component = CommandParser.getComponentByParsingCommand(getCommand());
        LineComponent lineComponent = (LineComponent) component;

        int x1 = lineComponent.getFirstPoint().getXCoordinate();
        int y1 = lineComponent.getFirstPoint().getYCoordinate();
        int x2 = lineComponent.getSecondPoint().getXCoordinate();
        int y2 = lineComponent.getSecondPoint().getYCoordinate();

        if(x1 == x2) {
            int minYCoordinate = Math.min(y1, y2);
            int maxYCoordinate = Math.max(y1, y2);
            oldContent = new String[maxYCoordinate - minYCoordinate + 1];
            for(int i = 0, yCoordinate = minYCoordinate; yCoordinate <= maxYCoordinate; i++, yCoordinate++) {
                oldContent[i] = mainCanvas.getColorAtPoint(x1, yCoordinate);
            }
        } else {
            int minXCoordinate = Math.min(x1, x2);
            int maxXCoordinate = Math.max(x1, x2);
            oldContent = new String[maxXCoordinate - minXCoordinate + 1];
            for (int i = 0, xCoordinate = minXCoordinate; xCoordinate <= maxXCoordinate; i++, xCoordinate++) {
                oldContent[i] = mainCanvas.getColorAtPoint(xCoordinate, y1);
            }
        }
    }

    @Override
    public void undoChange() {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();
        ValidComponent component = CommandParser.getComponentByParsingCommand(getCommand());
        LineComponent lineComponent = (LineComponent) component;

        int x1 = lineComponent.getFirstPoint().getXCoordinate();
        int y1 = lineComponent.getFirstPoint().getYCoordinate();
        int x2 = lineComponent.getSecondPoint().getXCoordinate();

        if(x1 == x2) {
            for(String color : oldContent) {
                mainCanvas.setColorAtPoint(x1, y1++, color);
            }
        } else {
            for(String color : oldContent) {
                mainCanvas.setColorAtPoint(x1++, y1, color);
            }
        }
    }
}

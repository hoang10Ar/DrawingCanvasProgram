package com.hoang.change_on_canvas;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.HistoryComponent;
import com.hoang.component_classes.LineComponent;
import com.hoang.util_classes.DrawingProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeByLineCommandTest {
    @BeforeEach
    public void setUp() {
        HistoryComponent.getHistoryList().clear();
        HistoryComponent.addHistory(new ChangeByLineCommand("L 1 1 3 1"));
        HistoryComponent.addHistory(new ChangeByRectangleCommand("R 2 2 4 4"));
        DrawingProgram.setMainCanvas(new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"|", " ", "x", " ", "x", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        }));
    }

    @Test
    public void whenOldLineRestoredThenUndoChangeSuccess() {
        CanvasComponent oldCanvas;

        oldCanvas = new CanvasComponent(DrawingProgram.getMainCanvas());

        LineComponent line = new LineComponent(1, 3, 4, 3);
        line.drawOnCanvas(DrawingProgram.getMainCanvas());

        ChangeByCommand lastChange = HistoryComponent.getAndRemoveLastHistory();
        assertTrue(lastChange.getCommand().equals("L 1 3 4 3"));

        lastChange.undoChange();
        assertTrue((DrawingProgram.getMainCanvas()).isEqual(oldCanvas));
    }
}

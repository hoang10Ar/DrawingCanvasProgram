package com.hoang.change_on_canvas;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.HistoryComponent;
import com.hoang.util_classes.DrawingProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeByCanvasCommandTest {
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
    public void whenOldCanvasRestoredThenUndoChangeSuccess() {
        CanvasComponent oldCanvas, newCanvas;

        oldCanvas = new CanvasComponent(DrawingProgram.getMainCanvas());

        newCanvas = new CanvasComponent(3, 2);
        newCanvas.drawOnCanvas(DrawingProgram.getMainCanvas());

        ChangeByCommand lastChange = HistoryComponent.getAndRemoveLastHistory();
        assertTrue(lastChange.getCommand().equals("C 3 2"));

        lastChange.undoChange();
        assertTrue((DrawingProgram.getMainCanvas()).isEqual(oldCanvas));
    }
}

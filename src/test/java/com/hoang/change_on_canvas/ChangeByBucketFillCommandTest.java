package com.hoang.change_on_canvas;

import com.hoang.component_classes.BucketFillComponent;
import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.HistoryComponent;
import com.hoang.util_classes.DrawingProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeByBucketFillCommandTest {
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
    public void testUndoChange() {
        CanvasComponent targetCanvas;
        BucketFillComponent bucketFill = new BucketFillComponent(5, 2, "H");
        bucketFill.drawOnCanvas(DrawingProgram.getMainCanvas());
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "H", "H", "|"},
                {"|", " ", "x", "x", "x", "H", "|"},
                {"|", " ", "x", " ", "x", "H", "|"},
                {"|", " ", "x", "x", "x", "H", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue((DrawingProgram.getMainCanvas()).isEqual(targetCanvas));

        ChangeByCommand lastChange = HistoryComponent.getAndRemoveLastHistory();
        assertTrue(lastChange.getCommand().equals("B 5 2 H"));

        lastChange.undoChange();
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"|", " ", "x", " ", "x", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue((DrawingProgram.getMainCanvas()).isEqual(targetCanvas));
    }
}

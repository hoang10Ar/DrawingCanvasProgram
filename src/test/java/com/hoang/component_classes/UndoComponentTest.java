package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByBucketFillCommand;
import com.hoang.change_on_canvas.ChangeByLineCommand;
import com.hoang.change_on_canvas.ChangeByRectangleCommand;
import com.hoang.util_classes.DrawingProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UndoComponentTest {
    @BeforeEach
    public void setUp() {
        DrawingProgram.setMainCanvas(new CanvasComponent(5, 4));
        HistoryComponent.getHistoryList().clear();
    }

    @Test
    public void whenEnterXCommandAndEnterUndoThenRemainXMinus1Command() {
        HistoryComponent.addHistory(new ChangeByLineCommand("L 1 1 3 1"));
        HistoryComponent.addHistory(new ChangeByRectangleCommand("R 2 2 4 4"));
        HistoryComponent.addHistory(new ChangeByBucketFillCommand("B 2 3 h"));

        assertTrue(HistoryComponent.getLastHistory().getCommand().equals("B 2 3 h"));
        assertEquals(3, HistoryComponent.getHistoryList().size());

        (new UndoComponent()).performFunction();
        assertTrue(HistoryComponent.getLastHistory().getCommand().equals("R 2 2 4 4"));
        assertEquals(2, HistoryComponent.getHistoryList().size());

        (new UndoComponent()).performFunction();
        assertTrue(HistoryComponent.getLastHistory().getCommand().equals("L 1 1 3 1"));
        assertEquals(1, HistoryComponent.getHistoryList().size());

        (new UndoComponent()).performFunction();
        assertNull(HistoryComponent.getLastHistory());
        assertEquals(0, HistoryComponent.getHistoryList().size());
    }
}

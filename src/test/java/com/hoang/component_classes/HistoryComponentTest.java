package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByBucketFillCommand;
import com.hoang.change_on_canvas.ChangeByCommand;
import com.hoang.change_on_canvas.ChangeByLineCommand;
import com.hoang.change_on_canvas.ChangeByRectangleCommand;
import com.hoang.util_classes.DrawingProgram;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryComponentTest {
    @BeforeEach
    public void setUp() {
        DrawingProgram.setMainCanvas(new CanvasComponent(5, 4));
        HistoryComponent.getHistoryList().clear();
    }

    @Test
    public void whenLastHistoryEqualRecentlyHistoryThenAddHistorySuccess() {
        List<ChangeByCommand> historyList = HistoryComponent.getHistoryList();

        HistoryComponent.addHistory(new ChangeByLineCommand("L 1 1 3 1"));
        assertEquals(1, HistoryComponent.getHistoryList().size());
        assertTrue(historyList.get(0).getCommand().equals("L 1 1 3 1"));

        HistoryComponent.addHistory(new ChangeByRectangleCommand("R 2 2 4 4"));
        assertEquals(2, HistoryComponent.getHistoryList().size());
        assertTrue(historyList.get(1).getCommand().equals("R 2 2 4 4"));

        HistoryComponent.addHistory(new ChangeByBucketFillCommand("B 2 3 h"));
        assertEquals(3, HistoryComponent.getHistoryList().size());
        assertTrue(historyList.get(2).getCommand().equals("B 2 3 h"));
    }

    @Test
    public void whenLastHistoryEqualRecentlyHistoryThenGetLastHistorySuccess() {
        HistoryComponent.addHistory(new ChangeByLineCommand("L 1 1 3 1"));
        HistoryComponent.addHistory(new ChangeByRectangleCommand("R 2 2 4 4"));
        HistoryComponent.addHistory(new ChangeByBucketFillCommand("B 2 3 h"));

        assertTrue(HistoryComponent.getLastHistory().getCommand().equals("B 2 3 h"));
        assertEquals(3, HistoryComponent.getHistoryList().size());
    }

    @Test
    public void whenHistoryEmptyThenLastHistoryIsNull() {
        HistoryComponent.getHistoryList().clear();
        assertNull(HistoryComponent.getLastHistory());
    }

    @Test
    public void whenLastHistoryEqualSecondRecentlyHistoryThenGetAndRemoveLastHistorySuccess() {
        HistoryComponent.addHistory(new ChangeByLineCommand("L 1 1 3 1"));
        HistoryComponent.addHistory(new ChangeByRectangleCommand("R 2 2 4 4"));
        HistoryComponent.addHistory(new ChangeByBucketFillCommand("B 2 3 h"));

        HistoryComponent.getAndRemoveLastHistory();

        assertEquals(2, HistoryComponent.getHistoryList().size());
        assertTrue(HistoryComponent.getLastHistory().getCommand().equals("R 2 2 4 4"));

        HistoryComponent.getHistoryList().clear();
        assertNull(HistoryComponent.getAndRemoveLastHistory());
    }
}

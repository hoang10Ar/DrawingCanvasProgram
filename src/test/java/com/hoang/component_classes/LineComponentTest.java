package com.hoang.component_classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineComponentTest {
    @Test
    public void testDrawOnCanvas() {
        CanvasComponent actualCanvas, targetCanvas;
        LineComponent line;
        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });

        line = new LineComponent(1, 1, 4, 1);
        line.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        line = new LineComponent(3, 1, 3, 3);
        line.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        line = new LineComponent(4, 3, 6, 3);
        line.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        line = new LineComponent(0, 3, 4, 3);
        line.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", "x", "x", "x", "x", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        line = new LineComponent(-1, 2, 4, 2);
        line.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));

        line = new LineComponent(1, 2, -4, 2);
        line.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));

        line = new LineComponent(-1, 2, -4, 2);
        line.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));
    }
}

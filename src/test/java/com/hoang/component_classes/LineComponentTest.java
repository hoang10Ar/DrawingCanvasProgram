package com.hoang.component_classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineComponentTest {
    @Test
    public void whenDrawLineInsideCanvasOnCanvasThenCanvasDrawLine() {
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

        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", " ", " ", " ", " ", "|"},
                {"|", " ", "x", "A", " ", " ", "|"},
                {"|", " ", "B", "x", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        line = new LineComponent(3, 1, 3, 4);
        line.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "x", " ", "x", " ", " ", "|"},
                {"|", " ", "x", "x", " ", " ", "|"},
                {"|", " ", "B", "x", " ", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "h", " ", " ", " ", " ", "|"},
                {"|", "h", "h", " ", " ", " ", "|"},
                {"|", " ", "h", "h", " ", " ", "|"},
                {"|", " ", " ", "h", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        line = new LineComponent(5, 0, 5, 3);
        line.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "h", " ", " ", " ", "x", "|"},
                {"|", "h", "h", " ", " ", "x", "|"},
                {"|", " ", "h", "h", " ", "x", "|"},
                {"|", " ", " ", "h", " ", " ", "|"},
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

    @Test
    public void whenDrawLineNotHorizonalOrVerticalThenCanvasNotDrawLine() {
        CanvasComponent oldCanvas, newCanvas;
        LineComponent line;

        oldCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", "A", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(oldCanvas);
        line = new LineComponent(1, 2, 2, 1);
        line.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));
    }

    @Test
    public void whenDrawLineOutsideCanvasOnCanvasThenCanvasNotDrawLine() {
        CanvasComponent oldCanvas, newCanvas;
        LineComponent line;

        oldCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", "A", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(oldCanvas);

        line = new LineComponent(-1, 2, 4, 2);
        line.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));

        line = new LineComponent(1, 2, -4, 2);
        line.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));

        line = new LineComponent(-1, 2, -4, 2);
        line.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));
    }
}

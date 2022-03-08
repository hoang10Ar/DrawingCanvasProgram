package com.hoang.component_classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleComponentTest {
    @Test
    public void whenDrawRectangleInsideCanvasThenCanvasDrawRectangle() {
        CanvasComponent actualCanvas, targetCanvas;
        RectangleComponent rectangle;

        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        rectangle = new RectangleComponent(2, 1, 4, 3);
        rectangle.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"|", " ", "x", " ", "x", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", "C", " ", " ", "|"},
                {"|", "A", "B", " ", "D", " ", "|"},
                {"|", " ", " ", " ", " ", "E", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        rectangle = new RectangleComponent(2, 0, 4, 3);
        rectangle.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "x", "C", "x", " ", "|"},
                {"|", "A", "x", " ", "x", " ", "|"},
                {"|", " ", "x", "x", "x", "E", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));
    }

    @Test
    public void whenDrawRectangleOutsideCanvasThenCanvasNotDrawRectangle() {
        CanvasComponent oldCanvas, newCanvas;
        RectangleComponent rectangle;

        oldCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", "H", "H", "H", "|"},
                {"|", "x", " ", " ", " ", "H", "|"},
                {"|", " ", "x", " ", "H", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(oldCanvas);

        rectangle = new RectangleComponent(1, 1, 6, 2);
        rectangle.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));

        rectangle = new RectangleComponent(1, 1, 4, 5);
        rectangle.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));

        rectangle = new RectangleComponent(-1, 1, 4, 4);
        rectangle.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));

        rectangle = new RectangleComponent(1, 1, 4, -4);
        rectangle.drawOnCanvas(newCanvas);
        assertTrue(newCanvas.isEqual(oldCanvas));
    }
}

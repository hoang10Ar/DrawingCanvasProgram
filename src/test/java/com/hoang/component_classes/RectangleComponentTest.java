package com.hoang.component_classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleComponentTest {
    @Test
    public void testDrawOnCanvas() {
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

        rectangle = new RectangleComponent(0, 2, 2, 4);
        rectangle.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"|", "x", "x", " ", "x", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"|", "x", "x", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        rectangle = new RectangleComponent(1, 1, 6, 2);
        rectangle.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));

        rectangle = new RectangleComponent(1, 1, 4, 5);
        rectangle.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));

        rectangle = new RectangleComponent(-1, 1, 4, 4);
        rectangle.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));

        rectangle = new RectangleComponent(1, 1, 4, -4);
        rectangle.drawOnCanvas(actualCanvas);
        assertTrue(actualCanvas.isEqual(targetCanvas));
    }
}

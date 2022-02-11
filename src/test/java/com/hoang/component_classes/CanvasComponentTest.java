package com.hoang.component_classes;

import com.hoang.util_interfaces.ColorOfComponent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CanvasComponentTest {
    @Test
    public void testIsEqual() {
        CanvasComponent inputCanvas, testCanvas;

        inputCanvas = new CanvasComponent(5, 2);
        testCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(inputCanvas.isEqual(testCanvas));

        inputCanvas = new CanvasComponent(5, 2);
        testCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(inputCanvas.isEqual(testCanvas));

        inputCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        testCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(inputCanvas.isEqual(testCanvas));

        inputCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        testCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "h", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(inputCanvas.isEqual(testCanvas));

        inputCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        testCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(inputCanvas.isEqual(testCanvas));

        inputCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        testCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", "|"},
                {"|", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-"}
        });
        assertFalse(inputCanvas.isEqual(testCanvas));
    }

    @Test
    public void testSetColorAtPoint() {
        CanvasComponent actualCanvas, targetCanvas;

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(2, 1, "H");
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(2, 1, "H");
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", "H", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(2, 1, "H");
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "h", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(actualCanvas.isEqual(targetCanvas));

        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(0, 2, "H");
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(2, 0, "H");
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(2, -1, "H");
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(-2, 1, "H");
        assertTrue(actualCanvas.isEqual(targetCanvas));

        actualCanvas = new CanvasComponent(5, 2);
        actualCanvas.setColorAtPoint(-2, -1, "H");
        assertTrue(actualCanvas.isEqual(targetCanvas));
    }

    @Test
    public void testGetColorAtPoint() {
        CanvasComponent canvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        String expect, actual;

        actual = canvas.getColorAtPoint(1, 1);
        expect = " ";
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(2, 1);
        expect = "H";
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(2, 1);
        expect = "h";
        assertNotEquals(actual, expect);

        actual = canvas.getColorAtPoint(3, 2);
        expect = "x";
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(3, 2);
        expect = " ";
        assertNotEquals(actual, expect);

        actual = canvas.getColorAtPoint(0, 0);
        expect = ColorOfComponent.TOP_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(1, 0);
        expect = ColorOfComponent.TOP_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(0, 1);
        expect = ColorOfComponent.TOP_BORDER_CANVAS_COLOR;
        assertNotEquals(actual, expect);

        actual = canvas.getColorAtPoint(0, 3);
        expect = ColorOfComponent.BOTTOM_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(1, 3);
        expect = ColorOfComponent.BOTTOM_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(0, 2);
        expect = ColorOfComponent.BOTTOM_BORDER_CANVAS_COLOR;
        assertNotEquals(actual, expect);

        actual = canvas.getColorAtPoint(0, 1);
        expect = ColorOfComponent.LEFT_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(0, 2);
        expect = ColorOfComponent.LEFT_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(2, 0);
        expect = ColorOfComponent.LEFT_BORDER_CANVAS_COLOR;
        assertNotEquals(actual, expect);

        actual = canvas.getColorAtPoint(6, 1);
        expect = ColorOfComponent.RIGHT_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(6, 2);
        expect = ColorOfComponent.RIGHT_BORDER_CANVAS_COLOR;
        assertEquals(actual, expect);

        actual = canvas.getColorAtPoint(6, 0);
        expect = ColorOfComponent.RIGHT_BORDER_CANVAS_COLOR;
        assertNotEquals(actual, expect);

        actual = canvas.getColorAtPoint(2, -1);
        assertNull(actual);

        actual = canvas.getColorAtPoint(-2, 1);
        assertNull(actual);

        actual = canvas.getColorAtPoint(7, 2);
        assertNull(actual);

        actual = canvas.getColorAtPoint(2, 4);
        assertNull(actual);
    }

    @Test
    public void testIsHavePoint() {
        CanvasComponent canvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });

        assertTrue(canvas.isHavePoint(0, 0));
        assertTrue(canvas.isHavePoint(0, 1));
        assertTrue(canvas.isHavePoint(1, 0));
        assertTrue(canvas.isHavePoint(1, 1));
        assertTrue(canvas.isHavePoint(4, 2));
        assertFalse(canvas.isHavePoint(-1, 2));
        assertFalse(canvas.isHavePoint(6, 2));
        assertFalse(canvas.isHavePoint(1, -2));
        assertFalse(canvas.isHavePoint(1, 3));
        assertFalse(canvas.isHavePoint(-1, -2));
        assertFalse(canvas.isHavePoint(6, 3));
    }

    @Test
    public void testDrawOnCanvas() {
        CanvasComponent currentCanvas, newCanvas, targetCanvas;

        currentCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(3, 2);
        newCanvas.drawOnCanvas(currentCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", "|"},
                {"|", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-"}
        });
        assertTrue(currentCanvas.isEqual(targetCanvas));

        currentCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-"},
                {"|", "A", "B", "C", " ", "|"},
                {"|", " ", " ", "D", " ", "|"},
                {"|", " ", "x", "x", "x", "|"},
                {"|", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-"}
        });
        newCanvas.drawOnCanvas(currentCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-"},
                {"|", "A", "B", "C", " ", "|"},
                {"|", " ", " ", "D", " ", "|"},
                {"|", " ", "x", "x", "x", "|"},
                {"|", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-"}
        });
        assertTrue(currentCanvas.isEqual(targetCanvas));
    }
}

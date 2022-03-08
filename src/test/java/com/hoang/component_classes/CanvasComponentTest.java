package com.hoang.component_classes;

import com.hoang.util_interfaces.ColorOfComponent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CanvasComponentTest {
    @Test
    public void whenTwoCanvasesHaveSameContentThenCanvasesEqual() {
        CanvasComponent firstCanvas, secondCanvas;

        firstCanvas = new CanvasComponent(5, 2);
        secondCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(firstCanvas.isEqual(secondCanvas));


        firstCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        secondCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(firstCanvas.isEqual(secondCanvas));
    }

    @Test
    public void whenTwoCanvasesHaveDifferentContentThenCanvaseNotEqual() {
        CanvasComponent firstCanvas, secondCanvas;

        firstCanvas = new CanvasComponent(5, 2);
        secondCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(firstCanvas.isEqual(secondCanvas));

        firstCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        secondCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "h", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(firstCanvas.isEqual(secondCanvas));

        firstCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        secondCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertFalse(firstCanvas.isEqual(secondCanvas));

        firstCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        secondCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", "|"},
                {"|", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-"}
        });
        assertFalse(firstCanvas.isEqual(secondCanvas));
    }

    @Test
    public void whenSetColorAtPointInsideCanvasThenCanvasChanged() {
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

        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        actualCanvas.setColorAtPoint(4, 2, "k");
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", " ", " ", "k", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));
    }

    @Test
    public void whenSetColorAtPointOutsideCanvasThenCanvasNotChanged() {
        CanvasComponent oldCanvas, newCanvas;

        oldCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "x", " ", " ", " ", "|"},
                {"|", " ", " ", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(oldCanvas);

        newCanvas.setColorAtPoint(0, 2, "H");
        assertTrue(newCanvas.isEqual(oldCanvas));

        newCanvas.setColorAtPoint(2, 0, "H");
        assertTrue(newCanvas.isEqual(oldCanvas));

        newCanvas.setColorAtPoint(2, -1, "H");
        assertTrue(newCanvas.isEqual(oldCanvas));

        newCanvas.setColorAtPoint(-2, 1, "H");
        assertTrue(newCanvas.isEqual(oldCanvas));

        newCanvas.setColorAtPoint(-2, -1, "H");
        assertTrue(newCanvas.isEqual(oldCanvas));
    }

    @Test
    public void whenGetColorAtPointInsideCanvasThenColorReturned() {
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
    }

    @Test
    public void whenGetColorAtPointAtBorderCanvasThenBorderColorReturned() {
        CanvasComponent canvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        String expect, actual;

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
    }

    @Test
    public void whenGetColorAtPointOutsideCanvasThenNullReturned() {
        CanvasComponent canvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        String color;

        color = canvas.getColorAtPoint(2, -1);
        assertNull(color);

        color = canvas.getColorAtPoint(-2, 1);
        assertNull(color);

        color = canvas.getColorAtPoint(7, 2);
        assertNull(color);

        color = canvas.getColorAtPoint(2, 4);
        assertNull(color);
    }

    @Test
    public void whenCoordinateBetweenZeroAndWidthHeightThenCanvasHasPoint() {
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
        assertTrue(canvas.isHavePoint(5, 2));
    }

    @Test
    public void whenCoordinateNotBetweenZeroAndWidthHeightThenCanvasNotHasPoint() {
        CanvasComponent canvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });

        assertFalse(canvas.isHavePoint(-1, 2));
        assertFalse(canvas.isHavePoint(6, 2));
        assertFalse(canvas.isHavePoint(1, -2));
        assertFalse(canvas.isHavePoint(1, 3));
        assertFalse(canvas.isHavePoint(-1, -2));
        assertFalse(canvas.isHavePoint(6, 3));
    }

    @Test
    public void whenDrawCanvasOnAnotherCanvasThenOldCanvasRemoved() {
        CanvasComponent oldCanvas, newCanvas, targetCanvas;

        oldCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "H", " ", " ", " ", "|"},
                {"|", " ", "x", "x", "x", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        newCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", "|"},
                {"|", "A", " ", " ", "|"},
                {"-", "-", "-", "-", "-"}
        });
        newCanvas.drawOnCanvas(oldCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-"},
                {"|", " ", " ", " ", "|"},
                {"|", "A", " ", " ", "|"},
                {"-", "-", "-", "-", "-"}
        });
        assertTrue(oldCanvas.isEqual(targetCanvas));

        oldCanvas = new CanvasComponent(new String[][] {
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
        newCanvas.drawOnCanvas(oldCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-"},
                {"|", "A", "B", "C", " ", "|"},
                {"|", " ", " ", "D", " ", "|"},
                {"|", " ", "x", "x", "x", "|"},
                {"|", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-"}
        });
        assertTrue(oldCanvas.isEqual(targetCanvas));
    }
}

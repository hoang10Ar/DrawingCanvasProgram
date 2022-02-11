package com.hoang.component_classes;

import com.hoang.util_classes.DrawingProgram;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BucketFillComponentTest {
    @Test
    public void testDrawOnCanvas() {
        CanvasComponent actualCanvas, targetCanvas;
        actualCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "x", "x", " ", " ", "|"},
                {"|", " ", " ", "x", " ", " ", "|"},
                {"|", " ", " ", "x", "x", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        DrawingProgram.setMainCanvas(actualCanvas);
        BucketFillComponent bucketFill;

        bucketFill = new BucketFillComponent(3, 1, "O");
        bucketFill.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", " ", "O", "O", " ", " ", "|"},
                {"|", " ", " ", "O", " ", " ", "|"},
                {"|", " ", " ", "O", "O", " ", "|"},
                {"|", " ", " ", " ", " ", " ", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        bucketFill = new BucketFillComponent(4, 1, "H");
        bucketFill.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "H", "O", "O", "H", "H", "|"},
                {"|", "H", "H", "O", "H", "H", "|"},
                {"|", "H", "H", "O", "O", "H", "|"},
                {"|", "H", "H", "H", "H", "H", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        bucketFill = new BucketFillComponent(4, 1, "O");
        bucketFill.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "O", "O", "O", "O", "O", "|"},
                {"|", "O", "O", "O", "O", "O", "|"},
                {"|", "O", "O", "O", "O", "O", "|"},
                {"|", "O", "O", "O", "O", "O", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));

        bucketFill = new BucketFillComponent(2, 2, "a");
        bucketFill.drawOnCanvas(actualCanvas);
        targetCanvas = new CanvasComponent(new String[][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"|", "a", "a", "a", "a", "a", "|"},
                {"|", "a", "a", "a", "a", "a", "|"},
                {"|", "a", "a", "a", "a", "a", "|"},
                {"|", "a", "a", "a", "a", "a", "|"},
                {"-", "-", "-", "-", "-", "-", "-"}
        });
        assertTrue(actualCanvas.isEqual(targetCanvas));
    }
}

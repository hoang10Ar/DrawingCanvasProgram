package com.hoang.component_classes;

import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_interfaces.NonDrawableOnCanvas;

public class ViewCanvasComponent implements NonDrawableOnCanvas {
    @Override
    public void performFunction() {
        DrawingProgram.getMainCanvas().printCanvas();
    }
}

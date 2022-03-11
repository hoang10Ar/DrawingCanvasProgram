package com.hoang.component_classes;

import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import org.springframework.stereotype.Component;

@Component
public class QuitComponent implements NonDrawableOnCanvas {
    @Override
    public void performFunction() {
        DrawingProgram.getInput().close();
        System.out.println("Quit!!!");
        System.exit(0);
    }
}

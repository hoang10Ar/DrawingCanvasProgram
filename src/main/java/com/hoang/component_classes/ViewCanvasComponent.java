package com.hoang.component_classes;

import com.hoang.service.CanvasComponentService;
import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ViewCanvasComponent implements NonDrawableOnCanvas {
    public static void printCurrentCanvas() {
        System.out.println("Current Canvas:");
        ApplicationContext context = DrawingProgram.getMainAppContext();
        CanvasComponentService canvasService =
                context.getBean("canvasComponentService", CanvasComponentService.class);
        canvasService.getLastCanvasComponent().printCanvas();
    }

    @Override
    public void performFunction() {
        printCurrentCanvas();
    }
}

package com.hoang.change_on_canvas;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.component_classes.RectangleComponent;
import com.hoang.util_classes.CommandParser;
import com.hoang.util_classes.DateWithFormat;
import com.hoang.util_classes.DrawingProgram;
import com.hoang.util_interfaces.ValidComponent;
import java.util.UUID;

public class ChangeByRectangleCommand extends ChangeByCommand {
    private String[] oldTopContent, oldBottomContent, oldLeftContent, oldRightContent;

    public ChangeByRectangleCommand(String command) {
        super(command, UUID.randomUUID().toString(), DateWithFormat.getDateWithDayNameAndDateAndTime());
        findOldContentOnCanvas();
    }

    private void findOldContentOnCanvas() {
        ValidComponent component = CommandParser.getComponentByParsingCommand(getCommand());
        RectangleComponent rectangleComponent = (RectangleComponent) component;

        int x1 = rectangleComponent.getTopLeftPoint().getXCoordinate();
        int y1 = rectangleComponent.getTopLeftPoint().getYCoordinate();
        int x2 = rectangleComponent.getBottomRightPoint().getXCoordinate();
        int y2 = rectangleComponent.getBottomRightPoint().getYCoordinate();

        findOldTopContent(x1, y1, x2, y2);
        findOldBottomContent(x1, y1, x2, y2);
        findOldLeftContent(x1, y1, x2, y2);
        findOldRightContent(x1, y1, x2, y2);
    }

    private void findOldTopContent(int x1, int y1, int x2, int y2) {
        oldTopContent = new String[x2 - x1 + 1];
        for(int i = 0, xCoordinate = x1; xCoordinate <= x2; i++, xCoordinate++) {
            oldTopContent[i] = DrawingProgram.getMainCanvas().getColorAtPoint(xCoordinate, y1);
        }
    }

    private void findOldBottomContent(int x1, int y1, int x2, int y2) {
        oldBottomContent = new String[x2 - x1 + 1];
        for(int i = 0, xCoordinate = x1; xCoordinate <= x2; i++, xCoordinate++) {
            oldBottomContent[i] = DrawingProgram.getMainCanvas().getColorAtPoint(xCoordinate, y2);
        }
    }

    private void findOldLeftContent(int x1, int y1, int x2, int y2) {
        oldLeftContent = new String[y2 - y1 + 1];
        for(int i = 0, yCoordinate = y1; yCoordinate <= y2; i++, yCoordinate++) {
            oldLeftContent[i] = DrawingProgram.getMainCanvas().getColorAtPoint(x1, yCoordinate);
        }
    }

    private void findOldRightContent(int x1, int y1, int x2, int y2) {
        oldRightContent = new String[y2 - y1 + 1];
        for(int i = 0, yCoordinate = y1; yCoordinate <= y2; i++, yCoordinate++) {
            oldRightContent[i] = DrawingProgram.getMainCanvas().getColorAtPoint(x2, yCoordinate);
        }
    }

    @Override
    public void undoChange() {
        ValidComponent component = CommandParser.getComponentByParsingCommand(getCommand());
        RectangleComponent rectangleComponent = (RectangleComponent) component;

        int x1 = rectangleComponent.getTopLeftPoint().getXCoordinate();
        int y1 = rectangleComponent.getTopLeftPoint().getYCoordinate();
        int x2 = rectangleComponent.getBottomRightPoint().getXCoordinate();
        int y2 = rectangleComponent.getBottomRightPoint().getYCoordinate();

        undoTopChange(x1, x2, y1, y2);
        undoBottomChange(x1, x2, y1, y2);
        undoLeftChange(x1, x2, y1, y2);
        undoRightChange(x1, x2, y1, y2);
    }

    private void undoTopChange(int x1, int x2, int y1, int y2) {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();
        for(String color : oldTopContent) {
            mainCanvas.setColorAtPoint(x1++, y1, color);
        }
    }

    private void undoBottomChange(int x1, int x2, int y1, int y2) {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();
        for(String color : oldBottomContent) {
            mainCanvas.setColorAtPoint(x1++, y2, color);
        }
    }

    private void undoLeftChange(int x1, int x2, int y1, int y2) {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();
            for(String color : oldLeftContent) {
            mainCanvas.setColorAtPoint(x1, y1++, color);
        }
    }

    private void undoRightChange(int x1, int x2, int y1, int y2) {
        CanvasComponent mainCanvas = DrawingProgram.getMainCanvas();
        for(String color : oldRightContent) {
            mainCanvas.setColorAtPoint(x2, y1++, color);
        }
    }
}

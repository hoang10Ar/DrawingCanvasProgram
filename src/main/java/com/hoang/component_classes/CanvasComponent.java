package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByCanvasCommand;
import com.hoang.util_classes.PointXY;
import com.hoang.util_interfaces.ColorOfComponent;
import com.hoang.util_interfaces.DrawableOnCanvas;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CanvasComponent implements DrawableOnCanvas {
    private int canvasWidth, canvasHeight;
    private String[][] canvasMatrix;

    public CanvasComponent(int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;
        // Canvas sẽ hiển thị thêm 2 cột ở đầu và cuối, 2 hàng ở đầu và cuối để tạo thành khung hình chữ nhật
        this.canvasMatrix = new String[this.canvasHeight + 2][this.canvasWidth + 2];

        makeEmptyCanvas();
    }

    public CanvasComponent(String[][] matrix) {
        this(matrix[0].length - 2, matrix.length - 2);

        for(int xCoordinate = 1; xCoordinate <= this.canvasWidth; xCoordinate++) {
            for(int yCoordinate = 1; yCoordinate <= this.canvasHeight; yCoordinate++) {
                String color = matrix[yCoordinate][xCoordinate];
                this.setColorAtPoint(xCoordinate, yCoordinate, color);
            }
        }
    }

    public CanvasComponent(CanvasComponent canvas) {
        this(canvas.canvasWidth, canvas.canvasHeight);

        for(int xCoordinate = 1; xCoordinate <= canvas.canvasWidth; xCoordinate++) {
            for(int yCoordinate = 1; yCoordinate <= canvas.canvasHeight; yCoordinate++) {
                String color = canvas.getColorAtPoint(xCoordinate, yCoordinate);
                this.setColorAtPoint(xCoordinate, yCoordinate, color);
            }
        }
    }

    private void makeEmptyCanvas() {
        makeBorderCanvas();
        makeEmptyInsideCanvas();
    }

    private void makeBorderCanvas() {
        makeLeftBorderCanvas();
        makeRightBorderCanvas();
        makeTopBorderCanvas();
        makeBottomBorderCanvas();
    }

    private void makeLeftBorderCanvas() {
        String color = ColorOfComponent.LEFT_BORDER_CANVAS_COLOR;
        for(int yCoordinate = 0; yCoordinate < this.canvasHeight + 2; yCoordinate++) {
            setColorAtPoint(0, yCoordinate, color);
        }
    }

    private void makeRightBorderCanvas() {
        String color = ColorOfComponent.RIGHT_BORDER_CANVAS_COLOR;
        for(int yCoordinate = 0; yCoordinate < this.canvasHeight + 2; yCoordinate++) {
            setColorAtPoint(this.canvasWidth + 1, yCoordinate, color);
        }
    }

    private void makeTopBorderCanvas() {
        String color = ColorOfComponent.TOP_BORDER_CANVAS_COLOR;
        for(int xCoordinate = 0; xCoordinate < this.canvasWidth + 2; xCoordinate++) {
            setColorAtPoint(xCoordinate, 0, color);
        }
    }

    private void makeBottomBorderCanvas() {
        String color = ColorOfComponent.BOTTOM_BORDER_CANVAS_COLOR;
        for(int xCoordinate = 0; xCoordinate < this.canvasWidth + 2; xCoordinate++) {
            setColorAtPoint(xCoordinate, this.canvasHeight + 1, color);
        }
    }

    private void makeEmptyInsideCanvas() {
        String color = ColorOfComponent.EMPTY_COLOR;
        for(int xCoordinate = 1; xCoordinate <= this.canvasWidth; xCoordinate++) {
            for(int yCoordinate = 1; yCoordinate <= this.canvasHeight; yCoordinate++) {
                setColorAtPoint(xCoordinate, yCoordinate, color);
            }
        }
    }

    public void setColorAtPoint(PointXY po, String color) {
        setColorAtPoint(po.getXCoordinate(), po.getYCoordinate(), color);
    }

    public void setColorAtPoint(int xCoordinate, int yCoordinate, String color) {
        if(isHavePoint(xCoordinate, yCoordinate)) {
            this.canvasMatrix[yCoordinate][xCoordinate] = color;
        }
    }

    public boolean isHavePoint(int xCoordinate, int yCoordinate) {
        return ((xCoordinate >= 0) && (xCoordinate <= this.canvasWidth)
                && (yCoordinate >= 0) && (yCoordinate <= this.canvasHeight));
    }

    public boolean isHavePoint(PointXY po) {
        return isHavePoint(po.getXCoordinate(), po.getYCoordinate());
    }

    public String getColorAtPoint(PointXY po) {
        return getColorAtPoint(po.getXCoordinate(), po.getYCoordinate());
    }

    public String getColorAtPoint(int xCoordinate, int yCoordinate) {
        String color = null;
        if(isLeftBorderHavePoint(xCoordinate, yCoordinate)) {
            color = ColorOfComponent.LEFT_BORDER_CANVAS_COLOR;
        } else if(isRightBorderHavePoint(xCoordinate, yCoordinate)) {
            color = ColorOfComponent.RIGHT_BORDER_CANVAS_COLOR;
        } else if(isTopBorderHavePoint(xCoordinate, yCoordinate)) {
            color = ColorOfComponent.TOP_BORDER_CANVAS_COLOR;
        } else if(isBottomBorderHavePoint(xCoordinate, yCoordinate)) {
            color = ColorOfComponent.BOTTOM_BORDER_CANVAS_COLOR;
        } else if(isHavePoint(xCoordinate, yCoordinate)) {
            color = this.canvasMatrix[yCoordinate][xCoordinate];
        }

        return color;
    }

    private boolean isLeftBorderHavePoint(int xCoordinate, int yCoordinate) {
        return (xCoordinate == 0 && yCoordinate >= 1 && yCoordinate <= this.canvasHeight);
    }

    private boolean isRightBorderHavePoint(int xCoordinate, int yCoordinate) {
        return (xCoordinate == this.canvasWidth + 1 && yCoordinate >= 1 && yCoordinate <= this.canvasHeight);
    }

    private boolean isTopBorderHavePoint(int xCoordinate, int yCoordinate) {
        return (yCoordinate == 0 && xCoordinate >= 0 && xCoordinate < this.canvasWidth + 2);
    }

    private boolean isBottomBorderHavePoint(int xCoordinate, int yCoordinate) {
        return (yCoordinate == this.canvasHeight + 1 && xCoordinate >= 0 && xCoordinate < this.canvasWidth + 2);
    }

    public void printCanvas() {
        for(int yCoordinate = 0; yCoordinate < this.canvasHeight + 2; yCoordinate++) {
            for(int xCoordinate = 0; xCoordinate < this.canvasWidth + 2; xCoordinate++) {
                System.out.print(getColorAtPoint(xCoordinate, yCoordinate));
            }
            System.out.println();
        }
    }

    public String toString() {
        return "Canvas: width = " + this.canvasWidth + ", height = " + this.canvasHeight;
    }

    public boolean isEqual(CanvasComponent can) {
        if(can.canvasWidth == this.canvasWidth && can.canvasHeight == this.canvasHeight) {
            for(int xCoor = 0; xCoor < this.canvasWidth + 2; xCoor++) {
                for(int yCoor = 0; yCoor < this.canvasHeight + 2; yCoor++) {
                    if(!can.getColorAtPoint(xCoor, yCoor).equals(this.getColorAtPoint(xCoor, yCoor))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        String command = "C " + this.canvasWidth + " " + this.canvasHeight;
        HistoryComponent.addHistory(new ChangeByCanvasCommand(command));

        canvas.canvasWidth = this.canvasWidth;
        canvas.canvasHeight = this.canvasHeight;
        canvas.canvasMatrix = this.canvasMatrix;
    }
}

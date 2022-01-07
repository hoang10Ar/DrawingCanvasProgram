package ComponentClass;

import UtilInterface.DrawableOnCanvas;

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
        for(int yCoordinate = 0; yCoordinate < this.canvasHeight + 2; yCoordinate++) {
            setColorAtPoint(0, yCoordinate, "|");
        }
    }

    private void makeRightBorderCanvas() {
        for(int yCoordinate = 0; yCoordinate < this.canvasHeight + 2; yCoordinate++) {
            setColorAtPoint(this.canvasWidth + 1, yCoordinate, "|");
        }
    }

    private void makeTopBorderCanvas() {
        for(int xCoordinate = 0; xCoordinate < this.canvasWidth + 2; xCoordinate++) {
            setColorAtPoint(xCoordinate, 0, "-");
        }
    }

    private void makeBottomBorderCanvas() {
        for(int xCoordinate = 0; xCoordinate < this.canvasWidth + 2; xCoordinate++) {
            setColorAtPoint(xCoordinate, this.canvasHeight + 1, "-");
        }
    }

    private void makeEmptyInsideCanvas() {
        for(int xCoordinate = 1; xCoordinate <= this.canvasWidth; xCoordinate++) {
            for(int yCoordinate = 1; yCoordinate <= this.canvasHeight; yCoordinate++) {
                setColorAtPoint(xCoordinate, yCoordinate, " ");
            }
        }
    }

    public void setColorAtPoint(int xCoordinate, int yCoordinate, String color) {
        this.canvasMatrix[yCoordinate][xCoordinate] = color;
    }

    public String getColorAtPoint(int xCoordinate, int yCoordinate) {
        String color = null;
        if(isHavePoint(xCoordinate, yCoordinate)) {
            color = this.canvasMatrix[yCoordinate][xCoordinate];
        }

        return color;
    }

    public boolean isHavePoint(int xCoordinate, int yCoordinate) {
        if((xCoordinate >= 0) && (xCoordinate < this.canvasWidth + 2)
                && (yCoordinate >= 0) && (yCoordinate < this.canvasHeight + 2)) {
            return true;
        } else {
            return false;
        }
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

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        canvas = new CanvasComponent(this.canvasWidth, this.canvasHeight);
        for(int xCoordinate = 1; xCoordinate <= canvas.canvasWidth; xCoordinate++) {
            for(int yCoordinate = 1; yCoordinate <= canvas.canvasHeight; yCoordinate++) {
                String colorOfThisCanvas = getColorAtPoint(xCoordinate, yCoordinate);
                canvas.setColorAtPoint(xCoordinate, yCoordinate, colorOfThisCanvas);
            }
        }
    }
}

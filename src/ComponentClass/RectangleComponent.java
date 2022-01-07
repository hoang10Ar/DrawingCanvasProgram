package ComponentClass;

import UtilInterface.DrawableOnCanvas;

public class RectangleComponent implements DrawableOnCanvas {
    private int xTopLeft, yTopLeft, xBottomRight, yBottomRight;

    public RectangleComponent(int x1, int y1, int x2, int y2) {
        this.xTopLeft = x1;
        this.yTopLeft = y1;
        this.xBottomRight = x2;
        this.yBottomRight = y2;
    }

    public String toString() {
        return "Rectangle: ( " + this.xTopLeft + " ; " + this.yTopLeft + " ) --> ( "
                + this.xBottomRight + " ; " + this.yBottomRight + " )";
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        if(canvas.isHavePoint(this.xTopLeft, this.yTopLeft)
                && canvas.isHavePoint(this.xBottomRight, this.yBottomRight)) {
            drawLeftBorderOnCanvas(canvas);
            drawRightBorderOnCanvas(canvas);
            drawTopBorderOnCanvas(canvas);
            drawBottomBorderOnCanvas(canvas);
        }
    }

    private void drawLeftBorderOnCanvas(CanvasComponent can) {
        for(int yCoordinate = this.yTopLeft; yCoordinate <= this.yBottomRight; yCoordinate++) {
            can.setColorAtPoint(this.xTopLeft, yCoordinate, "x");
        }
    }

    private void drawRightBorderOnCanvas(CanvasComponent can) {
        for(int yCoordinate = this.yTopLeft; yCoordinate <= this.yBottomRight; yCoordinate++) {
            can.setColorAtPoint(this.xBottomRight, yCoordinate, "x");
        }
    }

    private void drawTopBorderOnCanvas(CanvasComponent can) {
        for(int xCoordinate = this.xTopLeft; xCoordinate <= this.xBottomRight; xCoordinate++) {
            can.setColorAtPoint(xCoordinate, this.yTopLeft, "x");
        }
    }

    private void drawBottomBorderOnCanvas(CanvasComponent can) {
        for(int xCoordinate = this.xTopLeft; xCoordinate <= this.xBottomRight; xCoordinate++) {
            can.setColorAtPoint(xCoordinate, this.yBottomRight, "x");
        }
    }
}

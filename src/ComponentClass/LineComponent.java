package ComponentClass;

import UtilInterface.DrawableOnCanvas;

public class LineComponent implements DrawableOnCanvas {
    private int x1Coordinate, y1Coordinate, x2Coordinate, y2Coordinate;

    public LineComponent(int x1, int y1, int x2, int y2) {
        this.x1Coordinate = x1;
        this.y1Coordinate = y1;
        this.x2Coordinate = x2;
        this.y2Coordinate = y2;
    }

    public String toString() {
        return "Line: ( " + this.x1Coordinate + " ; " + this.y1Coordinate + " ) --> ( "
                + this.x2Coordinate + " ; " + this.y2Coordinate + " )";
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        if(isLineInsideCanvas(canvas)) {
            if(isVerticalLine()) {
                drawVerticalLineOnCanvas(canvas);
            } else if(isHorizontalLine()) {
                drawHorizontalLineOnCanvas(canvas);
            }
        }
    }

    private boolean isLineInsideCanvas(CanvasComponent canvas) {
        return (canvas.isHavePoint(this.x1Coordinate, this.y1Coordinate)
                && canvas.isHavePoint(this.x2Coordinate, this.y2Coordinate));
    }

    private boolean isVerticalLine() {
        return (this.x1Coordinate == this.x2Coordinate);
    }

    private boolean isHorizontalLine() {
        return (this.y1Coordinate == this.y2Coordinate);
    }

    private void drawVerticalLineOnCanvas(CanvasComponent canvas) {
        int minYCoordinate = Math.min(this.y1Coordinate, this.y2Coordinate);
        int maxYCoordinate = Math.max(this.y1Coordinate, this.y2Coordinate);
        for(int yCoordinate = minYCoordinate; yCoordinate <= maxYCoordinate; yCoordinate++) {
            canvas.setColorAtPoint(this.x1Coordinate, yCoordinate, "x");
        }
    }

    private void drawHorizontalLineOnCanvas(CanvasComponent canvas) {
        int minXCoordinate = Math.min(this.x1Coordinate, this.x2Coordinate);
        int maxXCoordinate = Math.max(this.x1Coordinate, this.x2Coordinate);
        for(int xCoordinate = minXCoordinate; xCoordinate <= maxXCoordinate; xCoordinate++) {
            canvas.setColorAtPoint(xCoordinate, this.y1Coordinate, "x");
        }
    }
}

package ComponentClass;

import UtilInterface.DrawableOnCanvas;
import UtilInterface.SymbolOnCanvas;

public class BucketFillComponent implements DrawableOnCanvas {
    private int xCoordinate, yCoordinate;
    private String oldColor, newColor;

    public BucketFillComponent(int x, int y, String color) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.newColor = color;
        this.oldColor = new String();
    }

    public String toString() {
        return "Bucket fill: ( " + this.xCoordinate + " ; " + this.yCoordinate
                + " ) with color " + this.newColor ;
    }

    @Override
    public void drawOnCanvas(CanvasComponent canvas) {
        oldColor = canvas.getColorAtPoint(this.xCoordinate, this.yCoordinate);
        String SYMBOL_OF_LINE = SymbolOnCanvas.LINE_SYMBOL;
        if(canvas.isHavePoint(this.xCoordinate, this.yCoordinate)
                && !canvas.getColorAtPoint(this.xCoordinate, this.yCoordinate).equals(SYMBOL_OF_LINE)
                && !newColor.equals(SYMBOL_OF_LINE)) {
            fillOnCanvasAtPoint(canvas, this.xCoordinate, this.yCoordinate);
        }
    }

    private void fillOnCanvasAtPoint(CanvasComponent can, int x, int y) {
        if(can.getColorAtPoint(x, y).equals(oldColor)) {
            can.setColorAtPoint(x, y, newColor);
            fillOnCanvasAtPoint(can, x - 1, y);
            fillOnCanvasAtPoint(can, x + 1, y);
            fillOnCanvasAtPoint(can, x, y - 1);
            fillOnCanvasAtPoint(can, x, y + 1);
        }
    }
}

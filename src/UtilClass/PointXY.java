package UtilClass;

public class PointXY {
    private int xCoordinate, yCoordinate;

    public PointXY(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public PointXY(PointXY po) {
        this.xCoordinate = po.getXCoordinate();
        this.yCoordinate = po.getYCoordinate();
    }

    public String toString() {
        return "Point: ( " + this.xCoordinate + " ; " + this.yCoordinate + " )";
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public void setXCoordinate(int x) {
        this.xCoordinate = x;
    }

    public void setYCoordinate(int y) {
        this.yCoordinate = y;
    }
}

package com.hoang.util_classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PointXY {
    private int xCoordinate, yCoordinate;

    public PointXY(PointXY po) {
        this.xCoordinate = po.getXCoordinate();
        this.yCoordinate = po.getYCoordinate();
    }

    public String toString() {
        return "Point: ( " + this.xCoordinate + " ; " + this.yCoordinate + " )";
    }
}

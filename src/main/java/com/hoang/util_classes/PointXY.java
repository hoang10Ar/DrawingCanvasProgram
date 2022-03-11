package com.hoang.util_classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
public class PointXY {
    private int xCoordinate, yCoordinate;

    @Autowired
    public PointXY(@Value("0") int x, @Value("0") int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public PointXY(PointXY po) {
        this(po.getXCoordinate(), po.getYCoordinate());
    }

    public String toString() {
        return "Point: ( " + this.xCoordinate + " ; " + this.yCoordinate + " )";
    }
}

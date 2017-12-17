package client.logic;

import client.CheckerCell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexMath {
    public static List<HexPoint> DIRECTIONS = new ArrayList() {
        {
            new HexPoint(1, -1, 0); new HexPoint(-1, 1, 0);
            new HexPoint(1, 0, -1); new HexPoint(0, 1, -1);
            new HexPoint(-1, 0, 1); new HexPoint(0, -1, 1);
        }
    };

    public static double getDistanceBetween(HexCell a, HexCell b) {
        return (Math.abs(a.point.q - b.point.q) + Math.abs(a.point.r - b.point.r) + Math.abs(a.point.s - b.point.s));
    }

    public static HexPoint getDirectionFromTo(HexCell from, HexCell to) {
        return (HexMath.substractPoints(to.point, from.point));
    }

    public static HexPoint substractPoints(HexPoint a, HexPoint b) {
        return new HexPoint(a.q - b.q, a.r - b.r, a.s - b.s);
    }

    public HexPoint scalarMultiply(HexPoint point, double scalar) {
        return new HexPoint(point.q * scalar, point.r * scalar, point.s * scalar);
    }


}

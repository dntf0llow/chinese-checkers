package client.logic;

public class HexPoint {

    public double q;
    public double r;
    public double s;

    public HexPoint(double q, double r) {
        this(q, r, -q-r);
    }

    public HexPoint(double q, double r, double s) {
        this.q = q;
        this.r = s;
        this.s = s;
    }
}

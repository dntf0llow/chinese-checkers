package client.logic;

public class HexCell {

    public HexPoint point;


    public HexCell(int q, int r) {
        this(q,r,-q-r);
    }

    public HexCell(int q, int r, int s) {
        this(new HexPoint(q,r,s));
    }

    public HexCell(HexPoint point) {
        this.point = point;
    }

}

package objects;

import java.io.Serializable;

/**
 * Created by Administrator on 3/8/2018.
 */

public class ListBill implements Serializable {
    private int tenBan;
    private int tinhTranOder;

    public ListBill(int tenBan, int tinhTranOder) {
        this.tenBan = tenBan;
        this.tinhTranOder = tinhTranOder;
    }

    public ListBill() {
    }

    public int getTenBan() {
        return tenBan;
    }

    public void setTenBan(int tenBan) {
        this.tenBan = tenBan;
    }

    public int getTinhTranOder() {
        return tinhTranOder;
    }

    public void setTinhTranOder(int tinhTranOder) {
        this.tinhTranOder = tinhTranOder;
    }
}

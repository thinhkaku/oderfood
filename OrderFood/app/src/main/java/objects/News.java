package objects;

import java.io.Serializable;

/**
 * Created by Administrator on 3/9/2018.
 */

public class News implements Serializable {
    private int id;
    private String ten,hinhAnh, noiDung, thoiGian;

    public News(int id, String ten, String hinhAnh, String noiDung, String thoiGian) {
        this.id = id;
        this.ten = ten;
        this.hinhAnh = hinhAnh;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
    }


    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}

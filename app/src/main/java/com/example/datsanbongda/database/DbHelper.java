package com.example.datsanbongda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.datsanbongda.model.ChuSan;
import com.example.datsanbongda.model.KhachHang;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "DATSANBONGDA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tDanhGia = "CREATE TABLE DANHGIA(maDanhGia integer primary key autoincrement, tenND text, danhGia text)";
        db.execSQL(tDanhGia);
        db.execSQL("INSERT INTO DANHGIA VALUES(1,'Lê Gia Bảo', 'Sân đẹp'), (2,'Phùng Thái Dương' ,'Phục vụ tốt')," +
                "(3, 'Nguyễn Hoàng Phúc', 'ok'), (4, 'Trương Quốc Trung', 'Tuyệt vời')");

        String tKhachHang = "CREATE TABLE KHACHHANG(maKhachHang integer primary key autoincrement, tenKhachHang text, soDienThoai integer, matKhau text)";
        db.execSQL(tKhachHang);
        db.execSQL("INSERT INTO KHACHHANG VALUES(1, 'Lê Gia Bảo', 0933145378, '12345678')");

        String tLoaiSan = "CREATE TABLE LOAISAN(maLoaiSan integer primary key, loaiSan text, tienSanSang integer, tienSanToi integer)";
        db.execSQL(tLoaiSan);
        db.execSQL("INSERT INTO LOAISAN VALUES(1, 'Sân 7', 300000, 450000), (2, 'Sân 5', 150000, 300000)");

        String tSan = "CREATE TABLE SAN(maSan integer primary key autoincrement, tenSan text, trangThaiSan integer, maLoaiSan integer references LOAISAN(maLoaiSan))";
        db.execSQL(tSan);
        /*
        * Trang Thái
        * 0: Hoat dong
        * 1: Ngung hoat dong
        */
        db.execSQL("INSERT INTO SAN VALUES(1, 'Sân số 1', 0, 2), (2, 'Sân số 2', 0, 2), (3, 'Sân số 3', 0, 2), " +
                "(4, 'Sân số 4', 0, 1), (5, 'Sân số 5', 1, 1)");

        String tChuSan = "CREATE TABLE CHUSAN(maChuSan integer primary key, taiKhoan text, matKhau text, soDienThoai text, facebook text)";
        db.execSQL(tChuSan);
        db.execSQL("INSERT INTO CHUSAN VALUES(1, '0123456789', 'admin', '0987654321', 'https://www.facebook.com/thaiduong.phung.102')");

        String tDatCho = "CREATE TABLE DATCHO(maVe integer primary key autoincrement, thoiGianBatDau text, thoiGianKetThuc text, ngay text, ngayDat text,trangThaiDatCho integer ," +
                "maSan integer references SAN(maSan), maKhachHang integer references KHACHHANG(maKhachHang), maChuSan integer references CHUSAN(maChuSan))";
        db.execSQL(tDatCho);
        /*
        Trang thai
        2: bi huy
        0: cho xac nhan
        1: thanh cong
        */
//        db.execSQL("INSERT INTO DATCHO VALUES(1, '16:00', '17:30', '1/12/2023', '25/11/2023', 2, 1, 1, 1), (2, '17:00', '18:00', '30/11/2023', '27/11/2023',1, 2, 1, 1)");

        String tDatChoChuSan = "CREATE TABLE DATCHOCHUSAN(maVe integer primary key autoincrement, thoiGianBatDau text, thoiGianKetThuc text, ngay text, ngayDat text,trangThaiDatCho integer, maThanhToan text, " +
                "maSan integer references SAN(maSan), maKhachHang integer references KHACHHANG(maKhachHang), maChuSan integer references CHUSAN(maChuSan))";
        db.execSQL(tDatChoChuSan);
        /*
        Trang thai
        2: bi huy
        0: cho xac nhan
        1: thanh cong
        */
//        db.execSQL("INSERT INTO DATCHOCHUSAN VALUES(1, '16:00', '17:30', '1/12/2023', '25/11/2023', 2, 1, 1, 1), (2, '17:00', '18:00', '30/11/2023', '27/11/2023',1, 2, 1, 1)");

        String tDoanhThu = "CREATE TABLE DOANHTHU(maVe integer primary key autoincrement, thoiGianBatDau text, thoiGianKetThuc text, ngay text, ngayDat text, tienDoanhThu integer, trangThaiDatCho integer, " +
                "maSan integer references SAN(maSan), maKhachHang integer references KHACHHANG(maKhachHang), maChuSan integer references CHUSAN(maChuSan))";
        db.execSQL(tDoanhThu);
        /*
        Trang thai
        0: cho xac nhan
        1: thanh cong
        2: bi huy
        Trang Thai doanh thu 2: coc 3: thanh toan du
        */
//        db.execSQL("INSERT INTO DOANHTHU VALUES(1, '16:00', '17:30', '01/12/2023', '25/11/2023', 225000, 2, 1, 1, 1), (2, '17:00', '18:00', '30/11/2023', '27/11/2023', 150000,1, 2, 1, 1)");

        String tMaThanhToan = "CREATE TABLE MATHANHTOAN(maThanhToan integer primary key autoincrement, noiDung text, maVe integer references DATCHOCHUSAN(maVe))";
        db.execSQL(tMaThanhToan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS DANHGIA");
            db.execSQL("DROP TABLE IF EXISTS DOANHTHU");
            db.execSQL("DROP TABLE IF EXISTS CHUSAN");
            db.execSQL("DROP TABLE IF EXISTS DATCHO");
            db.execSQL("DROP TABLE IF EXISTS DATCHOCHUSAN");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            db.execSQL("DROP TABLE IF EXISTS LOAISAN");
            db.execSQL("DROP TABLE IF EXISTS SAN");
            onCreate(db);
        }
    }
    public void DangKi(String hoTen,String sdt, String matKhau){
        ContentValues cv = new ContentValues();
        cv.put("tenKhachHang",hoTen);
        cv.put("soDienThoai",sdt);
        cv.put("matKhau",matKhau);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("KHACHHANG",null,cv);
        db.close();
    }
    public KhachHang DangNhap(String sdt, String matKhau){
       // int result = 0 ;
        KhachHang khachhang = null;
        String str[] = new String [2];
        str [0] =sdt;
        str[1] = matKhau;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from KHACHHANG where soDienThoai=? and matKhau=?",str);
        if(c.getCount() > 0){
            c.moveToFirst();
            khachhang = new KhachHang(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
           // result = 1 ;
        }
        return  khachhang;

    }
    public boolean DangNhapChuSan(String sdt, String matKhau){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CHUSAN WHERE taiKhoan=? and matKhau=?",
                new String[]{sdt, matKhau});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    // hàm cập nhật lại password
    public boolean updatePassKH(String sdt, String pass){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("matKhau",pass);
        long resutl  = database.update("KHACHHANG", cv , "soDienThoai=?",
                new String[]{sdt});
        return resutl != -1;
    }
    public boolean updatePassCS(String taikhoan, String pass){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("matKhau",pass);
        long resutl  = database.update("CHUSAN", cv , "soDienThoai=?",
                new String[]{taikhoan});
        return resutl != -1;
    }
    //Ham kiem tra username
    public boolean KiemTraAdmin(String soDienThoai){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from CHUSAN where soDienThoai=?",new String[]{soDienThoai});
        if(c.getCount()>0){
            return  true;
        }else{
            return  false;
        }
    }
    //kiểm tra tồn tài số điện thoại
    public boolean KiemTraDangNhap(String sdt){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from KHACHHANG where soDienThoai=?",new String []{sdt});
        if(c.getCount()> 0 ){
            return true;
        }else {
            return false;
        }
    }

    public boolean KiemTraPass(String soDienThoai, String matKhau){
        SQLiteDatabase db = getWritableDatabase();

        // Kiểm tra mật khẩu dựa trên số điện thoại và mật khẩu
        Cursor c = db.rawQuery("SELECT * FROM KHACHHANG WHERE soDienThoai=? AND matKhau=?", new String[]{soDienThoai, matKhau});

        if(c.getCount() > 0){
            // Nếu có kết quả từ truy vấn, tức là mật khẩu đúng
            return true;
        } else {
            // Nếu không có kết quả từ truy vấn, tức là mật khẩu sai
            return false;
        }
    }


}

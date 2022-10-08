package com.example.thuchanhtuan05.Service;

import com.example.thuchanhtuan05.Entity.NhanVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface NhanVienRepository extends CrudRepository<NhanVien, String> {
    @Query("select nv from NhanVien nv where nv.luong < 10000")
    List<NhanVien> findNhanVienByLuong();
    @Query("select Sum(nv.luong) from NhanVien nv")
    int tinhTongLuong();
    @Query(value = "select nv.maNV from ChungNhan cn, MayBay mb, NhanVien nv " +
            "where nv.maNV = cn.MaNV and mb.maMB = cn.MaMB and mb.loai like 'Boeing%'", nativeQuery = true)
    List<String> getMaByLoaiMayBay();
    @Query(value = "select nv.maNV, nv.ten, nv.luong from NhanVien nv, ChungNhan cn" +
            " where nv.maNV = cn.MaNV and cn.MaMB = ?1", nativeQuery = true)
    List<NhanVien> findNhanVienByMaMB(int maMB);

    @Query(value = "select nv.maNV from NhanVien nv " +
            "where nv.maNV in (select cn.MaNV from ChungNhan cn, MayBay mb " +
            "where cn.MaMB = mb.maMB and mb.loai like 'Boeing%') " +
            "and nv.maNV in (select cn.MaNV from ChungNhan cn, MayBay mb " +
            "where cn.MaMB = mb.maMB and mb.loai like 'Airbus%')", nativeQuery = true)
    List<String> getMaByLoaiMB();
    @Query(value = "select nv.ten from NhanVien nv " +
            "where nv.maNV in (select cn.MaNV from ChungNhan cn, MayBay mb " +
            "where cn.MaMB = mb.maMB and mb.loai like 'Boeing%')", nativeQuery = true)
    List<String> findTenNhanVienLaiBoeing();
    @Query(value = "select nv.maNV from NhanVien nv " +
            "where nv.maNV in (select cn.MaNV from ChungNhan cn " +
            "group by cn.MaNV " +
            "having count(cn.maMB) = 3)", nativeQuery = true)
    List<String> findMaPhiCongLai3LoaiMayBay();

    @Query(value = "select n.maNV as maNV, max(m.tamBay) as tamBayLonNhat " +
            "from NhanVien n, ChungNhan c, MayBay m " +
            "where n.maNV = c.MaNV  " +
            "and c.MaMB = m.maMB " +
            "group by c.MaNV " +
            "having count(c.MaMB) >= 3", nativeQuery = true)
    public List<Map<String, Object>> findMaNhanVienLai3LoaiMBVaTamBayLonNhat();
    @Query(value = "select cn.MaNV, count(cn.MaMB) as SoLuongLoaiMayBayCoTheLai from chungnhan cn group by cn.MaNV", nativeQuery = true)
    List<Map<String, Object>> phiCongVoiSoLuongMBCoTheLai();
    @Query(value = "select * from NhanVien nv " +
            "where nv.maNV not in (select cn.MaNV from ChungNhan cn " +
            "group by cn.MaNV )", nativeQuery = true)
    List<NhanVien> findNhanVienKhongPhaiLaPhiCong();
    @Query(value = "select nv.MaNV from NhanVien nv " +
            "where nv.luong = (select max(nv.luong) from NhanVien nv)", nativeQuery = true)
    String findNhanVienCoMaxLuong();
    @Query(value = "select sum(nv.luong) from NhanVien nv " +
            "where nv.maNV in (select cn.MaNV from ChungNhan cn " +
            "group by cn.MaNV )", nativeQuery = true)
    int tinhTongLuongChoPhiCong();
}

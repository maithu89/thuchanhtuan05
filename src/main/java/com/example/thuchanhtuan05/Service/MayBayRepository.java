package com.example.thuchanhtuan05.Service;

import com.example.thuchanhtuan05.Entity.MayBay;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface MayBayRepository extends CrudRepository<MayBay, Integer> {
    @Query("select mb.loai from MayBay mb where mb.tamBay > 10000")
    List<String> findLoaiMayBayByTamBay();
    @Query("select count(mb) from MayBay mb where mb.loai like 'Boeing%'")
    int findLoaiMayBay();
    @Query(value = "select mb.maMB from ChungNhan cn, MayBay mb, NhanVien nv " +
            "where nv.maNV = cn.MaNV and mb.maMB = cn.MaMB and nv.ten like ?1%", nativeQuery = true)
    List<Integer> getMaMBByTenNV(String hoNV);
    @Query("select mb.loai from MayBay mb " +
            "where mb.tamBay >= (select cb.doDai from ChuyenBay cb where cb.maCB = ?1)")
    List<String> loaiMayBayChuyenVN280(String maCB);
    @Query(value = "select mb.MaMB as maMB, mb.loai as loai, count(cn.MaNV) as TongPhiCong " +
            "from MayBay mb " +
            "JOIN chungnhan cn ON mb.maMB = cn.MaMB " +
            "group by cn.MaMB", nativeQuery = true)
    List<Map<String, Object>> demSoPhiCongLaiMayBay();
}

package com.example.thuchanhtuan05.Service;

import com.example.thuchanhtuan05.Entity.ChuyenBay;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface ChuyenBayRepository extends CrudRepository<ChuyenBay, String> {
    @Query("select cb from ChuyenBay cb where cb.gaDen = ?1")
    List<ChuyenBay> findChuyenBayByGaDen(String gaDen);
    @Query("select cb from ChuyenBay cb where cb.doDai between 8000 and 10000")
    List<ChuyenBay> findChuyenBayByDoDai();
    @Query("select cb from ChuyenBay cb where cb.gaDi = 'SGN' and cb.gaDen = 'BMV'")
    List<ChuyenBay> findChuyenBayByGaDiVaGaDen();
    @Query("select count(cb) from ChuyenBay cb where cb.gaDi = ?1")
    int findChuyenBayByGaDi(String gaDi);
    @Query("select cb from ChuyenBay cb " +
            "where cb.doDai <= (select mb.tamBay from MayBay mb where mb.loai = ?1)")
    List<ChuyenBay> findChuyenBayByMayBayAirbusA320(String loaiMB);
    @Query(value = "select * from chuyenbay cb where (cb.gaDi, cb.gaDen) in (select cb1.gaDen, cb1.gaDi from chuyenbay cb1)", nativeQuery = true)
    List<ChuyenBay> findCBDiTuGaADenBVeLaiA();
    @Query(value = "select cb.gaDi, count(cb.maCB) as SoLuongChuyenBay from chuyenbay cb group by cb.gaDi", nativeQuery = true)
    List<Map<String, Object>> countCBDiTuGaADenBVeLaiA();
    @Query(value = "select cb.gaDi, sum(cb.chiPhi) as ChiPhi from chuyenbay cb group by cb.gaDi", nativeQuery = true)
    List<Map<String, Object>> chiPhiCBDiTuGaADenBVeLaiA();
    @Query("select cb from ChuyenBay cb " +
            "where cb.gioDi < Time('12:00')")
    List<ChuyenBay> findChuyenBayKhoiHanhTruoc12h();
    @Query(value = "select *, count(cb.maCB) as TongChuyenBay from ChuyenBay cb " +
            "where cb.gioDi < Time('12:00') " +
            "group by cb.gaDi", nativeQuery = true)
    List<Map<String, Object>> demChuyenBayKhoiHanhTruoc12h();
    @Query("select cb from ChuyenBay cb " +
            "where cb.doDai " +
            "between (select min(mb.tamBay) from MayBay mb where mb.loai like 'Boeing%') " +
            "and (select max(mb.tamBay) from MayBay mb where mb.loai like 'Boeing%')")
    List<ChuyenBay> timChuyenBayThucHienBoiTacCaMBBoeing();
}

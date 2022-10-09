package com.example.thuchanhtuan05.Controller;

import com.example.thuchanhtuan05.Entity.ChuyenBay;
import com.example.thuchanhtuan05.Entity.NhanVien;
import com.example.thuchanhtuan05.Service.ChuyenBayRepository;
import com.example.thuchanhtuan05.Service.MayBayRepository;
import com.example.thuchanhtuan05.Service.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    ChuyenBayRepository chuyenBayRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    MayBayRepository mayBayRepository;

    //Cau 1
    @RequestMapping(value = "/timchuyenbayboigaden/{gaden}", method = RequestMethod.GET)
    public List<ChuyenBay> timChuyenBayBoiGaDen(@PathVariable("gaden") String gaDen){
        List<ChuyenBay> chuyenBays = chuyenBayRepository.findChuyenBayByGaDen(gaDen);
        if(chuyenBays == null){
            ResponseEntity.notFound().build();
        }
        return chuyenBays;
    }

    //Cau 2
    @RequestMapping(value = "/maybaycotambaylonhon10000", method = RequestMethod.GET)
    public List<String> mayBayCoTamBayLonHon10000(){
        List<String> mayBays = mayBayRepository.findLoaiMayBayByTamBay();
        if(mayBays == null){
            ResponseEntity.notFound().build();
        }
        return mayBays;
    }
    //Cau 3
    @RequestMapping(value = "/timnhanvientheoluong", method = RequestMethod.GET)
    public List<NhanVien> timNhanVienTheoLuong(){
        List<NhanVien> nhanViens = nhanVienRepository.findNhanVienByLuong();
        if(nhanViens == null){
            ResponseEntity.notFound().build();
        }
        return nhanViens;
    }

    //Cau 4
    @RequestMapping(value = "/timchuyenbayboidodai", method = RequestMethod.GET)
    public List<ChuyenBay> timChuyenBayBoiDoDai(){
        List<ChuyenBay> chuyenBays = chuyenBayRepository.findChuyenBayByDoDai();
        if(chuyenBays == null){
            ResponseEntity.notFound().build();
        }
        return chuyenBays;
    }

    //Cau 5
    @RequestMapping(value = "/timchuyenbayboigadenvagadi", method = RequestMethod.GET)
    public List<ChuyenBay> timChuyenBayBoiGaDenVaGaDi(){
        List<ChuyenBay> chuyenBays = chuyenBayRepository.findChuyenBayByGaDiVaGaDen();
        if(chuyenBays == null){
            ResponseEntity.notFound().build();
        }
        return chuyenBays;
    }
    //Cau 6
    @RequestMapping(value = "/sochuyenbayxuatphattusaigon/{gaDi}", method = RequestMethod.GET)
    public String soChuyenBayXuatPhatTuSaiGon(@PathVariable("gaDi") String gaDi){
        int soChuyenBay = chuyenBayRepository.findChuyenBayByGaDi(gaDi);
        if(soChuyenBay == 0){
            ResponseEntity.notFound().build();
        }
        return  "Có " + soChuyenBay + " chuyến bay xuất phát từ Sài Gòn";
    }


    //Cau 7
    @RequestMapping(value = "/soloaimaybayboeing", method = RequestMethod.GET)
    public String soLoaiMayBayBoeing(){
        int soLoaiMayBay = mayBayRepository.findLoaiMayBay();
        if(soLoaiMayBay == 0){
            ResponseEntity.notFound().build();
        }
        return  "Có " + soLoaiMayBay + " loại máy bay Boeing";
    }
    //Cau 8
    @RequestMapping(value = "/tongsoluongnhanvien", method = RequestMethod.GET)
    public String tongSoLuongNhanVien(){
        int soLuong = nhanVienRepository.tinhTongLuong();
        if(soLuong == 0){
            ResponseEntity.notFound().build();
        }
        return  "Tổng số lương phải trả cho các nhân viên là " + soLuong;
    }
    //Cau 9
    @RequestMapping(value = "/manhanvienlaimaybayboeing", method = RequestMethod.GET)
    public List<String> maNhanVienLaiMayBayBoeing(){
        List<String> maNhanViens = nhanVienRepository.getMaByLoaiMayBay();
        if(maNhanViens == null){
            ResponseEntity.notFound().build();
        }
        return  maNhanViens;
    }
    //Cau 10
    @RequestMapping(value = "/nhanvienlaimaybay747/{maMB}", method = RequestMethod.GET)
    public List<NhanVien> nhanVienLaiMayBay747(@PathVariable("maMB") int maMB){
        List<NhanVien> nhanViens = nhanVienRepository.findNhanVienByMaMB(maMB);
        if(nhanViens == null){
            ResponseEntity.notFound().build();
        }
        return  nhanViens;

    }


    //Cau 11
    @RequestMapping(value = "/timmamaybaytheoho/{hoNV}", method = RequestMethod.GET)
    public List<Integer> nhanVienLaiMayBay747(@PathVariable("hoNV") String hoNV){
        List<Integer> maMayBays = mayBayRepository.getMaMBByTenNV(hoNV);
        if(maMayBays == null){
            ResponseEntity.notFound().build();
        }
        return  maMayBays;
    }
    //Cau 12
    @RequestMapping(value = "/maphicongvualaiboeingvaairbus", method = RequestMethod.GET)
    public List<String> maPhiCongVuaLaiBoeingVuaLaiAirbus(){
        List<String> maPhiCongs = nhanVienRepository.getMaByLoaiMB();
        if(maPhiCongs == null){
            ResponseEntity.notFound().build();
        }
        return  maPhiCongs;
    }
    // Cau 13
    @RequestMapping(value = "/loaimaybaydichuyenvn280/{maCB}", method = RequestMethod.GET)
    public List<String> maPhiCongVuaLaiBoeingVuaLaiAirbus(@PathVariable("maCB") String maCB){
        List<String> loaiMBs = mayBayRepository.loaiMayBayChuyenVN280(maCB);
        if(loaiMBs == null){
            ResponseEntity.notFound().build();
        }
        return  loaiMBs;
    }
    // Cau 14
    @RequestMapping(value = "/chuyenbaythuchienboiairbusa320/{loaiMB}", method = RequestMethod.GET)
    public List<ChuyenBay> chuyenBayThucHienBoiAirbusA320(@PathVariable("loaiMB") String loaiMB){
        List<ChuyenBay> chuyenBays = chuyenBayRepository.findChuyenBayByMayBayAirbusA320(loaiMB);
        if(chuyenBays == null || chuyenBays.size() == 0){
            ResponseEntity.notFound().build();
        }
        return  chuyenBays;
    }
    //Cau 15
    @RequestMapping(value = "/phiconglaimaybayboeing", method = RequestMethod.GET)
    public List<String> phiCongLaiMayBayBoeing(){
        List<String> tenPhiCongs = nhanVienRepository.findTenNhanVienLaiBoeing();
        if(tenPhiCongs == null){
            ResponseEntity.notFound().build();
        }
        return  tenPhiCongs;
    }

    //Cau 16
    @RequestMapping(value = "/demsophiconglaimaybay", method = RequestMethod.GET)
    public List<Map<String, Object>> demSoPhiCongLaiMayBay(){
        List<Map<String, Object>> mayBays = mayBayRepository.demSoPhiCongLaiMayBay();
        if(mayBays== null){
            ResponseEntity.notFound().build();
        }
        return  mayBays;
    }

    //Cau 17
    @RequestMapping(value = "/timchuyenbayditugaadengabvelaigaa", method = RequestMethod.GET)
    public List<ChuyenBay> timCBDiTuGaADenBVeLaiA(){
        List<ChuyenBay> chuyenBays = chuyenBayRepository.findCBDiTuGaADenBVeLaiA();
        if(chuyenBays== null){
            ResponseEntity.notFound().build();
        }
        return  chuyenBays;
    }
    //Cau 18
    @RequestMapping(value = "/demchuyenbayditugaadenbvelaia", method = RequestMethod.GET)
    public List<Map<String, Object>> countCBDiTuGaADenBVeLaiGaA(){
        List<Map<String, Object>> chuyenBays = chuyenBayRepository.countCBDiTuGaADenBVeLaiA();
        if(chuyenBays == null){
            ResponseEntity.notFound().build();
        }
        return  chuyenBays;
    }


    //Cau 19
    @RequestMapping(value = "/chiphichuyenbayditugaadenbvelaia", method = RequestMethod.GET)
    public List<Map<String, Object>> chiPhiCBDiTuGaADenBVeLaiGaA(){
        List<Map<String, Object>> chuyenBays = chuyenBayRepository.chiPhiCBDiTuGaADenBVeLaiA();
        if(chuyenBays == null){
            ResponseEntity.notFound().build();
        }
        return  chuyenBays;
    }
    //Cau 20
    @RequestMapping(value = "/chuyenbaykhoihanhtruoc12h", method = RequestMethod.GET)
    public List<ChuyenBay> chuyenBayKhoiHanhTruoc12h(){
        List<ChuyenBay> chuyenBays = chuyenBayRepository.findChuyenBayKhoiHanhTruoc12h();
        if(chuyenBays == null){
            ResponseEntity.notFound().build();
        }
        return  chuyenBays;
    }
}

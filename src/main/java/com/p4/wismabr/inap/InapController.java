package com.p4.wismabr.inap;

import com.p4.wismabr.kamar.Kamar;
import com.p4.wismabr.kamar.KamarDao;
import com.p4.wismabr.pagination.Pagination;
import com.p4.wismabr.pembayaran.Pembayaran;
import com.p4.wismabr.pembayaran.PembayaranDao;
import com.p4.wismabr.reservasi.Reservasi;
import com.p4.wismabr.reservasi.ReservasiDao;
import com.p4.wismabr.statusinap.StatusInap;
import com.p4.wismabr.statusinap.StatusInapDao;
import com.p4.wismabr.tamu.Tamu;
import com.p4.wismabr.tamu.TamuDao;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InapController {
    @Autowired private InapDao oid;
    @Autowired private TamuDao otd;
    @Autowired private KamarDao okd;
    @Autowired private StatusInapDao osid;
    @Autowired private ReservasiDao ord;
    @Autowired private PembayaranDao opd;
    
    @RequestMapping("/inap/daftar-inap")
    public void tampilDaftarInap(@RequestParam(value="page",required=false) Integer halaman,
    ModelMap mm) throws SQLException, Exception{
        List<Inap> linap =  oid.tampilInap();        
        if(halaman == null){
            halaman=1;
        }
        Pagination oPage = new Pagination(15, 8, halaman, linap.size());
        
        mm.addAttribute("mulaiPage", oPage.getAwalPageTampil());
        mm.addAttribute("akhirPage", oPage.getAkhirPageTampil());
        mm.addAttribute("totalHalaman", oPage.getTotalHalaman());
        mm.addAttribute("halamanAktif", halaman);
                        
        linap = linap.subList(oPage.getAwalList(), oPage.getAkhirList());
        
        mm.addAttribute("daftarInap", linap);
    }
    
    @RequestMapping("/inap/daftar-checkin")
    public void tampilDaftarCheckin(@RequestParam(value="page",required=false) Integer halaman,
    ModelMap mm) throws SQLException, Exception{
        List<Inap> linap =  oid.tampilCheckin();        
        
        if(halaman == null){
            halaman=1;
        }
        Pagination oPage = new Pagination(10, 8, halaman, linap.size());
        
        mm.addAttribute("mulaiPage", oPage.getAwalPageTampil());
        mm.addAttribute("akhirPage", oPage.getAkhirPageTampil());
        mm.addAttribute("totalHalaman", oPage.getTotalHalaman());
        mm.addAttribute("halamanAktif", halaman);
                        
        linap = linap.subList(oPage.getAwalList(), oPage.getAkhirList());
        
        mm.addAttribute("daftarCheckin",linap);
    }
    
    @RequestMapping(value="/inap/input", method= RequestMethod.GET)
    public void tampilFormCheckin(@RequestParam(value="idReservasi",required=false) Integer idReservasi,
    ModelMap mm) throws Exception{                
        StatusInap oStatusInap = new StatusInap();
        
        oStatusInap.setoInap(new Inap());        
        oStatusInap.getoInap().setInapId(oid.tampilInapId());
        
        Reservasi oReservasi = ord.cariReservasiById(idReservasi);
        if(oReservasi!=null){
            oStatusInap.getoInap().setoTamu(oReservasi.getoTamu());
            oStatusInap.setoKamar(oReservasi.getoKamar());
            mm.addAttribute("idReservasi",idReservasi);
        }
        
        mm.addAttribute("statusInap", oStatusInap);
        List<Tamu> lTamu =  otd.tampilTamu();
        mm.addAttribute("daftarTamu", lTamu);
        
        List lKota = otd.cariGroupKota();
        mm.addAttribute("daftarKota", lKota);
        
        List<Kamar> lKamar = okd.tampilKamarIn();
        mm.addAttribute("daftarKamar", lKamar);
    }
    
    @RequestMapping(value="/inap/input", method= RequestMethod.POST)
    public String prosesFormCheckin(@ModelAttribute StatusInap oStatusInap,
    @RequestParam("idReservasi") Integer idReservasi,
    ModelMap mm) throws Exception{
    
        if(idReservasi!=null){
            ord.jadiCheckin(idReservasi);
            Reservasi oReservasi = ord.cariReservasiById(idReservasi);
            okd.updateStatResKosong(oReservasi.getoKamar().getId());
        }
        
//        checkin
        oStatusInap.getoInap().setWaktuCheckin(new Date());
        oid.simpanCheckin(oStatusInap.getoInap());
//
//        set objek oInap baru berdasarkan inap by inapID
        oStatusInap.setoInap(oid.cariInapByInapId(oStatusInap.getoInap().getInapId()));
//        
//      status inap
        oStatusInap.setWaktuStatus(new Date());
        osid.simpanStatusInap(oStatusInap);
//        
//        ubah status kamar        
        okd.updateStatInTerisi(oStatusInap.getoKamar().getId());
//      
//        karena sudah pasti masuk, jadi pesan dibuat sndri
        mm.addAttribute("hasilCheckin",true);
        return "redirect:daftar-checkin";
    }
    
    
    @RequestMapping("/inap/hapus")
    public String hapusCheckin(@RequestParam("id") Integer id,
    ModelMap mm) throws SQLException, Exception{        
        Integer hasilHapus = null;
        
        List<StatusInap> lstatusInap = osid.tampilStatusInap(id);
        if(lstatusInap.size()<=1){
            if(lstatusInap.size()==1){
                StatusInap oStatusInap = lstatusInap.get(0);
                osid.hapusStatusInapById(oStatusInap.getId());
                okd.updateStatInKosong(oStatusInap.getoKamar().getId());
            }
            hasilHapus = oid.hapusCheckin(id);
        }else{
            hasilHapus=0;
        }
        
        mm.addAttribute("hasilHapus", hasilHapus);
        return "redirect:daftar-checkin";
    }
    
    @RequestMapping("/inap/checkout")
    public String checkout(@RequestParam("id") Integer Id,
    ModelMap mm) throws SQLException, Exception{
        oid.checkout(Id,new Date());        
        StatusInap oStatusInap = osid.tampilStatusInap(Id).get(0);
        okd.updateStatInKosong(oStatusInap.getoKamar().getId());
               
        Pembayaran oPembayaran = new Pembayaran();
        oPembayaran.setoInap(oStatusInap.getoInap());
        oPembayaran.setTotalBiaya(osid.cariTotalBiayaByIdInap(oStatusInap.getoInap().getId()));
        
        opd.tambahPembayaran(oPembayaran);
                
        mm.addAttribute("hasilCheckout",true);
        return "redirect:daftar-inap";
    } 
}

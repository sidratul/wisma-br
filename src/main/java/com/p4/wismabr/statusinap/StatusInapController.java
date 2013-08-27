package com.p4.wismabr.statusinap;

import com.p4.wismabr.kamar.Kamar;
import com.p4.wismabr.kamar.KamarDao;
import com.p4.wismabr.pembayaran.Pembayaran;
import com.p4.wismabr.pembayaran.PembayaranDao;
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
public class StatusInapController {
    @Autowired private StatusInapDao osid;
    @Autowired private KamarDao okd;
    @Autowired private PembayaranDao opd;
    
    @RequestMapping("/status-inap/daftar-status")
    public ModelMap tampilStatusCheckin(@RequestParam("idInap") Integer idInap) throws SQLException, Exception{
        ModelMap mm = new ModelMap();
        
        List<StatusInap> lStatusInap=osid.tampilStatusInap(idInap);
        mm.addAttribute("daftarStatusInap",lStatusInap);
        
        return mm;
    }
    
    @RequestMapping("/status-inap/daftar-status-inap")
    public ModelMap tampilStatusInap(@RequestParam("idInap") Integer idInap) throws SQLException, Exception{
        ModelMap mm = new ModelMap();
        
        List<StatusInap> lStatusInap=osid.tampilStatusInap(idInap);
        mm.addAttribute("daftarStatusInap",lStatusInap);
        
        Pembayaran oPembayaran=opd.cariPembayaranByIdInap(idInap);
        mm.addAttribute("pembayaran", oPembayaran);
        
        return mm;
    }
    
    @RequestMapping(value="/status-inap/input",method= RequestMethod.GET)
    public ModelMap formInputStatusInap(@RequestParam("id") Integer id,@RequestParam("aksi") String aksi) throws SQLException, Exception{
        ModelMap mm = new ModelMap();
        StatusInap oStatusInap = osid.tampilStatusInapById(id);
        if("tambah".equals(aksi)){            
            oStatusInap.setId(null);
        }
        
        mm.addAttribute("statusInap", oStatusInap);
        
        List<Kamar> lKamar = okd.tampilKamarIn();
        mm.addAttribute("daftarKamar", lKamar);
        
        return mm;
    }
    
    @RequestMapping(value="/status-inap/proses-input",method= RequestMethod.POST)
    public String prosesInputStatusInap(@ModelAttribute StatusInap oStatusInap,
    @RequestParam("idKamarLama") Integer idKamar,
    ModelMap mm) throws SQLException, Exception{        
        //untuk tambah
        oStatusInap.setWaktuStatus(new Date());
        
        osid.simpanStatusInap(oStatusInap);
        okd.updateStatInKosong(idKamar);
        okd.updateStatInTerisi(oStatusInap.getoKamar().getId());
        
        String hasil="update";
        if(oStatusInap.getId()==null){
            hasil="simpan";
        }
        
        mm.addAttribute("hasilSimpan",hasil);
        mm.addAttribute("idInap",oStatusInap.getoInap().getId());
        return "redirect:daftar-status";
    }
}
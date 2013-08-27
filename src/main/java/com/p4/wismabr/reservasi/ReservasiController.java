package com.p4.wismabr.reservasi;

import com.p4.wismabr.kamar.Kamar;
import com.p4.wismabr.kamar.KamarDao;
import com.p4.wismabr.pagination.Pagination;
import com.p4.wismabr.tamu.Tamu;
import com.p4.wismabr.tamu.TamuDao;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class ReservasiController {
    @Autowired ReservasiDao ord;
    @Autowired TamuDao otd;
    @Autowired KamarDao okd;
    
    @RequestMapping("/reservasi/status") 
    public void statusReservasi(@RequestParam(value="page",required=false) Integer halaman,
    ModelMap mm) throws Exception{
        List<Reservasi> lReservasi = ord.tampilReservasi();
        
        if(halaman == null){
            halaman=1;
        }
        Pagination oPage = new Pagination(10, 8, halaman, lReservasi.size());
        
        mm.addAttribute("mulaiPage", oPage.getAwalPageTampil());
        mm.addAttribute("akhirPage", oPage.getAkhirPageTampil());
        mm.addAttribute("totalHalaman", oPage.getTotalHalaman());
        mm.addAttribute("halamanAktif", halaman);
                        
        lReservasi = lReservasi.subList(oPage.getAwalList(), oPage.getAkhirList());
        
        mm.addAttribute("daftarReservasi", lReservasi);
    }    
    
    @RequestMapping(value="/reservasi/input",method= RequestMethod.GET)
    public void formReservasi(ModelMap mm) throws Exception{
        Reservasi oReservasi = new Reservasi();
        oReservasi.setReservasiId(ord.tampilReservasiId());
        mm.addAttribute("reservasi", oReservasi);
        
        //        untuk tanggal reservasi min bsok
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        Date tglBsok = cal.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String stringTglBesok = sf.format(tglBsok);        
        
        mm.addAttribute("tglBesok",stringTglBesok);
        
        mm = addListInputReservasi(mm);
    }
    
    private ModelMap addListInputReservasi(ModelMap mm) throws Exception{
        List<Tamu> lTamu = otd.tampilTamu();
        mm.addAttribute("daftarTamu", lTamu);
//        
        List lKota = otd.cariGroupKota();
        mm.addAttribute("daftarKota", lKota);
//        
        List<Kamar> lKamar = okd.tampilKamar();
        mm.addAttribute("daftarKamar", lKamar);
        
        return mm;
    }
        
    
    @RequestMapping(value="/reservasi/input",method= RequestMethod.POST)
    public String prosesFormReservasi(@ModelAttribute @Valid Reservasi oReservasi,
    @RequestParam("resTgl") String resTgl,    
    BindingResult brHasilValidasi,
    SessionStatus status,
    ModelMap mm) throws Exception{
        Date reservasiTgl = new SimpleDateFormat("yyyy-MM-dd").parse(resTgl);
        oReservasi.setReservasiTgl(reservasiTgl);
        oReservasi.setTglReservasi(new Date());
        oReservasi.setStatusReservasi("1");
        
        if(!ord.cekReservasi(oReservasi)){
            mm.addAttribute("hasilReservasi", 0);
            brHasilValidasi.addError(null);
        }
        
        if(brHasilValidasi.hasErrors()){
            mm = addListInputReservasi(mm);
            return "/reservasi/input";
        }
        
        ord.simpanReservasi(oReservasi);
        okd.updateStatResTerpesan(oReservasi.getoKamar().getId());
               
        mm.addAttribute("hasilReservasi", 1);
        status.setComplete();
        return "redirect:status";
    }
    
    @RequestMapping("/reservasi/hapus")
    public String hapusReservasi(@RequestParam("id") Integer id,
    ModelMap mm) throws SQLException, Exception{
        Reservasi oReservasi = ord.cariReservasiById(id);
        ord.hapusReservasi(id);
        okd.updateStatResKosong(oReservasi.getoKamar().getId());
        
        mm.addAttribute("hasilHapus", 1);
        return "redirect:status";
    } 
    
    @RequestMapping("/reservasi/batal")
    public String batalReservasi(@RequestParam("id") Integer id,
    ModelMap mm) throws SQLException, Exception{        
        Reservasi oReservasi = ord.cariReservasiById(id);
        ord.batalReservasi(id);
        okd.updateStatResKosong(oReservasi.getoKamar().getId());
        
        mm.addAttribute("hasilBatal", 1);
        return "redirect:status";
    }
    
}
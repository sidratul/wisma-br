
package com.p4.wismabr.laporan;

import com.p4.wismabr.pembayaran.Pembayaran;
import com.p4.wismabr.pembayaran.PembayaranDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LaporanController {
    @Autowired private LaporanDao old;
    
    @RequestMapping("laporan/input-tanggal")
    public void tampilFomLaporan(){
        
    }
    
    @RequestMapping(value="laporan/tampil-laporan",method= RequestMethod.POST)
    public void prosesLaporan(@RequestParam("tglAwal") String tglAwal,
    @RequestParam("tglAkhir") String tglAkhir,
    ModelMap mm) throws ParseException, SQLException, Exception{
        Date tglAwalLaporan = new SimpleDateFormat("yyyy-MM-dd").parse(tglAwal);
        Date tglAkhirLaporan = new SimpleDateFormat("yyyy-MM-dd").parse(tglAkhir);
        List<Pembayaran> lPembayaran = old.tampilPembayaran(tglAwalLaporan,tglAkhirLaporan);
        
        mm.addAttribute("tglAwal", tglAwal);
        mm.addAttribute("tglAkhir", tglAkhir);
        mm.addAttribute("laporanPembayaran", lPembayaran);
    }
    
    
}

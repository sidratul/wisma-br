package com.p4.wismabr.pembayaran;

import com.p4.wismabr.pagination.Pagination;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class PembayaranController {
    @Autowired private PembayaranDao opd;
    
    @RequestMapping("/pembayaran/belum-lunas")
    public void tampilPembayaranBelumLunas(@RequestParam(value="page",required=false) Integer halaman,
    ModelMap mm) throws SQLException, Exception{
        List<Pembayaran> lPembayaran =  opd.tampilPembayaranBelumLunas();
        
        if(halaman == null){
            halaman=1;
        }
        Pagination oPage = new Pagination(10, 8, halaman, lPembayaran.size());
        
        mm.addAttribute("mulaiPage", oPage.getAwalPageTampil());
        mm.addAttribute("akhirPage", oPage.getAkhirPageTampil());
        mm.addAttribute("totalHalaman", oPage.getTotalHalaman());
        mm.addAttribute("halamanAktif", halaman);
                        
        lPembayaran = lPembayaran.subList(oPage.getAwalList(), oPage.getAkhirList());
        
        mm.addAttribute("daftarPembayaran", lPembayaran);
    }
    
    @RequestMapping("/pembayaran/lunas")
    public void tampilPembayaranLunas(@RequestParam(value="page",required=false) Integer halaman,
    ModelMap mm) throws SQLException, Exception{
        List<Pembayaran> lPembayaran =  opd.tampilPembayaranLunas();
        
        if(halaman == null){
            halaman=1;
        }
        Pagination oPage = new Pagination(15, 8, halaman, lPembayaran.size());
        
        mm.addAttribute("mulaiPage", oPage.getAwalPageTampil());
        mm.addAttribute("akhirPage", oPage.getAkhirPageTampil());
        mm.addAttribute("totalHalaman", oPage.getTotalHalaman());
        mm.addAttribute("halamanAktif", halaman);
                        
        lPembayaran = lPembayaran.subList(oPage.getAwalList(), oPage.getAkhirList());
        mm.addAttribute("daftarPembayaran", lPembayaran);
    }    
    
    @RequestMapping(value="/pembayaran/input-pembayaran",method= RequestMethod.GET)
    public ModelMap formPembayaran(@RequestParam("id") Integer id) throws SQLException, Exception{
        ModelMap mm = new ModelMap();
        Pembayaran oPembayaran = opd.cariPembayaranByid(id);
        mm.addAttribute("pembayaran", oPembayaran);
        return mm;
    }
    
    @RequestMapping(value="/pembayaran/input-pembayaran",method= RequestMethod.POST)
    public String prosesPembayaran(@ModelAttribute Pembayaran oPembayaran,
    ModelMap mm) throws SQLException, Exception{
        oPembayaran.setTglBayar(new Date());        
        if(oPembayaran.getTotalBiaya().stripTrailingZeros().hashCode() == oPembayaran.getSudahBayar().stripTrailingZeros().hashCode()){
            oPembayaran.setStatusBayar("1");
            opd.tambahPembayaran(oPembayaran);        
            mm.addAttribute("hasilPembayaran", true);
            return "redirect:lunas";
        }
        oPembayaran.setStatusBayar("0");
        opd.tambahPembayaran(oPembayaran);
        
        mm.addAttribute("hasilPembayaran", true);
        mm.addAttribute("sisaPembayaran",oPembayaran.getTotalBiaya().subtract(oPembayaran.getSudahBayar()));
        return "redirect:belum-lunas";
    }
}
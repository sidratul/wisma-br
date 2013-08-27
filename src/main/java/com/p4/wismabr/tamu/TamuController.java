package com.p4.wismabr.tamu;

import com.p4.wismabr.pagination.Pagination;
import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class TamuController {
    @Autowired private TamuDao otd;    
    
    @RequestMapping("/tamu/status")
    public void statuTamu(@RequestParam(value="page",required=false) Integer halaman,
    ModelMap mm)throws Exception{
        List<Tamu> lTamu =  otd.tampilTamu();
        if(halaman == null){
            halaman=1;
        }
        
//        jumlah list yg tampil dalam 1 halaman = 10
//        jumlah pag yg terluhat 8 + 1
//        halaman yg aktif
//        jumlah list
        Pagination oPage = new Pagination(10, 8, halaman, lTamu.size());
        
        mm.addAttribute("mulaiPage", oPage.getAwalPageTampil());
        mm.addAttribute("akhirPage", oPage.getAkhirPageTampil());
        mm.addAttribute("totalHalaman", oPage.getTotalHalaman());
        mm.addAttribute("halamanAktif", halaman);
                
        lTamu = lTamu.subList(oPage.getAwalList(),oPage.getAkhirList());
        mm.addAttribute("daftarTamu", lTamu);
    }    
    
    @RequestMapping(value="/tamu/input",method= RequestMethod.GET)
    public void formTamu(@RequestParam(required=false) Integer id,
    ModelMap mm)throws Exception{
        Tamu oTamu = otd.cariTamuById(id);
        if(oTamu==null){
            oTamu = new Tamu();
        }
        mm.addAttribute("tamu", oTamu);
        
    }
    
    @RequestMapping(value="/tamu/input",method= RequestMethod.POST)
    public String prosesFormTamu(@ModelAttribute @Valid Tamu oTamu,
    BindingResult hasilValidasi,
    SessionStatus status,
    ModelMap mm) throws Exception{
        if(hasilValidasi.hasErrors()){
            return "/tamu/input";
        }
        
        otd.simpanTamu(oTamu);
        
        String hasilSimpan="update";
        if(oTamu.getId()==null){
            hasilSimpan="tambah";
        }
                
        mm.addAttribute("hasilSimpan", hasilSimpan);
        
        status.setComplete();
        return "redirect:status";
    }
    
    @RequestMapping("/tamu/hapus")
    public String hapusTamu(@RequestParam Integer id, ModelMap mm) throws Exception{                
        Integer hasilHapus=null;
        try {
            hasilHapus = 1;
            otd.hapus(id);
        } catch (SQLException se){
            hasilHapus=0;
        }
                
        mm.addAttribute("hasilHapus", hasilHapus);
        return "redirect:status";
    }
}

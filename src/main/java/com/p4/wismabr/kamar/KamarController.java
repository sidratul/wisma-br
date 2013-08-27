package com.p4.wismabr.kamar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KamarController {
    @Autowired private KamarDao okd;
    
    @RequestMapping("/kamar/status")
    public ModelMap statuKamar()throws Exception{
        List<Kamar> lKamar =  okd.tampilKamar();
        ModelMap mm = new ModelMap();
        mm.addAttribute("daftarKamar", lKamar);
        return mm;
    }
}

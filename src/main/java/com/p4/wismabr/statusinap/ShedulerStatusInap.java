package com.p4.wismabr.statusinap;

import com.p4.wismabr.inap.Inap;
import com.p4.wismabr.inap.InapDao;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShedulerStatusInap {
    @Autowired StatusInapDao osid;
    @Autowired InapDao oid;
    
    public void statusInapAuto(){        
        try {
            List<Inap> linap = oid.tampilCheckin();
            
            for (Inap oInap : linap) {
    //                mencari status inap berdasarkan idInap dan tanggal sekarang
                Date tglSkrg = new Date();
                StatusInap oStatusInapHariIni= oStatusInapHariIni = osid.cariByIdInapAndWaktuStatus(oInap.getId(),tglSkrg);
            
                if(oStatusInapHariIni == null){
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, -1);
                    StatusInap oStatusInap= osid.cariByIdInapAndWaktuStatus(oInap.getId(),cal.getTime());

    //                    untuk insert baru dengan nilai kamar, idTamu, harga, jumlah orang yang sama
                    StatusInap oStatusInapBaru = new StatusInap();
                    
                    oStatusInapBaru.setoInap(oStatusInap.getoInap());
                    oStatusInapBaru.setoKamar(oStatusInap.getoKamar());
                    oStatusInapBaru.setWaktuStatus(tglSkrg);
                    oStatusInapBaru.setJumOrang(oStatusInap.getJumOrang());
                    oStatusInapBaru.setBiaya(oStatusInap.getBiaya());
                    
                    osid.simpanStatusInap(oStatusInapBaru);
                }
            }
        }catch (NullPointerException npe) {
            Logger.getLogger(ShedulerStatusInap.class.getName()).log(Level.SEVERE, null, npe);
        }catch (Exception ex) {
            Logger.getLogger(ShedulerStatusInap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

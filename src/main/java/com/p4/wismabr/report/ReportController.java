
package com.p4.wismabr.report;

import com.p4.wismabr.laporan.LaporanDao;
import com.p4.wismabr.pembayaran.Pembayaran;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRFillVariable;
import net.sf.jasperreports.engine.xml.JRVariableFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file-laporan/")
public class ReportController {
    @Autowired private LaporanDao old;
    
    @RequestMapping("pdf")
    public ModelAndView tampilLaporanInapPdf(@RequestParam("tglAwal") String tglAwal,
    @RequestParam("tglAkhir") String tglAkhir,
    ModelAndView mav) throws SQLException, ParseException, Exception {
        Date tglAwalLaporan = new SimpleDateFormat("yyyy-MM-dd").parse(tglAwal);
        Date tglAkhirLaporan = new SimpleDateFormat("yyyy-MM-dd").parse(tglAkhir);
        
        List<Pembayaran> lPembayaran = old.tampilPembayaran(tglAwalLaporan,tglAkhirLaporan);
        JRDataSource jrDs = new JRBeanCollectionDataSource(lPembayaran);
        
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("datasource", jrDs);
        map.put("tglAwalLaporan", tglAwalLaporan);
        map.put("tglAkhirLaporan", tglAkhirLaporan);
        
        mav = new ModelAndView("pdfReport", map);
        return mav;
    }
    
    @RequestMapping("xls")
    public ModelAndView tampilLaporanInapXls(@RequestParam("tglAwal") String tglAwal,
    @RequestParam("tglAkhir") String tglAkhir,
    ModelAndView mav) throws SQLException, ParseException, Exception {
        Date tglAwalLaporan = new SimpleDateFormat("yyyy-MM-dd").parse(tglAwal);
        Date tglAkhirLaporan = new SimpleDateFormat("yyyy-MM-dd").parse(tglAkhir);
        
        List<Pembayaran> lPembayaran = old.tampilPembayaran(tglAwalLaporan,tglAkhirLaporan);
        JRDataSource jrDs = new JRBeanCollectionDataSource(lPembayaran);
                
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("datasource", jrDs);
        map.put("tglAwalLaporan", tglAwalLaporan);
        map.put("tglAkhirLaporan", tglAkhirLaporan);
        
        
        mav = new ModelAndView("xlsReport", map);
        return mav;
    }
}
package com.p4.wismabr.laporan;

import com.p4.wismabr.inap.InapDao;
import com.p4.wismabr.pembayaran.Pembayaran;
import com.p4.wismabr.pembayaran.PembayaranDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LaporanDao {
    @Autowired private DataSource dataSource;    
    @Autowired private InapDao oid;    
    
    private String sqlTampilLaporan = "SELECT pembayaran.* FROM pembayaran "
            + "INNER JOIN inap ON pembayaran.idInap = inap.id "
            + "WHERE inap.waktuCheckout BETWEEN ? AND  ? ORDER BY inap.waktuCheckout ASC ";
    
    public List<Pembayaran> tampilPembayaran(Date tglAwalLaporan, Date tglAkhirLaporan) throws SQLException, Exception{
        Connection c = dataSource.getConnection();
        
        PreparedStatement psTampilLaporan = c.prepareStatement(sqlTampilLaporan);
        psTampilLaporan.setDate(1,new java.sql.Date(tglAwalLaporan.getTime()));
        psTampilLaporan.setDate(2,new java.sql.Date(tglAkhirLaporan.getTime()));
        
        ResultSet rs = psTampilLaporan.executeQuery();
        List<Pembayaran> lPembayaran  = new ArrayList<Pembayaran>();
        while(rs.next()){
            Pembayaran oPembayaran = new Pembayaran();
            
            oPembayaran.setId(rs.getInt("id"));
            oPembayaran.setoInap(oid.cariInapById(rs.getInt("idInap")));
            oPembayaran.setTotalBiaya(rs.getBigDecimal("totalBiaya"));
            oPembayaran.setSudahBayar(rs.getBigDecimal("sudahBayar"));
            oPembayaran.setTglBayar(rs.getDate("tglBayar"));
            oPembayaran.setStatusBayar(rs.getString("statusBayar"));
            
            lPembayaran.add(oPembayaran);
        }        
        c.close();
        return lPembayaran;
    }
}

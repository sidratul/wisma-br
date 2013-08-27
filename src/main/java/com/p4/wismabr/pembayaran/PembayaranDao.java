package com.p4.wismabr.pembayaran;

import com.p4.wismabr.inap.InapDao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PembayaranDao {
    @Autowired DataSource dataSource;
    @Autowired InapDao oid;
    
    private String sqlSimpanPembayaran="INSERT INTO `pembayaran`(`idInap`, `totalBiaya`) VALUES (?,?)";
    private String sqlUpdatePembayaran="UPDATE `pembayaran` SET `sudahBayar`=?,`tglBayar`=?,`statusBayar`=? WHERE id=?";
    private String sqlTampilPembayaranBelumLunas="SELECT * FROM `pembayaran` WHERE statusBayar='0' ORDER BY id DESC";
    private String sqlTampilPembayaranLunas="SELECT * FROM `pembayaran` WHERE statusBayar='1' ORDER BY id DESC";
    private String sqlCariPembayaranById="SELECT * FROM `pembayaran` WHERE id=?";
    private String sqlCariPembayaranByIdInap="SELECT * FROM `pembayaran` WHERE idInap=?";
    
    public void tambahPembayaran(Pembayaran oPembayaran) throws SQLException{
        Connection c = dataSource.getConnection();
        if(oPembayaran.getId()==null){
            PreparedStatement psSimpanPembayaran = c.prepareStatement(sqlSimpanPembayaran);
            psSimpanPembayaran.setInt(1, oPembayaran.getoInap().getId());
            psSimpanPembayaran.setBigDecimal(2,oPembayaran.getTotalBiaya());
            
            psSimpanPembayaran.executeUpdate();
        }else{
            PreparedStatement psUpdatePembayaran = c.prepareStatement(sqlUpdatePembayaran);
            psUpdatePembayaran.setBigDecimal(1, oPembayaran.getSudahBayar());
            psUpdatePembayaran.setDate(2,new java.sql.Date(oPembayaran.getTglBayar().getTime()));
            psUpdatePembayaran.setString(3, oPembayaran.getStatusBayar());
            psUpdatePembayaran.setInt(4, oPembayaran.getId());
            
            psUpdatePembayaran.executeUpdate();
        }
        c.close();
    }
    
    public List<Pembayaran> tampilPembayaranLunas() throws SQLException, Exception{
        return prosesListPembayaran(sqlTampilPembayaranLunas);
    }
    
    public List<Pembayaran> tampilPembayaranBelumLunas() throws SQLException, Exception{
        return prosesListPembayaran(sqlTampilPembayaranBelumLunas);
    }
    
    private List<Pembayaran> prosesListPembayaran(String sqlPembayaran) throws SQLException, Exception{
        Connection c = dataSource.getConnection();
        PreparedStatement psTampilPembayaran = c.prepareStatement(sqlPembayaran);
        ResultSet rs = psTampilPembayaran.executeQuery();
        List<Pembayaran> lPembayaran  = new ArrayList<Pembayaran>();
        while(rs.next()){
            Pembayaran oPembayaran = rsToPembayaran(rs);
            lPembayaran.add(oPembayaran);
        }        
        c.close();
        return lPembayaran;
    }

    private Pembayaran rsToPembayaran(ResultSet rs) throws SQLException, Exception {
        Pembayaran oPembayaran = new Pembayaran();
        oPembayaran.setId(rs.getInt("id"));
        oPembayaran.setoInap(oid.cariInapById(rs.getInt("idInap")));
        oPembayaran.setTotalBiaya(rs.getBigDecimal("totalBiaya"));
        oPembayaran.setSudahBayar(rs.getBigDecimal("sudahBayar"));
        oPembayaran.setTglBayar(rs.getDate("tglBayar"));
        oPembayaran.setStatusBayar(rs.getString("statusBayar"));
        return oPembayaran;
    }
    
    public Pembayaran cariPembayaranByid(Integer id) throws SQLException, Exception{
        if(id==null){
            return null;
        }
        
        Connection c = dataSource.getConnection();
        PreparedStatement psCariPembayaranById = c.prepareStatement(sqlCariPembayaranById);
        psCariPembayaranById.setInt(1, id);
        ResultSet rs = psCariPembayaranById.executeQuery();
        if(!rs.next()){
            return null;
        }
        Pembayaran oPembayaran = rsToPembayaran(rs);
        c.close();
        return oPembayaran;
    }
    
    public Pembayaran cariPembayaranByIdInap(Integer idInap) throws SQLException, Exception{
        if(idInap==null){
            return null;
        }
        
        Connection c = dataSource.getConnection();
        PreparedStatement psCariPembayaranByIdInap = c.prepareStatement(sqlCariPembayaranByIdInap);
        psCariPembayaranByIdInap.setInt(1, idInap);
        ResultSet rs = psCariPembayaranByIdInap.executeQuery();
        if(!rs.next()){
            return null;
        }
        Pembayaran oPembayaran = rsToPembayaran(rs);
        c.close();
        return oPembayaran;
    }
}

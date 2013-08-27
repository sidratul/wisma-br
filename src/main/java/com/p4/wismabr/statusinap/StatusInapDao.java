package com.p4.wismabr.statusinap;

import com.p4.wismabr.inap.InapDao;
import com.p4.wismabr.kamar.KamarDao;
import java.math.BigDecimal;
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
public class StatusInapDao {
    @Autowired private DataSource dataSource;
    @Autowired private KamarDao okd;
    @Autowired private InapDao oid;
    
    private String sqlInsetStatusInap = "INSERT INTO `status_inap`(idInap,idKamar, jumOrang,biaya, waktuStatus) "
            + "VALUES (?,?,?,?,?)";
    private String sqlTampilStatusInapOrderDesc ="SELECT * FROM `status_inap` WHERE idInap=? ORDER BY id DESC";
    private String sqlTampilStatusInapById ="SELECT * FROM `status_inap` WHERE id=?";
    private String sqlUpdateStatusInap = "UPDATE status_inap SET idKamar=?,jumOrang=?,biaya=? WHERE id=?";
    private String sqlHapusStatusById = "DELETE FROM status_inap WHERE id=?";
    private String sqlCariByIdInapAndWaktuStatus = "SELECT * FROM status_inap WHERE idInap=?  AND waktuStatus=?";
    private String sqlCariTotalBiayaByIdInap = "SELECT sum(biaya) as total FROM `status_inap` WHERE idInap=?";
    
    public List<StatusInap> tampilStatusInap(Integer idInap) throws SQLException, Exception{
        if(idInap==null){
            return null;
        }
        
        List<StatusInap> lStatusInap = new ArrayList<StatusInap>();
        Connection c= dataSource.getConnection();
        PreparedStatement psTampilStatusInap = c.prepareStatement(sqlTampilStatusInapOrderDesc);
        psTampilStatusInap.setInt(1, idInap);
        ResultSet rs = psTampilStatusInap.executeQuery();
        while(rs.next()){
            StatusInap oStatusInap = rsToStatusInap(rs);
            lStatusInap.add(oStatusInap);
        }
        c.close();
        return lStatusInap;
    }
    
    public StatusInap tampilStatusInapById(Integer id) throws SQLException, Exception{
        if(id==null){
            return null;
        }
        
        Connection c = dataSource.getConnection();
        PreparedStatement psTampilByInapId = c.prepareStatement(sqlTampilStatusInapById);
        psTampilByInapId.setInt(1,id);
        ResultSet rs = psTampilByInapId.executeQuery();
        if(!rs.next()){
            return null;
        }
        StatusInap oStatusInap = rsToStatusInap(rs);
        c.close();
        return  oStatusInap;
    }

    private StatusInap rsToStatusInap(ResultSet rs) throws Exception, SQLException {
        StatusInap oStatusInap = new StatusInap();
        oStatusInap.setId(rs.getInt("id"));
        oStatusInap.setJumOrang(rs.getInt("jumOrang"));
        oStatusInap.setWaktuStatus(rs.getDate("waktuStatus"));
        oStatusInap.setBiaya(rs.getBigDecimal("biaya"));
        oStatusInap.setoInap(oid.cariInapById(rs.getInt("idInap")));
        oStatusInap.setoKamar(okd.cariKamarById(rs.getInt("idKamar")));
        return oStatusInap;
    }
    
    public void simpanStatusInap(StatusInap oStatusInap) throws SQLException{
        Connection c = dataSource.getConnection();
        if(oStatusInap.getId()== null){
            System.out.println("id null");
            
            PreparedStatement psInsertStatusInap = c.prepareStatement(sqlInsetStatusInap);
            psInsertStatusInap.setInt(1, oStatusInap.getoInap().getId());
            psInsertStatusInap.setInt(2, oStatusInap.getoKamar().getId());
            psInsertStatusInap.setInt(3, oStatusInap.getJumOrang());
            psInsertStatusInap.setBigDecimal(4, oStatusInap.getBiaya());
            psInsertStatusInap.setDate(5, new java.sql.Date(oStatusInap.getWaktuStatus().getTime()));
            
            psInsertStatusInap.executeUpdate();
        }else{            
            System.out.println("id tidak null");
            
            PreparedStatement psInsertStatusInap = c.prepareStatement(sqlUpdateStatusInap);
            psInsertStatusInap.setInt(1, oStatusInap.getoKamar().getId());
            psInsertStatusInap.setInt(2, oStatusInap.getJumOrang());
            psInsertStatusInap.setBigDecimal(3, oStatusInap.getBiaya());
            psInsertStatusInap.setInt(4, oStatusInap.getId());
            
            psInsertStatusInap.executeUpdate();
        }
        c.close();
    }
    
    public void hapusStatusInapById(Integer id) throws SQLException{
        if(id==null){
            return;
        }
        
        Connection c = dataSource.getConnection();
        PreparedStatement psHapusStatusById = c.prepareStatement(sqlHapusStatusById);
        psHapusStatusById.setInt(1, id);
        psHapusStatusById.executeUpdate();
        c.close();
    }
    
    public StatusInap cariByIdInapAndWaktuStatus(Integer idInap, Date tglSekarang) throws SQLException, Exception{
        Connection c = dataSource.getConnection();
        PreparedStatement psCariByIdInapAndWaktuStatus = c.prepareStatement(sqlCariByIdInapAndWaktuStatus);
        psCariByIdInapAndWaktuStatus.setInt(1, idInap);
        psCariByIdInapAndWaktuStatus.setDate(2, new java.sql.Date(tglSekarang.getTime()));
        
        ResultSet rs = psCariByIdInapAndWaktuStatus.executeQuery();
        if(!rs.next()){
            return null;
        }
        StatusInap oStatusInap = rsToStatusInap(rs);
        c.close();
        return oStatusInap;
    }
    
    public BigDecimal cariTotalBiayaByIdInap(Integer idInap) throws SQLException{
        Connection c = dataSource.getConnection();
        PreparedStatement psCariTotalBiayaByIdInap = c.prepareStatement(sqlCariTotalBiayaByIdInap);
        psCariTotalBiayaByIdInap.setInt(1, idInap);
        ResultSet rs = psCariTotalBiayaByIdInap.executeQuery();
        if(!rs.next()){
            return null;
        }
        BigDecimal total  = rs.getBigDecimal("total");
        c.close();
        return total;
    }
}
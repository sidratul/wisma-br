package com.p4.wismabr.inap;

import com.p4.wismabr.tamu.Tamu;
import com.p4.wismabr.tamu.TamuDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InapDao {
    @Autowired private DataSource dataSource;
    @Autowired private TamuDao otd;
    
    private String sqlTampilInap = "SELECT * FROM inap ORDER BY id DESC";
    private String sqlCariInapById = "SELECT * FROM inap where id=?";
    private String sqlCariInapByinapId = "SELECT * FROM inap where inapId=?";
    
//    private String sqlUpdateCheckin = "UPDATE `inap` SET `InapId`=?,`idTamu`=? WHERE id=?";
    private String sqlInsertCheckin = "INSERT INTO `inap`(`inapId`, `idTamu`, `waktuCheckin`) "
            + "VALUES (?,?,?)";
    private String sqlDeleteCheckin = "DELETE FROM `inap` WHERE id=?";
    private String sqlTampilChekcin = "SELECT * FROM `inap` WHERE waktuCheckout iS NULL ORDER BY id DESC";
    private String sqlCariInapId="SELECT inapId FROM inap Where waktuCheckin=? ORDER BY id DESC";
    
    private String sqlCheckout = "UPDATE `inap` SET `waktuCheckout`=? WHERE id=?";
    
    public List<Inap> tampilInap() throws SQLException, Exception{
        List<Inap> linap = listTampilInap(sqlTampilInap);
        return linap;
    }
    
    public List<Inap> tampilCheckin() throws Exception{
        List<Inap> linap = listTampilInap(sqlTampilChekcin);
        return linap;
    }
    
    public Inap cariInapById(Integer id) throws SQLException, Exception{
        if(id==null){
            return null;
        }
        Connection c = dataSource.getConnection();
        PreparedStatement psCarInapById = c.prepareStatement(sqlCariInapById);
        psCarInapById.setInt(1, id);
        ResultSet rs = psCarInapById.executeQuery();
        if(!rs.next()){            
            return null;
        }
        Inap oInap = resultSetInap(rs);
        c.close();
        return oInap;
    }
    
    public Inap cariInapByInapId(String inapId) throws SQLException, Exception{
        if(inapId==null){
            return null;
        }
        
        Connection c = dataSource.getConnection();
        PreparedStatement psCarInapById = c.prepareStatement(sqlCariInapByinapId);
        psCarInapById.setString(1,inapId);
        ResultSet rs = psCarInapById.executeQuery();
        if(!rs.next()){            
            return null;
        }        
        Inap oInap = resultSetInap(rs);        
        c.close();
        return oInap;
    }
    
    private List<Inap> listTampilInap(String sqlTampil) throws Exception, SQLException {                 
        List<Inap> linap = new ArrayList<Inap>();        
        Connection c = dataSource.getConnection();
        PreparedStatement psTampilInap =  c.prepareStatement(sqlTampil);
        ResultSet rs = psTampilInap.executeQuery();
        while(rs.next()){
            Inap oInap = resultSetInap(rs);
            linap.add(oInap);
        }                    
        c.close();
        return linap;        
    }

    private Inap resultSetInap(ResultSet rs) throws SQLException, Exception {
        Inap oInap  = new Inap();
        oInap.setId(rs.getInt("id"));
        oInap.setInapId(rs.getString("inapId"));
        oInap.setWaktuCheckin(rs.getDate("waktuCheckin"));
        oInap.setWaktuCheckout(rs.getDate("waktuCheckout"));
        oInap.setoTamu(otd.cariTamuById(rs.getInt("idTamu")));
        return oInap;
    }
    
    public Integer hapusCheckin(Integer id) throws SQLException{
        if(id==null){
            return null;
        }
        Connection c= dataSource.getConnection();
        PreparedStatement psDeleteCheckin = c.prepareStatement(sqlDeleteCheckin);
        psDeleteCheckin.setInt(1, id);
        Integer hasil = psDeleteCheckin.executeUpdate();
        c.close();
        return hasil;
    }
    
    public void simpanCheckin(Inap oInap) throws SQLException{
        Connection c = dataSource.getConnection();
        PreparedStatement psInsertCheckin =c.prepareStatement(sqlInsertCheckin);
        psInsertCheckin.setString(1, oInap.getInapId());
        psInsertCheckin.setInt(2, oInap.getoTamu().getId());
        psInsertCheckin.setDate(3,new java.sql.Date(oInap.getWaktuCheckin().getTime()));
        psInsertCheckin.executeUpdate();
        c.close();
    }
    
    public void checkout(Integer id,Date tglCheckout) throws SQLException{
        Connection c= dataSource.getConnection();
        PreparedStatement psCheckout = c.prepareStatement(sqlCheckout);
        psCheckout.setDate(1, new java.sql.Date(tglCheckout.getTime()));
        psCheckout.setInt(2, id);
        psCheckout.executeUpdate();
        c.close();
    }
    
    public String tampilInapId() throws Exception{
        Date tanggal = new Date();
        
        Connection c =dataSource.getConnection();
        PreparedStatement psCariInapId = c.prepareStatement(sqlCariInapId);
        psCariInapId.setDate(1,new java.sql.Date(tanggal.getTime()));
        ResultSet rs = psCariInapId.executeQuery();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String checkinId = "CK"+sdf.format(tanggal);
        if(rs.next()){            
            Integer angkaBelakang = Integer.parseInt(rs.getString("inapId").substring(10));
            angkaBelakang++;
            if(angkaBelakang < 10){
               checkinId+="0"+angkaBelakang;
            }else{
                checkinId+=angkaBelakang;
            }
        }else{
            checkinId+="01";
        }        
        c.close();
        return checkinId;
    }
}

package com.p4.wismabr.reservasi;

import com.p4.wismabr.kamar.Kamar;
import com.p4.wismabr.kamar.KamarDao;
import com.p4.wismabr.tamu.Tamu;
import com.p4.wismabr.tamu.TamuDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservasiDao {
    @Autowired private DataSource dataSource;    
    @Autowired private TamuDao otd;
    @Autowired private KamarDao okd;
    
    private String sqlInsert = "INSERT INTO reservasi(reservasiId, idTamu, idKamar, tglReservasi, ReservasiTgl, statusReservasi) VALUES"
                + "(?,?,?,?,?,?)";
//    private String sqlUpdate = "UPDATE reservasi SET reservasiId=?,idTamu=?,idKamar=?,tglReservasi=?,reservasiTgl=?,statusReservasi=?"
//            + "WHERE id=?";
    
    private String sqlDelete = "DELETE FROM reservasi WHERE id=?";
    private String sqlBatalReservasi = "UPDATE reservasi SET statusReservasi='0' WHERE id=?";
    private String sqlJadiCheckin = "UPDATE reservasi SET statusReservasi='2' WHERE id=?";
    private String sqlTampil = "SELECT * FROM reservasi WHERE statusReservasi='1' ORDER BY id DESC";
    private String sqlCariReservasiById="SELECT * FROM reservasi Where id=?";
    private String sqlCariReservasiId="SELECT reservasiId FROM reservasi Where tglReservasi=? ORDER BY id DESC";
    private String sqlCekReservasi="SELECT * FROM reservasi Where reservasiTgl=? AND idKamar = ? AND statusReservasi='1'";
    
    public void simpanReservasi(Reservasi oReservasi)throws Exception{
        Connection c=dataSource.getConnection();
        PreparedStatement psInsert=c.prepareStatement(sqlInsert);
        
        psInsert.setString(1, oReservasi.getReservasiId());
        psInsert.setInt(2, oReservasi.getoTamu().getId());
        psInsert.setInt(3, oReservasi.getoKamar().getId());
        psInsert.setDate(4, new java.sql.Date(oReservasi.getTglReservasi().getTime()));
        psInsert.setDate(5, new java.sql.Date(oReservasi.getReservasiTgl().getTime()));
        psInsert.setString(6, oReservasi.getStatusReservasi());
        
        psInsert.executeUpdate();
        c.close();
    }
    
    public List<Reservasi> tampilReservasi()throws Exception{
        Connection c = dataSource.getConnection();
        PreparedStatement psTampil=c.prepareStatement(sqlTampil);
        
        List<Reservasi> hasilReservasi = new ArrayList<Reservasi>();
        ResultSet rs= psTampil.executeQuery();
        while(rs.next()){
            Reservasi oReservasi = resultSetReservasi(rs);
            hasilReservasi.add(oReservasi);
        }
        c.close();
        return hasilReservasi;
    }

    private Reservasi resultSetReservasi(ResultSet rs) throws SQLException, Exception {
        Reservasi oReservasi= new Reservasi();
        oReservasi.setId(rs.getInt("id"));
        oReservasi.setReservasiId(rs.getString("reservasiId"));
        oReservasi.setStatusReservasi(rs.getString("statusReservasi"));
        oReservasi.setTglReservasi(rs.getDate("tglReservasi"));
        oReservasi.setReservasiTgl(rs.getDate("reservasiTgl"));
        oReservasi.setoTamu(otd.cariTamuById(rs.getInt("idTamu")));
        oReservasi.setoKamar(okd.cariKamarById(rs.getInt("idKamar")));
        return oReservasi;
    }
    
    public Reservasi cariReservasiById(Integer id) throws SQLException, Exception{
        if(id == null){
            return null;
        }
        
        Connection c = dataSource.getConnection();
        PreparedStatement psCariReservasiById = c.prepareStatement(sqlCariReservasiById);
        psCariReservasiById.setInt(1, id);
        ResultSet rs = psCariReservasiById.executeQuery();
        if(!rs.next()){
            return null;
        }
        Reservasi oReservasi = resultSetReservasi(rs);
        c.close();
        return oReservasi;
    }
    
    public String tampilReservasiId() throws Exception{
        Connection c =dataSource.getConnection();
        PreparedStatement psCariReservasiId = c.prepareStatement(sqlCariReservasiId);
        psCariReservasiId.setDate(1, new java.sql.Date(new Date().getTime()));
        ResultSet rs = psCariReservasiId.executeQuery();
        
        Date tanggal = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String reservasiId = "RS"+sdf.format(tanggal);
        if(rs.next()){            
            Integer angkaBelakang = Integer.parseInt(rs.getString("reservasiId").substring(10));
            angkaBelakang++;
            if(angkaBelakang < 10){
               reservasiId+="0"+angkaBelakang;
            }else{
                reservasiId+=angkaBelakang;
            }
        }else{
            reservasiId+="01";
        }     
        c.close();
        return reservasiId;
    }
    
    public void hapusReservasi(Integer id) throws SQLException{
        if(id==null){
            return;
        }
        Connection c = dataSource.getConnection();
        PreparedStatement psDelete = c.prepareStatement(sqlDelete);
        psDelete.setInt(1, id);
        psDelete.executeUpdate();
        
        c.close();
    } 
    
    public void batalReservasi(Integer id) throws SQLException{
        if(id==null){
            return;
        }
        Connection c = dataSource.getConnection();
        PreparedStatement psBatal = c.prepareStatement(sqlBatalReservasi);
        psBatal.setInt(1, id);
        psBatal.executeUpdate();
        
        c.close();
    } 
    
    public void jadiCheckin(Integer id) throws SQLException{
        if(id==null){
            return;
        }
        Connection c = dataSource.getConnection();
        PreparedStatement psJadiCheckin = c.prepareStatement(sqlJadiCheckin);
        psJadiCheckin.setInt(1, id);
        psJadiCheckin.executeUpdate();
        
        c.close();
    }
    
    public Boolean cekReservasi(Reservasi oReservasi) throws SQLException{
        Connection c = dataSource.getConnection();
        PreparedStatement psCekReservasi = c.prepareStatement(sqlCekReservasi);
        psCekReservasi.setDate(1,new java.sql.Date(oReservasi.getReservasiTgl().getTime()));
        psCekReservasi.setInt(2,oReservasi.getoKamar().getId());
        ResultSet rs = psCekReservasi.executeQuery();
        if(!rs.next()){
            return true;
        }
        c.close();
        return false;
    }
}

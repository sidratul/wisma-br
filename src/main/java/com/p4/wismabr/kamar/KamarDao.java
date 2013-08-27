package com.p4.wismabr.kamar;

import com.p4.wismabr.tamu.Tamu;
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
public class KamarDao {
    @Autowired private DataSource dataSource;    
    private String sqlUpdateInTerisi = "UPDATE kamar SET statIn='1' WHERE id=?";
    private String sqlUpdateInKosong = "UPDATE kamar SET statIn='0' WHERE id=?";
    private String sqlUpdateResTerpesan = "UPDATE kamar SET statReservasi='1' WHERE id=?";    
    private String sqlUpdateResKosong = "UPDATE kamar SET statReservasi='0' WHERE id=?";    
    private String sqlTampilIn = "SELECT * FROM kamar Where statIn='0'";
    private String sqlTampil = "SELECT * FROM kamar";
    private String sqlCariKamarById = "SELECT * FROM kamar where id=?";
    
    public List<Kamar> tampilKamar()throws Exception{
        List<Kamar> hasilKamar = new ArrayList<Kamar>();
        prosesWithSql(hasilKamar,sqlTampil);
        return hasilKamar;
    }
    
    public List<Kamar> tampilKamarIn()throws Exception{
        List<Kamar> hasilKamar = new ArrayList<Kamar>();
        prosesWithSql(hasilKamar,sqlTampilIn);
        return hasilKamar;
    }

    private void prosesWithSql(List<Kamar> hasilKamar,String sql) throws SQLException {
        Connection c=dataSource.getConnection();
        PreparedStatement psTampil=c.prepareStatement(sql);
        ResultSet rs = psTampil.executeQuery();
        while(rs.next()){
            Kamar oKamar = resultSetKamar(rs);
            hasilKamar.add(oKamar);
        }
        c.close();
    }
    
    private Kamar resultSetKamar(ResultSet rs) throws SQLException {
        Kamar oKamar = new Kamar();
        oKamar.setId(rs.getInt("id"));
        oKamar.setNoKamar(rs.getString("noKamar"));
        oKamar.setAcTv(rs.getString("acTv"));
        oKamar.setMaxOrang(rs.getBigDecimal("maxOrang"));
        oKamar.setStatIn(rs.getString("statIn"));
        oKamar.setStatReservasi(rs.getString("statReservasi"));
        oKamar.setKelasKamar(rs.getString("kelasKamar"));
        return oKamar;
    }
    
    public Kamar cariKamarById(Integer id)throws Exception{
        if(id==null){
            return null;
        }
        Connection c=dataSource.getConnection();
        PreparedStatement psCariKamarById = c.prepareStatement(sqlCariKamarById);
        psCariKamarById.setInt(1, id);
        ResultSet rs= psCariKamarById.executeQuery();
        if(!rs.next()){
            return null;
        }
        Kamar oKamar= resultSetKamar(rs);
        c.close();
        return oKamar;
    }
    
    public void updateStatInTerisi(Integer id) throws Exception{
        if(id==null){
            return;
        }
        updateStatIn(id,sqlUpdateInTerisi);
    }
    
    public void updateStatInKosong(Integer id) throws Exception{
        if(id==null){
            return;
        }
        updateStatIn(id,sqlUpdateInKosong);
    }
    
    private void updateStatIn(Integer id,String sqlUpdateIn)throws Exception{        
        Connection c=dataSource.getConnection();
        PreparedStatement psUpdateIn = c.prepareStatement(sqlUpdateIn);
        psUpdateIn.setInt(1,id);        
        psUpdateIn.executeUpdate();
        c.close();
    }
    
    public void updateStatResTerpesan(Integer id) throws Exception{
        if(id==null){
            return;
        }
        updateStatRes(id,sqlUpdateResTerpesan);
    }
    
    public void updateStatResKosong(Integer id) throws Exception{
        if(id==null){
            return;
        }
        updateStatRes(id,sqlUpdateResKosong);
    }
    
    private void updateStatRes(Integer id,String sqlUpdateRes)throws Exception{        
        Connection c=dataSource.getConnection();
        PreparedStatement psUpdateRes = c.prepareStatement(sqlUpdateRes);
        psUpdateRes.setInt(1,id);        
        psUpdateRes.executeUpdate();
        c.close();
    }
}

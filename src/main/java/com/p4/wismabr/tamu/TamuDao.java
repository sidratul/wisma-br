package com.p4.wismabr.tamu;

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
public class TamuDao {
    @Autowired private DataSource dataSource;
    
    private String sqlInsert = "INSERT INTO tamu(noId, jenisId, nama, jenisKelamin, kota, alamat, telpon) VALUES "
                + "(?,?,?,?,?,?,?)";
    private String sqlUpdate = "UPDATE tamu SET noId=?,jenisId=?,nama=?,jenisKelamin=?,kota=?,alamat=?,telpon=? "
            + "WHERE id=?";
    private String sqlDelete = "DELETE FROM tamu WHERE id=?";
    private String sqlTampil = "SELECT * FROM tamu ORDER BY id DESC";
    private String sqlCariById = "SELECT * FROM tamu where id=?";
    private String sqlGroupKota = "SELECT kota FROM tamu GROUP BY kota";

    public void simpanTamu(Tamu oTamu) throws Exception {
        Connection c= dataSource.getConnection();
        if(oTamu.getId()==null){
            PreparedStatement psInsert = c.prepareStatement(sqlInsert);

            psInsert.setString(1, oTamu.getNoId());
            psInsert.setString(2, oTamu.getJenisId());
            psInsert.setString(3, oTamu.getNama().toLowerCase());
            psInsert.setString(4, oTamu.getJenisKelamin());
            psInsert.setString(5, oTamu.getKota().toLowerCase());
            psInsert.setString(6, oTamu.getAlamat().toLowerCase());
            psInsert.setString(7, oTamu.getTelpon());

            psInsert.executeUpdate();
        }else{
            PreparedStatement psUpdate = c.prepareStatement(sqlUpdate);

            psUpdate.setString(1, oTamu.getNoId());
            psUpdate.setString(2, oTamu.getJenisId());
            psUpdate.setString(3, oTamu.getNama());
            psUpdate.setString(4, oTamu.getJenisKelamin());
            psUpdate.setString(5, oTamu.getKota());
            psUpdate.setString(6, oTamu.getAlamat());
            psUpdate.setString(7, oTamu.getTelpon());
            psUpdate.setInt(8, oTamu.getId());
            
            psUpdate.executeUpdate();

        }
        c.close();
    }

    public List<Tamu> tampilTamu() throws Exception {
        Connection c = dataSource.getConnection();
        PreparedStatement psTampil = c.prepareStatement(sqlTampil);
        List<Tamu> hasilTamu = new ArrayList<Tamu>();

        ResultSet rs = psTampil.executeQuery();
        while (rs.next()) {
            Tamu oTamu = resultSetTamu(rs);
            hasilTamu.add(oTamu);
        }
        c.close();
        return hasilTamu;
    }

    public Tamu cariTamuById(Integer id)throws Exception{
        if(id==null){
            return null;
        }
        Connection c=dataSource.getConnection();
        PreparedStatement psCariById = c.prepareStatement(sqlCariById);
        psCariById.setInt(1, id);
        ResultSet rs= psCariById.executeQuery();
        if(!rs.next()){
            return null;
        }
        Tamu oTamu= resultSetTamu(rs);
        c.close();
        return oTamu;
    }
    
    private Tamu resultSetTamu(ResultSet rs) throws SQLException {
        Tamu oTamu = new Tamu();
        oTamu.setId(rs.getInt("id"));
        oTamu.setNoId(rs.getString("noId"));
        oTamu.setJenisId(rs.getString("jenisId"));
        oTamu.setNama(rs.getString("nama"));
        oTamu.setJenisKelamin(rs.getString("jenisKelamin"));
        oTamu.setKota(rs.getString("kota"));
        oTamu.setAlamat(rs.getString("alamat"));
        oTamu.setTelpon(rs.getString("telpon"));
        return oTamu;
    }
    
    public void hapus(Integer id)throws Exception{
        if(id==null){
            return;
        }
        Connection c = dataSource.getConnection();
        PreparedStatement psDelele= c.prepareStatement(sqlDelete);
        psDelele.setInt(1, id);
        Integer hasil = psDelele.executeUpdate();
        c.close();        
    }
    
    public List cariGroupKota()throws Exception{
        List lkota = new ArrayList();
        Connection c = dataSource.getConnection();
        PreparedStatement psGroupKota= c.prepareStatement(sqlGroupKota);
        ResultSet rs = psGroupKota.executeQuery();
        while(rs.next()){
            lkota.add(rs.getString("kota"));
        }
        c.close();
        return lkota;
    }
}

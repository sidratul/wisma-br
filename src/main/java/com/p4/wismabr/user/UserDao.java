package com.p4.wismabr.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired private DataSource dataSource;
    private String sqlInsert="INSERT INTO user(username, password, nama, kota, alamat, telpon) VALUES"
                + "?,?,?,?,?,?)";
    private String sqlUpdate="UPDATE user SET username=?,password=?,nama=?,kota=?,alamat=?,telpon=? "
            + "WHERE id=?";
    private String sqlDelete="DELETE FROM user WHERE id=?";
    private String sqlTampil="SELECT * FROM user ";
    
    public void setDataSourceUser(DataSource dataSource)throws Exception{
        this.dataSource = dataSource;
    }
    
    public void simpanUser(User oUser)throws Exception{
        Connection c=dataSource.getConnection();
        PreparedStatement psInsert = c.prepareStatement(sqlInsert);
        
        psInsert.setString(1,oUser.getUsername());
        psInsert.setString(2,oUser.getPassword());
        psInsert.setString(3,oUser.getNama());
        psInsert.setString(4,oUser.getKota());
        psInsert.setString(5,oUser.getAlamat());
        psInsert.setString(6,oUser.getTelpon());
        
        psInsert.executeUpdate();
        c.close();
    }
    
    public List<User> tampilUser() throws Exception{
        Connection c=dataSource.getConnection();
        PreparedStatement psTampil = c.prepareStatement(sqlTampil);
        
        List<User> hasilUser = new ArrayList<User>();
        ResultSet rs = psTampil.executeQuery();
        while(rs.next()){
            User oUser = new User();
            oUser.setId(rs.getInt("id"));
            oUser.setUsername(rs.getString("username"));
            oUser.setPassword(rs.getString("password"));
            oUser.setNama(rs.getString("nama"));
            oUser.setKota(rs.getString("kota"));
            oUser.setAlamat(rs.getString("Alamat"));
            oUser.setTelpon(rs.getString("telpon"));
            
            hasilUser.add(oUser);
        }
        c.close();
        return hasilUser;
    }
    
}

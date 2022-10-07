/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;
import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author hp
 */
public class UserDAO {
       private static PreparedStatement ps,ps1,ps2,ps3,ps4;
    static{
        try{
            ps=DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
            ps1=DBConnection.getConnection().prepareStatement("select * from user_details where user_type='Voter'");
            ps2=DBConnection.getConnection().prepareStatement("select adhar_no from user_details where user_type='Voter'");
            ps3=DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
            ps4=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static String validateUser(UserDTO user) throws SQLException{
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return rs.getString(1);   //this returns user type
        }
        return null;
    }
    
    public static ArrayList<UserDetails> getAllUser() throws SQLException{
    	ArrayList<UserDetails> userList = new ArrayList<>();
    	
    	ResultSet rs=ps1.executeQuery();
    	while(rs.next()) {
    		UserDetails user=new UserDetails();
    		user.setUserid(rs.getString(1));
    		user.setPassword(rs.getString(2));
    		user.setUsername(rs.getString(3));
    		user.setAddress(rs.getString(4));
    		user.setCity(rs.getString(5));
    		user.setEmail(rs.getString(6));
    		user.setMobile(rs.getString(7));
    		userList.add(user);
    	}
    	return userList;
    }
    public static ArrayList<String> getAllUserId() throws SQLException{
    	ArrayList<String> uid=new ArrayList<>();
    	ResultSet rs=ps2.executeQuery();
    	while(rs.next()) {
    		String id=rs.getString(1);
    		uid.add(id);
    	}
    	return uid;
    }
    public static boolean deleteUser(String uid) throws SQLException{
    	ps3.setString(1, uid);
    	return ps3.executeUpdate()!=0;
    }
    public static UserDetails getUserById(String uid) throws SQLException{
    	ps4.setString(1, uid);
    	ResultSet rs=ps4.executeQuery();
    	UserDetails user=new UserDetails();
    	if(rs.next()) {
    		user.setUserid(rs.getString(1));
    		user.setPassword(rs.getString(2));
    		user.setUsername(rs.getString(3));
    		user.setAddress(rs.getString(4));
    		user.setCity(rs.getString(5));
    		user.setEmail(rs.getString(6));
    		user.setMobile(rs.getString(7));
    	}
    	return user;
    } 
}

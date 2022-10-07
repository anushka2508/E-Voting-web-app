/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hp
 */

//read the pdf  of dao and its method 
public class RegistrationDAO {
    //it is static bcoz we dont want ki every user use kare websteit to ye connection baar bar bane 
     private static PreparedStatement ps,ps1;
    //static block me islie dala hai taki sirf ek baar hi ye objects create ho (first time only)
     //yaha par ps ek hi bana hai to usse threads mekoi dikkat ni ayegi kyuki jo query hai vo static h multiple threads usko manioulate nhi kar rhe h islie humko locking bhi nhi karni padegi 
     static{
        try{
            ps=DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
            //ps1 is for inserting the user (register karne k liye)
            ps1=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?,?)");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    //dao me we never do exceptional handling therefore we will throw (using throws ) exception to servlet
    public static boolean searchUser(String userid) throws SQLException{
        ps.setString(1, userid);
        //agar record mila to next() true dekha warna false 
        return ps.executeQuery().next();
    }
    //registerUser will take the pojo as the argument (jo servlet bejhegi)
    public static boolean registerUser(UserDetails user) throws SQLException{
        System.out.print("helllo");
        //db ki table column ke order me hi insert karna padega 
        ps1.setString(1,user.getUserid()); //userid hi adhar_no hai 
        ps1.setString(2, user.getPassword());
        ps1.setString(3, user.getUsername());
        ps1.setString(4, user.getAddress());
        ps1.setString(5, user.getCity());
        ps1.setString(6, user.getEmail());
        ps1.setString(7, user.getMobile());
        ps1.setString(8, "Voter"); //we are directly passing voter bcoz we know we are registring voters
        ps1.setString(9, user.getGender());
        System.out.println("All the data are set");
        return (ps1.executeUpdate()==1); //executeupdate gives you the no of records inserted in table
    }
}

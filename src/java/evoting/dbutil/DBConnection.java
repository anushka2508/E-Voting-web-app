/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DBConnection {
    private static Connection conn=null;
    //this block will run when the first user will register 
    //connection sirf ek baar hi banega islie vo static me likhenge
    static
    {
    try{
      Class.forName("oracle.jdbc.OracleDriver"); //forName provide karane k liye we have to add odbc6 jar file in class path otherwise it will give class not found exception 
      System.out.println("class loaded");
//now to open connecton call the static mathod getconnection
      //which returns object of some implement class of connection interface
      conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "Kanti*2508");
      System.out.println("Driver loaded and conn opened");
      }
    
    catch(ClassNotFoundException cnf)
    {
        cnf.printStackTrace();
    }
    catch(SQLException sqlex)
    {
        sqlex.printStackTrace();
    }
    

    }
     public static Connection getConnection(){
        return conn;
    }
//ye method call hoga jo method poori application ke shut hone pr sirf ek baar call hota h servletcontext listner ka context destroyed but hum maan ke chal rhe h close nhi hoga but for the safer side we are making it 
    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
                System.out.println("Connection Close Successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
    

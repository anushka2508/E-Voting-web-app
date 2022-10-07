/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;
import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;


/**
 *
 * @author hp
 */
public class CandidateDAO {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7,ps8;
    private static Statement st;
    static{
        try{
        	st=DBConnection.getConnection().createStatement();
            ps=DBConnection.getConnection().prepareStatement("select max(candidate_id) from candidate");
            ps1=DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
            ps2=DBConnection.getConnection().prepareStatement("select distinct city from user_details");
            ps3=DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)"); 
            ps4=DBConnection.getConnection().prepareStatement("Select * from candidate where candidate_id=?");
            ps5=DBConnection.getConnection().prepareStatement("select candidate_id, username, party, symbol from candidate cd, user_details ud where cd.user_id=ud.adhar_no and cd.city=(select city from user_details where adhar_no=?)");
            ps6=DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
            ps7=DBConnection.getConnection().prepareStatement("update candidate set party=?, symbol=?,city=? where candidate_id=?");
            ps8=DBConnection.getConnection().prepareStatement("select symbol from candidate where candidate_id=?");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public static String getNewId() throws SQLException{
        ResultSet rs=ps.executeQuery();
        rs.next();
        String cid=rs.getString(1);
        if(cid==null)
            return "C101";
        int id=Integer.parseInt(cid.substring(1));
        return "C"+(id+1);
    }
    public static String getUserNameById(String uid) throws SQLException{
        ps1.setString(1, uid);
        ResultSet rs=ps1.executeQuery();
        if(rs.next()){
            return rs.getString(1);
        }else{
            return null;  //is id ka koi record nhi h 
        }
    }
    public static ArrayList<String> getCity() throws SQLException{
        ArrayList <String> cityList = new ArrayList<>();
        ResultSet rs=ps2.executeQuery();
        while(rs.next()){
            cityList.add(rs.getString(1));
        }
        return cityList;
    }
    public static boolean addCandidate(CandidateDTO obj) throws SQLException{
        ps3.setString(1, obj.getCandidateId());
        ps3.setString(2, obj.getParty());
        ps3.setString(3, obj.getUserid());
        ps3.setBinaryStream(4, obj.getSymbol());
        ps3.setString(5, obj.getCity());
        return ps3.executeUpdate()!=0;
    }
    public static ArrayList<String> getCandidateId() throws SQLException{
    	ArrayList<String> candidateIdList = new ArrayList<>();
    	ResultSet rs=st.executeQuery("Select candidate_id from candidate");
    	while(rs.next()) {
    		candidateIdList.add(rs.getString(1));
    	}
    	return candidateIdList;
    }
    public static CandidateDetails getDetailsById(String cid) throws SQLException, IOException{
    	ps4.setString(1, cid);
    	ResultSet rs=ps4.executeQuery();
    	CandidateDetails cd=new CandidateDetails();
    	Blob blob;
    	InputStream inputStream;
    	byte[] buffer;
    	byte[] imageBytes;
    	int bytesRead;
    	String base64Image;
    	ByteArrayOutputStream outputStream;
    	if(rs.next()) {
    		blob=rs.getBlob(4);
    		inputStream = blob.getBinaryStream();
    		outputStream = new ByteArrayOutputStream();
    		buffer=new byte[4096];
    		bytesRead=-1;
    		while((bytesRead=inputStream.read(buffer))!=-1) {
    			outputStream.write(buffer, 0, bytesRead);
    		}
    		imageBytes=outputStream.toByteArray();
    		Base64.Encoder en =Base64.getEncoder();
    		base64Image = en.encodeToString(imageBytes);
    		cd.setSymbol(base64Image);
    		cd.setCandidateId(rs.getString(1));
    		cd.setCandidateName(getUserNameById(rs.getString(3)));
    		cd.setCity(rs.getString(5));
    		cd.setParty(rs.getString(2));
    		cd.setUserId(rs.getString(3));
    	}
    	return cd;
    }
    public static ArrayList<CandidateInfo> viewCandidate(String adhar_id) throws SQLException, IOException {
    	ArrayList<CandidateInfo> candidateList = new  ArrayList<CandidateInfo>();
    	ps5.setString(1, adhar_id);
    	ResultSet rs=ps5.executeQuery();
    	Blob blob;
    	InputStream inputStream;
    	byte[] buffer;
    	byte[] imageBytes;
    	int bytesRead;
    	String base64Image;
    	ByteArrayOutputStream outputStream;
    	while(rs.next()) {
    		CandidateInfo cd=new CandidateInfo();
    		blob=rs.getBlob(4);
    		inputStream = blob.getBinaryStream();
    		outputStream = new ByteArrayOutputStream();
    		buffer=new byte[4096];
    		bytesRead=-1;
    		while((bytesRead=inputStream.read(buffer))!=-1) {
    			outputStream.write(buffer, 0, bytesRead);
    		}
    		imageBytes=outputStream.toByteArray();
    		Base64.Encoder en =Base64.getEncoder();
    		base64Image = en.encodeToString(imageBytes);
    		cd.setSymbol(base64Image);
    		cd.setCandidateId(rs.getString(1));
    		cd.setCandidateName(rs.getString(2));
    		cd.setParty(rs.getString(3));
    		candidateList.add(cd);
    	}
    	return candidateList;
    }
    public static boolean removeCandidate(String cid) throws SQLException{
    	ps6.setString(1, cid);
    	return ps6.executeUpdate()!=0;
    }
    public static boolean updateCandidate(CandidateDTO cd) throws SQLException{
    	
    	ps7.setString(1, cd.getParty());
    	ps7.setBinaryStream(2, cd.getSymbol());
    	ps7.setString(3, cd.getCity());
    	ps7.setString(4, cd.getCandidateId());
    	return ps7.executeUpdate()!=0;
    }
    public static InputStream getSymbolById(String cid) throws Exception{
    	ps8.setString(1, cid);
    	ResultSet rs=ps8.executeQuery();
    	rs.next();
    	Blob blob;
    	blob=rs.getBlob(1);
    	return blob.getBinaryStream();
    }
}

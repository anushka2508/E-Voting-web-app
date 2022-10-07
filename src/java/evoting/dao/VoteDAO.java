/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;
import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfo;
import evoting.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author hp
 */
public class VoteDAO {
    private static Statement st;
	private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6;
	static {
		try {
			ps1=DBConnection.getConnection().prepareStatement("select candidate_Id from Voting where voter_id=?");
			ps2=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from candidate,user_details where candidate.user_id=user_details.adhar_no and candidate.candidate_id=?");
			ps3=DBConnection.getConnection().prepareStatement("insert into voting values (?,?)");
			ps4=DBConnection.getConnection().prepareStatement("select candidate_id, count(*) as votes_obt from voting group by candidate_id order by votes_obt desc");
			ps5=DBConnection.getConnection().prepareStatement("select candidate.party, count(*) as vote_obt from candidate inner join voting on candidate.candidate_id=voting.candidate_id group by candidate.party order by vote_obt desc");
			ps6=DBConnection.getConnection().prepareStatement("select user_details.gender,count(*) as vote_obt from user_details inner join voting on user_details.adhar_no=voting.votor_id group by user_details.gender order by vote_obt desc");
			st=DBConnection.getConnection().createStatement();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static String getCandidateId(String userid) throws SQLException {
		ps1.setString(1, userid);
		ResultSet rs=ps1.executeQuery();
		if(rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	public static CandidateInfo getVote(String candidateId) throws SQLException, IOException {
		ps2.setString(1, candidateId);
    	ResultSet rs=ps2.executeQuery();
    	CandidateInfo cd=new CandidateInfo();
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
    		cd.setCandidateName(rs.getString(2));
    		cd.setParty(rs.getString(3));
    	}
    	return cd;
	}
	public static boolean addVote(VoteDTO obj) throws SQLException {
		ps3.setString(1, obj.getCandidateId());
		ps3.setString(2, obj.getVoterId());
		return ps3.executeUpdate()!=0;
	}
	
	public static Map<String,Integer> getResult() throws SQLException {
		Map<String, Integer> result=new LinkedHashMap<>(); 
		ResultSet rs=ps4.executeQuery();
		while(rs.next()) {
			result.put(rs.getString(1), rs.getInt(2));
		}
		return result;
	}
	public static int getVoteCount() throws SQLException {
		ResultSet rs=st.executeQuery("select count(*) from voting");
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	public static Map<String,Integer> getResultByParty() throws SQLException {
		Map<String,Integer> result=new LinkedHashMap<>();
		ResultSet rs=ps5.executeQuery();
		while(rs.next()) {
			result.put(rs.getString(1), rs.getInt(2));
		}
		return result;
	}
	public static Map<String,Integer> getResultByGender() throws SQLException {
		Map<String,Integer> result=new LinkedHashMap<>();
		ResultSet rs=ps6.executeQuery();
		while(rs.next()) {
			result.put(rs.getString(1), rs.getInt(2));
		}
		return result;
	}
}

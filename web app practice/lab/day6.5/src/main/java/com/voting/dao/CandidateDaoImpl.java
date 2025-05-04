package com.voting.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.voting.pojos.Candidate;
import static com.voting.utils.DBUtils.getConnection;

public class CandidateDaoImpl implements CandidateDao {
	//state
	private Connection connection;
	private PreparedStatement pst1,pst2;
	
	//def ctor
	public CandidateDaoImpl() throws SQLException{
		//get db cn
		connection=getConnection();
		//pst1 - to get all candidates
		pst1=connection.prepareStatement("select * from candidates");
		//pst2 - update votes
		pst2=connection.prepareStatement("update candidates set votes=votes+1 where id=?");
		System.out.println("candidate dao created !");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> candidates=new ArrayList<>();
		try(ResultSet rst=pst1.executeQuery())
		{
			/*
			 * int candidateId, String candidateName, 
			 * String partyName, int votes) {
			 */
			while (rst.next()) {
				candidates.add(new Candidate(rst.getInt(1), 
						rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		}
		return candidates;
	}
	

	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		// set IN param - candidateId
		pst2.setInt(1, candidateId);
		//exec update
		int updateCount=pst2.executeUpdate();
		if(updateCount == 1)
			return "Votes updated !";
		return "Updating votes failed!!!!!!!!";
	}

	@Override
	public void cleanUp() throws SQLException {
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		System.out.println("candiate dao cleaned up !");

	}

}

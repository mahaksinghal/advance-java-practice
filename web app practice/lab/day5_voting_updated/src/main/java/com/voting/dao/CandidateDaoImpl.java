package com.voting.dao;

import static com.voting.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.voting.pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	private Connection connection;
	private PreparedStatement pst1;
	
	public CandidateDaoImpl() throws SQLException{
		connection = getConnection();
		
		pst1 = connection.prepareStatement("select * from candidates");
		System.out.println("candidate dao created");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		List<Candidate> candidates = new ArrayList<>();
		try(ResultSet rst = pst1.executeQuery()){
			while(rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), 
						rst.getString(3), rst.getInt(4)));
		}		
		return candidates;
	}

	@Override
	public void cleanUp() throws SQLException {
		if(pst1!=null)
			pst1.close();
		System.out.println("candidates dao cleaned up!");
	}

}

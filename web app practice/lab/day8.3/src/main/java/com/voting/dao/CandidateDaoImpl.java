package com.voting.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.voting.utils.DBUtils.*;

import com.voting.pojos.Candidate;

public class CandidateDaoImpl implements CandidateDao {
	// state
	private Connection cn;
	private PreparedStatement pst1, pst2,pst3;

	// def ctor
	public CandidateDaoImpl() throws SQLException {
		// get cn
		cn = getConnection();
		// pst1 - list of candidates
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		pst3=cn.prepareStatement("select * from candidates order by votes desc limit 2");
		System.out.println("candidate dao created !");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {
		// create empty list
		List<Candidate> list = new ArrayList<>();
		// exec Query
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				list.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return list;
	}

	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		// set IN param : candidate id
		pst2.setInt(1, candidateId);

		int rowCount = pst2.executeUpdate();
		if (rowCount == 1)
			return "Votes incremented!";
		return "Incrementing votes  failed !!!!!";
	}
	

	@Override
	public List<Candidate> getTop2Candidates() throws SQLException {
		List<Candidate> candidates=new ArrayList<>();
		//exec query
		try(ResultSet rst=pst3.executeQuery()) {
			while(rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		return candidates;
	}

	@Override
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		System.out.println("candiate dao cleaned up!");
	}

}

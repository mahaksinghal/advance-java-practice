package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.pojos.Candidate;

public interface CandidateDao {
//add a method to get list of all candidates
	List<Candidate> getAllCandidates() throws SQLException;
	//clean up
	void cleanUp() throws SQLException;
	//add a method to updated candidate votes
	String incrementVotes(int candidateId) throws SQLException;
	//add a method to list top 2 candidates
	List<Candidate> getTop2Candidates() throws SQLException;
}

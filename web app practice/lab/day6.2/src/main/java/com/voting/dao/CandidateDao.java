package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.pojos.Candidate;

public interface CandidateDao {
//add a method to get all candidates
	List<Candidate> getAllCandidates() throws SQLException;
	//add a method to increment selected candidate votes
	String incrementVotes(int candidateId) throws SQLException;
	//add a method for clean up
	void cleanUp() throws SQLException;
}

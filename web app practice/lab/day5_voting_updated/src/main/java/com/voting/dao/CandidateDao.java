package com.voting.dao;

import java.sql.SQLException;
import java.util.List;

import com.voting.pojos.Candidate;

public interface CandidateDao {
	// add a method to get all candidates
	List<Candidate> getAllCandidates() throws SQLException;
	
	// add a method to clean the resources
	void cleanUp() throws SQLException;
}

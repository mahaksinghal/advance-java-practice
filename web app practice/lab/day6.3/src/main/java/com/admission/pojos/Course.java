package com.admission.pojos;

public enum Course {
	PG_DAC(80), PG_DBDA(65), PG_DAI(90);

	private int minScore;

	private Course(int minScore) {
		this.minScore = minScore;
	}

	public int getMinScore() {
		return minScore;
	}
	
	

}

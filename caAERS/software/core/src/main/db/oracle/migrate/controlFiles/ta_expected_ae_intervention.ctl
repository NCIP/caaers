OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ta_expected_ae_intervention.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ta_expected_ae_intervention
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TA_EXPECTED_ID 				    INTEGER EXTERNAL(10),
		TA_AGENT_ID					    INTEGER EXTERNAL(10)
	)


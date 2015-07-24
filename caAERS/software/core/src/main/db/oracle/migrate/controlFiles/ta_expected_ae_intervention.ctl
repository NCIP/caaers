
LOAD DATA
	INFILE 'ta_expected_ae_intervention.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ta_expected_ae_intervention
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		TA_EXPECTED_ID 				    INTEGER EXTERNAL(10),
		TA_AGENT_ID					    INTEGER EXTERNAL(10)
	)


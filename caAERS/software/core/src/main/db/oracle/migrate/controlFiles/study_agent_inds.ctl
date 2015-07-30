
LOAD DATA
	INFILE 'study_agent_inds.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_agent_inds
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_AGENT_ID 			    INTEGER EXTERNAL(10),
		IND_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)


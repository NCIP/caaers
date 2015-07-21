OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ta_agents.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ta_agents
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_AGENT_ID 				    INTEGER EXTERNAL(10)
	)


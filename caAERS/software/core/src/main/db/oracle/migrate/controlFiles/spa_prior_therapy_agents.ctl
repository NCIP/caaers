
LOAD DATA
	INFILE 'spa_prior_therapy_agents.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE spa_prior_therapy_agents
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		SPA_PRIOR_THERAPY_ID			    INTEGER EXTERNAL(10),
		CHEMO_AGENT_ID 				    INTEGER EXTERNAL(10),
		LIST_INDEX				    INTEGER EXTERNAL(10),
		AGENT_ID					    INTEGER EXTERNAL(10)
	)


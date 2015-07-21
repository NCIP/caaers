OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'spa_prior_therapy_agents.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE spa_prior_therapy_agents
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		SPA_PRIOR_THERAPY_ID			    INTEGER EXTERNAL(10),
		CHEMO_AGENT_ID 				    INTEGER EXTERNAL(10),
		LIST_INDEX				    INTEGER EXTERNAL(10),
		AGENT_ID					    INTEGER EXTERNAL(10)
	)


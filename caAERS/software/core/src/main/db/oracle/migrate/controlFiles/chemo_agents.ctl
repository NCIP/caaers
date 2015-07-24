
LOAD DATA
	INFILE 'chemo_agents.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE chemo_agents
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR(2000),
		GENERIC_NAME					    CHAR(2000)
	)


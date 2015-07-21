OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\study_agent_inds.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_agent_inds
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_AGENT_ID 			    INTEGER EXTERNAL(10),
		IND_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)


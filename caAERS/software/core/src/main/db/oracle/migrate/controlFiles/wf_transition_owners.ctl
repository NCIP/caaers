OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\wf_transition_owners.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE wf_transition_owners
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		USER_ROLE_ID					    INTEGER EXTERNAL(10),
		TRANSITION_CONFIG_ID			    INTEGER EXTERNAL(10),
		DTYPE					    CHAR,
		GRID_ID					    CHAR,
		INVESTIGATOR_ID				    INTEGER EXTERNAL(10),
		RESEARCHSTAFF_ID				    INTEGER EXTERNAL(10)
	)


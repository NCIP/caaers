OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\wf_assignees.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE wf_assignees
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		USER_ROLE_ID					    INTEGER EXTERNAL(10),
		TASK_CONFIG_ID 			    INTEGER EXTERNAL(10),
		DTYPE					    CHAR,
		INVESTIGATOR_ID				    INTEGER EXTERNAL(10),
		RESEARCHSTAFF_ID				    INTEGER EXTERNAL(10)
	)


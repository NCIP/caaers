OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\participant_index.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE participant_index
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID					    CHAR,
		PARTICIPANT_ID 			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ROLE					    INTEGER EXTERNAL(10)
	)


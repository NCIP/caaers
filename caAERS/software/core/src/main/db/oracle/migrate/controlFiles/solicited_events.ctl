OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\solicited_events.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE solicited_events
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ARM_ID 				    INTEGER EXTERNAL(10),
		CTC_TERM_ID					    INTEGER EXTERNAL(10),
		LOWLEVEL_TERM_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		OTHER_TERM_ID					    INTEGER EXTERNAL(10),
		VERBATIM					    CHAR
	)


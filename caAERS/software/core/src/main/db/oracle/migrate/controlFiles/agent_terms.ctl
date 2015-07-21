OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\agent_terms.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE agent_terms
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		AGENT_ID					    INTEGER EXTERNAL(10),
		TERM_ID				    INTEGER EXTERNAL(10),
		TERM_TYPE					    CHAR,
		LOW_LEVEL_TERM_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		OTHER_TOXICITY 				    CHAR,
		EXPECTEDNESS_FREQUENCY 			    FLOAT EXTERNAL(126),
		GRADE1FREQUENCY				    FLOAT EXTERNAL(126),
		GRADE2FREQUENCY				    FLOAT EXTERNAL(126),
		GRADE3FREQUENCY				    FLOAT EXTERNAL(126),
		GRADE4FREQUENCY				    FLOAT EXTERNAL(126),
		GRADE5FREQUENCY				    FLOAT EXTERNAL(126),
		EXPECTED					    INTEGER EXTERNAL(1) "case :EXPECTED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE=""
	)


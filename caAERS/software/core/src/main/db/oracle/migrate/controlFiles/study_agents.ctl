OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'study_agents.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_agents
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		AGENT_ID					    INTEGER EXTERNAL(10),
		IND_IDENTIFIER 				    CHAR,
		IND_INDICATOR					    INTEGER EXTERNAL(1)  "case :IND_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		OTHER_AGENT					    CHAR,
		IND_TYPE					    INTEGER EXTERNAL(10),
		PART_OF_LEADIND				    INTEGER EXTERNAL(1)  "case :PART_OF_LEADIND
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1)  "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		STUDY_THERAPY_TYPE			    INTEGER EXTERNAL(10)
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'studies.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE studies
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		SHORT_TITLE				    CHAR,
		LONG_TITLE					    CHAR,
		DESCRIPTION					    CHAR,
		MULTI_INSTITUTION_INDICATOR			    INTEGER EXTERNAL(1)  "case :MULTI_INSTITUTION_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		PRIMARY_SPONSOR_CODE				    CHAR,
		PHASE_CODE					    CHAR,
		BLINDED_INDICATOR				    INTEGER EXTERNAL(1)  "case :BLINDED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		RANDOMIZED_INDICATOR				    INTEGER EXTERNAL(1)  "case :RANDOMIZED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		PRECIS 					    CHAR,
		DISEASE_CODE					    CHAR,
		MONITOR_CODE					    CHAR,
		STATUS 					    CHAR,
		TARGET_ACCRUAL_NUMBER				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		LOAD_STATUS					    INTEGER EXTERNAL(10),
		ADEERS_REPORTING				    INTEGER EXTERNAL(1)  "case :ADEERS_REPORTING
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		DESIGN_CODE					    INTEGER EXTERNAL(10),
		OTHER_MEDDRA_ID				    INTEGER EXTERNAL(10),
		DATA_ENTRY_STATUS				    INTEGER EXTERNAL(1)  "case :DATA_ENTRY_STATUS
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		TYPE						    CHAR,
		EXTERNAL_ID					    CHAR,
		VERBATIM_FIRST 				    INTEGER EXTERNAL(1)  "case :VERBATIM_FIRST
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		OTHER_TREATMENT_ASSIGNMENT			    CHAR,
		STUDY_PURPOSE					    CHAR,
		AE_TERM_UNIQUE 			    INTEGER EXTERNAL(1)  "case :AE_TERM_UNIQUE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE="",
		PARTICIPATION_TYPE				    CHAR,
		AE_REPORTING_LEVEL				    CHAR
	)


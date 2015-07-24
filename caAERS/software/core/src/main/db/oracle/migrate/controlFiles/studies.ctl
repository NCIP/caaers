
LOAD DATA
	INFILE 'studies.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE studies
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		SHORT_TITLE				    CHAR(2000),
		LONG_TITLE					    CHAR(2000),
		DESCRIPTION					    CHAR(2000),
		MULTI_INSTITUTION_INDICATOR			    INTEGER EXTERNAL(1)  "case :MULTI_INSTITUTION_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		PRIMARY_SPONSOR_CODE				    CHAR(2000),
		PHASE_CODE					    CHAR(2000),
		BLINDED_INDICATOR				    INTEGER EXTERNAL(1)  "case :BLINDED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		RANDOMIZED_INDICATOR				    INTEGER EXTERNAL(1)  "case :RANDOMIZED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		PRECIS 					    CHAR(2000),
		DISEASE_CODE					    CHAR(2000),
		MONITOR_CODE					    CHAR(2000),
		STATUS 					    CHAR(2000),
		TARGET_ACCRUAL_NUMBER				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
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
		TYPE						    CHAR(2000),
		EXTERNAL_ID					    CHAR(2000),
		VERBATIM_FIRST 				    INTEGER EXTERNAL(1)  "case :VERBATIM_FIRST
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		OTHER_TREATMENT_ASSIGNMENT			    CHAR(2000),
		STUDY_PURPOSE					    CHAR(2000),
		AE_TERM_UNIQUE 			    INTEGER EXTERNAL(1)  "case :AE_TERM_UNIQUE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE="",
		PARTICIPATION_TYPE				    CHAR(2000),
		AE_REPORTING_LEVEL				    CHAR(2000)
	)


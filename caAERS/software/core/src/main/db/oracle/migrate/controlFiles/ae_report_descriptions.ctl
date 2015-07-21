OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'ae_report_descriptions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_report_descriptions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		REPORT_ID				    INTEGER EXTERNAL(10),
		EVENT_DESCRIPTION				    CHAR,
		PRESENT_STATUS_CODE				    INTEGER EXTERNAL(10),
		RECOVERY_DATE					   DATE "YYYY-MM-DD" NULLIF RECOVERY_DATE="",
		RETREATED					    INTEGER EXTERNAL(1) "case :RETREATED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		DATE_REMOVED_FROM_PROTOCOL			   DATE "YYYY-MM-DD" NULLIF DATE_REMOVED_FROM_PROTOCOL="",
		BLIND_BROKEN					    INTEGER EXTERNAL(1) "case :BLIND_BROKEN
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		STUDY_DRUG_INTERRUPTED 			    INTEGER EXTERNAL(1) "case :STUDY_DRUG_INTERRUPTED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		REDUCED_DOSE					    CHAR,
		REDUCED_DATE					   DATE "YYYY-MM-DD" NULLIF REDUCED_DATE="",
		DAYS_NOT_GIVEN 				    INTEGER EXTERNAL(10),
		AUTOPSY_PERFORMED				    INTEGER EXTERNAL(1) "case :AUTOPSY_PERFORMED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CAUSE_OF_DEATH 				    CHAR,
		PRIMARY_TREATMENT				    CHAR,
		TREATMENT_TIME_HOUR				    INTEGER EXTERNAL(10),
		TREATMENT_TIME_MINUTE				    INTEGER EXTERNAL(10),
		TREATMENT_TIME_ZONE				    INTEGER EXTERNAL(10),
		EVENT_ABATE					    INTEGER EXTERNAL(10),
		EVENT_REAPPEAR 				    INTEGER EXTERNAL(10)
	)


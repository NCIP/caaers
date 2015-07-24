
LOAD DATA
	INFILE 'ae_report_descriptions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_report_descriptions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		REPORT_ID				    INTEGER EXTERNAL(10),
		EVENT_DESCRIPTION				    CHAR(2000),
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
		REDUCED_DOSE					    CHAR(2000),
		REDUCED_DATE					   DATE "YYYY-MM-DD" NULLIF REDUCED_DATE="",
		DAYS_NOT_GIVEN 				    INTEGER EXTERNAL(10),
		AUTOPSY_PERFORMED				    INTEGER EXTERNAL(1) "case :AUTOPSY_PERFORMED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CAUSE_OF_DEATH 				    CHAR(2000),
		PRIMARY_TREATMENT				    CHAR(2000),
		TREATMENT_TIME_HOUR				    INTEGER EXTERNAL(10),
		TREATMENT_TIME_MINUTE				    INTEGER EXTERNAL(10),
		TREATMENT_TIME_ZONE				    INTEGER EXTERNAL(10),
		EVENT_ABATE					    INTEGER EXTERNAL(10),
		EVENT_REAPPEAR 				    INTEGER EXTERNAL(10)
	)


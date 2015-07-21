OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'adverse_events.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE adverse_events
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		DETAILS_FOR_OTHER				    CHAR,
		GRADE_CODE					    INTEGER EXTERNAL(10),
		EXPECTED					    INTEGER EXTERNAL(1) "case :EXPECTED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		HOSPITALIZATION_CODE				    INTEGER EXTERNAL(10),
		COMMENTS					    CHAR,
		REPORT_ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		LIST_INDEX					    INTEGER EXTERNAL(10),
		ATTRIBUTION_SUMMARY_CODE			    INTEGER EXTERNAL(10),
		ROUTINE_REPORT_ID				    INTEGER EXTERNAL(10),
		ROUTINE_LIST_INDEX				    INTEGER EXTERNAL(10),
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		LOW_LEVEL_TERM_ID				    INTEGER EXTERNAL(10),
		SOLICITED					    INTEGER EXTERNAL(1) "case :SOLICITED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		REPORTING_PERIOD_ID				    INTEGER EXTERNAL(10),
		REQUIRES_REPORTING				    INTEGER EXTERNAL(1) "case :REQUIRES_REPORTING
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		EVENT_TIME_HOUR				    INTEGER EXTERNAL(10),
		EVENT_TIME_MINUTE				    INTEGER EXTERNAL(10),
		EVENT_TIME_ZONE				    INTEGER EXTERNAL(10),
		EVENT_LOCATION 				    CHAR,
		GRADED_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF GRADED_DATE="",
		SIGNATURE					    CHAR,
		REPORTED					    INTEGER EXTERNAL(1),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		POST_SUBMISSION_UPDATED_DATE			   TIMESTAMP(6)  "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF POST_SUBMISSION_UPDATED_DATE="",
		PARTICIPANT_AT_RISK				    INTEGER EXTERNAL(1),
		EXTERNAL_ID					    CHAR,
		OTHER_SPECIFY					    CHAR,
		REPORTER_EMAIL 				    CHAR,
		ADDED_TO_REPORT_AT_LEAST_ONCE			    INTEGER EXTERNAL(1) "case :ADDED_TO_REPORT_AT_LEAST_ONCE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CREATED_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF CREATED_DATE=""
	)
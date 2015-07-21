OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\report_calendar_templates.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE report_calendar_templates
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME					    CHAR,
		DESCRIPTION					    CHAR,
		DURATION				    INTEGER EXTERNAL(10),
		ORG_ID 					    INTEGER EXTERNAL(10),
		TIME_SCALE_UNIT_CODE			    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		AMENDABLE					    INTEGER EXTERNAL(1) "case :AMENDABLE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		ATTRIBUTION_REQUIRED				    INTEGER EXTERNAL(1) "case :ATTRIBUTION_REQUIRED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LABEL						    CHAR,
		REPORT_FORMAT_TYPE			    INTEGER EXTERNAL(10),
		PHYSICIAN_SIGNOFF			    INTEGER EXTERNAL(1) "case :PHYSICIAN_SIGNOFF
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		PARENT_ID					    INTEGER EXTERNAL(10),
		GROUP_ID					    INTEGER EXTERNAL(10),
		REPORT_TYPE					    INTEGER EXTERNAL(10),
		WORKFLOW_ENABLED			    INTEGER EXTERNAL(1) "case :WORKFLOW_ENABLED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		HEADER 					    CHAR,
		FOOTER 					    CHAR,
		ENABLED				    INTEGER EXTERNAL(1) "case :ENABLED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		INCLUDE_NON_SERIOUS_AES			    INTEGER EXTERNAL(1) "case :INCLUDE_NON_SERIOUS_AES
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


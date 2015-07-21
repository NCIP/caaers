OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'organizations.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE organizations
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		NCI_INSTITUTE_CODE				    CHAR,
		DESCRIPTION_TEXT				    CHAR,
		CITY						    CHAR,
		STATE						    CHAR,
		COUNTRY					    CHAR,
		TYPE						    CHAR,
		EXTERNAL_ID					    CHAR,
		MERGED_ORG_ID					    INTEGER EXTERNAL(10),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		ORG_TYPE					    CHAR,
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE=""
	)


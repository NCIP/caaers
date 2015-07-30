
LOAD DATA
	INFILE 'organizations.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE organizations
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		NCI_INSTITUTE_CODE				    CHAR(2000),
		DESCRIPTION_TEXT				    CHAR(2000),
		CITY						    CHAR(2000),
		STATE						    CHAR(2000),
		COUNTRY					    CHAR(2000),
		TYPE						    CHAR(2000),
		EXTERNAL_ID					    CHAR(2000),
		MERGED_ORG_ID					    INTEGER EXTERNAL(10),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		ORG_TYPE					    CHAR(2000),
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE=""
	)


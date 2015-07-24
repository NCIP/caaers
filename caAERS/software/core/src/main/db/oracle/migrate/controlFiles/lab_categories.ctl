
LOAD DATA
	INFILE 'lab_categories.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE lab_categories
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION_ID				    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE=""
	)


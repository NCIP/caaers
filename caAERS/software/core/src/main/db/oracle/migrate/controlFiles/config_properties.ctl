OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'config_properties.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE config_properties
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		CODE					    CHAR,
		NAME					    CHAR,
		DESCRIPTION					    CHAR,
		GRID_ID					    CHAR,
		CONFIG_TYPE					    INTEGER EXTERNAL(10),
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE=""
	)


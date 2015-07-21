OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_filter_clause.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_filter_clause
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		FILTER_CLAUSE_ID			    INTEGER EXTERNAL(38),
		CLASS_NAME				    CHAR,
		FILTER_CHAIN				    CHAR,
		TARGET_CLASS_NAME			    CHAR,
		TARGET_CLASS_ATTRIBUTE_NAME		    CHAR,
		TARGET_CLASS_ATTRIBUTE_TYPE		    CHAR,
		TARGET_CLASS_ALIAS				    CHAR,
		TARGET_CLASS_ATTRIBUTE_ALIAS			    CHAR,
		GENERATED_SQL_USER			    CHAR,
		GENERATED_SQL_GROUP			    CHAR,
		APPLICATION_ID 			    INTEGER EXTERNAL(38),
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)


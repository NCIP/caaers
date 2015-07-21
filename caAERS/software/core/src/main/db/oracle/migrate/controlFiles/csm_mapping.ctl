OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_mapping.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_mapping
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		MAPPING_ID				    INTEGER EXTERNAL(38),
		APPLICATION_ID 			    INTEGER EXTERNAL(38),
		OBJECT_NAME				    CHAR,
		ATTRIBUTE_NAME 			    CHAR,
		OBJECT_PACKAGE_NAME				    CHAR,
		TABLE_NAME					    CHAR,
		TABLE_NAME_GROUP				    CHAR,
		TABLE_NAME_USER				    CHAR,
		VIEW_NAME_GROUP				    CHAR,
		VIEW_NAME_USER 				    CHAR,
		ACTIVE_FLAG				    INTEGER EXTERNAL(1),
		MAINTAINED_FLAG			    INTEGER EXTERNAL(1),
		UPDATE_DATE					   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)


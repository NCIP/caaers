OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'meddra_hlt.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE meddra_hlt
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		MEDDRA_CODE					    CHAR,
		MEDDRA_TERM					    CHAR,
		COSTART_SYMBOL 				    CHAR,
		HARTS_CODE					    CHAR,
		WHO_ART_CODE					    CHAR,
		ICD9_CODE					    CHAR,
		ICD9_CM_CODE					    CHAR,
		ICD10_CODE					    CHAR,
		JART_CODE					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		VERSION_ID					    INTEGER EXTERNAL(10)
	)


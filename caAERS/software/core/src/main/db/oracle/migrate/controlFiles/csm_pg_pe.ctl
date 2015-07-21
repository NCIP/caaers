OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_pg_pe.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_pg_pe
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		PG_PE_ID				    INTEGER EXTERNAL(38),
		PROTECTION_GROUP_ID			    INTEGER EXTERNAL(38),
		PROTECTION_ELEMENT_ID			    INTEGER EXTERNAL(38),
		UPDATE_DATE					   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)


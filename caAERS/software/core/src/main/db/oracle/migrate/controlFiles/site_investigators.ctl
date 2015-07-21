OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'site_investigators.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE site_investigators
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		SITE_ID				    INTEGER EXTERNAL(10),
		INVESTIGATOR_ID			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		EMAIL_ADDRESS					    CHAR,
		START_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF START_DATE="",
		END_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF END_DATE="",
		CODE						    INTEGER EXTERNAL(10),
		PHONE_NUMBER					    CHAR,
		FAX_NUMBER					    CHAR,
		STREET 					    CHAR,
		CITY						    CHAR,
		STATE						    CHAR,
		ZIP						    CHAR,
		COUNTRY					    CHAR
	)


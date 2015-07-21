OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\site_research_staffs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE site_research_staffs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		SITE_ID				    INTEGER EXTERNAL(10),
		RESEARCHSTAFF_ID			    INTEGER EXTERNAL(10),
		EMAIL_ADDRESS					    CHAR,
		PHONE_NUMBER					    CHAR,
		FAX_NUMBER					    CHAR,
		GRID_ID					    CHAR,
		STREET 					    CHAR,
		CITY						    CHAR,
		STATE						    CHAR,
		ZIP						    CHAR,
		CODE						    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		COUNTRY					    CHAR,
		ASSOCIATE_ALL_STUDIES				    INTEGER EXTERNAL(1)  "case :ASSOCIATE_ALL_STUDIES
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1)  "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		START_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF START_DATE="",
		END_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF END_DATE=""
	)


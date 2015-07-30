
LOAD DATA
	INFILE 'site_research_staffs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE site_research_staffs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		SITE_ID				    INTEGER EXTERNAL(10),
		RESEARCHSTAFF_ID			    INTEGER EXTERNAL(10),
		EMAIL_ADDRESS					    CHAR(2000),
		PHONE_NUMBER					    CHAR(2000),
		FAX_NUMBER					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		STREET 					    CHAR(2000),
		CITY						    CHAR(2000),
		STATE						    CHAR(2000),
		ZIP						    CHAR(2000),
		CODE						    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		COUNTRY					    CHAR(2000),
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


OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'ae_report_people.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_report_people
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		FIRST_NAME					    CHAR,
		MIDDLE_NAME					    CHAR,
		LAST_NAME					    CHAR,
		REPORT_ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ROLE					    CHAR,
		REPORT_VERSION_ID				    INTEGER EXTERNAL(10),
		TITLE						    CHAR,
		STREET 					    CHAR,
		CITY						    CHAR,
		STATE						    CHAR,
		ZIP						    CHAR,
		CODE						    INTEGER EXTERNAL(10),
		INVESTIGATOR_ID				    INTEGER EXTERNAL(10),
		RESEARCHSTAFF_ID				    INTEGER EXTERNAL(10),
		COUNTRY					    CHAR
	)


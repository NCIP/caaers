OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\research_staffs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE research_staffs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FIRST_NAME					    CHAR,
		LAST_NAME					    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		MIDDLE_NAME					    CHAR,
		EMAIL_ADDRESS				    CHAR,
		PHONE_NUMBER					    CHAR,
		FAX_NUMBER					    CHAR,
		NCI_IDENTIFIER 				    CHAR,
		LOGIN_ID					    CHAR,
		TYPE						    CHAR,
		EXTERNAL_ID					    CHAR,
		STATUS_CODE					    CHAR,
		STREET 					    CHAR,
		CITY						    CHAR,
		STATE						    CHAR,
		ZIP						    CHAR,
		CODE						    INTEGER EXTERNAL(10),
		COUNTRY					    CHAR,
		USER_ID					    INTEGER EXTERNAL(10)
	)



LOAD DATA
	INFILE 'research_staffs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE research_staffs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FIRST_NAME					    CHAR(2000),
		LAST_NAME					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		MIDDLE_NAME					    CHAR(2000),
		EMAIL_ADDRESS				    CHAR(2000),
		PHONE_NUMBER					    CHAR(2000),
		FAX_NUMBER					    CHAR(2000),
		NCI_IDENTIFIER 				    CHAR(2000),
		LOGIN_ID					    CHAR(2000),
		TYPE						    CHAR(2000),
		EXTERNAL_ID					    CHAR(2000),
		STATUS_CODE					    CHAR(2000),
		STREET 					    CHAR(2000),
		CITY						    CHAR(2000),
		STATE						    CHAR(2000),
		ZIP						    CHAR(2000),
		CODE						    INTEGER EXTERNAL(10),
		COUNTRY					    CHAR(2000),
		USER_ID					    INTEGER EXTERNAL(10)
	)


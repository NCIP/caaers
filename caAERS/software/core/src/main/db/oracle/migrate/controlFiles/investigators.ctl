
LOAD DATA
	INFILE 'investigators.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE investigators
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FIRST_NAME					    CHAR(2000),
		LAST_NAME					    CHAR(2000),
		NCI_IDENTIFIER 				    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		MIDDLE_NAME					    CHAR(2000),
		EMAIL_ADDRESS				    CHAR(2000),
		PHONE_NUMBER					    CHAR(2000),
		FAX_NUMBER					    CHAR(2000),
		LOGIN_ID					    CHAR(2000),
		TYPE						    CHAR(2000),
		EXTERNAL_ID					    CHAR(2000),
		ALLOWED_TO_LOGIN				    INTEGER EXTERNAL(1) "case :ALLOWED_TO_LOGIN
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		USER_ID					    INTEGER EXTERNAL(10)
	)


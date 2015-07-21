OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\investigators.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE investigators
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FIRST_NAME					    CHAR,
		LAST_NAME					    CHAR,
		NCI_IDENTIFIER 				    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		MIDDLE_NAME					    CHAR,
		EMAIL_ADDRESS				    CHAR,
		PHONE_NUMBER					    CHAR,
		FAX_NUMBER					    CHAR,
		LOGIN_ID					    CHAR,
		TYPE						    CHAR,
		EXTERNAL_ID					    CHAR,
		ALLOWED_TO_LOGIN				    INTEGER EXTERNAL(1) "case :ALLOWED_TO_LOGIN
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		USER_ID					    INTEGER EXTERNAL(10)
	)


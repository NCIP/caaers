OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'recipients.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE recipients
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		PLNF_ID					    INTEGER EXTERNAL(10),
		DTYPE					    CHAR,
		ROLE_NAME					    CHAR,
		CONTACT_NAME					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)


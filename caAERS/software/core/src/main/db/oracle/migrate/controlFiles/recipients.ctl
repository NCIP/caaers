
LOAD DATA
	INFILE 'recipients.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE recipients
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		PLNF_ID					    INTEGER EXTERNAL(10),
		DTYPE					    CHAR(2000),
		ROLE_NAME					    CHAR(2000),
		CONTACT_NAME					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)


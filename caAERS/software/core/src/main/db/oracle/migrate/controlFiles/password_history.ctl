
LOAD DATA
	INFILE 'password_history.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE password_history
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		USER_ID				    INTEGER EXTERNAL(10),
		PASSWORD					    CHAR(2000),
		LIST_INDEX				    INTEGER EXTERNAL(10)
	)


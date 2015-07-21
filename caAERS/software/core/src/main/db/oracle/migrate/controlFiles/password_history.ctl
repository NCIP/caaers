OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'password_history.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE password_history
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		USER_ID				    INTEGER EXTERNAL(10),
		PASSWORD					    CHAR,
		LIST_INDEX				    INTEGER EXTERNAL(10)
	)


OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'adverseevent_index.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE adverseevent_index
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID					    CHAR,
		ADVERSEEVENT_ID			    INTEGER EXTERNAL,
		GRID_ID					    CHAR,
		ROLE					    INTEGER EXTERNAL(10)
	)


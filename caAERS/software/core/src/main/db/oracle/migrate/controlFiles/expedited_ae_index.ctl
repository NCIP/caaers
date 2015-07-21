OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'expedited_ae_index.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE expedited_ae_index
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID					    CHAR,
		EXPEDITED_AE_ID			    INTEGER EXTERNAL,
		GRID_ID					    CHAR,
		ROLE					    INTEGER EXTERNAL(10)
	)


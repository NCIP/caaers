OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'disease_categories.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE disease_categories
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		PARENT_ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)


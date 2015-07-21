OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'configuration.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE configuration
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		KEY					    CHAR,
		VALUE						    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)


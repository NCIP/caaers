
LOAD DATA
	INFILE 'configuration.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE configuration
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		KEY					    CHAR(2000),
		VALUE						    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)


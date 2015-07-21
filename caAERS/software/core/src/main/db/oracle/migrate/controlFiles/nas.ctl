OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'nas.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE nas
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		NA					    CHAR
	)


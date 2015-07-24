
LOAD DATA
	INFILE 'nas.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE nas
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		NA					    CHAR(2000)
	)


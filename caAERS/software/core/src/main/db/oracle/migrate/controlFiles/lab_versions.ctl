
LOAD DATA
	INFILE 'lab_versions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE lab_versions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000)
	)

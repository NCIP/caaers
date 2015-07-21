OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'lab_versions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE lab_versions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME					    CHAR
	)


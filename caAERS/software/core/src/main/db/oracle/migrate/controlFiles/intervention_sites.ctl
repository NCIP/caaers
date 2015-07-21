OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'intervention_sites.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE intervention_sites
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR
	)


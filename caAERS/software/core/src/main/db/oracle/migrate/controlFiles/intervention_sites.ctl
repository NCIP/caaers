
LOAD DATA
	INFILE 'intervention_sites.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE intervention_sites
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR(2000)
	)


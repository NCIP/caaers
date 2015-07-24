
LOAD DATA
	INFILE 'ae_cause_types.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_cause_types
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		CODE					    CHAR(2000),
		NAME					    CHAR(2000)
	)


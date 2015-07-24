
LOAD DATA
	INFILE 'disease_categories.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE disease_categories
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		PARENT_ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)


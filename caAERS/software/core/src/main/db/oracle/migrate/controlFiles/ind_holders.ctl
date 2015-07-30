
LOAD DATA
	INFILE 'ind_holders.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ind_holders
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		DTYPE					    CHAR(2000),
		ORG_ID 					    INTEGER EXTERNAL(10),
		INV_ID 					    INTEGER EXTERNAL(10),
		DRUG_ID				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)


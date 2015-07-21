OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ind_holders.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ind_holders
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		DTYPE					    CHAR,
		ORG_ID 					    INTEGER EXTERNAL(10),
		INV_ID 					    INTEGER EXTERNAL(10),
		DRUG_ID				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)


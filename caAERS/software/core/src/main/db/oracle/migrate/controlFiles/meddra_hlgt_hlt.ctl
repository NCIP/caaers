
LOAD DATA
	INFILE 'meddra_hlgt_hlt.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE meddra_hlgt_hlt
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		MEDDRA_HLGT_ID 				    INTEGER EXTERNAL(10),
		MEDDRA_HLT_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		VERSION_ID					    INTEGER EXTERNAL(10)
	)


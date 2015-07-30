
LOAD DATA
	INFILE 'meddra_soc_hlgt.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE meddra_soc_hlgt
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		MEDDRA_SOC_ID					    INTEGER EXTERNAL(10),
		MEDDRA_HLGT_ID 				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		VERSION_ID					    INTEGER EXTERNAL(10)
	)


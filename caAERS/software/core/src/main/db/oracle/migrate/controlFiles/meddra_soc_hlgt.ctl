OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'meddra_soc_hlgt.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE meddra_soc_hlgt
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		MEDDRA_SOC_ID					    INTEGER EXTERNAL(10),
		MEDDRA_HLGT_ID 				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		VERSION_ID					    INTEGER EXTERNAL(10)
	)


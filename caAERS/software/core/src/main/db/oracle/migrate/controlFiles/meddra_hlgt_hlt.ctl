OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\meddra_hlgt_hlt.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE meddra_hlgt_hlt
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		MEDDRA_HLGT_ID 				    INTEGER EXTERNAL(10),
		MEDDRA_HLT_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		VERSION_ID					    INTEGER EXTERNAL(10)
	)


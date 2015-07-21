OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ae_pre_existing_conds.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_pre_existing_conds
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		REPORT_ID				    INTEGER EXTERNAL(10),
		PRE_EXISTING_CONDITION_ID			    INTEGER EXTERNAL(10),
		OTHER						    CHAR,
		LIST_INDEX				    INTEGER EXTERNAL(10),
		SYNCED_TO_CAUSE				    INTEGER EXTERNAL(1) "case :SYNCED_TO_CAUSE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


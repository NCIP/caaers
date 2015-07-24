LOAD DATA
	INFILE 'ae_pre_existing_conds.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_pre_existing_conds
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		REPORT_ID				    INTEGER EXTERNAL(10),
		PRE_EXISTING_CONDITION_ID			    INTEGER EXTERNAL(10),
		OTHER						    CHAR(2000),
		LIST_INDEX				    INTEGER EXTERNAL(10),
		SYNCED_TO_CAUSE				    INTEGER EXTERNAL(1) "case :SYNCED_TO_CAUSE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'mandatory_field_defs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE mandatory_field_defs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FIELD_PATH				    CHAR,
		RCT_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		MANDATORY				    INTEGER EXTERNAL(10),
		RULE_BINDURL					    CHAR,
		RULE_NAME					    CHAR,
		SELF_REFERENCED				    INTEGER EXTERNAL(1) "case :SELF_REFERENCED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


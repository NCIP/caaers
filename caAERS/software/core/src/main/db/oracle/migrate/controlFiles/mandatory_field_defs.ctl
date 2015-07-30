
LOAD DATA
	INFILE 'mandatory_field_defs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE mandatory_field_defs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FIELD_PATH				    CHAR(2000),
		RCT_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		MANDATORY				    INTEGER EXTERNAL(10),
		RULE_BINDURL					    CHAR(2000),
		RULE_NAME					    CHAR(2000),
		SELF_REFERENCED				    INTEGER EXTERNAL(1) "case :SELF_REFERENCED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


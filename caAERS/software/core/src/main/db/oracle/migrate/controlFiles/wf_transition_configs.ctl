OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'wf_transition_configs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE wf_transition_configs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		TRANSITION_NAME				    CHAR,
		TASK_CONFIG_ID 			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)


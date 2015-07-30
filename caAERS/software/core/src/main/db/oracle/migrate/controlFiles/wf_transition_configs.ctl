
LOAD DATA
	INFILE 'wf_transition_configs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE wf_transition_configs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		TRANSITION_NAME				    CHAR(2000),
		TASK_CONFIG_ID 			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)


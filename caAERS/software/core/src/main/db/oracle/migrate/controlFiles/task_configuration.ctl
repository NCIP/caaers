
LOAD DATA
	INFILE 'task_configuration.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE task_configuration
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		STATUS_NAME				    CHAR(2000),
		APPLICABLE				    INTEGER EXTERNAL(1) "case :APPLICABLE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LOCATION				    INTEGER EXTERNAL(10),
		WORKFLOW_CONFIG_ID			    INTEGER EXTERNAL(10),
		MESSAGE					    CHAR(2000) "replace(:MESSAGE,'\\n',chr(10))",
		TASK_NAME					    CHAR(2000)
	)


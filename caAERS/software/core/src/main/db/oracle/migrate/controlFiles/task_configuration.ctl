OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\task_configuration.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE task_configuration
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		STATUS_NAME				    CHAR,
		APPLICABLE				    INTEGER EXTERNAL(1) "case :APPLICABLE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LOCATION				    INTEGER EXTERNAL(10),
		WORKFLOW_CONFIG_ID			    INTEGER EXTERNAL(10),
		MESSAGE					    CHAR,
		TASK_NAME					    CHAR
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\workflow_configuration.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE workflow_configuration
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		WORKFLOW_DEFINITION_NAME			    CHAR,
		DEFAULT_ASSIGNEE				    CHAR,
		ENABLED					    INTEGER EXTERNAL(1) "case :ENABLED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		DOMAIN_OBJECT					    CHAR,
		NAME						    CHAR
	)


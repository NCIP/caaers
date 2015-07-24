
LOAD DATA
	INFILE 'workflow_configuration.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE workflow_configuration
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		WORKFLOW_DEFINITION_NAME			    CHAR(2000),
		DEFAULT_ASSIGNEE				    CHAR(2000),
		ENABLED					    INTEGER EXTERNAL(1) "case :ENABLED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		DOMAIN_OBJECT					    CHAR(2000),
		NAME						    CHAR(2000)
	)


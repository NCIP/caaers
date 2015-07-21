OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\chemo_agents.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE chemo_agents
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		GENERIC_NAME					    CHAR
	)


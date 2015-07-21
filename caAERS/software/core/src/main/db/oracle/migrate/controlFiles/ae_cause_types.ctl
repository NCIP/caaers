OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ae_cause_types.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_cause_types
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		CODE					    CHAR,
		NAME					    CHAR
	)


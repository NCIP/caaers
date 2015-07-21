OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\contact_mechanisms.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE contact_mechanisms
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		PERSON_ID				    INTEGER EXTERNAL(10),
		TYPE					    CHAR,
		VALUE						    CHAR
	)


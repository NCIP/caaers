OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\configuration.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE configuration
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		KEY					    CHAR,
		VALUE						    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)


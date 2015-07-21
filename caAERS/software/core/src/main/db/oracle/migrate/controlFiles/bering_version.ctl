OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\bering_version.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE bering_version
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		RELEASE					    INTEGER EXTERNAL(10),
		MIGRATION					    INTEGER EXTERNAL(10)
	)


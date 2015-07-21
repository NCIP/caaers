OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\rep_ver_prop.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_ver_prop
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


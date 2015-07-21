OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'rep_ver_refs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_ver_refs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


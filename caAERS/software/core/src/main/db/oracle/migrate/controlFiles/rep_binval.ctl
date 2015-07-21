OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'rep_binval.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_binval
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


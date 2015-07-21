OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'rep_fsentry.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_fsentry
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'rep_prop.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_prop
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'rep_ver_node.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_ver_node
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


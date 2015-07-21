OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'rep_node.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rep_node
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'attachments.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE attachments
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		PLNF_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		blob_filename     FILLER CHAR(100),
		CONTENT					    LOBFILE(blob_filename)
	)


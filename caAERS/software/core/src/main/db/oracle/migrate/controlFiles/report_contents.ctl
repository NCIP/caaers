OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'report_contents.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE report_contents
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CONTENT_TYPE					    CHAR,
		REPORT_VERSION_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		 blob_filename     FILLER CHAR(100),
		CONTENT					     LOBFILE(blob_filename) TERMINATED BY EOF
	)


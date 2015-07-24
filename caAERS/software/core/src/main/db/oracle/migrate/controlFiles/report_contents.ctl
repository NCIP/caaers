
LOAD DATA
	INFILE 'report_contents.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE report_contents
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CONTENT_TYPE					    CHAR(2000),
		REPORT_VERSION_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		 blob_filename     FILLER CHAR(2000)(100),
		CONTENT					     LOBFILE(blob_filename) TERMINATED BY EOF
	)


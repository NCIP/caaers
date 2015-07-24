
LOAD DATA
	INFILE 'attachments.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE attachments
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		PLNF_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		blob_filename     FILLER CHAR(2000)(100),
		CONTENT					    LOBFILE(blob_filename)
	)



LOAD DATA
	INFILE 'study_device_inds.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_device_inds
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_DEVICE_ID				    INTEGER EXTERNAL(10),
		IND_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)


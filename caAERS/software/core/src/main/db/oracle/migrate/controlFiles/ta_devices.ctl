OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ta_devices.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ta_devices
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_DEVICE_ID				    INTEGER EXTERNAL(10)
	)


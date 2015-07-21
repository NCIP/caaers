OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'report_deliveries.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE report_deliveries
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		END_POINT				    CHAR,
		DELIVERY_STATUS			    CHAR,
		RDD_ID 					    INTEGER EXTERNAL(10),
		RP_ID						    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)


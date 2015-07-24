
LOAD DATA
	INFILE 'report_deliveries.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE report_deliveries
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		END_POINT				    CHAR(2000),
		DELIVERY_STATUS			    CHAR(2000),
		RDD_ID 					    INTEGER EXTERNAL(10),
		RP_ID						    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)



LOAD DATA
	INFILE 'study_devices.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_devices
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		BRAND_NAME					    CHAR(2000),
		COMMON_NAME					    CHAR(2000),
		CATALOG_NUMBER 				    CHAR(2000),
		MANUFACTURER_NAME				    CHAR(2000),
		MANUFACTURER_CITY				    CHAR(2000),
		MANUFACTURER_STATE				    CHAR(2000),
		MODEL_NUMBER					    CHAR(2000),
		DEVICE_TYPE					    CHAR(2000),
		DEVICE_ID					    INTEGER EXTERNAL(10),
		STUDY_ID					    INTEGER EXTERNAL(10),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1)  "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		GRID_ID					    CHAR(2000),
		STUDY_THERAPY_TYPE			    INTEGER EXTERNAL(10)
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\study_devices.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_devices
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		BRAND_NAME					    CHAR,
		COMMON_NAME					    CHAR,
		CATALOG_NUMBER 				    CHAR,
		MANUFACTURER_NAME				    CHAR,
		MANUFACTURER_CITY				    CHAR,
		MANUFACTURER_STATE				    CHAR,
		MODEL_NUMBER					    CHAR,
		DEVICE_TYPE					    CHAR,
		DEVICE_ID					    INTEGER EXTERNAL(10),
		STUDY_ID					    INTEGER EXTERNAL(10),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1)  "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		GRID_ID					    CHAR,
		STUDY_THERAPY_TYPE			    INTEGER EXTERNAL(10)
	)


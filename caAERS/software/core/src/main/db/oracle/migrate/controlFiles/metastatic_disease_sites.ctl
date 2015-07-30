
LOAD DATA
	INFILE 'metastatic_disease_sites.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE metastatic_disease_sites
	fields terminated by '\t'	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		OTHER_SITE					    CHAR(2000),
		CODED_SITE_ID					    INTEGER EXTERNAL(10),
		DISEASE_HISTORY_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)


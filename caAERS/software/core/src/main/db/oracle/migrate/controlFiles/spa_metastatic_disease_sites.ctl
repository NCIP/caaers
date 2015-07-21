OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\spa_metastatic_disease_sites.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE spa_metastatic_disease_sites
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		OTHER_SITE					    CHAR,
		CODED_SITE_ID					    INTEGER EXTERNAL(10),
		SPA_DISEASE_HISTORY_ID 			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)


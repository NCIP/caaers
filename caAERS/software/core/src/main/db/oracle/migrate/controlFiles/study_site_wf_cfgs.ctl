OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'study_site_wf_cfgs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_site_wf_cfgs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		GRID_ID					    CHAR,
		STUDY_ORGANIZATION_ID				    INTEGER EXTERNAL(10),
		WORKFLOW_CFG_ID				    INTEGER EXTERNAL(10)
	)


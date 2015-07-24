
LOAD DATA
	INFILE 'study_site_wf_cfgs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_site_wf_cfgs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME						    CHAR(2000),
		GRID_ID					    CHAR(2000),
		STUDY_ORGANIZATION_ID				    INTEGER EXTERNAL(10),
		WORKFLOW_CFG_ID				    INTEGER EXTERNAL(10)
	)


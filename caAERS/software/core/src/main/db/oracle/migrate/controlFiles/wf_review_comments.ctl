
LOAD DATA
	INFILE 'wf_review_comments.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE wf_review_comments
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		USER_COMMENT					    CHAR(2000),
		AUTO_TEXT					    CHAR(2000),
		RP_ID						    INTEGER EXTERNAL(10),
		REPORT_ID					    INTEGER EXTERNAL(10),
		LIST_INDEX					    INTEGER EXTERNAL(10),
		DTYPE					    CHAR(2000),
		EDITABLE					    INTEGER EXTERNAL(1) "case :EDITABLE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		USER_ID					    CHAR(2000),
		CREATED_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF CREATED_DATE=""
	)


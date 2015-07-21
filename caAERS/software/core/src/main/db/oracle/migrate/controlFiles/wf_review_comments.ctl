OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'wf_review_comments.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE wf_review_comments
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		USER_COMMENT					    CHAR,
		AUTO_TEXT					    CHAR,
		RP_ID						    INTEGER EXTERNAL(10),
		REPORT_ID					    INTEGER EXTERNAL(10),
		LIST_INDEX					    INTEGER EXTERNAL(10),
		DTYPE					    CHAR,
		EDITABLE					    INTEGER EXTERNAL(1) "case :EDITABLE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		USER_ID					    CHAR,
		CREATED_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF CREATED_DATE=""
	)


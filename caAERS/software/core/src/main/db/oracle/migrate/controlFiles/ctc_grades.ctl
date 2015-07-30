
LOAD DATA
	INFILE 'ctc_grades.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ctc_grades
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TERM_ID				    INTEGER EXTERNAL(10),
		GRADE_CODE				    INTEGER EXTERNAL(10),
		GRADE_TEXT				    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)


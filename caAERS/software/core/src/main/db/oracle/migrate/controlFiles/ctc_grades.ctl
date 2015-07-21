OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ctc_grades.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ctc_grades
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TERM_ID				    INTEGER EXTERNAL(10),
		GRADE_CODE				    INTEGER EXTERNAL(10),
		GRADE_TEXT				    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'labs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE labs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME					    CHAR,
		LAB_DATE					   DATE "YYYY-MM-DD" NULLIF LAB_DATE="",
		RESULT 					    CHAR,
		UNITS						    CHAR,
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		DISMISSED				    INTEGER EXTERNAL(1) "case :DISMISSED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		GRID_ID					    CHAR
	)


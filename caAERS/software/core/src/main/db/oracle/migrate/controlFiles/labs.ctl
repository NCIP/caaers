
LOAD DATA
	INFILE 'labs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE labs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		LAB_DATE					   DATE "YYYY-MM-DD" NULLIF LAB_DATE="",
		RESULT 					    CHAR(2000),
		UNITS						    CHAR(2000),
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		DISMISSED				    INTEGER EXTERNAL(1) "case :DISMISSED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		GRID_ID					    CHAR(2000)
	)


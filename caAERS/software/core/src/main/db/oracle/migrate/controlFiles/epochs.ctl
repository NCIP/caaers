
LOAD DATA
	INFILE 'epochs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE epochs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		DESCRIPTION					    CHAR(2000),
		STUDY_ID					    INTEGER EXTERNAL(10),
		ORDER_NO				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


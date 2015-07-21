OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\epochs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE epochs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME					    CHAR,
		DESCRIPTION					    CHAR,
		STUDY_ID					    INTEGER EXTERNAL(10),
		ORDER_NO				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


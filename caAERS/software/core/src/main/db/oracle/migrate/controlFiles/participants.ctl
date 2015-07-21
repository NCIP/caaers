OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\participants.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE participants
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		INSTITUITIONAL_PATIENT_NUMBER			    CHAR,
		INSTITUTION					    CHAR,
		STUDY_PARTICIPANT_NAME 			    CHAR,
		FIRST_NAME					    CHAR,
		LAST_NAME					    CHAR,
		GENDER 					    CHAR,
		ETHNICITY					    CHAR,
		RACE						    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		MIDDLE_NAME					    CHAR,
		MAIDEN_NAME					    CHAR,
		GRID_ID					    CHAR,
		LOAD_STATUS					    INTEGER EXTERNAL(10),
		BIRTH_YEAR					    INTEGER EXTERNAL(10),
		BIRTH_MONTH					    INTEGER EXTERNAL(10),
		BIRTH_DAY					    INTEGER EXTERNAL(10),
		BIRTH_ZONE				    INTEGER EXTERNAL(10)
	)


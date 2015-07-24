
LOAD DATA
	INFILE 'contact_mechanisms.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE contact_mechanisms
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		PERSON_ID				    INTEGER EXTERNAL(10),
		TYPE					    CHAR(2000),
		VALUE						    CHAR(2000)
	)


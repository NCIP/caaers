
LOAD DATA
	INFILE 'bering_version.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE bering_version
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		RELEASE					    INTEGER EXTERNAL(10),
		MIGRATION					    INTEGER EXTERNAL(10)
	)


OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_user_pe.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_user_pe
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		USER_PROTECTION_ELEMENT_ID		    INTEGER EXTERNAL(38),
		PROTECTION_ELEMENT_ID			    INTEGER EXTERNAL(38),
		USER_ID				    INTEGER EXTERNAL(38)
	)


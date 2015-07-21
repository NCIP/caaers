OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_protection_element.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_protection_element
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		PROTECTION_ELEMENT_ID			    INTEGER EXTERNAL(38),
		PROTECTION_ELEMENT_NAME		    CHAR,
		PROTECTION_ELEMENT_DESCRIPTION 		    CHAR,
		OBJECT_ID				    CHAR,
		ATTRIBUTE					    CHAR,
		ATTRIBUTE_VALUE				    CHAR,
		PROTECTION_ELEMENT_TYPE			    CHAR,
		APPLICATION_ID 			    INTEGER EXTERNAL(38),
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)


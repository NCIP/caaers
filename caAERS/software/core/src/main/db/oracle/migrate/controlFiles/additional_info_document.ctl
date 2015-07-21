OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\additional_info_document.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE additional_info_document
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FILE_ID				    CHAR,
		ORIGINAL_FILE_NAME			    CHAR,
		FILE_NAME				    CHAR,
		FILE_PATH				    CHAR,
		FILE_SIZE				    CHAR,
		RELATIVE_PATH				    CHAR,
		ADDITIONAL_INFORMATION_ID		    INTEGER EXTERNAL(10),
		GRID_ID				    CHAR,
		DOCUMENT_TYPE				    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)


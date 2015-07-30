
LOAD DATA
	INFILE 'additional_info_document.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE additional_info_document
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FILE_ID				    CHAR(2000),
		ORIGINAL_FILE_NAME			    CHAR(2000),
		FILE_NAME				    CHAR(2000),
		FILE_PATH				    CHAR(2000),
		FILE_SIZE				    CHAR(2000),
		RELATIVE_PATH				    CHAR(2000),
		ADDITIONAL_INFORMATION_ID		    INTEGER EXTERNAL(10),
		GRID_ID				    CHAR(2000),
		DOCUMENT_TYPE				    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)



LOAD DATA
	INFILE 'integration_log_message.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE integration_log_message
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		COMBO_MESSAGE_ID			    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		MESSAGE				    CHAR(100000),
		STAGE					    CHAR(2000),
		LOG_ID 				    INTEGER EXTERNAL(10)
	)


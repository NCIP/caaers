OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'integration_log_message.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE integration_log_message
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		COMBO_MESSAGE_ID			    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		MESSAGE				    CHAR,
		STAGE					    CHAR,
		LOG_ID 				    INTEGER EXTERNAL(10)
	)


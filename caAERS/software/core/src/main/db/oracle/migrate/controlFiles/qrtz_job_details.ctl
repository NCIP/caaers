OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_job_details.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_job_details
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		JOB_NAME				    CHAR,
		JOB_GROUP				    CHAR,
		DESCRIPTION					    CHAR,
		JOB_CLASS_NAME 			    CHAR,
		IS_DURABLE				    CHAR "case :IS_DURABLE
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		IS_VOLATILE				    CHAR "case :IS_VOLATILE
												when 't'then to_char('1')
												when 'f'then to_char('0')
												END",
		IS_STATEFUL				    CHAR "case :IS_STATEFUL
												when 't'then to_char('1')
												when 'f'then to_char('0')
												END",
		REQUESTS_RECOVERY			    CHAR "case :REQUESTS_RECOVERY
												when 't'then to_char('1')
												when 'f'then to_char('0')
												END",
		blob_filename     FILLER CHAR(100),
		JOB_DATA					     LOBFILE(blob_filename) TERMINATED BY EOF
	)


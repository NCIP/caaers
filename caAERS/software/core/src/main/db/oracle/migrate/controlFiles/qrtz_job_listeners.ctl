OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_job_listeners.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_job_listeners
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		JOB_NAME				    CHAR,
		JOB_GROUP				    CHAR,
		JOB_LISTENER				    CHAR
	)


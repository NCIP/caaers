OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'qrtz_locks.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_locks
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		LOCK_NAME				    CHAR
	)


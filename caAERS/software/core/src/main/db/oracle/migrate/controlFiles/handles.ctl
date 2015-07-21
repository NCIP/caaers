OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'handles.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE handles
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		PREFIX 				    CHAR,
		HANDLE 				    CHAR,
		IDX					    INTEGER EXTERNAL,
		TYPE						    CHAR(255),
		DATA						    CHAR(345),
		TTL_TYPE					    INTEGER EXTERNAL,
		TTL						    INTEGER EXTERNAL,
		TIMESTAMP					    INTEGER EXTERNAL,
		REFS						    CHAR,
		ADMIN_READ					    CHAR "case :ADMIN_READ
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		ADMIN_WRITE					    CHAR "case :ADMIN_WRITE
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		PUB_READ					    CHAR "case :PUB_READ
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		PUB_WRITE					    CHAR "case :PUB_WRITE
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END"
	)



LOAD DATA
	INFILE 'handles.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE handles
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		PREFIX 				    CHAR(2000),
		HANDLE 				    CHAR(2000),
		IDX					    INTEGER EXTERNAL,
		TYPE						    CHAR(2000)(255),
		DATA						    CHAR(2000)(345),
		TTL_TYPE					    INTEGER EXTERNAL,
		TTL						    INTEGER EXTERNAL,
		TIMESTAMP					    INTEGER EXTERNAL,
		REFS						    CHAR(2000),
		ADMIN_READ					    CHAR(2000) "case :ADMIN_READ
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		ADMIN_WRITE					    CHAR(2000) "case :ADMIN_WRITE
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		PUB_READ					    CHAR(2000) "case :PUB_READ
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		PUB_WRITE					    CHAR(2000) "case :PUB_WRITE
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END"
	)


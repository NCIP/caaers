
LOAD DATA
	INFILE 'role_privilege.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE role_privilege
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ROLE_NAME				    CHAR(2000),
		OBJECT_ID				    CHAR(2000),
		PRIVILEGE				    CHAR(2000)
	)


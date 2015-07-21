OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\role_privilege.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE role_privilege
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ROLE_NAME				    CHAR,
		OBJECT_ID				    CHAR,
		PRIVILEGE				    CHAR
	)


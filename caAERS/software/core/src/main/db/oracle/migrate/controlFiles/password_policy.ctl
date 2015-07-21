OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'password_policy.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE password_policy
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		HASH_ALGORITHM 				    CHAR,
		LN_ALLOWED_ATTEMPTS				    INTEGER EXTERNAL(10),
		LN_LOCKOUT_DURATION				    INTEGER EXTERNAL(10),
		LN_MAX_AGE					    INTEGER EXTERNAL(10),
		CN_MIN_AGE					    INTEGER EXTERNAL(10),
		CN_HISTORY_SIZE				    INTEGER EXTERNAL(10),
		CN_MIN_LENGTH					    INTEGER EXTERNAL(10),
		CN_CB_MIN_REQUIRED				    INTEGER EXTERNAL(10),
		CN_CB_IS_UPPER_CASE_ALPHABET			    INTEGER EXTERNAL(1) "case :CN_CB_IS_UPPER_CASE_ALPHABET
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CN_CB_IS_LOWER_CASE_ALPHABET			    INTEGER EXTERNAL(1) "case :CN_CB_IS_LOWER_CASE_ALPHABET
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CN_CB_IS_NON_ALPHA_NUMERIC			    INTEGER EXTERNAL(1) "case :CN_CB_IS_NON_ALPHA_NUMERIC
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CN_CB_IS_BASE_TEN_DIGIT			    INTEGER EXTERNAL(1) "case :CN_CB_IS_BASE_TEN_DIGIT
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CN_CB_MAX_SUBSTRING_LENGTH			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		ALLOWED_LOGIN_TIME				    INTEGER EXTERNAL(10)
	)


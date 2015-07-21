OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'additional_information.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE additional_information
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		AUTOPSY_REPORT 				    INTEGER EXTERNAL(1) "case :AUTOPSY_REPORT
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CONSULTS					    INTEGER EXTERNAL(1) "case :CONSULTS
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		DISCHARGE_SUMMARY				    INTEGER EXTERNAL(1) "case :DISCHARGE_SUMMARY
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		FLOW_CHARTS					    INTEGER EXTERNAL(1) "case :FLOW_CHARTS
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		LAB_REPORTS					    INTEGER EXTERNAL(1) "case :LAB_REPORTS
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		OBA_FORM					    INTEGER EXTERNAL(1) "case :OBA_FORM
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		OTHER						    INTEGER EXTERNAL(1) "case :OTHER
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		PATHOLOGY_REPORT				    INTEGER EXTERNAL(1) "case :PATHOLOGY_REPORT
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		RADIOLOGY_REPORTS				    INTEGER EXTERNAL(1) "case :RADIOLOGY_REPORTS
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		IRB_REPORT					    INTEGER EXTERNAL(1) "case :IRB_REPORT
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		OTHER_INFORMATION				    CHAR,
		PROGRESS_NOTES 				    INTEGER EXTERNAL(1) "case :PROGRESS_NOTES
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		REFERRAL_LETTERS				    INTEGER EXTERNAL(1) "case :REFERRAL_LETTERS
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)


source .bash_profile
sqlplus /nolog @refreshSelenium.sql
imp caaers_selenium/caaers_selenium FULL=y FILE=caaers_oracle_qa.dmp


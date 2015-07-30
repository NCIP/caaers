#!/bin/bash
sqlplus username/password << EOF
DECLARE
   MAX_VAL NUMBER;
   TABLENAME VARCHAR2(2000);
   SEQUENCE_COUNT NUMBER;
   SEQUENCE_NAME VARCHAR2(2000);
BEGIN  
  MAX_VAL := 0;
  SEQUENCE_COUNT := 0;
  FOR r IN ( SELECT table_name FROM user_tab_columns WHERE column_name = 'ID')
       LOOP
          EXECUTE IMMEDIATE 'select max(id+1) from '||r.table_name into MAX_VAL;
          TABLENAME := r.table_name;
           IF LENGTH(TABLENAME) > 23 THEN
            TABLENAME := SUBSTR(TABLENAME, 1, 23);
           END IF;
           SEQUENCE_NAME := 'SEQ_'|| TABLENAME ||'_ID';
           EXECUTE IMMEDIATE 'select count(*) from user_sequences where sequence_name = ''' || SEQUENCE_NAME || ''''  into SEQUENCE_COUNT;
           IF SEQUENCE_COUNT = 1 AND MAX_VAL > 0 THEN
            EXECUTE IMMEDIATE 'DROP SEQUENCE '|| SEQUENCE_NAME;
            EXECUTE IMMEDIATE 'CREATE SEQUENCE '|| SEQUENCE_NAME ||' START WITH '|| MAX_VAL ||' INCREMENT BY 1 CACHE 20';  
           END IF; 
       END LOOP; 
END;
/
quit
EOF
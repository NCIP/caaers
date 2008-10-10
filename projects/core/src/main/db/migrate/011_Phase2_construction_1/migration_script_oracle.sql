--studies-epochs-arms data migration script for users migrating from caAERS1.1.x to caAERS 1.5.x
--Instructions
--Please execute below provided pl/sql block against the oracle database configured for caAERS.
--Copy the PL/SQL block between "--Start" & "--End" for execution.   

--pl/sql block
--Start
DECLARE
    study_rec studies%ROWTYPE;
    CURSOR c_studies IS SELECT * FROM studies;
	
BEGIN
	FOR study_rec IN c_studies LOOP
	
		INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(SEQ_EPOCHS_ID.NEXTVAL,0,'Baseline',null,study_rec.id,0,null);
		INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(SEQ_ARMS_ID.NEXTVAL,0,'Baseline',null,SEQ_EPOCHS_ID.CURRVAL,null);
			
	    INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(SEQ_EPOCHS_ID.NEXTVAL,0,'Treatment',null,study_rec.id,1,null);
	    INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(SEQ_ARMS_ID.NEXTVAL,0,'Treatment',null,SEQ_EPOCHS_ID.CURRVAL,null);
	    
	    INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(SEQ_EPOCHS_ID.NEXTVAL,0,'Post-treatment',null,study_rec.id,2,null);
	    INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(SEQ_ARMS_ID.NEXTVAL,0,'Post-treatment',null,SEQ_EPOCHS_ID.CURRVAL,null);
  	END LOOP;

END;
/
--End
DECLARE
    study_rec studies%ROWTYPE;
    CURSOR c_studies IS SELECT * FROM studies;
	
	staff_rec research_staffs%ROWTYPE;
    CURSOR c_research_staffs IS SELECT * FROM research_staffs;

BEGIN
	--Studies migration
	FOR study_rec IN c_studies LOOP
	
		INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(SEQ_EPOCHS_ID.NEXTVAL,0,'Baseline',null,study_rec.id,0,null);
		INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(SEQ_ARMS_ID.NEXTVAL,0,'Baseline',null,SEQ_EPOCHS_ID.CURRVAL,null);
			
	    INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(SEQ_EPOCHS_ID.NEXTVAL,0,'Treatment',null,study_rec.id,1,null);
	    INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(SEQ_ARMS_ID.NEXTVAL,0,'Treatment',null,SEQ_EPOCHS_ID.CURRVAL,null);
	    
	    INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(SEQ_EPOCHS_ID.NEXTVAL,0,'Post-treatment',null,study_rec.id,2,null);
	    INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(SEQ_ARMS_ID.NEXTVAL,0,'Post-treatment',null,SEQ_EPOCHS_ID.CURRVAL,null);
  	END LOOP;

	--Researchstaff migration
	FOR staff_rec IN c_research_staffs LOOP
		UPDATE research_staffs set login_id=email_address where id=staff_rec.id;
	END LOOP;

END;
/
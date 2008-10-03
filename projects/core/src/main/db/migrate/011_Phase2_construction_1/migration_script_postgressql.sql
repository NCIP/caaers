CREATE LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION studies_epochs_arms() RETURNS SETOF text AS 
$$
DECLARE
    study studies%rowtype;
    epochid integer;
BEGIN
	
	--studies-epochs-arms  migration
    FOR study IN SELECT * FROM studies WHERE id > 0 order by id
    LOOP
        epochid := nextval('epochs_id_seq');
		INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(epochid,0,'Baseline',null,study.id,0,null);
		INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(nextval('arms_id_seq'),0,'Baseline',null,epochid,null);
		epochid := nextval('epochs_id_seq');	
        INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(epochid,0,'Treatment',null,study.id,1,null);
        INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(nextval('arms_id_seq'),0,'Treatment',null,epochid,null);
        epochid := nextval('epochs_id_seq');
        INSERT INTO epochs (id,version,name,description,study_id,order_no,grid_id) values(epochid,0,'Post-treatment',null,study.id,2,null);
        INSERT INTO arms (id,version,name,description,epoch_id,grid_id) values(nextval('arms_id_seq'),0,'Post-treatment',null,epochid,null);
        RETURN NEXT study.id || '--DONE';
    END LOOP;
END;
$$ 
LANGUAGE 'plpgsql' ;


DELETE FROM arms;
DELETE FROM epochs;

SELECT * from studies_epochs_arms();

DROP FUNCTION studies_epochs_arms();
